package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainBActivity extends AppCompatActivity {
private ListView lv;
    private List<Time>list = MainActivity.list;
    private EditText et_name,et_time;
    private Button add,confirm;
    private TimeAdapter adapter;
    private Handler handler;
    private TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        initView();
        initData();
       initHandler();
        initListener();

    }

    public void initView(){
        tv1 = (TextView) findViewById(R.id.tv_name_show);
        tv2 = (TextView) findViewById(R.id.tv_time_show);
    lv = (ListView) findViewById(R.id.lv_main);
    add = (Button) findViewById(R.id.btn_add);
}

    private void initData() {
        adapter = new TimeAdapter(list,this);
        lv.setAdapter(adapter);
        int  p=getIntent().getIntExtra("index",0);
        tv1.setText(list.get(p).getName());
        tv2.setText(list.get(p).getSeconds());
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
                }
                adapter.notifyDataSetChanged();
            }
        };
    }

    private void initListener() {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainBActivity.this.finish();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainBActivity.this,MainBActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });
    }

}
