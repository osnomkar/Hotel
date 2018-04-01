package com.example.akashbagade.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        (findViewById(R.id.btnBookTable)).setOnClickListener(this::bTable);
        (findViewById(R.id.btnConference)).setOnClickListener(this::bBanquet);
        (findViewById(R.id.btnFoodParcel)).setOnClickListener(this::mParcel);
        (findViewById(R.id.btnHomeDelivery)).setOnClickListener(this::oFood);
    }

    public void bTable(View view) {

        Intent intent = new Intent(this,Table_Booking.class);
        startActivity(intent);

    }

    public void oFood(View view) {
        Intent intent = new Intent(this,HomeDelivery.class);
        startActivity(intent);
    }

    public void mParcel(View view) {
        Intent intent = new Intent(this,FoodParcel.class);
        startActivity(intent);
    }

    public void bBanquet(View view) {
        Intent intent = new Intent(this,Banquet.class);
        startActivity(intent);
    }
}
