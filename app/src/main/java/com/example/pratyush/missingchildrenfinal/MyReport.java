package com.example.pratyush.missingchildrenfinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyReport extends Fragment {
    private RecyclerView mRecyclerView;
    private Recycler_adapter mRecyclerAdapter;
    private ArrayList<List_Adapter> mModel_CardView;
    private RequestQueue requestQueue;

    public MyReport() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_report, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_View);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mModel_CardView = new ArrayList<>();

//

        //requestQueue using volley library
        requestQueue= Volley.newRequestQueue(getActivity());
//        calling ParseJSON method which will parse the json data into java object.
        ParseJSON();
        return view;

    }
    private void ParseJSON(){
        String URL="https://api.myjson.com/bins/1ebswp";
//        request to fetch data from the given url
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("response");
                            //for loop for count number of data from api
                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject res=jsonArray.getJSONObject(i);

                                //get data and set into the suitable format...
                                String name=res.getString("name");
                                String image=res.getString("image_url");
                                String description=res.getString("description");

                                mModel_CardView.add(new List_Adapter(name, image, description));
                            }
                            mRecyclerAdapter=new Recycler_adapter(getActivity(), mModel_CardView);
                            mRecyclerView.setAdapter(mRecyclerAdapter);
//                            mRecyclerAdapter.setOnClckListener(r.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
