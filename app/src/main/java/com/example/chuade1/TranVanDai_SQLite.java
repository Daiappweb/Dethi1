package com.example.chuade1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TranVanDai_SQLite extends SQLiteOpenHelper {
    public static final String TableName = "Contact_TranVanDai";
    public static final String Id = "Id";
    public static final String Name = "Name";
    public static final String Phone = "Phone";
    public TranVanDai_SQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table if not exists "+TableName+"( " +
                Id+" integer primary key, "+Name+" text,"+Phone+" text)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table  if exists " +TableName);
        onCreate(db);
    }

    public void addInfo(person info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name,info.getName());
        values.put(Phone,info.getPhone());
        db.insert(TableName,null,values);
        db.close();
    }
    public  void updateInfo(person info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id,info.getId());
        values.put(Name,info.getName());
        values.put(Phone,info.getPhone());
        db.update(TableName,values,Id+"=?",new String[]{String.valueOf(Id)});
        db.close();
    }
    public void deleteInfo(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "Delete from "+TableName + " where Id ="+id;
        db.execSQL(sqlDelete);
        db.close();
    }
    public ArrayList<person> getAllInfo(){
        ArrayList<person>list= new ArrayList<>();
        String sql = "Select * from "+TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                person info = new person(cursor.getInt(0),cursor.getString(1)
                        ,cursor.getString(2));
                list.add(info);
            }
        }
        return list;
    }
}
