package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    EditText mobile, username;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile = (EditText) findViewById(R.id.mobileno_login);
        username = (EditText) findViewById(R.id.user_login);
        pref = getSharedPreferences(Config.MAIN, 0);

        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        date = sdf.format(c1.getTime());
        String sharedate = pref.getString(Config.date1, "");
        Log.e("sharedate",sharedate+date);
        if (date.equals(sharedate)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void logIn(View view) {


        String mobile_no = mobile.getText().toString().trim();
        String user_name = username.getText().toString().trim();
//        if (mobile_no.length() == 10) {
            editor = pref.edit();
            editor.putString(Config.mobile, mobile_no);
            editor.putString(Config.username, user_name);
            editor.putString(Config.date1, date);
            editor.commit();
            String sharedate = pref.getString(Config.date1, "");
            Log.e("sharedate1",sharedate+".."+date);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

//        } else {
//            mobile.setError("Invalied Mobile");
//        }
    }
}
