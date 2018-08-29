package com.example.pratyush.missingchildrenfinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.Recycler_ViewHolder> {

    private Context mContext;
    private ArrayList<List_Adapter> mModel_cardViews;
    //detail activity member variable... :)
    private onClickListener mListener;

    //Detail activiy codes.... :)
    public void setItemOnClickListener(onClickListener listener){
        mListener=listener;
    }

    public interface onClickListener{
        void onItemClick(int position);
    }
    //constructor..... :)
    public Recycler_adapter(Context context, ArrayList<List_Adapter> model_cardViews){
        mContext=context;
        mModel_cardViews= model_cardViews;

    }
    @Override
    public Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.card_view, parent,false);
        return new Recycler_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Recycler_ViewHolder holder, int position) {
        List_Adapter model_cardView=mModel_cardViews.get(position);

        String name = model_cardView.getmPerson_name();
        String image = model_cardView.getmPerson_Image();
        String des = model_cardView.getmPerson_Detail();


        holder.my_name.setText(name);
//        Glide.with(mContext).load(image).into(holder.my_image);
        holder.my_detals.setText(des);
        //Picasso.with(mContext).load(image).into(holder.my_image);
        Picasso.get().load(image).into(holder.my_image);

    }

    @Override
    public int getItemCount() {
        return mModel_cardViews.size();
    }

    public interface OnItemClickListener {
    }

    public class Recycler_ViewHolder extends RecyclerView.ViewHolder{

        TextView my_name;
        ImageView my_image;
        TextView my_detals;
        public Recycler_ViewHolder(View itemView) {
            super(itemView);
            my_name=itemView.findViewById(R.id.person_name);
            my_image=itemView.findViewById(R.id.profile_image);
            my_detals=itemView.findViewById(R.id.description);



            //Detail Activity code....... :)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position=getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);
                        mListener.onItemClick(position);
                    }
                }
            });
        }
    }

}