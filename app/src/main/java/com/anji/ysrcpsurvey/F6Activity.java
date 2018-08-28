package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class F6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f6);
    }

    public void nextPage(View view) {
        Toast.makeText(this, "succusfully saved", Toast.LENGTH_SHORT).show();
    }

    public void backPage(View view) {
        Intent intent = new Intent(getApplicationContext(), F5Activity.class);
        startActivity(intent);
    }
}
