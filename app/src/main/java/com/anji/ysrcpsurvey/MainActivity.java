package com.anji.ysrcpsurvey;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup radiogroup;
    Button next_main;
    EditText name, age, address, village, assembly, district, tx1, tx2, tx3;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(Config.MAIN, 0);
        editor = pref.edit();
        editor.putString(Config.mobile, "");
        editor.putString(Config.username, "");
        editor.putString(Config.date1, "");
        editor.commit();
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_main);
        next_main = (Button) findViewById(R.id.next_main);
        next_main.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name_main);
        age = (EditText) findViewById(R.id.age_main);
        address = (EditText) findViewById(R.id.address_main);
        village = (EditText) findViewById(R.id.village_main);
        assembly = (EditText) findViewById(R.id.ass_main);
        district = (EditText) findViewById(R.id.dist_main);
        tx1 = (EditText) findViewById(R.id.text_main);
        tx2 = (EditText) findViewById(R.id.tx2_main);
        tx3 = (EditText) findViewById(R.id.tx3_main);


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

    static String s_name, s_age, s_gender, s_village, s_assembly, s_dist, s_tx1, s_tx2, s_tx3, s_add;

    void nextPage() {
        try {
            int selectedId = radiogroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            s_gender = radioButton.getText().toString();
            s_name = name.getText().toString().trim();
            s_age = age.getText().toString().trim();
            s_village = village.getText().toString().trim();
            s_assembly = assembly.getText().toString().trim();
            s_dist = district.getText().toString().trim();
            s_tx1 = tx1.getText().toString().trim();
            s_tx2 = tx2.getText().toString().trim();
            s_tx3 = tx3.getText().toString().trim();
            s_add = address.getText().toString().trim();

//
//            s_name = name.getText().toString().trim();
//            s_age = age.getText().toString().trim();
//            s_village = village.getText().toString().trim();
//            s_assembly = assembly.getText().toString().trim();
//            s_dist = district.getText().toString().trim();
//            s_tx1 = tx1.getText().toString().trim();
//            s_tx2 = tx2.getText().toString().trim();
//            s_tx3 = tx3.getText().toString().trim();

            Intent intent = new Intent(MainActivity.this, F1Activity.class);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
                break;

            // case blocks for other MenuItems (if any)
        }
        return false;
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
