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

public class MandalAndVillage extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup man;
    EditText villge;
    Button mandal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandal_and_village);

        man = (RadioGroup) findViewById(R.id.radiogroup_mandal);
        villge = (EditText) findViewById(R.id.village);
        mandal = (Button) findViewById(R.id.next_mandal);
        mandal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_mandal:
                nextPage();
                break;
            default:
                break;
        }


    }

   static String s_mandal, s_village;

    private void nextPage() {

        try {
            int selectedId = man.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectedId);
            s_mandal = radioButton.getText().toString();

        } catch (Exception e) {
            Toast.makeText(this, "Please Select Mandal", Toast.LENGTH_SHORT).show();
        }
        s_village = villge.getText().toString().trim();
        if (!s_mandal.isEmpty()) {
            Intent intent = new Intent(this, Cast.class);
            startActivity(intent);

        } else {
            villge.setError("Can't Be Empty");
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
