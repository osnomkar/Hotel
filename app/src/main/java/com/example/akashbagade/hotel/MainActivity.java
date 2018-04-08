package com.example.akashbagade.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ImageView imageView = findViewById(R.id.imageView);

       imageView.setOnClickListener(this :: imgClick);
    }

    public void imgClick(View view) {
        Intent intent = new Intent(this,home.class);
        startActivity(intent);

    }
}
