package com.example.etners;

import android.content.Context;

import java.util.Comparator;

public class Timememo {

    String time ;
    String memo;
    int id;



    public Timememo() {
    }

    public Timememo(String time, String memo) {
        this.time = time;
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }



//    @Override
//    public int compare(Timememo t, Timememo m) {          아래거랑 동일한 코드.
//        return Integer.compare(Integer.parseInt(t.time), Integer.parseInt(m.time));



}
