package com.ultimate.infits;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingMain extends Fragment {

    ImageView aboutUs,help;
    CardView gotoAccount,achievements,notifications;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingMain.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingMain newInstance(String param1, String param2) {
        SettingMain fragment = new SettingMain();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_main, container, false);
        gotoAccount = view.findViewById(R.id.dieitician_gotoAccount);
        notifications = view.findViewById(R.id.notification);
        gotoAccount.setOnClickListener(v->{
            FragmentTransaction ftset1= getActivity().getSupportFragmentManager().beginTransaction();
            ftset1.replace(R.id.FrameContainer,new BlankFragment());
            ftset1.add(R.id.FrameContainer,new Account());
            ftset1.addToBackStack("settings_account");
            ftset1.commit();
            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new Account()).commit();
            //Navigation.findNavController(v).navigate(R.id.action_settingMain_to_account);
        });
        achievements=view.findViewById(R.id.dieitician_achievements_settings_view);
        achievements.setOnClickListener(v->{
            FragmentTransaction ftset2= getActivity().getSupportFragmentManager().beginTransaction();
            ftset2.replace(R.id.FrameContainer,new BlankFragment());
            ftset2.add(R.id.FrameContainer,new Achivement());
            ftset2.addToBackStack("settings_achievement");
            ftset2.commit();
       });
        notifications.setOnClickListener(v->{
            FragmentTransaction ftset3= getActivity().getSupportFragmentManager().beginTransaction();
            ftset3.replace(R.id.FrameContainer,new BlankFragment());
            ftset3.add(R.id.FrameContainer,new Notification());
            ftset3.addToBackStack("settings_notification");
            ftset3.commit();
       });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        aboutUs=view.findViewById(R.id.dietician_about_us_view);
//        aboutUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.settings_frag,new )
//            }
//        });
//        help=view.findViewById(R.id.dietician_help_view);
//        help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}