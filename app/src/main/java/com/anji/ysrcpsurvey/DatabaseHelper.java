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
    public static final String TABLE_NAME = "survey";
    private static final String DATABASE_NAME = "ysrcp";
    private static final String KEY_time = "createOn";
    private static final String KEY_sNAme = "sname";
    private static final String KEY_sNumber = "number";
    private static final String KEY_NAME = "name";
    private static final String KEY_gender = "gender";
    private static final String KEY_age = "age";
    private static final String KEY_address = "address";
    private static final String KEY_village = "village";
    private static final String KEY_assembly = "assembly";
    private static final String KEY_district = "district";
    private static final String KEY_tx1 = "tx1";
    private static final String KEY_tx2 = "tx2";
    private static final String KEY_tx3 = "tx3";
    private static final String KEYQ1 = "q1";
    private static final String KEYQ2 = "q2";
    private static final String KEYQ3 = "q3";
    private static final String KEYQ4 = "q4";
    private static final String KEYQ5 = "q5";
    private static final String KEYQ6 = "q6";
    private static final String KEYQ7 = "q7";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_time + " TEXT,"
                + KEY_sNAme + " TEXT,"
                + KEY_sNumber + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_gender + " TEXT,"
                + KEY_age + " TEXT,"
                + KEY_address + " TEXT,"
                + KEY_village + " TEXT,"
                + KEY_assembly + " TEXT,"
                + KEY_district + " TEXT,"
                + KEY_tx1 + " TEXT,"
                + KEY_tx2 + " TEXT,"
                + KEY_tx3 + " TEXT,"
                + KEYQ1 + " INTEGER,"
                + KEYQ2 + " INTEGER,"
                + KEYQ3 + " INTEGER,"
                + KEYQ4 + " INTEGER,"
                + KEYQ5 + " INTEGER,"
                + KEYQ6 + " INTEGER"
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
        values.put(KEY_sNAme, contact.getSname()); // Contact Phone
        values.put(KEY_sNumber, contact.getSno());
        values.put(KEY_NAME, contact.getNamec());
        values.put(KEY_gender, contact.getGenderc());
        values.put(KEY_age, contact.getAgec());
        values.put(KEY_address, contact.getAddc());
        values.put(KEY_village, contact.getVill());
        values.put(KEY_assembly, contact.getAssemblyc());
        values.put(KEY_district, contact.getDistc());
        values.put(KEY_tx1, contact.getTex1());
        values.put(KEY_tx2, contact.getTex2());
        values.put(KEY_tx3, contact.getTex3());
        values.put(KEYQ1, contact.getQ1c());
        values.put(KEYQ2, contact.getQ2c());
        values.put(KEYQ3, contact.getQ3c());
        values.put(KEYQ4, contact.getQ4c());
        values.put(KEYQ5, contact.getQ5c());
        values.put(KEYQ6, contact.getQ6c());

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
                Contact contact = new Contact();
                contact.setTimeon(cursor.getString(0));
                contact.setSname(cursor.getString(1));
                contact.setSno(cursor.getString(2));
                contact.setNamec(cursor.getString(3));
                contact.setGenderc(cursor.getString(4));
                contact.setAgec(cursor.getString(5));
                contact.setAddc(cursor.getString(6));
                contact.setVill(cursor.getString(7));
                contact.setAssemblyc(cursor.getString(8));
                contact.setDistc(cursor.getString(9));
                contact.setTex1(cursor.getString(10));
                contact.setTex2(cursor.getString(11));
                contact.setTex3(cursor.getString(12));
                contact.setQ1c(cursor.getInt(13));
                contact.setQ2c(cursor.getInt(14));
                contact.setQ3c(cursor.getInt(15));
                contact.setQ4c(cursor.getInt(16));
                contact.setQ5c(cursor.getInt(17));
                contact.setQ6c(cursor.getInt(18));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
}
