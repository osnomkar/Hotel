package com.example.akashbagade.hotel;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Table_Booking extends AppCompatActivity {

        //Creating views
        private EditText tbName;
        private EditText tbEmail;
        private EditText tbPhone;
        private EditText tbGuests;
        private EditText tbTime;
        private EditText tbDate;

        // @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table__booking);

        try {
            //Initializing Views
            tbName = findViewById(R.id.tbName);
            tbEmail = findViewById(R.id.tbEmail);
            tbPhone = findViewById(R.id.tbPhone);
            tbGuests = findViewById(R.id.tbGuests);
            tbTime = findViewById(R.id.tbTime);
            tbDate = findViewById(R.id.tbDate);
            Button tbSubmit = findViewById(R.id.tbSubmit);

            tbSubmit.setOnClickListener(this::onSubmit);

        }catch (Exception e){
            Log.i(Table_Booking.class.getCanonicalName(),"Exception Oncreate");
        }

    }

        public void onSubmit (View view){

        try {
            Intent intent = new Intent(this, Table_Booking2.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", tbName.getText().toString());
            bundle.putString("email", tbEmail.getText().toString());
            bundle.putString("phone", tbPhone.getText().toString());
            bundle.putString("guests", tbGuests.getText().toString());
            bundle.putString("time", tbTime.getText().toString());
            bundle.putString("date", tbDate.getText().toString());

            intent.putExtras(bundle);

            Log.i(Table_Booking.class.getCanonicalName(), "all info" + tbName + " " + tbEmail + " " + tbPhone);

            startActivity(intent);
            ClearText();
        } catch (Exception e) {
            Log.i(Table_Booking.class.getCanonicalName(), "Exception onSubmit");
        }
    }

   public void ClearText(){
        tbName.setText("");
        tbEmail.setText("");
        tbPhone.setText("");
        tbGuests.setText("");
        tbTime.setText("");
        tbDate.setText("");
    }


}


