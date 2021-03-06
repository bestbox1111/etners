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

        context2 = parent.getContext();     //??????????????? ????????? ????????? ?????? ?????? ??????.

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


//??????????????? ?????? ??? ????????????.
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
//??????????????? ?????? ???.


                ContentValues contentValues = new ContentValues();
                contentValues.put("time",tv_time.getText().toString());
                contentValues.put("memo",et_work.getText().toString());


                sqLiteDatabase.update("timememo",contentValues,"_id=?",
                        new String[]{ String.valueOf(MainActivity.arrayList3.get(pos2).getId())});


            }else if(dialog==custumDialog && DialogInterface.BUTTON_NEGATIVE==i) {



                dbHelper= new DBHelper(context2);
                sqLiteDatabase = dbHelper.getReadableDatabase();

                //?????? ???????????? ????????????????????? ???????????? ?????? ???????????? ????????? ????????? ??????????????????.
                //????????? ?????? ???????????? ???????????? ????????? ?????? ???????????? ???????????? ???????????? ??????????????? ??? ????????? ?????? ?????? ????????? ???????????????.
                //cusor ?????? ???????????? timememo???????????? _id????????? _id????????? ?????? ??????.
//                Cursor cursor = sqLiteDatabase.rawQuery("select _id from timememo order by _id ", null);  //select ?????? time,memo ??????, from  ???????????????

                //while???????????? cusor??? ???????????? (??????)?????? ?????????.

                timememo = new Timememo(); //timememo ?????? ????????????

                sqLiteDatabase.delete("timememo", "_id=?", new String[]{String.valueOf(MainActivity.arrayList3.get(pos2).getId())});    //????????? ????????? ???????????? ????????? ?????? ?????????, ????????? id ?????? ???

                // 1??? ?????? ????????? ??????, 2?????? ?????? ?????? ???????????? ?????????, 3?????? ????????? 2?????? ????????? ????????? ???????????????.



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

                        timePickerDialog.setTitle("?????? ??????");
                        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        timePickerDialog.show();


                    }
                });




                et_work.setText(MainActivity.arrayList3.get(position).getMemo());

                builder.setView(view1);
                builder.setTitle("????????? ????????? ??????????????????");
                builder.setPositiveButton("??????",dialogListner);
                builder.setNegativeButton("??????",dialogListner);

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
