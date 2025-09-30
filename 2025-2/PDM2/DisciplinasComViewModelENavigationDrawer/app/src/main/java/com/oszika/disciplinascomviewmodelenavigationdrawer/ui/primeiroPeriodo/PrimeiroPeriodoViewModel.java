package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.primeiroPeriodo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrimeiroPeriodoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PrimeiroPeriodoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}