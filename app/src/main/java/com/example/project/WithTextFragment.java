package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WithTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WithTextFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WithTextFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WithTextFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WithTextFragment newInstance(String param1, String param2) {
        WithTextFragment fragment = new WithTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.slide_left));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFr = inflater.inflate(R.layout.fragment_with_text, container, false);
        Bundle args = getArguments();
        if (args != null) {
            ArrayList transactionList = (ArrayList) args.getSerializable("transactionList");
            TextView zagolovor = viewFr.findViewById(R.id.zagolovok);
            TextView scrolltext = viewFr.findViewById(R.id.text_for_scroll);
            String text_for_zag = (String) transactionList.get(0);
            String text_for_scroll = (String) transactionList.get(1);
            zagolovor.setText(text_for_zag);
            scrolltext.setText(text_for_scroll);
            Log.d("=============================", String.valueOf(transactionList));
        }
        return viewFr;
        }
}