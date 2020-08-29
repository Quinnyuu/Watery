package com.example.watery.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.watery.utils.User;

public class UserService {
    private DatabaseHelper databaseHelper;
    public UserService(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    //登录判断
    public boolean login(String username,String password){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from user where username = ? and password = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{username,password});
        if(cursor.moveToNext() == true){
            cursor.close();
            return true;
        }
        return false;
    }

    //注册
    public boolean register(User user){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "insert into user(username,password) values(?,?)";
        Object obj[] = {user.getUsername(),user.getPassword()};
        db.execSQL(sql,obj);
        return true;
    }

    //按用户的体重
    public int getUserName(String name) {
        int weight = 0;
        String str[] = {name};
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sql = "select * from user where username = ?";
        Cursor cursor = db.rawQuery(sql,str);
        while (cursor.moveToNext()){
            weight = Integer.parseInt(cursor.getString(cursor.getColumnIndex("weight")));
        }
        return weight;
    }

    //修改用户的体重
    public void changeWeight(String name,int weight) {
        String str[] = {name};
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("weight",weight);
        db.update("user",values,"username = ?",str);
    }

}
