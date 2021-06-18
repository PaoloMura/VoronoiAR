package com.google.ar.core.examples.java.augmentedimage;

public class Anchor {
    String filename;
    float width;
    float[] translation;
    float[] rotation;
    float scale;

    public Anchor(String filename, float width, float[] translation, float[] rotation, float scale) {
        this.filename = filename;
        this.width = width;
        this.translation = translation.clone();
        this.rotation = rotation.clone();
        this.scale = scale;
    }

    public void setTranslation(float[] t) { translation = t; }
    public void setRotation(float[] r) { rotation = r; }
    public void setScale(float s) { scale = s; }
    public String getName() { return filename; }
    public float getWidth() { return width; }
    public float[] getTranslation() { return translation.clone(); }
    public float[] getRotation() { return rotation.clone(); }
    public float getScale() { return scale; }
}
