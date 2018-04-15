package com.example.akashbagade.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FoodParcel3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_parcel3);
    }

    public void onfpBack(View view) {
        Intent intent = new Intent(this,FoodParcel.class);
        startActivity(intent);
    }
}
