package com.example.demo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
private ListView lv;
    public static List<Time>list = new ArrayList<>();
    private EditText et_name,et_time;
    private Button add,confirm;
    private TimeAdapter adapter;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
       initHandler();
        initListener();



    }

    public void initView(){
    lv = (ListView) findViewById(R.id.lv_main);
    et_name = (EditText) findViewById(R.id.et_name);
    et_time = (EditText) findViewById(R.id.et_time);
    add = (Button) findViewById(R.id.btn_add);
    confirm = (Button) findViewById(R.id.btn_comfirm);

}

    private void initData() {
        adapter = new TimeAdapter(list,this);
        lv.setAdapter(adapter);
    }

    private void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int id = msg.what;
                Time time =null;
                for(Time t:list){
                    if(id==t.getId()){
                        time=t;
                    }
                }
                if(time==null)return;
                boolean flag = time.decTime();
                if(!flag){
                    list.remove(time);
                    Toast.makeText(MainActivity.this,"Task ["+time.getName()+"] has completed in["+time.getSeconds()+"] seconds!",Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();
            }
        };
    }

    private void initListener() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n =et_name.getText().toString().trim();
                String t = et_time.getText().toString().trim();
                int tm  = 0;
                try{
                    tm = Integer.parseInt(t);
                }catch (Exception e){
                    tm = 3;// auto default set 3 seconds
                }
                Time time = new Time(n,tm);
                time.setTimer(new Timer(handler,time.getId()));
                list.add(time);
                adapter.notifyDataSetChanged();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_name.setText("");
                et_name.setHint("Enter Task Name");
                et_time.setText("");
                et_time.setHint("Enter Time(seconds)");
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,MainBActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });
    }
}
