package com.anji.ysrcpsurvey;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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


public class F8Activity extends AppCompatActivity {

    RadioGroup radioGroup;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f8);
        pref = getSharedPreferences(Config.MAIN, 0);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_q1);
    }

    String q8;

    public void nextPage(View view) {

        try {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            q8 = radioButton.getText().toString();

            save();

        } catch (Exception e) {
            Toast.makeText(this, "Please Answer Above Question", Toast.LENGTH_SHORT).show();
        }

        //  Toast.makeText(this, "succusfully saved", Toast.LENGTH_SHORT).show();


    }

//    timeon,snames,gender,s_age,s_education,s_contactNo,s_bussiness,s_mandal,s_village,s_cast,q1,q2,q3,q4,q5,q6,q7,q8

    void save() {
        String sname = pref.getString(Config.username, "");
//        String samobile = pref.getString(Config.mobile, "");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String time = formatter.format(ts);
        try{
            databaseHelper.addContact(new Contact(time, MainActivity.s_name, MainActivity.s_gender, MainActivity.s_age, MainActivity.s_education, MainActivity.s_contactNo, MainActivity.s_bussiness, MandalAndVillage.s_mandal, MandalAndVillage.s_village, Cast.s_cast, F1Activity.q1, F2Activity.q2, F3Activity.q3, F4Activity.q4, F5Activity.q5, F6Activity.q6, F7Activity.q7, q8));

        }catch (Exception e){
            Log.e("dberror",e.getMessage().toString());
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Success ");
        alertDialogBuilder.setMessage("Successfully Inserted ");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        //  alertDialogBuilder.show();
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }
}
