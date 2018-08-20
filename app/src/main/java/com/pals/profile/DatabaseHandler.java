package com.pals.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_ADDRESS = "user_address";
    public static final String COLUMN_USER_LISENCE = "user_lisence";
    public static final String COLUMN_USER_IMAGE_PATH = "user_image_path";
    public static final String COLUMN_USER_GENDER = "user_gender";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_ADDRESS + " TEXT, " +  COLUMN_USER_LISENCE + " TEXT, " +
                COLUMN_USER_GENDER + " TEXT, " + COLUMN_USER_IMAGE_PATH + " BLOB " + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public void addUser(Users user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getUser_name());
        values.put(COLUMN_USER_ADDRESS, user.getUser_address());
        values.put(COLUMN_USER_LISENCE, user.getUser_license());
        values.put(COLUMN_USER_IMAGE_PATH, user.getUser_image_path());
        values.put(COLUMN_USER_GENDER, user.getUser_gender());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void deleteUser(String userId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER + " WHERE " + COLUMN_ID + "=\"" + userId + "\";");
    }

    public String databasetoString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("user_name"))!=null){
                dbString += c.getString(c.getColumnIndex("user_id"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_name"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_address"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_lisence"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_image_path"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("user_gender"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
