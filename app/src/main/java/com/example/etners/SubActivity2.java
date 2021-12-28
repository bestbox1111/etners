package com.example.etners;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.example.etners.DBHelper.DATABASE_VERSION;

public class SubActivity2 extends AppCompatActivity implements View.OnClickListener{


    MainAdapter2 mainAdapter2;
    LinearLayoutManager linearLayoutManager2;
    RecyclerView recyclerView2;
    Context context;

    AlertDialog custumDialog;
    AlarmManager alarmManager;

    TextView schel;
    TextView last;

    TextView tv_time;
    EditText et_work;

    Button btn_new;
    Button btn_del;

    String hour2;
    String minute2;

    static int count=-1;

    int hour;
    int minute;

    String total;
    TimePickerDialog timePickerDialog ;
    Timememo timememo;


    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);


        context=this;

        schel = findViewById(R.id.schel);
        last = findViewById(R.id.last);

        btn_new = findViewById(R.id.btn_new);
        btn_del = findViewById(R.id.btn_del);


        btn_new.setOnClickListener(this);
        btn_del.setOnClickListener(this);


        AlertDialog.Builder builders = new AlertDialog.Builder(this);
        builders.setIcon(R.drawable.ic_launcher_foreground);
        builders.setTitle("Notice");
        builders.setMessage("\n 일정이 추가되면 \n 추가된 일정 부분을 3초간 누르시면   \n 수정,삭제 할수 있습니다.");
        builders.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                schel.setText("SCHEDULE 등록");
            }
        });
        builders.create();
        builders.show();


        recyclerView2 = findViewById(R.id.recyclerview2);

        linearLayoutManager2 = new LinearLayoutManager(SubActivity2.this);
        recyclerView2.setLayoutManager(linearLayoutManager2);


        //만약 매인 어탭터의 인자가 어레이리스트와, 컨텍스트일떄
        mainAdapter2 = new MainAdapter2(MainActivity.arrayList3, context);
//        mainAdapter2= new MainAdapter2(MainActivity.arrayList3); // 인자가 어레이 리스트 하나일때 인자넣고
//        mainAdapter2.context=this;  //매인어탭터 인스턴스에서 멤버변수인 컨텍스트 사용함.
        recyclerView2.setAdapter(mainAdapter2);


        //데이터관련
        //db 생성 및 오픈 관련 구문
        dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase = openOrCreateDatabase("bigtimememo", MODE_PRIVATE, null);
        //db 오픈  종료구문
    }

    DialogInterface.OnClickListener dialogListner = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int i) {
            if (dialog==custumDialog && i == DialogInterface.BUTTON_POSITIVE) {
                count++;
                timememo = new Timememo(tv_time.getText().toString()+" :", et_work.getText().toString());

                //데이터 삽입 구문
                ContentValues contentValues = new ContentValues();
                contentValues.put("time", tv_time.getText().toString());
                contentValues.put("memo", et_work.getText().toString());
                sqLiteDatabase.insert("timememo",null,contentValues);
                //데이터 삽입구문 종료




                MainActivity.arrayList3.add(timememo);
                MainActivity.arrayList3.sort(new TimeAscending());      //어레이리스트 정렬 시키는 매소드 실행.
                mainAdapter2.notifyDataSetChanged();    //메인어탭터에 데이터가 변했다고 알려주는 메서드.- 꼭 넣어줘야함 -

                //여기서 부터는 알람 매니저 관련 -구문
                alarmManager =(AlarmManager)context.getSystemService(ALARM_SERVICE);
                Calendar calendar = Calendar.getInstance();

                hour2=String.valueOf(hour);
                minute2=String.valueOf(minute);

                calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour2));
                calendar.set(Calendar.MINUTE,Integer.parseInt(minute2));
                calendar.set(Calendar.SECOND,0);

                //만약 시간이 지났으면 다음날 같은 시간으로 알람 하는 코드1
