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
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        View viewFr = inflater.inflate(R.layout.fragment_information, container, false);
        Bundle args = getArguments();
        TextView info = viewFr.findViewById(R.id.info);
        if (args != null) {
            String transactionList = args.getString("transactionList");
            Log.d("=================", String.valueOf(transactionList));
            switch(transactionList){
                case "О приложении":
                    info.setText("Данное приложение разработано для IT School Sumsung, ее учеником Конахиным Даниилом Андреевичем.");
                    break;
                case "Справка":
                    info.setText("Конфиденциальность приложения \"Здоровое поколение\" является нашим приоритетом. Мы серьезно относимся к защите данных и личной информации наших пользователей. Мы строго соблюдаем законы о защите данных. Мы также обеспечиваем безопасность приложения путем регулярного обновления программного обеспечения и мониторинга возможных уязвимостей. Мы ценим доверие, которое нам доверяют наши пользователи, и гарантируем, что мы делаем все возможное для обеспечения конфиденциальности и безопасности их данных.");
                    break;
                case "Помощь":
                    info.setText("Если у вас возникли какие-нибудь проблемы, то вы можете обратиться по этому адресу, и Вам в скором времени ответят\nkonakhin.da@gmail.com");
                    break;
            }
        }

        return viewFr;
    }
}