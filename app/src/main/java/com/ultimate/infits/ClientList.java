
package com.ultimate.infits;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientList extends Fragment {
    //implements ClientListAdapter.Selecteditem

    DataFromDatabase dataFromDatabase;
    RecyclerView clientList;
    RadioButton active,pending;
    ImageView search,filter;
    String url = "http://192.168.231.1/clientsList.php";
    EditText searchtext;
    RequestQueue queue;
    //ClientListAdapter cd;
    String dietician = dataFromDatabase.dietitianuserID;
    String client_list_image[]={"@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic",
            "@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic"};
//    String client_list_client_name[]={"Ronald richard","Ronald richard","Ronald richard","Ronald richard","Ronald richard",
//            "Ronald richard","Ronald richard","Ronald richard","Ronald richard","Ronald richard"};
//    String client_list_plan[]={"Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan",
//            "Muscle Plan","Muscle Plan"};
//    String client_list_start_date[]={"10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022",
//            "10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022"};
//    String client_list_end_date[]={"10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023",
//            "10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023"};

    List<List_Clients> client_list_active =new ArrayList<>();
    List<List_Clients> client_list_pending =new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientList.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientList newInstance(String param1, String param2) {
        ClientList fragment = new ClientList();
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

        queue = Volley.newRequestQueue(getContext());
        Log.d("ClientList","before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response -> {
            if (!response.equals("failure")){
                Log.d("ClientList","success");
                Log.d("response",response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i< jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        List_Clients obj = new List_Clients(object.getString("plan"), object.getString("clientID"),
                                client_list_image[i],
                                object.getString("startdate"), object.getString("enddate"), true);


                        String dietchart = object.getString("dietChart");
                        if(dietchart=="null"){
                            client_list_pending.add(obj);
                        }else if (dietchart!=null){
                            client_list_active.add(obj);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getContext(), "ClientList success", Toast.LENGTH_SHORT).show();
            }
            else if (response.equals("failure")){
                Log.d("clientList","failure");
                Toast.makeText(getContext(), "ClientList failed", Toast.LENGTH_SHORT).show();
            }
        },error -> {
            Toast.makeText(getContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();})
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                Log.d("ClientList","dietitianid = "+dataFromDatabase.dietitianuserID);
                data.put("userID", dataFromDatabase.dietitianuserID);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        Log.d("ClientList","at end");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);
        clientList = view.findViewById(R.id.client_list);
        active = view.findViewById(R.id.active_btn);
        pending = view.findViewById(R.id.pending_btn);
        search = view.findViewById(R.id.search_client_icon);
        filter = view.findViewById(R.id.filter_client_icon);
        searchtext = view.findViewById(R.id.search_bar_text);
        searchtext.setLines(1);

        searchtext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Toast.makeText(getContext(), "Click on search icon after entering the name", Toast.LENGTH_SHORT).show();
                    searchtext.setText("");
                    return true;
                }
                return false;
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchtext.getVisibility() == v.VISIBLE) {
                    String client_search_name = searchtext.getText().toString();
                    searchtext.setVisibility(v.INVISIBLE);
                    if(!(client_search_name.equals("")) && !(client_search_name.equals(" "))){
                        Toast.makeText(getContext(),"Searching for the client "+client_search_name,Toast.LENGTH_SHORT).show();
                        //query database for the searched username
                    }
                }
                else
                    searchtext.setVisibility(v.VISIBLE);
            }
        });
//        client_list_active.clear();
//        for (int i = 0; i < client_list_image.length; i++) {
//            List_Clients obj = new List_Clients(client_list_plan[i], client_list_client_name[i], client_list_image[i],
//                    client_list_start_date[i], client_list_end_date[i], true);
//            client_list_active.add(obj);
//        }
        ClientListAdapter cd = new ClientListAdapter(getContext(), client_list_active);
        clientList.setAdapter(cd);
        clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientListAdapter cd1 = new ClientListAdapter(getContext(), client_list_active);
                // cd = new ClientListAdapter(getContext(),true);
                clientList.setAdapter(cd1);
                clientList.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        pending.setOnClickListener(v -> {
//            client_list_pending.clear();
//            for (int i = 0; i < client_list_image.length; i++) {
//                List_Clients obj = new List_Clients(client_list_plan[i], client_list_client_name[i], client_list_image[i],
//                        client_list_start_date[i], client_list_end_date[i], false);
//                client_list_pending.add(obj);
//            }
            ClientListAdapter cd2 = new ClientListAdapter(getContext(), client_list_pending);
            clientList.setAdapter(cd2);
            clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        // ClientListAdapter cd = new ClientListAdapter(getContext(), client_list_active, (ClientListAdapter.Selecteditem) this);
        // clientList.setAdapter(cd);
        // clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}