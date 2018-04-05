package com.example.android.organdonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class FourthOne extends AppCompatActivity {

    public static final String PREFS = "storedData";

    private EditText FirstName;
    private EditText LastName;
    private EditText EmailID;
    private EditText BloodGrp;
    private EditText PhoneNumber;
    private EditText postalAddress;

    private CheckBox Newsletter;
    private Button generate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_one);

        FirstName = (EditText) findViewById(R.id.fname3);
//        SharedPreferences received = getSharedPreferences(PREFS,0);
//        String userString = received.getString("kinFname","");
//        FirstName.setText(userString);
        LastName = (EditText) findViewById(R.id.lname2);
        EmailID = (EditText) findViewById(R.id.editText2);
        BloodGrp = (EditText) findViewById(R.id.bgroup);
        PhoneNumber = (EditText) findViewById(R.id.editText3);
        postalAddress = (EditText) findViewById(R.id.editText4);

        Newsletter = (CheckBox) findViewById(R.id.checkBox3);

        generate = (Button) findViewById(R.id.button);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sFName = FirstName.getText().toString();
                String sLName = LastName.getText().toString();
                String sEmail = EmailID.getText().toString();
                String sBloodG = BloodGrp.getText().toString();
                String sPhoneN = PhoneNumber.getText().toString();
                String sPAddress = postalAddress.getText().toString();

                SharedPreferences storedData = getSharedPreferences(PREFS, 0);
                SharedPreferences.Editor editor = storedData.edit();

                editor.putString("kinFname",sFName);
                editor.putString("kinLname",sLName);
                editor.putString("kinEmail",sEmail);
                editor.putString("kinBloodG",sBloodG);
                editor.putString("kinPhoneN",sPhoneN);
                editor.putString("kinPAddress",sPAddress);

                editor.putBoolean("newsletter",Newsletter.isChecked());

                editor.commit();


                startActivity(new Intent(FourthOne.this, FifthOne.class));


            }
        });




    }
}
