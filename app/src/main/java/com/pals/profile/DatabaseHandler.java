package com.pals.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_ADDRESS = "user_address";
    public static final String COLUMN_USER_LICENSE = "user_license";
    public static final String COLUMN_USER_GENDER = "user_gender";
    public static final String COLUMN_USER_IMAGE = "user_image";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_ADDRESS + " TEXT, " + COLUMN_USER_LICENSE + " TEXT, " +
                COLUMN_USER_GENDER + " TEXT, " + COLUMN_USER_IMAGE + " BLOB " + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public void addUser(Users user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getUserName());
        values.put(COLUMN_USER_ADDRESS, user.getUserAddress());
        values.put(COLUMN_USER_LICENSE, user.getUserLicense());
        values.put(COLUMN_USER_GENDER, user.getUserGender());
        values.put(COLUMN_USER_IMAGE, user.getUserImage());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void deleteUser(String userId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + COLUMN_ID + "=\"" + userId + "\";");
    }

    public List<String> databaseToList(){
        List<String> dbStringList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            String dbString = "";
            if(c.getString(c.getColumnIndex("user_id"))!=null){
                dbString += c.getString(c.getColumnIndex("user_id"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_name"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_address"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_license"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_gender"));
                /*dbString += " ";
                try {
                    byte[] blob = c.getBlob(c.getColumnIndex("user_image"));
                    dbString += new String(blob, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    c.moveToLast();
                }*/
                dbString += "\n";
            }
            c.moveToNext();
            dbStringList.add(dbString);
        }
        db.close();
        return dbStringList;
    }
}
