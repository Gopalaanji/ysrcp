package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class F6Activity extends AppCompatActivity {
    RadioGroup radiogroup;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f6);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_q1);
        pref = getSharedPreferences(Config.MAIN, 0);
    }

    int f6;

    public void nextPage(View view) {

        try {
            int selectedId = radiogroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            String a = radioButton.getText().toString();

            if (a.equals(getResources().getString(R.string.vp))) {
                f6 = 1;
            } else if (a.equals(getResources().getString(R.string.poor))) {
                f6 = 2;
            } else if (a.equals(getResources().getString(R.string.ok))) {
                f6 = 3;
            } else {
                f6 = 4;
            }
            save();
        } catch (Exception e) {
            Toast.makeText(this, "Please Answer Above Question", Toast.LENGTH_SHORT).show();
        }
        Log.e("output", MainActivity.s_age);
        //  Toast.makeText(this, "succusfully saved", Toast.LENGTH_SHORT).show();


    }

    void save() {
        String sname = pref.getString(Config.username, "");
        String samobile = pref.getString(Config.mobile, "");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String time = formatter.format(ts);
        databaseHelper.addContact(new Contact(F1Activity.f1, F2Activity.f2, F3Activity.f3, F4Activity.f4, F5Activity.f5, f6, time, sname, samobile, MainActivity.s_name, MainActivity.s_gender, MainActivity.s_age, MainActivity.s_add, MainActivity.s_village, MainActivity.s_assembly, MainActivity.s_dist, MainActivity.s_tx1, MainActivity.s_tx2, MainActivity.s_tx3));
    }

    public void backPage(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
