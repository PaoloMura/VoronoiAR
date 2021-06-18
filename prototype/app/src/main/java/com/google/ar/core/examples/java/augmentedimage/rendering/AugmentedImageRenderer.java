/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.ar.core.examples.java.augmentedimage.rendering;

import android.content.Context;
import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Pose;
import com.google.ar.core.examples.java.augmentedimage.HomeActivity;
import com.google.ar.core.examples.java.common.rendering.ObjectRenderer;
import com.google.ar.core.examples.java.common.rendering.ObjectRenderer.BlendMode;
import java.io.IOException;

/** Renders an augmented image. */
public class AugmentedImageRenderer {
  private static final String TAG = "AugmentedImageRenderer";

  private static final float TINT_INTENSITY = 0.1f;
  private static final float TINT_ALPHA = 1.0f;
  private static final int[] TINT_COLORS_HEX = {
    0x000000, 0xF44336, 0xE91E63, 0x9C27B0, 0x673AB7, 0x3F51B5, 0x2196F3, 0x03A9F4, 0x00BCD4,
    0x009688, 0x4CAF50, 0x8BC34A, 0xCDDC39, 0xFFEB3B, 0xFFC107, 0xFF9800,
  };

  private final ObjectRenderer objRender = new ObjectRenderer();

  // initial attribute assignments
  private float[] initialTranslation;
  private float[] initialTheta;
  private float initialScale;

  // attributes
  private float[] translation;
  private float[] theta; // angle of counterclockwise rotation in radians, divided by π (i.e. 2.0f corresponds to 2π radians)
  private float scale;
  private final float[][] vector = {{1,0,0}, {0,1,0}, {0,0,1}};


  public AugmentedImageRenderer() {
    setInitialOffsets();
  }

  public void createOnGlThread(Context context, String model, String texture) throws IOException {
    objRender.createOnGlThread(context, model, texture);
    objRender.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    objRender.setBlendMode(BlendMode.AlphaBlending);
  }

//  returns a quaternion for rotation about the specified axis (0=x, 1=y, 2=z)
  private float[] getQuaternion(int axis) {
    float x = (float) Math.sin(theta[axis] * (float) Math.PI / 2) * vector[axis][0];
    float y = (float) Math.sin(theta[axis] * (float) Math.PI / 2) * vector[axis][1];
    float z = (float) Math.sin(theta[axis] * (float) Math.PI / 2) * vector[axis][2];
    float w = (float) Math.cos(theta[axis] * (float) Math.PI / 2);
    return new float[]{x, y, z, w};
  }

  public void draw(
      float[] viewMatrix,
      float[] projectionMatrix,
      AugmentedImage augmentedImage,
      Anchor centerAnchor,
      float[] colorCorrectionRgba) {

    // set tint colour
    float[] tintColor =
        convertHexToColor(TINT_COLORS_HEX[augmentedImage.getIndex() % TINT_COLORS_HEX.length]);

    // make the poses (transformation matrices) for translating and rotating the model about each axis
    float[] rotationX = getQuaternion(0);
    Pose localRotationPoseX = Pose.makeRotation(rotationX);
    float[] rotationY = getQuaternion(1);
    Pose localRotationPoseY = Pose.makeRotation(rotationY);
    float[] rotationZ = getQuaternion(2);
    Pose localRotationPoseZ = Pose.makeRotation(rotationZ);
    Pose localTranslationPose = Pose.makeTranslation(translation);

    // apply the transformations
    Pose anchorPose = centerAnchor.getPose();
    Pose worldBoundaryPose = anchorPose.compose(localRotationPoseX)
                                       .compose(localRotationPoseY)
                                       .compose(localRotationPoseZ)
                                       .compose(localTranslationPose);

    // scale the model and render
    float scaleFactor = scale;
    float[] modelMatrix = new float[16];
    worldBoundaryPose.toMatrix(modelMatrix, 0);
    objRender.updateModelMatrix(modelMatrix, scaleFactor);
    objRender.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);
  }

  public void  translate(int axis, int direction, float factor) { translation[axis] += direction * factor; }
  public void  rotate(int axis, float factor) { theta[axis] = factor; }

  public void  scale(int direction, float factor) {
    scale +=  direction * factor;
    if (scale < 0.01f) {
      scale = 0.01f;
    }
  }

  public void setTranslation(float[] translation) { this.translation = translation; }
  public void setTheta(float[] theta) { this.theta = theta; }
  public void setScale(float s){ scale = s; }

  public float getTranslation(int axis) { return translation[axis]; }
  public float[] getTranslation() { return translation.clone(); }
  public float getTheta(int axis){ return theta[axis]; }
  public float[] getTheta() { return theta.clone(); }
  public float getScale(){ return scale; }

  public void reset(){
    translation = initialTranslation.clone();
    theta = initialTheta.clone();
    scale = initialScale;
  }

  public void setInitialOffsets() {
    int anchor = HomeActivity.getCurrentAnchor();
    initialTranslation = HomeActivity.getTranslation(anchor);
    initialTheta = HomeActivity.getRotation(anchor);
    initialScale = HomeActivity.getScale(anchor);
    translation = initialTranslation.clone();
    theta = initialTheta.clone();
    scale = initialScale;
  }

  private static float[] convertHexToColor(int colorHex) {
    // colorHex is in 0xRRGGBB format
    float red = ((colorHex & 0xFF0000) >> 16) / 255.0f * TINT_INTENSITY;
    float green = ((colorHex & 0x00FF00) >> 8) / 255.0f * TINT_INTENSITY;
    float blue = (colorHex & 0x0000FF) / 255.0f * TINT_INTENSITY;
    return new float[] {red, green, blue, TINT_ALPHA};
  }
}
