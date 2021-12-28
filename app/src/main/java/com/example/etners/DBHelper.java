package com.example.etners;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION =1;



    public DBHelper(Context context){
        //원래 @Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version
        //4개짜리 생성자인대 하나짜리로 줄여서 만듬.

        super(context, "bigtimememo",null,DATABASE_VERSION);
//        super(context, "timememo",null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String timememoSQL ="create table if not exists timememo" +    //bigtimememo 디비이름  , timememo 테이블이름
                "(_id integer primary key autoincrement," +         //_id 와
                "time TEXT NOT NULL," +         //time과
                "memo TEXT NOT NULL)";          //memo를 속성으로 가지고있음

        db.execSQL(timememoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        if(i1==DATABASE_VERSION){
            db.execSQL("drop table timememo");
            onCreate(db);
        }
    }

}
