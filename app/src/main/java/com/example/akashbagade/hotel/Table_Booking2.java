package com.example.akashbagade.hotel;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.text.style.EasyEditSpan;
import android.util.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Table_Booking2 extends AppCompatActivity implements View.OnClickListener{

    //Creating views
    private EditText editTextConfirmOtp;

    private Button tbVerify;
    private Button buttonConfirm;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    //String variables to hold username password and phone
    public String name;
    public String email;
    public String phone;
    public String guests;
    public String time;
    public String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table__booking2);

        Intent intent1 = getIntent();
        Bundle bundle  = intent1.getExtras();

        String name = (bundle.getString("name")).trim();
        String email = (bundle.getString("email")).trim();
        String phone = (bundle.getString("phone")).trim();

        ((TextView)findViewById(R.id.tbName2)).setText(name);
        ((TextView)findViewById(R.id.tbEmail2)).setText(email);
        ((TextView)findViewById(R.id.tbPhone2)).setText(phone);

        //Initializing Views
        tbVerify = findViewById(R.id.tbVerify);

        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        //Adding a listener to button
        tbVerify.setOnClickListener(this);
    }

    //This method would confirm the otp
    private void confirmOtp() throws JSONException {
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        editTextConfirmOtp = confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(Table_Booking2.this, "Authenticating", "Please wait while we check the entered code", false,false);

                //Getting the user entered otp from edittext
                final String otp = editTextConfirmOtp.getText().toString().trim();

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, config.CONFIRM_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //if the server response is success
                                if(response.equalsIgnoreCase("success")){
                                    //dismissing the progressbar
                                    loading.dismiss();

                                    //Starting a new activity
                                    startActivity(new Intent(Table_Booking2.this, Table_Booking3.class));
                                }else{
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(Table_Booking2.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
                                    try {
                                        //Asking user to enter otp again
                                        confirmOtp();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(Table_Booking2.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put(config.KEY_OTP, otp);
                        params.put(config.KEY_NAME, name);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);
            }
        });
    }


    //this method will register the user
    private void register() {

        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Registering", "Please wait...", false, false);


        //Getting user data


       /* name = tbName.getText().toString().trim();
        email = tbEmail.getText().toString().trim();
        phone = tbPhone.getText().toString().trim();
        email = tbEmail.getText().toString().trim();
        time = tbTime.getText().toString().trim();
        date = tbDate.getText().toString().trim(); */

        Intent intent2 = getIntent();
        Bundle bundle2  = intent2.getExtras();

        String name = (bundle2.getString("name")).trim();
        String email = (bundle2.getString("email")).trim();
        String phone = (bundle2.getString("phone")).trim();
        String guests = (bundle2.getString("guests")).trim();
        String time = (bundle2.getString("time")).trim();
        String date = (bundle2.getString("date")).trim();


        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, config.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            //Creating the json object from the response
                            JSONObject jsonResponse = new JSONObject(response);

                            //If it is success
                            if(jsonResponse.getString(config.TAG_RESPONSE).equalsIgnoreCase("Success")){
                                //Asking user to confirm otp
                                confirmOtp();
                            }else{
                                //If not successful user may already have registered
                                Toast.makeText(Table_Booking2.this, "Username or Phone number already registered", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(Table_Booking2.this, error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put(config.KEY_NAME, name);
                params.put(config.KEY_EMAIL, email);
                params.put(config.KEY_PHONE, phone);
                params.put(config.KEY_GUESTS, guests);
                params.put(config.KEY_TIME, time);
                params.put(config.KEY_DATE, date);
                return params;
            }
        };

        //Adding request the the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        register();
    }
}
