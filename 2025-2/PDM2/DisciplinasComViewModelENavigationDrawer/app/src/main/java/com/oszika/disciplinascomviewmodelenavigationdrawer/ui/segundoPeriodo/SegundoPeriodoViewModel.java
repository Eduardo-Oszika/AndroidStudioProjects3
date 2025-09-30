package com.oszika.disciplinascomviewmodelenavigationdrawer.ui.segundoPeriodo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SegundoPeriodoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SegundoPeriodoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}