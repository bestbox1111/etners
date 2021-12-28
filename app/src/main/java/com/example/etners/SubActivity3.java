package com.example.etners;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SubActivity3 extends AppCompatActivity implements View.OnClickListener {

    MainAdapter3 mainAdapter3;
    LinearLayoutManager linearLayoutManager3;
    RecyclerView recyclerView3;
    Context context3;


    AlertDialog custumDialogs3;
    AlertDialog custumDialogs4;



    Data4 data4;


    Button btn_save3, btn_search3, btn_del3;

    EditText et_no1, et_no2, et_no3, et_no4, et_no5, et_no6, et_no7, et_no8;

    EditText search_no1, search_no2, search_no3;

    String string_no1, string_no2, string_no3, string_no4, string_no5, string_no6, string_no7, string_no8;
    String string_search1, string_search2, string_search3;

    int count;


    TextView textbig;


    DBHelper2 dbHelper3;
    SQLiteDatabase sqLiteDatabase3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);


        context3 = SubActivity3.this;

        MainActivity.dataarrayList3 = new ArrayList<>();

        recyclerView3 = findViewById(R.id.recyclerview3);

        linearLayoutManager3 = new LinearLayoutManager(SubActivity3.this);
        recyclerView3.setLayoutManager(linearLayoutManager3);


        mainAdapter3 = new MainAdapter3(MainActivity.dataarrayList3);
        recyclerView3.setAdapter(mainAdapter3);


        btn_save3 = findViewById(R.id.btn_save3);
        btn_search3 = findViewById(R.id.btn_search3);
        btn_del3 = findViewById(R.id.btn_del3);


        btn_save3.setOnClickListener(this);
        btn_search3.setOnClickListener(this);
        btn_del3.setOnClickListener(this);


        textbig = findViewById(R.id.textbig);
        //데이터관련
        //db 생성 및 오픈 관련 구문
        dbHelper3 = new DBHelper2(this);
        sqLiteDatabase3 = dbHelper3.getWritableDatabase();
        sqLiteDatabase3 = openOrCreateDatabase("chenandata", MODE_PRIVATE, null);


