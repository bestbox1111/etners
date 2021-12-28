package com.example.etners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyviewHolder> {

    ArrayList<Data> arrayList;
    RecyclerView recyclerView;
    Context context;
    int pos;


    public MainAdapter(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public MainAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyviewHolder holder, int position) {

        pos=position;


        holder.building.setText(arrayList.get(position).getBuilding());
        holder.location.setText(arrayList.get(position).getLocation());
        holder.floor.setText(arrayList.get(position).getFloor());
        holder.manage.setText(arrayList.get(position).getDepart());
        holder.name.setText(arrayList.get(position).getName());
        holder.cha.setText(arrayList.get(position).getCha());


    }

    @Override
    public int getItemCount() {

        return arrayList != null? arrayList.size():0 ;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

            TextView building;
            TextView location;
            TextView floor;
            TextView manage;
            TextView name;
            TextView cha;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);



            building= itemView.findViewById(R.id.A);
            location= itemView.findViewById(R.id.B);
            floor= itemView.findViewById(R.id.C);
            manage= itemView.findViewById(R.id.D);
            name= itemView.findViewById(R.id.E);
            cha= itemView.findViewById(R.id.F);


        }
    }
}
