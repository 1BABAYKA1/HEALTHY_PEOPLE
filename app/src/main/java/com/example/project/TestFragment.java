package com.example.project;

import static androidx.core.content.ContextCompat.getDrawable;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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

    private static int SPLASH_TIME_OUT = 2000;
    int q = 1;

    int correct = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFr = inflater.inflate(R.layout.fragment_test, container, false);
        TextView zagolovok = viewFr.findViewById(R.id.zagolovok);
        TextView question = viewFr.findViewById(R.id.question);
        Button answer1 = viewFr.findViewById(R.id.answer1);
        Button answer2 = viewFr.findViewById(R.id.answer2);
        Button answer3 = viewFr.findViewById(R.id.answer3);
        Bundle args = getArguments();
        if (args != null) {
            ArrayList transactionList = (ArrayList) args.getSerializable("transactionList");
            String text_for_zag = (String) transactionList.get(0);
            String text_for_question1 = (String) transactionList.get(1);
            String for_answer11 = (String) transactionList.get(2);
            String for_answer21 = (String) transactionList.get(3);
            String for_answer31 = (String) transactionList.get(4);
            String text_for_question2 = (String) transactionList.get(5);
            String for_answer12 = (String) transactionList.get(6);
            String for_answer22 = (String) transactionList.get(7);
            String for_answer32 = (String) transactionList.get(8);
            String text_for_question3 = (String) transactionList.get(9);
            String for_answer13 = (String) transactionList.get(10);
            String for_answer23 = (String) transactionList.get(11);
            String for_answer33 = (String) transactionList.get(12);

            zagolovok.setText(text_for_zag);

            question.setText(text_for_question1);

            TextView answer_cor = viewFr.findViewById(R.id.answer_cor);
            TextView answer_fail = viewFr.findViewById(R.id.answer_fail);

            answer1.setText(for_answer11);
            answer2.setText(for_answer21);
            answer3.setText(for_answer31);
            question.setText(text_for_question1);
            answer1.setText(for_answer11);
            answer2.setText(for_answer21);
            answer3.setText(for_answer31);

            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (q == 1){
                        correct += 1;
                        question.setText(text_for_question2);
                        answer1.setText(for_answer12);
                        answer2.setText(for_answer22);
                        answer3.setText(for_answer32);
                    } else if (q == 2){
                        question.setText(text_for_question3);
                        answer1.setText(for_answer13);
                        answer2.setText(for_answer23);
                        answer3.setText(for_answer33);
                    }
                    q += 1;
                    if (q == 4){
                        question.setVisibility(View.INVISIBLE);
                        answer1.setVisibility(View.INVISIBLE);
                        answer2.setVisibility(View.INVISIBLE);
                        answer3.setVisibility(View.INVISIBLE);
                        if (correct >= 2){
                            answer_cor.setText("Правильно отвеченных вопросов: " + correct);
                            answer_cor.setVisibility(View.VISIBLE);
                        }else{
                            answer_fail.setText("Правильно отвеченных вопросов: " + correct);
                            answer_fail.setVisibility(View.VISIBLE);
                        }
                    }
                    Log.d("============================", String.valueOf(q));
                    Log.d("============================", String.valueOf(correct));
                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (q == 1){
                        question.setText(text_for_question2);
                        answer1.setText(for_answer12);
                        answer2.setText(for_answer22);
                        answer3.setText(for_answer32);
                    } else if (q == 2){
                        question.setText(text_for_question3);
                        answer1.setText(for_answer13);
                        answer2.setText(for_answer23);
                        answer3.setText(for_answer33);
                        correct += 1;
                    }
                    q += 1;
                    if (q == 4){
                        question.setVisibility(View.INVISIBLE);
                        answer1.setVisibility(View.INVISIBLE);
                        answer2.setVisibility(View.INVISIBLE);
                        answer3.setVisibility(View.INVISIBLE);
                        if (correct >= 2){
                            answer_cor.setText("Правильно отвеченных вопросов: " + correct);
                            answer_cor.setVisibility(View.VISIBLE);
                        }else{
                            answer_fail.setText("Правильно отвеченных вопросов: " + correct);
                            answer_fail.setVisibility(View.VISIBLE);
                        }
                    }
                    Log.d("============================", String.valueOf(q));
                    Log.d("============================", String.valueOf(correct));


                }
            });
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (q == 1){
                        question.setText(text_for_question2);
                        answer1.setText(for_answer12);
                        answer2.setText(for_answer22);
                        answer3.setText(for_answer32);
                    } else if (q == 2){
                        question.setText(text_for_question3);
                        answer1.setText(for_answer13);
                        answer2.setText(for_answer23);
                        answer3.setText(for_answer33);
                    }
                    else if (q == 3){
                        correct += 1;
                    }

                    q += 1;
                    if (q == 4){
                        question.setVisibility(View.INVISIBLE);
                        answer1.setVisibility(View.INVISIBLE);
                        answer2.setVisibility(View.INVISIBLE);
                        answer3.setVisibility(View.INVISIBLE);
                        if (correct >= 2){
                            answer_cor.setText("Правильно отвеченных вопросов: " + correct);
                            answer_cor.setVisibility(View.VISIBLE);
                        }else{
                            answer_fail.setText("Правильно отвеченных вопросов: " + correct);
                            answer_fail.setVisibility(View.VISIBLE);
                        }
                    }
                    Log.d("============================", String.valueOf(q));
                    Log.d("============================", String.valueOf(correct));
                }
            });
            Log.d("============================", String.valueOf(q));
            Log.d("============================", String.valueOf(correct));


        }
        return viewFr;
    }
}