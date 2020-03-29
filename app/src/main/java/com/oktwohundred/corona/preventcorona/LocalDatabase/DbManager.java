package com.oktwohundred.corona.preventcorona.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.oktwohundred.corona.preventcorona.Model.User;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.PYC_LOG;


public class DbManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "PYC_databse_localapp";
    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(User.PYCTABLE_CREATE);

        } catch (SQLException e) {
            Log.i(PYC_LOG,"SQLlite Create Error"+e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.PYC_TABLE);
            onCreate(sqLiteDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public long insertUserData(long id,String firebaseID,String username,String email,String imageUrl, String gender,String dob ,String country, String stage, String status)
    {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(User.COLUMN_ID, id);
            values.put(User.COLUMN_FIREBASE_ID, firebaseID);
            values.put(User.COLUMN_FULL_NAME, username);
            values.put(User.COLUMN_EMAIL_ADD, email);
            values.put(User.COLUMN_PROFIMAGE, imageUrl);
            values.put(User.COLUMN_COUNTRY, country);
            values.put(User.COLUMN_GENDER, gender);
            values.put(User.COLUMN_STAGE, stage);
            values.put(User.COLUMN_DOB, dob);
            values.put(User.COLUMN_STATUS, status);
            db.insert(User.PYC_TABLE, null, values);
            db.close();
            return id;
        }
        catch (Exception e){
            return -1;

        }
    }

    public User getUserIds(){
        try {
            String query = "SELECT * FROM " + User.PYC_TABLE + " LIMIT 1";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                User model = new User();
                model.setId(User.COLUMN_ID);

                return model;
            } else {
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    public void deleteAllUsers(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(User.PYC_TABLE, null, null);
        }
        catch (Exception e){
            return;
        }
    }

    public User getUserData(){
        try {
            String query = "SELECT * FROM " + User.PYC_TABLE + " LIMIT 1";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                User model = new User();
                model.setId(cursor.getString(cursor.getColumnIndex(User.COLUMN_ID)));
                model.setUserName(cursor.getString(cursor.getColumnIndex(User.COLUMN_FULL_NAME)));
                model.setUserMail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL_ADD)));
                model.setUserImage(cursor.getString(cursor.getColumnIndex(User.COLUMN_PROFIMAGE)));
                model.setFirebaseId(cursor.getString(cursor.getColumnIndex(User.COLUMN_FIREBASE_ID)));
                model.setUserDob(cursor.getString(cursor.getColumnIndex(User.COLUMN_DOB)));
                model.setUserCountry(cursor.getString(cursor.getColumnIndex(User.COLUMN_COUNTRY)));
                model.setUserGender(cursor.getString(cursor.getColumnIndex(User.COLUMN_GENDER)));
                model.setUserStage(cursor.getString(cursor.getColumnIndex(User.COLUMN_STAGE)));
                model.setUserStatus(cursor.getString(cursor.getColumnIndex(User.COLUMN_STATUS)));
                return model;
            } else {
                return null;
            }
        }
        catch (Exception e){
            Log.i(PYC_LOG,"SQLlite getUserData Error"+e.getMessage());
            return null;
        }
    }





}
