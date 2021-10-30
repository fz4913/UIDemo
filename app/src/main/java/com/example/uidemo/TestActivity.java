package com.example.uidemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private TextView tv;
    private EditText et;
    private Button btn;

    private ScannerInterface scannerInterface;

    MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv = findViewById(R.id.test_tv1);
        et = findViewById(R.id.test_et1);
        btn = findViewById(R.id.test_btn1);

        scannerInterface = new ScannerInterface(this);
        scannerInterface.setOutputMode(1);

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("android.intent.action.SCANRESULT");
        registerReceiver(receiver,filter);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannerInterface.setOutputMode(0);
                et.setEnabled(true);
                et.requestFocus();
            }
        });

        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER){
                    if (keyEvent.getAction() == KeyEvent.ACTION_UP){
                        String s = et.getText().toString();
                        if (!TextUtils.isEmpty(s)){
                            if (s.startsWith(weStr) || s.startsWith(aliStr)){
                                scannerInterface.setOutputMode(1);
                                et.setEnabled(false);
                            }else {
                                et.setText("");
                                et.requestFocus();
                                Toast.makeText(TestActivity.this,"条码不正确！！！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = tv.getText().toString();
                String s2 = et.getText().toString();
                if (s2.contains(s1)){
                    if (TextUtils.isEmpty(s1)){
                        Toast.makeText(TestActivity.this,"未扫描条码",Toast.LENGTH_SHORT).show();
                    }else {
                        et.setText("");
                        tv.setText("");
                        Toast.makeText(TestActivity.this,"校验成功！！！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(TestActivity.this,"校验失败！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    final String weStr = "https://u.wechat.com/";
    final String aliStr = "https://qr.alipay.com/";
    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCANRESULT".equals(intent.getAction())){
                String str = intent.getStringExtra("value");
                if (TextUtils.isEmpty(str)){
                    return;
                }

                if (str.startsWith(weStr)){
                    tv.setText(str.substring(weStr.length()));
                }else if (str.startsWith(aliStr)){
                    tv.setText(str.substring(aliStr.length()));
                }else {
                    Toast.makeText(TestActivity.this,"条码不正确！！！",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerInterface.setOutputMode(0);
    }
}