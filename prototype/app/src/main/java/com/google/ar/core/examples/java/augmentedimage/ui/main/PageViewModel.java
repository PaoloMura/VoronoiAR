package com.google.ar.core.examples.java.augmentedimage.ui.main;

import android.content.res.Resources;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.ar.core.examples.java.augmentedimage.R;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Bye bye world from section: " + input;
        }
    });

//    switch (input) {
//        case 0: return Resources.getSystem().getString(R.string.tab_paragraph_1);
//        case 1: return Resources.getSystem().getString(R.string.tab_paragraph_2);
//        default: return "";
//    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}