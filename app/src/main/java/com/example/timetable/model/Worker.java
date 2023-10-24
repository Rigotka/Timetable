package com.example.timetable.model;

import androidx.annotation.DrawableRes;

public class Worker {
    @DrawableRes
    private int _image;

    private String _name;

    private boolean _nameIntroduced;

    private boolean _isWork;
    private String[] _workDays = new String[7];

    public Worker(int image, String name) {
        setImage(image);
        setName(name);
        setIsWork(true);
    }
    public void setImage(int image) {
        _image = image;
    }

    public void setName(String name) {
        _nameIntroduced = !name.equals("");
        _name = name;
    }
    public void setIsWork(boolean isWork){
        _isWork = isWork;
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

    public boolean getNameIntroduced(){
        return _nameIntroduced;
    }
    public boolean getIsWork(){
        return _isWork;
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
