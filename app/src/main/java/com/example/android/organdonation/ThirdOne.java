package com.example.android.organdonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ThirdOne extends AppCompatActivity {

    private Button secondDet;
    public static final String PREFS = "storedData";

    private RadioButton rCornea;
    private RadioButton rKidney;
    private RadioButton rHeart;
    private RadioButton rLungs;
    private RadioButton rLiver;
    private RadioButton rPancreas;
    private EditText bloodGrp;
    private CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_one);

        secondDet = (Button) findViewById(R.id.secondNext);
        rCornea = (RadioButton) findViewById(R.id.radioCornea);
        rKidney = (RadioButton) findViewById(R.id.radioKidneys);
        rHeart = (RadioButton) findViewById(R.id.radioHeart);
        rLungs = (RadioButton) findViewById(R.id.radioLungs);
        rLiver = (RadioButton) findViewById(R.id.radioLiver);
        rPancreas = (RadioButton) findViewById(R.id.radioPancreas);
        bloodGrp = (EditText) findViewById(R.id.bgroup);
        checkBox = (CheckBox) findViewById(R.id.checkBox2);

        secondDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sBloodGrp = bloodGrp.getText().toString();
                SharedPreferences storedData = getSharedPreferences(PREFS,0);
                SharedPreferences.Editor editor = storedData.edit();

                editor.putString("userBloodGrp",sBloodGrp);
                editor.putBoolean("Cornea",rCornea.isChecked());
                editor.putBoolean("Kidney",rKidney.isChecked());
                editor.putBoolean("Heart",rHeart.isChecked());
                editor.putBoolean("Lungs",rLungs.isChecked());
                editor.putBoolean("Pancreas",rPancreas.isChecked());
                editor.putBoolean("Liver",rLiver.isChecked());
                editor.putBoolean("CheckBox",checkBox.isChecked());

                editor.commit();

                Boolean checkboxDonate = storedData.getBoolean("CheckBox",false);

                if(checkboxDonate==false)
                {

                    Toast.makeText(ThirdOne.this,"Please agree to give someone life",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(bloodGrp.getText()))
                    Toast.makeText(ThirdOne.this,"Please enter your blood group",Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(ThirdOne.this,FourthOne.class));


            }
        });

    }
}
