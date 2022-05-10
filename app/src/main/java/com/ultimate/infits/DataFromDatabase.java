package com.ultimate.infits;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataFromDatabase {

    HashMap<String,String> map = new HashMap<>();
    String url = "http://192.168.127.1/getdietitiandetails.php";
    public static boolean flag= false;
    public static String userID,password,name,qualification,email,mobile,location,age,gender;

    private void getDetails(){

        if (flag==true){
            Log.i("userID",userID);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject object =new JSONObject();
                        String dietitianuserID= object.getString("dietitianuserID");
                        String name=object.getString("name");
                        String qualification=object.getString("qualification");
                        String email=object.getString("email");
                        String mobile=object.getString("mobile");
                        String profilePhoto=object.getString("profilePhoto");
                        String location=object.getString("location");
                        String age= object.getString("age");
                        String gender = object.getString("gender");

                        map.put("userID",dietitianuserID);
                        map.put("name",name);
                        map.put("qualification",qualification);
                        map.put("email",email);
                        map.put("mobile",mobile);
                        map.put("profilePhoto",profilePhoto);
                        map.put("location",location);
                        map.put("age",age);
                        map.put("gender",gender);
                        Log.i("data",dietitianuserID+" "+name+" "+qualification+" "+email+" "+
                                mobile+" "+profilePhoto+" "+location+" "+age+" "+gender);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(null,error.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(null).add(stringRequest);
        }
        else {
            Log.i("Flag token", String.valueOf(flag));
        }

    }
}
