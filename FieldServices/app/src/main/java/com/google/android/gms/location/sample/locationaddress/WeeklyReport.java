package com.google.android.gms.location.sample.locationaddress;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeeklyReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeeklyReport extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public WeeklyReport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeeklyReport.
     */
    // TODO: Rename and change types and number of parameters
    public static WeeklyReport newInstance(String param1, String param2) {
        WeeklyReport fragment = new WeeklyReport();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Map<String,Integer> succ =new HashMap<String,Integer>();
        View rootView = inflater.inflate(R.layout.fragment_weekly_report, container, false);

        succ.put("Week 1", 10);
        succ.put("Week 2", 20);

        succ.put("Week 3", 50);
        succ.put("Week 4", 20);

        WeeklyReportBarChartView mView = new WeeklyReportBarChartView(getActivity()
                .getBaseContext(), succ);
        container.addView(mView);

        return rootView;
        // Inflate the layout for this fragment
    }

}
