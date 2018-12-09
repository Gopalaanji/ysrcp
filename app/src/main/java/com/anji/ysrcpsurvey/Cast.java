package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Cast extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    RadioGroup cast;
    Button cast_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        cast = (RadioGroup) findViewById(R.id.cast);
        cast_main = (Button) findViewById(R.id.cast_main);
        cast_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cast_main:
                next();
                break;

        }

    }

    static String s_cast;

    private void next() {

        try {
            int selectedId = cast.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            s_cast = radioButton.getText().toString();

            Intent intent = new Intent(this, F1Activity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Please select Cast", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
