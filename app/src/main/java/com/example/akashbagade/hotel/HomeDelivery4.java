package com.example.akashbagade.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeDelivery4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_delivery4);
    }

    public void onhdBack(View view) {
        Intent intent = new Intent(this,HomeDelivery.class);
        startActivity(intent);
    }
}
