package com.example.timetable.model;

import androidx.annotation.DrawableRes;

public class Worker {
    @DrawableRes
    private int _image;
    private String _name;
    private String[] _workDays = new String[7];
    public Worker(int image, String name) {
        setImage(image);
        setName(name);
    }
    public void setImage(int image) {
        _image = image;
    }
    public void setName(String name) {
        _name = name;
    }
    public void setWorkDays(String[] workDays){
        _workDays = workDays;
    }
    public int getImage() {
        return _image;
    }
    public String getName() {
        return _name;
    }
    public String[] getWorkDays(){
        return _workDays;
    }

    public String getworkDaysAsString(){
        StringBuilder days = new StringBuilder();
        for (String day : _workDays) {
            if(day != null)
                days.append(day).append(" ");
        }
        if(days.length() == 0)
            return "-";

        return days.toString().trim();
    }
}
