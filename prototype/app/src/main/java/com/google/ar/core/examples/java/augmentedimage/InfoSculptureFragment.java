package com.google.ar.core.examples.java.augmentedimage;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class InfoSculptureFragment extends Fragment {

    TextView paragraph;
    ViewGroup rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_info_sculpture, container, false);

        // Ensures the hyperlinks are functional
        paragraph = rootView.findViewById(R.id.sculptureInfo);
        paragraph.setMovementMethod(LinkMovementMethod.getInstance());

        // Returns the final tab
        return rootView;
    }
}
