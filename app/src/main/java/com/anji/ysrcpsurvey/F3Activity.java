package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class F3Activity extends AppCompatActivity {
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f3);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_q1);
    }

    static String q3;

    public void nextPage(View view) {
        try {
            int selectedId = radiogroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            q3 = radioButton.getText().toString();


            Intent intent = new Intent(getApplicationContext(), F4Activity.class);
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
