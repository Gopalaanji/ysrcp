package com.anji.ysrcpsurvey;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    RadioGroup radiogroup_gender, radio_age, radiogroup_edu;
    Button next_main;
    EditText name, cNo, bussiness;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private static final int REQUEST_PERMISSIONS = 1;
    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(Config.MAIN, 0);
        editor = pref.edit();
        radio_age = (RadioGroup) findViewById(R.id.radiogroup_age);
        radiogroup_gender = (RadioGroup) findViewById(R.id.radiogroup_gender);
        radiogroup_edu = (RadioGroup) findViewById(R.id.radiogroup_edu);
        next_main = (Button) findViewById(R.id.next_main);
        next_main.setOnClickListener(this);
        name = (EditText) findViewById(R.id.name_main);
        cNo = (EditText) findViewById(R.id.contact);
        bussiness = (EditText) findViewById(R.id.bussiness);


        checkPermissions();

//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED)
//
//        {
//            if (ActivityCompat.shouldShowRequestPermissionRationale
//                    (this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    ) {
//                Snackbar.make(findViewById(android.R.id.content),
//                        "Please Grant Permissions",
//                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                        REQUEST_PERMISSIONS);
//                            }
//                        }).show();
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        REQUEST_PERMISSIONS);
//            }
//        } else {
//            //Call whatever you want
//
//        }


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        //radiogroup.setOnCheckedChangeListener(this);
    }


    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
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

    static String s_name, s_gender, s_age, s_education, s_contactNo, s_bussiness;

    void nextPage() {
        try {

            try {
                int selectedId = radiogroup_gender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                s_gender = radioButton.getText().toString();

            } catch (Exception e) {
                Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
            }
            try {
                int selectedId = radio_age.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                s_age = radioButton.getText().toString();

            } catch (Exception e) {
                Toast.makeText(this, "Please Select Age Group", Toast.LENGTH_SHORT).show();
            }
            try {
                int selectedId = radiogroup_edu.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                s_education = radioButton.getText().toString();
            } catch (Exception e) {
                Toast.makeText(this, "Please Select Education ", Toast.LENGTH_SHORT).show();
            }

            s_name = name.getText().toString().trim();
            s_contactNo = cNo.getText().toString().trim();
            s_bussiness = bussiness.getText().toString().trim();
            if (!s_name.isEmpty() && !s_contactNo.isEmpty() && !s_bussiness.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, MandalAndVillage.class);
                startActivity(intent);
            } else {

                if (s_name.isEmpty()) {
                    name.setError("Can't Be Empty");
                }
                if (s_contactNo.isEmpty()) {
                    cNo.setError("Can't Be Empty");
                }
                if (s_bussiness.isEmpty()) {
                    bussiness.setError("Can't Be Empty");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logOut();
                // do your sign-out stuff
                break;
            case R.id.sync:
                syncz();
                // Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
                break;
            // case blocks for other MenuItems (if any)
        }
        return false;
    }

    void syncz() {

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            // Do whatever
            DatabaseHelper db = new DatabaseHelper(this);
            List<Contact> contacts = db.getAllContacts();
            StringBuffer abc = new StringBuffer();
            Log.e("size", contacts.size() + "");
            if (contacts.size() == 0) {
                Toast.makeText(this, "You Don't Have Any SurveyList", Toast.LENGTH_SHORT).show();
            } else {
//                for (Contact cn : contacts) {
// }
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//
//                    new ExportDatabaseCSVTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//
//                } else {

                new ExportDatabaseCSVTask().execute();
//                }
            }


        } else {
            Toast.makeText(this, "Please Connect WIFI", Toast.LENGTH_SHORT).show();
        }
    }

    public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> {

        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        DatabaseHelper dbhelper;

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
            dbhelper = new DatabaseHelper(MainActivity.this);
        }

        protected Boolean doInBackground(final String... args) {

            File exportDir = new File(Environment.getExternalStorageDirectory(), "/survey/");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, "person.csv");
            try {
                file.createNewFile();
                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                Cursor curCSV = dbhelper.raw();
                csvWrite.writeNext(curCSV.getColumnNames());
                while (curCSV.moveToNext()) {
                    String arrStr[] = null;
                    String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
                    for (int i = 0; i < curCSV.getColumnNames().length; i++) {
                        mySecondStringArray[i] = curCSV.getString(i);
                    }
                    csvWrite.writeNext(mySecondStringArray);
                }
                csvWrite.close();
                curCSV.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(MainActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();

                try {
                    File exportDir = new File(Environment.getExternalStorageDirectory(), "/survey/");
                    String fileName = "person.csv";

                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());

                    File sharingGifFile = new File(exportDir, fileName);
                    Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("application/csv");
                    Uri uri = Uri.fromFile(sharingGifFile);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(shareIntent, "Share CSV"));
                } catch (Exception e) {
                    Log.e("Mainactivity file", e.getMessage().toString());
                }

                // ShareGif();
            } else {
                Toast.makeText(MainActivity.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void logOut() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        String log = getResources().getString(R.string.Logoutapp);
        alertDialog.setTitle(log);
        String exit = getResources().getString(R.string.closethisapplication);
        alertDialog.setMessage(exit);
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //dialog.cancel();
            }
        });
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            editor.putString(Config.mobile, "");
                            editor.putString(Config.username, "");
                            editor.putString(Config.date1, "");
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                        } catch (Exception ex) {
                            Log.e("ex", ex + "");
                        }


                    }
                }
        );
        // Showing Alert Message
        alertDialog.show();
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
