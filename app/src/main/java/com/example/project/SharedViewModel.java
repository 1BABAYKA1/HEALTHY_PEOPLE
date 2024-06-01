package com.example.project;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData selectedImage = new MutableLiveData<>();

    public void selectImage(Uri uri) {
        selectedImage.setValue(uri);
    }

    public LiveData getSelectedImage() {
        return selectedImage;
    }
}