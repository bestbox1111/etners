package com.example.etners;

public class Data {

    String building;
    String location;
    String floor;
    String depart;
    String name;
    String cha;

    public Data() {
    }

    public Data(String building, String location, String floor, String depart, String name, String cha) {
        this.building = building;
        this.location = location;
        this.floor = floor;
        this.depart = depart;
        this.name = name;
        this.cha = cha;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCha() {
        return cha;
    }

    public void setCha(String cha) {
        this.cha = cha;
    }
}
