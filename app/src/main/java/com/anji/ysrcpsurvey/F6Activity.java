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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f6);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_q1);

    }

    static String q6;

    public void nextPage(View view) {

        try {
            int selectedId = radiogroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            q6 = radioButton.getText().toString();
            Intent intent = new Intent(this, F7Activity.class);
            startActivity(intent);


        } catch (Exception e) {
            Toast.makeText(this, "Please Answer Above Question", Toast.LENGTH_SHORT).show();
        }

        //  Toast.makeText(this, "succusfully saved", Toast.LENGTH_SHORT).show();


    }


//    public void backPage(View view) {
//        onBackPressed();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
