package com.example.uidemo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.Toast.LENGTH_LONG;

public class SendKeyActivity extends Activity {

    private Button btn;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendkey);

        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.text);
        Log.e("fanz","SendKeyActivity:onCreate:>>>>>>>>>>>>>>>");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendKeyCode(KeyEvent.KEYCODE_ENTER);
                Intent intent = new Intent(SendKeyActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }



    private void sendKeyCode(final int keyCode){
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 66) {
            Toast.makeText(this,"当前发送的是Enter按键！", LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
