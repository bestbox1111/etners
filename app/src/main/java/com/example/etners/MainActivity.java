package com.example.etners;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_go0;
    Button btn_go1;
    Button btn_go2;
    Button btn_go3;
    Button btn_go5;

    Button btn_A;
    Button btn_B;
    Button btn_C;



    public static ArrayList<Timememo> arrayList3;
    public static ArrayList<Data4> dataarrayList3;
    public  Context context;


    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builders2 = new AlertDialog.Builder(this);
        builders2.setIcon(R.drawable.ic_launcher_background);
        builders2.setTitle("Notice");
        builders2.setMessage("\n 온양 물류 구성멤버들의 번호는 \n 임의의 숫자로 구성함. \n 구성원들은 이미셜로 구분했음   \n ");
        builders2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builders2.create();
        builders2.show();




        btn_go0=findViewById(R.id.btn_go0);
        btn_go1=findViewById(R.id.btn_go1);
        btn_go2=findViewById(R.id.btn_go2);
        btn_go3=findViewById(R.id.btn_go3);
        btn_go5=findViewById(R.id.btn_go5);

        btn_A=findViewById(R.id.btn_A);
        btn_B=findViewById(R.id.btn_B);
        btn_C=findViewById(R.id.btn_C);


        btn_go0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m0 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
                startActivity(m0);
            }
        });



        btn_go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m1 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-2345-6789"));
                startActivity(m1);
            }
        });

        btn_go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m2 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-3456-7890"));
                startActivity(m2);
            }
        });

        btn_go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m3 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-4567-8901"));
                startActivity(m3);
            }
        });

        btn_go5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m5 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-5678-9012"));
                startActivity(m5);
            }
        });



        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                startActivity(intent);
            }
        });

        btn_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubActivity2.class);
                startActivity(intent);
            }
        });

        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubActivity3.class);
                startActivity(intent);
            }
        });






            dbHelper = new DBHelper(this);
            sqLiteDatabase = dbHelper.getReadableDatabase();  //sqLiteDatabase 를 실행시작.
            Cursor cursor = sqLiteDatabase.rawQuery("select _id, time, memo from timememo order by _id ", null);  //select 선택 time,memo 속성, from  테이이름
            arrayList3 = new ArrayList<>();



            while (cursor.moveToNext()) {
                Timememo timememo = new Timememo();
                timememo.setId(cursor.getInt(0));
                timememo.setTime(cursor.getString(1));
                timememo.setMemo(cursor.getString(2));
                arrayList3.add(timememo);
            }

    }
}