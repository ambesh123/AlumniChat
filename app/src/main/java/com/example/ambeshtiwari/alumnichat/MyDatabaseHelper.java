package com.example.ambeshtiwari.alumnichat;

/**
 * Created by Ambesh Tiwari on 11-02-2018.
 */
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATBASE_VERSION = 1;
    private static final String DATABASE_NAME ="contacts.db";
    public static final String TABLE="Contacts";
    public static final String COLUMN_ID ="_id";
    public static final String CONTACT_NAME="contact_name";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACT_NAME + " TEXT " + ");";
        db.execSQL(query);
    }
    public void addContact(String contact){
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME,contact);
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE,null,values);
        db.close();
    }

    public String getContactAt(int index){
        String dbstring="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE ;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        c.move(index);
        if(!c.isAfterLast()){
            dbstring += c.getString(c.getColumnIndex(CONTACT_NAME));
        }
        else dbstring= null;
        db.close();
        return dbstring;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
