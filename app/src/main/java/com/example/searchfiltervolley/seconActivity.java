package com.example.searchfiltervolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class seconActivity extends AppCompatActivity {
    ImageView idSecond,ivFeedBack;
    TextView idSecondCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon);
        idSecond = findViewById(R.id.idSecond);
//        ivFeedBack = findViewById(R.id.ivFeedBack);
        idSecondCourseName = findViewById(R.id.idSecondCourseName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i=getIntent();
        String name=i.getStringExtra("name");
        String image = i.getStringExtra("img");
        Glide.with(seconActivity.this).load(image).into(idSecond);
        idSecondCourseName.setText(name);


//        ivFeedBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}