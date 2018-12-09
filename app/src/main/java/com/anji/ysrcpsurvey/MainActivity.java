package com.anji.ysrcpsurvey;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup radiogroup_gender, radio_age, radiogroup_edu;
    Button next_main;
    EditText name, cNo, bussiness;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(Config.MAIN, 0);
        editor = pref.edit();
        radio_age = (RadioGroup) findViewById(R.id.radiogroup_age);
        radiogroup_gender = (RadioGroup) findViewById(R.id.radiogroup_gender);
        radiogroup_edu = (RadioGroup) findViewById(R.id.radiogroup_edu);
        next_main = (Button) findViewById(R.id.next_main);
        next_main.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name_main);
        cNo = (EditText) findViewById(R.id.contact);
        bussiness = (EditText) findViewById(R.id.bussiness);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        //radiogroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_main:
                nextPage();
                break;
            default:
                break;


        }

    }

    static String s_name, s_gender, s_age, s_education, s_contactNo, s_bussiness;

    void nextPage() {
        try {

            try {
                int selectedId = radiogroup_gender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                s_gender = radioButton.getText().toString();

            } catch (Exception e) {
                Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
            }
            try {
                int selectedId = radio_age.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                s_age = radioButton.getText().toString();

            } catch (Exception e) {
                Toast.makeText(this, "Please Select Age Group", Toast.LENGTH_SHORT).show();
            }
            try {
                int selectedId = radiogroup_edu.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                s_education = radioButton.getText().toString();
            } catch (Exception e) {
                Toast.makeText(this, "Please Select Education ", Toast.LENGTH_SHORT).show();
            }

            s_name = name.getText().toString().trim();
            s_contactNo = cNo.getText().toString().trim();
            s_bussiness = bussiness.getText().toString().trim();
            if (!s_name.isEmpty() && !s_contactNo.isEmpty() && !s_bussiness.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, MandalAndVillage.class);
                startActivity(intent);
            } else {

                if (s_name.isEmpty()) {
                    name.setError("Can't Be Empty");
                }
                if (s_contactNo.isEmpty()) {
                    cNo.setError("Can't Be Empty");
                }
                if (s_bussiness.isEmpty()) {
                    bussiness.setError("Can't Be Empty");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logOut();
                // do your sign-out stuff
                break;
            case R.id.sync:
                syncz();
                // Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
    }

    void syncz() {

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            // Do whatever
            DatabaseHelper db = new DatabaseHelper(this);
            List<Contact> contacts = db.getAllContacts();
            StringBuffer abc = new StringBuffer();
            Log.e("size", contacts.size() + "");
            if (contacts.size() == 0) {
                Toast.makeText(this, "You Don't Have Any SurveyList", Toast.LENGTH_SHORT).show();
            } else {
                for (Contact cn : contacts) {


                }

            }


        } else {
            Toast.makeText(this, "Please Connect WIFI", Toast.LENGTH_SHORT).show();
        }


    }

    void logOut() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        String log = getResources().getString(R.string.Logoutapp);
        alertDialog.setTitle(log);
        String exit = getResources().getString(R.string.closethisapplication);
        alertDialog.setMessage(exit);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            editor.putString(Config.mobile, "");
                            editor.putString(Config.username, "");
                            editor.putString(Config.date1, "");
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                        } catch (Exception ex) {
                            Log.e("ex", ex + "");
                        }


                    }
                }
        );
        // Showing Alert Message
        alertDialog.show();
    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intCloseApp = new Intent(Intent.ACTION_MAIN);
            intCloseApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intCloseApp.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intCloseApp.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intCloseApp.addCategory(Intent.CATEGORY_HOME);
            intCloseApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intCloseApp);
        } else {
            Toast.makeText(getBaseContext(), "Tap back again to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