//                if (calendar.before(Calendar.getInstance())) {
//                    calendar.add(Calendar.DATE, 1);
//                }
                    //만약 이게 안되면 2번쨰 코드도있음.


                //2번쨰 코드
                long interval = 1000*24*60*60;  //인터벨 변수는 24시간 의 롱 타입

                long  nowtime =System.currentTimeMillis();
                long  choicetume = calendar.getTimeInMillis();

                if(nowtime>choicetume){
                    choicetume+=interval;
                }
                //2번째 코드 끝에 아래 알람매니저 부분 추가있음






                    Intent intent = new Intent(context, MyReceiver.class);   //펜딩 인텐트에 넣어줄 인텐트 구문을 위해

                    intent.putExtra("time", tv_time.getText().toString());
                    intent.putExtra("memo", et_work.getText().toString());




                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, count, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    // 첫번째 인자=컨텍스트, 두번째 인자 =인트형구분자(펜팅인텐트를 구분해주기 위한것(리퀘스트코드라고 칭함), 세번째 인자=인텐트, 네번째인자=펜딩인텐트

                    //버전 별로 시간을 나타내는 매소드가 다르다. 부정확할수 있으므로  버전별로 시간나타내는 매소드를 다르게 구분함.
                    // 리퀘스트 코드로 구분해줘야 하는것.
                    // 카운트0으로 초기화 해주고  - 버튼 클릭시 마다 ++해주고 requestcode에 인자로 카운트준다.ㅜㅜ

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
////                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);
//                else
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);


//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, choicetume, interval, pendingIntent); //2번쨰 코드 알람 매니저 부분





            }else{
                // 부정버튼 블라블라블라
            }

        }
    };





    @Override
    public void onClick(View view) {

        if (view == btn_new) {

            AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity2.this);
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

            View view1 = inflater.inflate(R.layout.activity_addtimework,null);

            tv_time=view1.findViewById(R.id.tv_time);
            tv_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timePickerDialog = new TimePickerDialog(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            hour = timePicker.getCurrentHour();

                            if(hour<10){
                                hour2= "0"+hour;
                            }else if(hour==12){
                                hour2= String.valueOf(hour);

                            } else {
                                hour2 = String.valueOf(hour);
                            }


                            minute=timePicker.getCurrentMinute();
                            if(minute<10){
                                minute2="0"+minute;
                            }else{
                                minute2=String.valueOf(minute);
                            }

                            total=hour2+":"+minute2;

                            tv_time.setText(total);

                        }
                    },hour,minute,true);

                    timePickerDialog.setTitle("시간 설정");
                    timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);    //타임다이얼로그를 스피너모드의 다이얼로그로 쓰기위함.
                    timePickerDialog.show();


                }
            });


            et_work=view1.findViewById(R.id.et_work);



            builder.setView(view1);
            builder.setPositiveButton("저장",dialogListner);
            builder.setNegativeButton("취소",null);


            custumDialog=builder.create();
            custumDialog.show();




        }else if (view == btn_del) {


            //데이터 지우기 위한구문 - 선택한 구문이 아닌 저장되 있던 모든 데이터 삭제 - 싹다 갈아엎어주세요


//            sqLiteDatabase.delete("timememo",null,null);     // timememo라는 테이블까지 삭제합니다.
//            sqLiteDatabase.close();     //데이터 베이스를 닫습니다.


            String sql3 = "DELETE FROM timememo;";   //timememo라는 테이블의 내용물만 삭제합니다.
            sqLiteDatabase.execSQL(sql3);


            MainActivity.arrayList3.clear();    //리싸이클러뷰에 표현되 있던(어탭터의 내용들) 껕데기들 클리어(지움)
            mainAdapter2.notifyDataSetChanged();    //메인어탭터에 내용 변경됐으니 사용해줘야함.

                //알람매니져 쓰기 위한 구문
                alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, MyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,SubActivity2.count, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);
                pendingIntent.cancel();



        }else{



        }


    }



}