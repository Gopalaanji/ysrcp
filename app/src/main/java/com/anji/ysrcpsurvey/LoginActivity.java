package com.anji.ysrcpsurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    EditText mobile, username;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String date;
    String userlist[] = {"8011","8012","8013","8014","8015","8016","8017","8018","8019","8020","8021","8022","8023","8024","8025","123456","9011@gmail.com", "9012@gmail.com", "9013@gmail.com", "9014@gmail.com", "9015@gmail.com", "9016@gmail.com", "9017@gmail.com", "9018@gmail.com", "9019@gmail.com", "9020@gmail.com","9021@gmail.com","9022@gmail.com","9023@gmail.com","9024@gmail.com","9025@gmail.com","9026@gmail.com","9027@gmail.com","9028@gmail.com","9029@gmail.com","9030@gmail.com"};
    String passList[] = {"8011","8012","8013","8014","8015","8016","8017","8018","8019","8020","8021","8022","8023","8024","8025","123456","uavbix", "jrlwi2", "450wij", "wf850u", "p35zyf", "a2x7k2", "eg5301", "kkv1y3", "hztoi0", "21b7yu","bap7hu","smc2t5","0hsvtu","2czu49","kpky76","pqp8x1","a0ct6p","13zcm6","a3bjp1","kf0b7z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile = (EditText) findViewById(R.id.mobileno_login);
        username = (EditText) findViewById(R.id.user_login);
        pref = getSharedPreferences(Config.MAIN, 0);

        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance(); // today
        date = sdf.format(c1.getTime());
        String sharedate = pref.getString(Config.date1, "");
        Log.e("sharedate", sharedate + date);
        if (date.equals(sharedate)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void logIn(View view) {

        try {


            String mobile_no = mobile.getText().toString().trim();
            String user_name = username.getText().toString().trim();
//        if (mobile_no.length() == 10) {

            Log.e("sharedate1", user_name);

            for (int i = 1; i < userlist.length; i++) {
                if (user_name.equals(userlist[i])) {
                    if (mobile_no.equals(passList[i])) {
                        editor = pref.edit();
                        editor.putString(Config.mobile, mobile_no);
                        editor.putString(Config.username, user_name);
                        editor.putString(Config.date1, date);
                        editor.commit();
                        String sharedate = pref.getString(Config.date1, "");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Password is  worng", Toast.LENGTH_SHORT).show();
                    }
                } else {
                  //  Toast.makeText(this, "Username And Password Are Worng", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, "Please check usename and password ", Toast.LENGTH_SHORT).show();
        }

//        } else {
//            mobile.setError("Invalied Mobile");
//        }
    }
}
