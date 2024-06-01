package com.example.project;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UslugiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UslugiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UslugiFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UslugiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UslugiFragment newInstance(String param1, String param2) {
        UslugiFragment fragment = new UslugiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Item> itemList;

    private static List<Item> List_for_gigiena;
    private static List<Item> List_for_zakalivanie;
    private static List<Item> List_for_duh_sam;
    private static List<Item> List_for_fizra;
    private static List<Item> List_for_pitanie;
    private static List<Item> List_for_sreda;
    private static List<Item> List_for_otkaz;
    private static List<Item> List_for_vyrab;
    private static List<Item> List_for_zanyatiya;
    private static List<Item> List_for_check;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFr = inflater.inflate(R.layout.fragment_uslugi, container, false);
        RecyclerView recyclerView = viewFr.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.baseline_clean_hands_24, "Гигиена"));
        itemList.add(new Item(R.drawable.baseline_child_care_24, "Духовное самочувствие"));
        itemList.add(new Item(R.drawable.baseline_bike_scooter_24, "Физическая культура"));
        itemList.add(new Item(R.drawable.baseline_brunch_dining_24, "Здоровое питание"));
        itemList.add(new Item(R.drawable.baseline_fort_24, "Безопасная среда"));
        itemList.add(new Item(R.drawable.baseline_do_not_touch_24, "Отказ от вредных привычек"));
        itemList.add(new Item(R.drawable.baseline_health_and_safety_24, "Выработка полезных привычек"));
        itemList.add(new Item(R.drawable.baseline_bathtub_24, "Закаливание"));
        itemList.add(new Item(R.drawable.baseline_directions_run_24, "Регулярные занятия"));
        itemList.add(new Item(R.drawable.baseline_grade_24, "Проверь себя"));
        Log.d("=============================", String.valueOf(itemList));
        adapter = new ItemAdapter(itemList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, Item currentItem) {
                switch (currentItem.getName()) {
                    case "Гигиена":
                        List_for_gigiena = new ArrayList<>();
                        List_for_gigiena.add(new Item(R.drawable.baseline_countertops_24, "Общая гигиена"));
                        List_for_gigiena.add(new Item(R.drawable.baseline_attribution_24, "Социальная гигиена"));
                        List_for_gigiena.add(new Item(R.drawable.baseline_landscape_24, "Гигиена окружающей среды"));
                        List_for_gigiena.add(new Item(R.drawable.baseline_groups_24, "Гигиена детей и подростков"));
                        replaceFragment(new GigienaFragment(), List_for_gigiena);
                        break;
                    case "Духовное самочувствие":
                        List_for_duh_sam = new ArrayList<>();
                        List_for_duh_sam.add(new Item(R.drawable.baseline_mood_24, "Оптимизм"));
                        List_for_duh_sam.add(new Item(R.drawable.baseline_person_add_24, "Внутрення гармония"));
                        List_for_duh_sam.add(new Item(R.drawable.baseline_nature_people_24, "Связь с природой"));
                        List_for_duh_sam.add(new Item(R.drawable.baseline_groups_24, "Поддержка от окружающих"));
                        List_for_duh_sam.add(new Item(R.drawable.baseline_self_improvement_24, "Практики медитации"));
                        replaceFragment(new GigienaFragment(), List_for_duh_sam);
                        break;

                    case "Физическая культура":
                        List_for_fizra = new ArrayList<>();
                        List_for_fizra.add(new Item(R.drawable.baseline_sports_24, "Физические упражнения"));
                        List_for_fizra.add(new Item(R.drawable.baseline_sports_handball_24, "Спортивные игры"));
                        List_for_fizra.add(new Item(R.drawable.baseline_sports_gymnastics_24, "Йога и пилатес"));
                        List_for_fizra.add(new Item(R.drawable.baseline_fitness_center_24, "Фитнес"));
                        List_for_fizra.add(new Item(R.drawable.baseline_girl_24, "Танцы"));
                        List_for_fizra.add(new Item(R.drawable.baseline_medication_24, "Лечебная физкультура"));
                        replaceFragment(new GigienaFragment(), List_for_fizra);
                        break;

                    case "Здоровое питание":
                        List_for_pitanie = new ArrayList<>();
                        List_for_pitanie.add(new Item(R.drawable.baseline_av_timer_24, "Соблюдение режима приема пищи"));
                        List_for_pitanie.add(new Item(R.drawable.baseline_food_bank_24, "Ограничение потребления жира и сахара"));
                        List_for_pitanie.add(new Item(R.drawable.baseline_set_meal_24, "Разнообразие продуктов"));
                        List_for_pitanie.add(new Item(R.drawable.baseline_no_food_24, "Предпочтение натуральным продуктам"));
                        replaceFragment(new GigienaFragment(), List_for_pitanie);

                        break;

                    case "Безопасная среда":
                        List_for_sreda = new ArrayList<>();
                        List_for_sreda.add(new Item(R.drawable.baseline_security_24, "Физическая безопасность"));
                        List_for_sreda.add(new Item(R.drawable.baseline_privacy_tip_24, "Информационная безопасность"));
                        List_for_sreda.add(new Item(R.drawable.baseline_psychology_24, "Психологическая безопасность"));
                        List_for_sreda.add(new Item(R.drawable.baseline_account_balance_24, "Экологическая безопасность"));
                        replaceFragment(new GigienaFragment(), List_for_sreda);

                        break;

                    case "Отказ от вредных привычек":
                        List_for_otkaz = new ArrayList<>();
                        List_for_otkaz.add(new Item(R.drawable.baseline_do_not_touch_24, "Самоконтроль"));
                        List_for_otkaz.add(new Item(R.drawable.baseline_polyline_24, "Решимость и мотивация"));
                        List_for_otkaz.add(new Item(R.drawable.baseline_people_24, "Поддержка окружающих"));
                        List_for_otkaz.add(new Item(R.drawable.baseline_add_home_work_24, "Изменения в образе жизни"));
                        replaceFragment(new GigienaFragment(), List_for_otkaz);

                        break;

                    case "Выработка полезных привычек":
                        List_for_vyrab = new ArrayList<>();
                        List_for_vyrab.add(new Item(R.drawable.baseline_keyboard_double_arrow_up_24, "Целеустремленность"));
                        List_for_vyrab.add(new Item(R.drawable.baseline_featured_play_list_24, "Планирование"));
                        List_for_vyrab.add(new Item(R.drawable.baseline_feed_24, "Регулярность действий"));
                        List_for_vyrab.add(new Item(R.drawable.baseline_upcoming_24, "Самомотивация"));
                        replaceFragment(new GigienaFragment(), List_for_vyrab);

                        break;

                    case "Закаливание":
                        List_for_zakalivanie = new ArrayList<>();
                        List_for_zakalivanie.add(new Item(R.drawable.baseline_medical_information_24, "Медицинские показания"));
                        List_for_zakalivanie.add(new Item(R.drawable.baseline_history_edu_24, "Исторические сведения"));
                        List_for_zakalivanie.add(new Item(R.drawable.baseline_bathroom_24, "Закаливание водой"));
                        List_for_zakalivanie.add(new Item(R.drawable.baseline_nordic_walking_24, "Закаливание тренировками"));
                        List_for_zakalivanie.add(new Item(R.drawable.baseline_emoji_people_24, "Закаливание у детей"));
                        replaceFragment(new GigienaFragment(), List_for_zakalivanie);
                        break;

                    case "Регулярные занятия":
                        List_for_zanyatiya = new ArrayList<>();
                        List_for_zanyatiya.add(new Item(R.drawable.baseline_feed_24, "Планирование занятий"));
                        List_for_zanyatiya.add(new Item(R.drawable.baseline_format_align_center_24, "Подготовка материалов"));
                        List_for_zanyatiya.add(new Item(R.drawable.baseline_bookmark_added_24, "Оценка и коррекция учебных успехов"));
                        replaceFragment(new GigienaFragment(), List_for_zanyatiya);

                        break;

                    case "Проверь себя":
                        List_for_check = new ArrayList<>();
                        List_for_check.add(new Item(R.drawable.baseline_check_box_24, "Тест 1"));
                        List_for_check.add(new Item(R.drawable.baseline_check_box_24, "Тест 2"));
                        List_for_check.add(new Item(R.drawable.baseline_check_box_24, "Тест 3"));
                        replaceFragment(new GigienaFragment(), List_for_check);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        return viewFr;
    }

    private void replaceFragment(Fragment fragment, List transactionList){
        ArrayList itemList1 = new ArrayList<>(transactionList);
        Bundle bundle = new Bundle();
        bundle.putSerializable("transactionList", itemList1);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}



