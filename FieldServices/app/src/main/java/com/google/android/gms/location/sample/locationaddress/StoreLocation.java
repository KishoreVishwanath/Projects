package com.google.android.gms.location.sample.locationaddress;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreLocation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreLocation extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Connection conn = null;
    private String url = "jdbc:mysql://asqappsdb.c0tepw11fsir.ap-southeast-1.rds.amazonaws.com:3306/";
    private String dbName = "fs24x7test";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "asqicampus";


    public StoreLocation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreLocation.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreLocation newInstance(String param1, String param2) {
        StoreLocation fragment = new StoreLocation();
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
        View view = inflater.inflate(R.layout.fragment_store_location, container, false);
        // Inflate the layout for this fragment
        Button button = (Button) view.findViewById(R.id.fetch_address_button1);

        String [] values =
                {"Location 1",
                        "Location 2",
                        "Location 3",
                      };
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    Class.forName(driver).newInstance();
                    conn = DriverManager.getConnection(url+dbName,userName,password);
                    System.out.println("Connected to the database");
                    Statement stmt = conn.createStatement();
                    String sql = "update  olm_location_master set OLM_Lattitude = '"
                            +ApplicationData.getInstance().gLastLocation.getLatitude()+"', OLM_Longitude = '"
                            +ApplicationData.getInstance().gLastLocation.getLongitude()+"' where OLM_organization_location_code = 'L0005'";
                    stmt.executeUpdate(sql);

                    conn.close();
                    System.out.println("Disconnected from database");
                } catch (Exception e) {
                    e.printStackTrace();
                }



                Toast.makeText(getActivity(), "Fetch Address Clicked New =" + "\n"  + ApplicationData.getInstance().gLastLocation.getLatitude() + "\n"  + ApplicationData.getInstance().gLastLocation.getLongitude(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
/*    public void FragmentfetchAddressButtonHandler(View view) {
        Toast.makeText(getActivity(), "Fetch Address Clicked", Toast.LENGTH_LONG).show();
    }*/

}
