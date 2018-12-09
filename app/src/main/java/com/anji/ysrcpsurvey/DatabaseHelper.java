package com.anji.ysrcpsurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anji on 5/23/2018.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version


    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "surveyANDHRA";
    private static final String DATABASE_NAME = "ysrcp";
    private static final String KEY_time = "createOn";
    private static final String KEY_sNAme = "sname";

    private static final String KEY_gender = "gender";
    private static final String KEY_age = "age";
    private static final String KEY_education = "education";
    private static final String KEY_bussiness = "bussiness";
    private static final String KEY_contactNo = "ContactNo";
    private static final String KEY_mandal = "mandal";
    private static final String KEY_village = "village";
    private static final String KEY_cast = "cast1";
    private static final String KEYQ1 = "q1";
    private static final String KEYQ2 = "q2";
    private static final String KEYQ3 = "q3";
    private static final String KEYQ4 = "q4";
    private static final String KEYQ5 = "q5";
    private static final String KEYQ6 = "q6";
    private static final String KEYQ7 = "q7";
    private static final String KEYQ8 = "q8";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_time + " TEXT,"
                + KEY_sNAme + " TEXT,"
                + KEY_gender + " TEXT,"
                + KEY_age + " TEXT,"
                + KEY_education + " TEXT,"
                + KEY_bussiness + " TEXT,"
                + KEY_mandal + " TEXT,"
                + KEY_contactNo + " TEXT,"
                + KEY_village + " TEXT,"
               // + KEY_cast + "TEXT,"
                + KEYQ1 + " TEXT,"
                + KEYQ2 + " TEXT,"
                + KEYQ3 + " TEXT,"
                + KEYQ4 + " TEXT,"
                + KEYQ5 + " TEXT,"
                + KEYQ6 + " TEXT,"
                + KEYQ7 + " TEXT,"
                + KEYQ8 + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_time, contact.getTimeon()); // Contact Name
        values.put(KEY_sNAme, contact.getSnames()); // Contact Phone
        values.put(KEY_gender, contact.getGender());
        values.put(KEY_age, contact.getS_age());
        values.put(KEY_education, contact.getS_education());
        values.put(KEY_contactNo, contact.getS_contactNo());
        values.put(KEY_bussiness, contact.getS_bussiness());
        values.put(KEY_mandal, contact.getS_mandal());
        values.put(KEY_village, contact.getS_village());
        //values.put(KEY_cast, contact.getS_cast());
        values.put(KEYQ1, contact.getQ1());
        values.put(KEYQ2, contact.getQ2());
        values.put(KEYQ3, contact.getQ3());
        values.put(KEYQ4, contact.getQ4());
        values.put(KEYQ5, contact.getQ5());
        values.put(KEYQ6, contact.getQ6());
        values.put(KEYQ7, contact.getQ7());
        values.put(KEYQ8, contact.getQ8());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // String userId = MainNotification.userId;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
//        selectQuery += " WHERE substr(";
//        selectQuery += KEY_time;
//        selectQuery += " , 1 , 8) = " + sdf.format(c1.getTime());
        //  String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + KEY_time + " LIKE '" + sdf.format(c1.getTime()) + "%' AND " + KEY_UserId + " = " + Integer.parseInt(userId) + " ORDER BY " + KEY_time + " DESC";
        // String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + KEY_time + " DESC";
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        // Log.e("sqlite", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

//                timeon, snames, gender, s_age, s_education, s_contactNo, s_bussiness, s_mandal, s_village, s_cast, q1, q2, q3, q4, q5, q6, q7, q8

                Contact contact = new Contact();
                contact.setTimeon(cursor.getString(0));
                contact.setSnames(cursor.getString(1));
                contact.setGender(cursor.getString(2));
                contact.setS_age(cursor.getString(3));
                contact.setS_education(cursor.getString(4));
                contact.setS_contactNo(cursor.getString(5));
                contact.setS_bussiness(cursor.getString(6));
                contact.setS_mandal(cursor.getString(7));
                contact.setS_village(cursor.getString(8));
               // contact.setS_cast(cursor.getString(9));
                contact.setQ1(cursor.getString(9));
                contact.setQ2(cursor.getString(10));
                contact.setQ3(cursor.getString(11));
                contact.setQ4(cursor.getString(12));
                contact.setQ5(cursor.getString(13));
                contact.setQ6(cursor.getString(14));
                contact.setQ7(cursor.getString(15));
                contact.setQ8(cursor.getString(16));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }


    public Cursor raw() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , new String[]{});

        return res;
    }
}
