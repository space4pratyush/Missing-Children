package com.example.pratyush.missingchildrenfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

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


public class Explore extends Fragment implements Recycler_adapter.OnItemClickListener, Recycler_adapter.onClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESCRIPTION = "description";

    private RecyclerView mRecyclerView;
    private Recycler_adapter mRecyclerAdapter;
    private ArrayList<List_Adapter> mModel_CardView;
    private RequestQueue requestQueue;

    public Explore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);


        mRecyclerView = view.findViewById(R.id.recycler_View);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mModel_CardView = new ArrayList<>();

        //Volley request quee.........
        requestQueue = Volley.newRequestQueue(getActivity());


        ParseJSON();
        return view;
    }

    private void ParseJSON() {
        String URL = "https://api.myjson.com/bins/fyov7";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("response");
                            //for loop for count number of data from api
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = jsonArray.getJSONObject(i).getJSONObject("data");

                                //get data...
                                String name = res.getString("name");
                                String image = res.getString("image_url");
                                String description = res.getString("description");

                                mModel_CardView.add(new List_Adapter(name, image, description));


                            }
                            mRecyclerAdapter = new Recycler_adapter(getActivity(), mModel_CardView);
                            mRecyclerAdapter.setItemOnClickListener(Explore.this);
                            mRecyclerView.setAdapter(mRecyclerAdapter);
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

//This code will navigate to the DetailExplore page with all the details after clicking on any item of the recyclerview.
    @Override
    public void onItemClick(int position) {
        Intent detailIntent=new Intent(getActivity(),DetailExplore.class);
        List_Adapter clickedItem=mModel_CardView.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getmPerson_Image());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getmPerson_name());
        detailIntent.putExtra(EXTRA_DESCRIPTION,clickedItem.getmPerson_Detail());
        startActivity(detailIntent);

    }
}