//        MainActivity.dataarrayList3= new ArrayList<>();
    }


    DialogInterface.OnClickListener dialogListners = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int i) {
            if (dialog == custumDialogs3 && i == DialogInterface.BUTTON_POSITIVE) {

                count++;

                string_no1 = et_no1.getText().toString();
                string_no2 = et_no2.getText().toString();
                string_no3 = et_no3.getText().toString();
                string_no4 = et_no4.getText().toString();
                string_no5 = et_no5.getText().toString();
                string_no6 = et_no6.getText().toString();
                string_no7 = et_no7.getText().toString();
                string_no8 = et_no8.getText().toString();

                data4 = new Data4(string_no1, string_no2, string_no3, string_no4, string_no5, string_no6, string_no7, string_no8);
                //데이터 삽입 구문
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("location", string_no1);
                contentValues2.put("building",string_no2);
                contentValues2.put("floor", string_no3);
                contentValues2.put("name", string_no4);
                contentValues2.put("depart", string_no5);
                contentValues2.put("number", string_no6);
                contentValues2.put("workplace", string_no7);
                contentValues2.put("workmanage", string_no8);
                sqLiteDatabase3.insert("cheonantable", null, contentValues2);
                //데이터 삽입구문 종료

                MainActivity.dataarrayList3.add(data4);
                mainAdapter3.notifyDataSetChanged();


            } else if (dialog == custumDialogs4 && i == DialogInterface.BUTTON_POSITIVE) {


                string_search1 = search_no1.getText().toString();
                string_search2 = search_no2.getText().toString();
                string_search3 = search_no3.getText().toString();

                dbHelper3 = new DBHelper2(context3);
                sqLiteDatabase3 = dbHelper3.getReadableDatabase();  //sqLiteDatabase 를 실행시작.
                Cursor cursor2 = sqLiteDatabase3.rawQuery("select _id, location, building, floor, name, depart, number, workplace, workmanage from cheonantable order by _id ", null);  //select 선택 time,memo 속성, from  테이이름
                MainActivity.dataarrayList3 = new ArrayList<>();

                while (cursor2.moveToNext()) {
                    data4 = new Data4();
                    data4.setId(cursor2.getInt(0));
                    data4.setLocation(cursor2.getString(1));
                    data4.setBuilding(cursor2.getString(2));
                    data4.setFloor(cursor2.getString(3));
                    data4.setName(cursor2.getString(4));
                    data4.setDepart(cursor2.getString(5));
                    data4.setNumber(cursor2.getString(6));
                    data4.setWorkplace(cursor2.getString(7));
                    data4.setWorkmanage(cursor2.getString(8));
                    if (data4.getLocation().equals(search_no1.getText().toString()) &&
                            data4.getName().equals(search_no2.getText().toString()) &&
                            data4.getNumber().equals(search_no3.getText().toString())) {

                        MainActivity.dataarrayList3.add(data4);


                    } else if (data4.getLocation().equals("") && data4.getName().equals(search_no2.getText().toString()) &&
                            data4.getNumber().equals(search_no3.getText().toString())) {
                        search_no1.setText("빈칸에 지역을 입력해주세요");
                    } else if (data4.getLocation().equals(search_no1.getText().toString()) &&  data4.getName().equals("") &&  data4.getNumber().equals(search_no3.getText().toString())) {
                        search_no2.setText("빈칸에 이름을 입력해 주세요");
                    } else if (data4.getLocation().equals(search_no1.getText().toString()) &&
                            data4.getName().equals(search_no2.getText().toString()) &&  data4.getNumber().equals("")) {
                        search_no3.setText("빈칸에 번호를 입력해 주세요");
                    } else {
                        textbig.setText("정보를 정확하게 입력해 주세요");
                    }

                }


            } else {

            }

        }
    };


    @Override
    public void onClick(View view) {


        if (view == btn_save3) {
            AlertDialog.Builder cheonan = new AlertDialog.Builder(SubActivity3.this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            View park = inflater.inflate(R.layout.chonaninput, null);

            et_no1 = park.findViewById(R.id.et_no1);
            et_no2 = park.findViewById(R.id.et_no2);
            et_no3 = park.findViewById(R.id.et_no3);
            et_no4 = park.findViewById(R.id.et_no4);
            et_no5 = park.findViewById(R.id.et_no5);
            et_no6 = park.findViewById(R.id.et_no6);
            et_no7 = park.findViewById(R.id.et_no7);
            et_no8 = park.findViewById(R.id.et_no8);

            cheonan.setView(park);
            cheonan.setPositiveButton("저장", dialogListners);
            cheonan.setNegativeButton("취소", null);

            custumDialogs3 = cheonan.create();
            custumDialogs3 = cheonan.show();
        } else if (view == btn_search3) {


            AlertDialog.Builder cheonansearch = new AlertDialog.Builder(SubActivity3.this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            View park2 = inflater.inflate(R.layout.chonansearch, null);

            search_no1 = park2.findViewById(R.id.search_no1);
            search_no2 = park2.findViewById(R.id.search_no2);
            search_no3 = park2.findViewById(R.id.search_no3);


            cheonansearch.setView(park2);
            cheonansearch.setPositiveButton("저장", dialogListners);
            cheonansearch.setNegativeButton("취소", null);

            custumDialogs4=cheonansearch.create();
            custumDialogs4=cheonansearch.show();


        } else {

            //데이터 지우기 위한구문 - 선택한 구문이 아닌 저장되 있던 모든 데이터 삭제 - 싹다 갈아엎어주세요
            sqLiteDatabase3.delete("cheonantable", null, null);     //timememo라는 테이블 전부를 삭제합니다.
            sqLiteDatabase3.close();     //데이터 베이스를 닫습니다.
            MainActivity.dataarrayList3.clear();    //리싸이클러뷰에 표현되 있던(어탭터의 내용들) 껕데기들 클리어(지움)
            mainAdapter3.notifyDataSetChanged();    //메인어탭터에 내용 변경됐으니 사용해줘야함.
        }

    }
}





