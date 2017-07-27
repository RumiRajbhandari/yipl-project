package com.example.root.yiplchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.root.yiplchallenge.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/27/17.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "yipl";
    private static final String TABLE_USERS = "users";
    private static final String KEY_USERID="userId";
    private static final String KEY_ID="_id";
    private static final String KEY_TITLE="title";
    private static final String KEY_BODY="body";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE= "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERID + " INTEGER," + KEY_ID + " INTEGER,"+ KEY_TITLE
                + " TEXT," + KEY_BODY + " TEXT " + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    void addUser(User user){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_USERID,user.getUserId());
        values.put(KEY_ID,user.getId());
        values.put(KEY_TITLE,user.getTitle());
        values.put(KEY_BODY,user.getBody());
        db.insert(TABLE_USERS,null,values);
        db.close();
    }

   public List<User> getAllUser(){
       List<User> userList=new ArrayList<User>();
       String selectQuery="SELECT * FROM "+TABLE_USERS;
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor cursor=db.rawQuery(selectQuery,null);

       if (cursor.moveToFirst()){
           do{
               User user=new User();
               user.setUserId(Integer.parseInt(cursor.getString(0)));
               user.setId(Integer.parseInt(cursor.getString(1)));
               user.setTitle(cursor.getString(2));
               user.setBody(cursor.getString(3));
               userList.add(user);
           }while (cursor.moveToNext());

       }
       return userList;
   }
}
