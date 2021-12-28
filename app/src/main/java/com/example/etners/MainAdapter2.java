package com.example.etners;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.CONNECTIVITY_DIAGNOSTICS_SERVICE;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.provider.UserDictionary.Words.APP_ID;
import static android.provider.UserDictionary.Words._ID;

public class MainAdapter2 extends RecyclerView.Adapter<MainAdapter2.MyViewHolder2> {


    int pos2;
    Context context;
    Context context2;

    TextView tv_time;
    EditText et_work;

    int hour;
    int minute;
    int count;
    String total;
    String hour2;
    String minute2;


    AlertDialog custumDialog;
    TimePickerDialog timePickerDialog;
    AlarmManager alarmManager;



    Timememo timememo;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;




    public MainAdapter2(ArrayList<Timememo> arrayList3, Context c) {
        MainActivity.arrayList3 = arrayList3;
        context = c;
    }




    @NonNull
    @Override
    public MainAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context2 = parent.getContext();     //어탭터에서 본인의 객체를 얻기 위한 방법.

        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        MyViewHolder2 myViewHolder2 = new MyViewHolder2(view2);

        return myViewHolder2;
    }



    DialogInterface.OnClickListener dialogListner = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int i) {
            if (dialog==custumDialog && i == DialogInterface.BUTTON_POSITIVE) {

                MainActivity.arrayList3.get(pos2).setMemo(et_work.getText().toString());
                MainActivity.arrayList3.get(pos2).setTime(tv_time.getText().toString());
                notifyDataSetChanged();


//알람매니저 등록 및 실행구문.
                alarmManager =(AlarmManager)context.getSystemService(ALARM_SERVICE);

                Calendar calendar = Calendar.getInstance();
                hour2=String.valueOf(hour);
                minute2=String.valueOf(minute);

                calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour2));
                calendar.set(Calendar.MINUTE,Integer.parseInt(minute2));
                calendar.set(Calendar.SECOND,0);

                Intent intent = new Intent(context,MyReceiver.class);
                intent.putExtra("time",tv_time.getText().toString());
                intent.putExtra("memo",et_work.getText().toString());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,count++,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);
                else
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                dbHelper = new DBHelper(context2);
                sqLiteDatabase = dbHelper.getWritableDatabase();
//알람매니저 구문 끝.


                ContentValues contentValues = new ContentValues();
                contentValues.put("time",tv_time.getText().toString());
                contentValues.put("memo",et_work.getText().toString());


                sqLiteDatabase.update("timememo",contentValues,"_id=?",
                        new String[]{ String.valueOf(MainActivity.arrayList3.get(pos2).getId())});


            }else if(dialog==custumDialog && DialogInterface.BUTTON_NEGATIVE==i) {



                dbHelper= new DBHelper(context2);
                sqLiteDatabase = dbHelper.getReadableDatabase();

                //삭제 시키려면 어레이리스트나 어탭터에 있는 값들로는 수시로 변하는 변수값들일뿐.
                //데이터 삭제 시키려면 데이터에 저장되 있는 속성이나 독립적인 키넘버를 지정해줘서 그 넘버에 속해 있는 값들을 삭제해야함.
                //cusor 구문 이용하면 timememo테이블의 _id속성의 _id값들을 일단 조회.
//                Cursor cursor = sqLiteDatabase.rawQuery("select _id from timememo order by _id ", null);  //select 선택 time,memo 속성, from  테이블속성

                //while구문으로 cusor를 줄옮김함 (로우)라고 보면됨.

                timememo = new Timememo(); //timememo 객체 생성하여

                sqLiteDatabase.delete("timememo", "_id=?", new String[]{String.valueOf(MainActivity.arrayList3.get(pos2).getId())});    //스태틱 어레이 리스트의 포지션 값을 선택한, 데이터 id 로우 값

                // 1째 인자 테이블 이름, 2번째 인자 행을 선택하는 구분자, 3번째 인자는 2번째 인자의 값으로 들어가야함.



                MainActivity.arrayList3.remove(pos2);
                notifyDataSetChanged();




                    alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(context, MyReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,pos2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.cancel(pendingIntent);
                            pendingIntent.cancel();

            }

        }

    };



    @Override
    public void onBindViewHolder(@NonNull MainAdapter2.MyViewHolder2 holder, int position) {



        holder.Time.setText(MainActivity.arrayList3.get(position).getTime());
        holder.Memo.setText(MainActivity.arrayList3.get(position).getMemo());





        holder.timework.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);

                View view1 = inflater.inflate(R.layout.activity_addtimework,null);



                pos2=position;
                et_work=view1.findViewById(R.id.et_work);
                tv_time=view1.findViewById(R.id.tv_time);

                tv_time.setText(MainActivity.arrayList3.get(position).getTime());




                tv_time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        timePickerDialog = new TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                                String hour2;
                                String minute2;

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

                                total=hour2+":"+minute2+":";

                                tv_time.setText(total);

                            }
                        },hour,minute,true);

                        timePickerDialog.setTitle("시간 변경");
                        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        timePickerDialog.show();


                    }
                });




                et_work.setText(MainActivity.arrayList3.get(position).getMemo());

                builder.setView(view1);
                builder.setTitle("수정할 내용을 입력해주세요");
                builder.setPositiveButton("저장",dialogListner);
                builder.setNegativeButton("삭제",dialogListner);

                custumDialog=builder.create();
                custumDialog.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return MainActivity.arrayList3!=null? MainActivity.arrayList3.size():0 ;
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView Time;
        TextView Memo;
        LinearLayout timework;



        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            Time= itemView.findViewById(R.id.item2_time);
            Memo= itemView.findViewById(R.id.item2_work);
            timework=itemView.findViewById(R.id.timework);





        }





    }
}
