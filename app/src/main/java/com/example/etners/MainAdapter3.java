package com.example.etners;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MainAdapter3 extends RecyclerView.Adapter<MainAdapter3.MyViewHolder3> {


    int pos3;
    Context context;
    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    AlertDialog custumDialog3;
    EditText et2_no1,et2_no2,et2_no3,et2_no4,et2_no5,et2_no6,et2_no7,et2_no8;
    Data data;


    public MainAdapter3(ArrayList<Data4> arrayList4) {
        MainActivity.dataarrayList3 = arrayList4;
    }


    @NonNull
    @Override
    public MainAdapter3.MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.chonanoutput,parent,false);
        MyViewHolder3 myViewHolder3 = new MyViewHolder3(view3);

        return myViewHolder3;
    }

    DialogInterface.OnClickListener dialogListner = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int i) {
            if (dialog==custumDialog3 && i == DialogInterface.BUTTON_POSITIVE) {

                MainActivity.dataarrayList3.get(pos3).setLocation(et2_no1.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setBuilding(et2_no2.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setFloor(et2_no3.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setName(et2_no4.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setDepart(et2_no5.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setNumber(et2_no6.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setWorkplace(et2_no7.getText().toString());
                MainActivity.dataarrayList3.get(pos3).setWorkmanage(et2_no8.getText().toString());
                notifyDataSetChanged();

                dbHelper = new DBHelper(context);
                sqLiteDatabase = dbHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put("location",et2_no1.getText().toString());
                contentValues.put("building",et2_no2.getText().toString());
                contentValues.put("floor",et2_no3.getText().toString());
                contentValues.put("name",et2_no4.getText().toString());
                contentValues.put("depart",et2_no5.getText().toString());
                contentValues.put("number",et2_no6.getText().toString());
                contentValues.put("workplace",et2_no7.getText().toString());
                contentValues.put("workmanage",et2_no8.getText().toString());

                sqLiteDatabase.update("cheonantable",contentValues,"_id=?",
                        new String[]{ String.valueOf(MainActivity.dataarrayList3.get(pos3).getId())});

            }else if(dialog==custumDialog3 && DialogInterface.BUTTON_NEGATIVE==i) {

                dbHelper= new DBHelper(context);
                sqLiteDatabase = dbHelper.getReadableDatabase();

                //?????? ???????????? ????????????????????? ???????????? ?????? ???????????? ????????? ????????? ??????????????????.
                //????????? ?????? ???????????? ???????????? ????????? ?????? ???????????? ???????????? ???????????? ??????????????? ??? ????????? ?????? ?????? ????????? ???????????????.
                //cusor ?????? ???????????? timememo???????????? _id????????? _id????????? ?????? ??????.
//                Cursor cursor = sqLiteDatabase.rawQuery("select _id from timememo order by _id ", null);  //select ?????? time,memo ??????, from  ???????????????

                //while???????????? cusor??? ???????????? (??????)?????? ?????????.

                data = new Data(); //timememo ?????? ????????????
                sqLiteDatabase.delete("cheonantable", "_id=?", new String[]{String.valueOf(MainActivity.dataarrayList3.get(pos3).getId())});    //????????? ????????? ???????????? ????????? ?????? ?????????, ????????? id ?????? ???

                // 1??? ?????? ????????? ??????, 2?????? ?????? ?????? ???????????? ?????????, 3?????? ????????? 2?????? ????????? ????????? ???????????????.

                MainActivity.dataarrayList3.remove(pos3);
                notifyDataSetChanged();

            }
        }
    };



    @Override
    public void onBindViewHolder(@NonNull MainAdapter3.MyViewHolder3 holder, int position) {

        pos3 = position;


        holder.Tx_no1.setText(MainActivity.dataarrayList3.get(position).getLocation());
        holder.Tx_no2.setText(MainActivity.dataarrayList3.get(position).getBuilding());
        holder.Tx_no3.setText(MainActivity.dataarrayList3.get(position).getFloor());
        holder.Tx_no4.setText(MainActivity.dataarrayList3.get(position).getName());
        holder.Tx_no5.setText(MainActivity.dataarrayList3.get(position).getDepart());
        holder.Tx_no6.setText(MainActivity.dataarrayList3.get(position).getNumber());
        holder.Tx_no7.setText(MainActivity.dataarrayList3.get(position).getWorkplace());
        holder.Tx_no8.setText(MainActivity.dataarrayList3.get(position).getWorkmanage());




    }

    @Override
    public int getItemCount() {
        return MainActivity.dataarrayList3!= null? MainActivity.dataarrayList3.size():0;
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView Tx_no1;
        TextView Tx_no2;
        TextView Tx_no3;
        TextView Tx_no4;
        TextView Tx_no5;
        TextView Tx_no6;
        TextView Tx_no7;
        TextView Tx_no8;
        LinearLayout linearout;

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
            Tx_no1=itemView.findViewById(R.id.no1);
            Tx_no2=itemView.findViewById(R.id.no2);
            Tx_no3=itemView.findViewById(R.id.no3);
            Tx_no4=itemView.findViewById(R.id.no4);
            Tx_no5=itemView.findViewById(R.id.no5);
            Tx_no6=itemView.findViewById(R.id.no6);
            Tx_no7=itemView.findViewById(R.id.no7);
            Tx_no8=itemView.findViewById(R.id.no8);
            linearout=itemView.findViewById(R.id.linearout);

            linearout.setOnLongClickListener(this);


        }

        @Override
        public boolean onLongClick(View view) {

            AlertDialog.Builder chonanmodify = new AlertDialog.Builder(context);
            LayoutInflater inflater2 = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view3 =inflater2.inflate(R.layout.chonaninput,null);

            et2_no1=view3.findViewById(R.id.et_no1);
            et2_no2=view3.findViewById(R.id.et_no2);
            et2_no3=view3.findViewById(R.id.et_no3);
            et2_no4=view3.findViewById(R.id.et_no4);
            et2_no5=view3.findViewById(R.id.et_no5);
            et2_no6=view3.findViewById(R.id.et_no6);
            et2_no7=view3.findViewById(R.id.et_no7);
            et2_no8=view3.findViewById(R.id.et_no8);

            et2_no1.setText(MainActivity.dataarrayList3.get(pos3).getLocation());
            et2_no2.setText(MainActivity.dataarrayList3.get(pos3).getBuilding());
            et2_no3.setText(MainActivity.dataarrayList3.get(pos3).getFloor());
            et2_no4.setText(MainActivity.dataarrayList3.get(pos3).getName());
            et2_no5.setText(MainActivity.dataarrayList3.get(pos3).getDepart());
            et2_no6.setText(MainActivity.dataarrayList3.get(pos3).getNumber());
            et2_no7.setText(MainActivity.dataarrayList3.get(pos3).getWorkplace());
            et2_no8.setText(MainActivity.dataarrayList3.get(pos3).getWorkmanage());

            chonanmodify.setView(view3);
            chonanmodify.setTitle("????????? ????????? ??????????????????");
            chonanmodify.setPositiveButton("??????",dialogListner);
            chonanmodify.setNegativeButton("??????",dialogListner);

            custumDialog3=chonanmodify.create();
            custumDialog3.show();

            return false;
        }
    }
}
