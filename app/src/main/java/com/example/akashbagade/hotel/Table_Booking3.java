package com.example.akashbagade.hotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Table_Booking3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table__booking3);
    }

    public void ontbBack(View view) {
        Intent intent = new Intent(this,Table_Booking.class);
        startActivity(intent);
    }

}
