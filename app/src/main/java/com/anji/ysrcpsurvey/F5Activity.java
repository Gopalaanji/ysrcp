package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class F5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f5);
    }
    public void nextPage(View view) {
        Intent intent = new Intent(getApplicationContext(), F6Activity.class);
        startActivity(intent);
        finish();
    }
}
