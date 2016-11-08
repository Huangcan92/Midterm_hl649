package com.example.demo;

import android.os.Handler;
import android.os.Message;

/**
 * Created by Huanagcan on 2016/11/8.
 */
public class Timer extends Thread {
    private int id;
    private  Handler handler;
    public Timer( Handler handler,int id){

        this.handler = handler;
        id=id;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            Message msg = new Message();
            msg.what = id;
            handler.sendMessage(msg);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
