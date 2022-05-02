package com.ultimate.infits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;

public class connectionHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="infitsDB";
    public connectionHelper(Context context) {
        super(context, "infitsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS dietician (uID int(5) primary key,pass TEXT,name VARCHAR," +
                "qualification VARCHAR,email VARCHAR,profilePhoto BLOB, clientid int(5))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",username);
        contentValues.put("pass",password);
        long result= sqLiteDatabase.insert("infitsDB",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUsernamePass(String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from infitsDB where username = ? and passwor = ?",new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
