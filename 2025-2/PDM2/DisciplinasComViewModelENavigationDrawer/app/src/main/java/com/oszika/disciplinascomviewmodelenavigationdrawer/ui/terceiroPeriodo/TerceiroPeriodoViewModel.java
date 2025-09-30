package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.terceiroPeriodo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TerceiroPeriodoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TerceiroPeriodoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}