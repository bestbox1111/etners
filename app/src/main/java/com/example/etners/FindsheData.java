package com.example.etners;

import android.widget.TextView;

public class FindsheData {

    String worker_name;
    String worker_location;
    String worker_depart;
    String worker_tel;
    String worker_place;
    String worker_manager;
    String worker_char;


    public FindsheData() {
    }

    public FindsheData(String worker_name, String worker_location,
                       String worker_depart, String worker_tel,
                       String worker_place, String worker_manager,String worker_char) {
        this.worker_name = worker_name;
        this.worker_location = worker_location;
        this.worker_depart = worker_depart;
        this.worker_tel = worker_tel;
        this.worker_place = worker_place;
        this.worker_manager = worker_manager;
        this.worker_char = worker_char;
    }


    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }

    public String getWorker_location() {
        return worker_location;
    }

    public void setWorker_location(String worker_location) {
        this.worker_location = worker_location;
    }

    public String getWorker_depart() {
        return worker_depart;
    }

    public void setWorker_depart(String worker_depart) {
        this.worker_depart = worker_depart;
    }

    public String getWorker_tel() {
        return worker_tel;
    }

    public void setWorker_tel(String worker_tel) {
        this.worker_tel = worker_tel;
    }

    public String getWorker_char() {
        return worker_char;
    }

    public void setWorker_char(String worker_char) {
        this.worker_char = worker_char;
    }

    public String getWorker_place() {
        return worker_place;
    }

    public void setWorker_place(String worker_place) {
        this.worker_place = worker_place;
    }

    public String getWorker_manager() {
        return worker_manager;
    }

    public void setWorker_manager(String worker_manager) {
        this.worker_manager = worker_manager;
    }
}
