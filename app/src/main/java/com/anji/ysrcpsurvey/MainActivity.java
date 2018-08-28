package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup radiogroup;
    Button next_main;
    EditText name, age, address, village, assembly, district, tx1, tx2, tx3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup_main);
        next_main = (Button) findViewById(R.id.next_main);
        next_main.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name_main);
        age = (EditText) findViewById(R.id.age_main);
        address = (EditText) findViewById(R.id.address_main);
        village = (EditText) findViewById(R.id.village_main);
        assembly = (EditText) findViewById(R.id.ass_main);
        district = (EditText) findViewById(R.id.dist_main);
        tx1 = (EditText) findViewById(R.id.text_main);
        tx2 = (EditText) findViewById(R.id.tx2_main);
        tx3 = (EditText) findViewById(R.id.tx3_main);

        //radiogroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_main:
                nextPage();
                break;
            default:
                break;


        }

    }


    void nextPage() {
        int selectedId = radiogroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        String radiostring = radioButton.getText().toString();

        Intent intent = new Intent(getApplicationContext(), F1Activity.class);
        startActivity(intent);

    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            Intent intCloseApp = new Intent(Intent.ACTION_MAIN);
            intCloseApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intCloseApp.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intCloseApp.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intCloseApp.addCategory(Intent.CATEGORY_HOME);
            intCloseApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intCloseApp);
        } else {
            Toast.makeText(getBaseContext(), "Tap back again to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
