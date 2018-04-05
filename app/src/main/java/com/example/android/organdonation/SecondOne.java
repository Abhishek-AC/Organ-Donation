package com.example.android.organdonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SecondOne extends AppCompatActivity {

    //Button button;
    public static final String PREFS = "storedData";
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    int backButtonCount = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    private Button firstDet;
    private EditText FirstName;
    private EditText LastName;
    private RadioGroup Gender;
    private RadioButton GenderButton;
    private EditText phoneNumber;
    private EditText dob;
    private EditText postalAddress;
    boolean isChecked;


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_one);

        firstDet = (Button) findViewById(R.id.firstNext);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null )
                {
                    startActivity(new Intent(SecondOne.this,MainActivity.class));
                }
            }
        };
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_logout:
                        Log.i("hey","logout clicked" );
                        mAuth.signOut();
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(SecondOne.this,MyAccount.class));
                        break;
                }
                return true;
            }
        });


//        mToolbar = (Toolbar)findViewById(R.id.nav_action);
//        setSupportActionBar(mToolbar);
//
//
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
//
//
//
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        button = (Button) findViewById(R.id.logout);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.signOut();
//            }
//        });

        FirstName = (EditText) findViewById(R.id.fname);
        LastName = (EditText) findViewById(R.id.lname);
        dob = (EditText) findViewById(R.id.dob);
        Gender = (RadioGroup) findViewById(R.id.radioGender);
        phoneNumber = (EditText) findViewById(R.id.phone);
        postalAddress = (EditText) findViewById(R.id.paddress);
        // This will get the radiogroup
//        Gender = (RadioGroup)findViewById(R.id.radioGender);
//        // This will get the radiobutton in the radiogroup that is checked
//        GenderButton = (RadioButton)Gender.findViewById(Gender.getCheckedRadioButtonId());


        firstDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mFirstName = FirstName.getText().toString();
                String mLastName = LastName.getText().toString();
                String mDob = dob.getText().toString();
                int GenderId = Gender.getCheckedRadioButtonId();
                //String sGenderId = Integer.toString(GenderId);
                //Log.i("hey",sGenderId);
                //GenderButton = (RadioButton) findViewById(GenderId);
                //String mGender = GenderButton.getText().toString();
                //Log.i("hey",mGender);
                String mPostalAddress = postalAddress.getText().toString();
                String mPhoneNumber = phoneNumber.getText().toString();

                SharedPreferences storedData = getSharedPreferences(PREFS,0);
                final SharedPreferences.Editor editor = storedData.edit();
                editor.putString("userFname",mFirstName);
                editor.putString("userLname",mLastName);
                editor.putString("userDob",mDob);
                //editor.putBoolean("userGender",GenderButton.isChecked());
                //editor.putString("userGender",mGender);
                editor.putString("userPhoneNumber",mPhoneNumber);
                editor.putString("userPostalAddress",mPostalAddress);




                Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId)
                    {
                        // This will get the radiobutton that has changed in its check state
                        RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                        // This puts the value (true/false) into the variable
                        isChecked = checkedRadioButton.isChecked();
                        // If the radiobutton that has changed in check state is now checked...
                        if (isChecked) {
                            // Changes the textview's text to "Checked: example radiobutton text"
                            editor.putBoolean("userGender", isChecked);
                        }
                    }
                });


                if(isChecked==false|| TextUtils.isEmpty(FirstName.getText())||TextUtils.isEmpty(LastName.getText())||TextUtils.isEmpty(dob.getText())||TextUtils.isEmpty(phoneNumber.getText())||TextUtils.isEmpty(postalAddress.getText()))
                    Toast.makeText(SecondOne.this,"Please fill all the details",Toast.LENGTH_SHORT).show();
                else {
                    editor.commit();
                    startActivity(new Intent(SecondOne.this, ThirdOne.class));
                }
            }
        });


}
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        if(mToggle.onOptionsItemSelected(item)){
////            return true;
////        }
//        switch (item.getItemId()) {
//            case R.id.nav_logout:
//                Log.i("hey","logout clicked" );
//
//                mAuth.signOut();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
// }
}
