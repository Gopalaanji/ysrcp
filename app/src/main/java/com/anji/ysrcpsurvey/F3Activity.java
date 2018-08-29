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

    static int f3;

    public void nextPage(View view) {
        try {
            int selectedId = radiogroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            String a = radioButton.getText().toString();

            if (a.equals(getResources().getString(R.string.vp))) {
                f3 = 1;
            } else if (a.equals(getResources().getString(R.string.poor))) {
                f3 = 2;
            } else if (a.equals(getResources().getString(R.string.ok))) {
                f3 = 3;
            } else {
                f3 = 4;
            }
            Intent intent = new Intent(getApplicationContext(), F4Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Please Answer Above Question", Toast.LENGTH_SHORT).show();
        }
        // finish();
    }

    public void backPage(View view) {
        Intent intent = new Intent(getApplicationContext(), F2Activity.class);
        startActivity(intent);
    }
}
