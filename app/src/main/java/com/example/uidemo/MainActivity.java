package com.example.uidemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Spinner spinner;

    List<Map<String,String>> dataList;

    ListView listView;
    Button _btn;
    EditText editText;
    TextView textView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int a= 10,b=4,c=20,d=6;
        Log.i("fanz",""+(a++*b+c*--d));
        initView();
        initData();
    }

    private void initView(){
        //spinner = findViewById(R.id.spinner1);
        _btn = findViewById(R.id.test_btn1);
        editText = findViewById(R.id.edittext);
        textView = findViewById(R.id.textview);
        listView = findViewById(R.id.listview1);

        ClickListener clickListener = new ClickListener();

        _btn.setOnClickListener(clickListener);

    }

    private void initData(){
        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        dataList = new ArrayList<>();
        for (int i=0;i<4;i++){
            Map<String,String> map = new HashMap<>();
            map.put("text1","123456789");
            map.put("text2","9");
            map.put("img",R.mipmap.ic_launcher +"");
            dataList.add(map);
        }

        adapter = new MyAdapter(this,dataList);
        textView.setText("数量为：" + dataList.size());
        listView.setAdapter(adapter);

    }

    class ClickListener implements View.OnClickListener {
        String str1 = "";
        int str2 = 0;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.test_btn1:
                    str1 = editText.getText().toString();
                    str2 = str1.length();
                    Log.i("fz1227","str1 == " + str1 + "-  str2 == " +str2);
                    Map<String,String> map = new HashMap<>();
                    map.put("text1",str1);
                    map.put("text2",str2+"");
                    map.put("img",R.mipmap.ic_launcher +"");
                    dataList.add(map);
                    adapter = new MyAdapter(MainActivity.this,dataList);
                    textView.setText("数量为：" + dataList.size());
                    listView.setAdapter(adapter);
            }
        }
    }

}
