package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class F6Activity extends AppCompatActivity {
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f6);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_q1);
    }

    public void nextPage(View view) {
        Log.e("output", MainActivity.s_age);
        MainActivity.s_age = "0";
        Log.e("output", MainActivity.s_age);
        Toast.makeText(this, "succusfully saved", Toast.LENGTH_SHORT).show();
    }

    public void backPage(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
