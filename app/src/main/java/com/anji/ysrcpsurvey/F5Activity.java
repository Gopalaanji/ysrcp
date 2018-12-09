package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class F5Activity extends AppCompatActivity {
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f5);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_q1);
    }

    static String q5;

    public void nextPage(View view) {
        try {
            int selectedId = radiogroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            q5 = radioButton.getText().toString();
//
//            if (a.equals("Mr.Galla Jayadev(TDP)")) {
//                f5 = 1;
//            } else if (a.equals("Mr.Sri Lavu Krishnadevarayya(YSRCP)")) {
//                f5 = 2;
//            } else if (a.equals("Anyone from Janasena")) {
//                f5 = 3;
//            } else if (a.equals("Anyone from TDP")) {
//                f5 = 4;
//            } else if (a.equals("Anyone from YSRCP")) {
//                f5 = 5;
//            } else {
//                f5 = 6;
//            }
            Intent intent = new Intent(getApplicationContext(), F6Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Please Answer Above Question", Toast.LENGTH_SHORT).show();
        }
        // finish();
    }

//    public void backPage(View view) {
//        onBackPressed();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}