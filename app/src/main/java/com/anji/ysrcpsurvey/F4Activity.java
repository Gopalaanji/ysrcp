package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class F4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f4);
    }
    public void nextPage(View view) {
        Intent intent = new Intent(getApplicationContext(), F5Activity.class);
        startActivity(intent);
        // finish();
    }
    public void backPage(View view){
        Intent intent = new Intent(getApplicationContext(), F3Activity.class);
        startActivity(intent);
    }
}
