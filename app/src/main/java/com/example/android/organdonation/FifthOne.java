package com.example.android.organdonation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FifthOne extends AppCompatActivity {

    public static final String PREFS = "storedData";
//    int backButtonCount = 0;
    TextView donorNumber;
    TextView fullName;
    ImageView profile;
    TextView address;
    ListView listView;
    String organName [];
    Boolean organDonated[] = new Boolean[6];
    ArrayList<String> selectedOrgans;
    int c =0;


//    @Override
//    public void onBackPressed()
//    {
//        if(backButtonCount >= 1)
//        {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        else
//        {
//            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//            backButtonCount++;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_one);

        donorNumber = (TextView) findViewById(R.id.donorNumber);
        fullName = (TextView) findViewById(R.id.fullName);
        profile = (ImageView) findViewById(R.id.proImage);
        address = (TextView) findViewById(R.id.address);
        listView = (ListView) findViewById(R.id.listOrgans);

        SharedPreferences received = getSharedPreferences(PREFS,0);
//
//        String userDonorN = received.getString("userPhoneNumber","");
//        donorNumber.setText(userDonorN);
//
//        String userfirstN = received.getString("userFname","");
//        String userlastN = received.getString("userLname","");
//        fullName.setText(userfirstN.concat(" ").concat(userlastN));
//
//
//        String userProfile = received.getString("userProfile","");
//        Glide.with(this).load(userProfile).into(profile);
//
//        String userAddress = received.getString("userPostalAddress","");
//        address.setText(userAddress);

        organName= new String[6];
        organDonated[0] = received.getBoolean("Cornea",false);
        organName[0] = "Cornea";
        organDonated[1] = received.getBoolean("Kidney",false);
        organName[1] = "Kidneys";
        organDonated[2] = received.getBoolean("Heart",false);
        organName[2] = "Heart";
        organDonated[3] = received.getBoolean("Lungs",false);
        organName[3] = "Lungs";
        organDonated[4] = received.getBoolean("Pancreas",false);
        organName[4] = "Pancreas";
        organDonated[5] = received.getBoolean("Liver",false);
        organName[5] = "Liver";

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);


        selectedOrgans = new ArrayList<>();

        for(int i=0;i<6;++i)
        {
            if(organDonated[i]==true)
            {
                selectedOrgans.add(organName[i]);
                c++;
            }

        }





    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
          return c;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_list,null);

            TextView textView = (TextView)view.findViewById(R.id.organText);
            textView.setText(selectedOrgans.get(i));

            SharedPreferences received = getSharedPreferences(PREFS,0);

            String userDonorN = received.getString("userPhoneNumber","");
            donorNumber.setText(userDonorN);

            String userfirstN = received.getString("userFname","");
            String userlastN = received.getString("userLname","");
            fullName.setText(userfirstN.concat(" ").concat(userlastN));


            String userProfile = received.getString("userProfile","");
            Glide.with(FifthOne.this).load(userProfile).into(profile);

            String userAddress = received.getString("userPostalAddress","");
            address.setText(userAddress);


            return view;
        }
    }
}
