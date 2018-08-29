package com.example.pratyush.missingchildrenfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.pratyush.missingchildrenfinal.Explore.EXTRA_DESCRIPTION;
import static com.example.pratyush.missingchildrenfinal.Explore.EXTRA_NAME;
import static com.example.pratyush.missingchildrenfinal.Explore.EXTRA_URL;

public class DetailExplore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_explore);

        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra(EXTRA_URL);
        String name=intent.getStringExtra(EXTRA_NAME);
        String description=intent.getStringExtra(EXTRA_DESCRIPTION);

        ImageView imageViewDetails=findViewById(R.id.imagedetail);
        TextView nameDetail=findViewById(R.id.namedetail);
        TextView descriptionDetail=findViewById(R.id.descriptiondetail);
        Picasso.get().load(imageUrl).into(imageViewDetails);

        nameDetail.setText(name);
        descriptionDetail.setText(description);

    }
}