package com.example.demo;

/**
 * Created by Huangcan on 2016/11/8.
 */
public class Time {
    private  int id;
    private String name;
    private int seconds;
    private int seconds2;

    private Timer timer;
    public Time() {
        id = (int)System.currentTimeMillis();
    }
    public Time(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
        this.seconds2 = seconds;

        id = (int)System.currentTimeMillis();
    }
    public int getId(){
        return id;
    }
    public void setTimer(Timer timer){
        this.timer = timer;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean decTime(){
        if(seconds2==0)return false;
        else seconds2--;
        return true;
    }
    @Override
    public String toString() {
        return "Time{" +
                "name='" + name + '\'' +
                ", seconds=" + seconds +
                '}';
    }
}
