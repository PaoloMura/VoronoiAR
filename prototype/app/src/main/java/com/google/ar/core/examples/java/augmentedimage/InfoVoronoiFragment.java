package com.google.ar.core.examples.java.augmentedimage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoVoronoiFragment extends Fragment {

    TextView paragraph;
    float textSize = 35f;
    ViewGroup rootView;
    ScaleGestureDetector sGD;
    NestedScrollView nestedView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_info_voronoi, container, false);

        // Ensures the hyperlinks are functional
        paragraph = rootView.findViewById(R.id.voronoiInfo);
        paragraph.setMovementMethod(LinkMovementMethod.getInstance());

        // Sets the initial text size
//        paragraph = rootView.findViewById(R.id.voronoiInfo);
//        paragraph.setTextSize(textSize);
//        sGD = new ScaleGestureDetector(this.getContext(), new ScaleListener());
//
        // Initializes the gesture listener
//        rootView.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if(event.getAction() == MotionEvent.ACTION_MOVE){
//                    sGD.onTouchEvent(event);
//                }
//                return true;
//            }
//        });

        // Returns the final tab
        return rootView;
    }

    // Makes sure the Scale gesture listener edits the text size according to detected pinch zooms
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            textSize = textSize * detector.getScaleFactor();
//            textSize = Math.max(18f, Math.min(textSize, 35f));
//            paragraph.setTextSize(textSize);
////            nestedView.
//            return super.onScale(detector);
//        }
//    }
}
