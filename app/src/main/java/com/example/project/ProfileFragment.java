package com.example.project;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;

    private SharedViewModel viewModel;

    public ProfileFragment() {
// Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle args = getArguments();
        if (args != null) {
            ArrayList transactionList = (ArrayList) args.getSerializable("transactionList");
            Log.d("=======================", String.valueOf(transactionList));
            TextView nike = view.findViewById(R.id.name);
            TextView email = view.findViewById(R.id.email);
            TextView phone = view.findViewById(R.id.phone);
            String List_for_pro = (String) transactionList.get(0);
            Log.d("=======================", String.valueOf(List_for_pro));

            Pattern pattern = Pattern.compile("\"([^\"]*)\"");
            Matcher matcher = pattern.matcher(List_for_pro);
            int wordCount = 0;
            while (matcher.find() && wordCount < 3) {
                String word = matcher.group(1);
                if(wordCount == 0){
                    nike.setText("Никнейм: "+word);
                } else if(wordCount == 1){
                    email.setText("Почта: "+word);
                }
                else if(wordCount == 2){
                    phone.setText("Телефон: "+word);
                }
                wordCount++;
                Log.d("=======================", String.valueOf(word));
            }
        }
//            Log.d("====LIST====", String.valueOf(List_for_prof));
//            String text_for_nike = (String) List_for_prof.get(0);
//            String text_for_email = (String) List_for_prof.get(1);
//            String text_for_phone = (String) List_for_prof.get(2);
//            nike.setText("Никнейм" + text_for_nike);
//            email.setText("Почта" + text_for_email);
//            phone.setText("Телефон" + text_for_phone);

        imageView = view.findViewById(R.id.image_profile);
        Button chooseImageButton = view.findViewById(R.id.chooseImageButton);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
            }
        });

        viewModel.getSelectedImage().observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                imageView.setImageURI((Uri) uri);
            }
        });
        Button deauth = view.findViewById(R.id.deauth);
        deauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), splash_screen.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            viewModel.selectImage(selectedImage);
        }
    }

}