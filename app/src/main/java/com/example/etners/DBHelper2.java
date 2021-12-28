package com.example.etners;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;

    public DBHelper2(@Nullable Context context) {
        super(context, "chenandata", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db3) {
        String chonanSQL ="create table if not exists cheonantable" +    //cheonandata 디비이름  , cheonantable 테이블이름
                "(_id integer primary key autoincrement," +         //_id 와
                "location TEXT NOT NULL," +
                "building TEXT NOT NULL," +
                "floor TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "depart TEXT NOT NULL," +
                "number TEXT NOT NULL," +
                "workplace TEXT NOT NULL," +
                "workmanage TEXT NOT NULL)";

        db3.execSQL(chonanSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db3, int i, int i1) {
        if(i1==DATABASE_VERSION){
            db3.execSQL("drop table cheonantable");
            onCreate(db3);
        }
    }
}
