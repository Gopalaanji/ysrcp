package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class F7Activity extends AppCompatActivity {
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f7);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_q1);
    }

    static String q7;

    public void nextPage(View view) {

        try {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            q7 = radioButton.getText().toString();
            Intent intent = new Intent(this, F8Activity.class);
            startActivity(intent);


        } catch (Exception e) {
            Toast.makeText(this, "Please Answer Above Question", Toast.LENGTH_SHORT).show();
        }

        //  Toast.makeText(this, "succusfully saved", Toast.LENGTH_SHORT).show();


    }
}
