package com.example.testconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button mCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCheck = findViewById(R.id.btn_check);

        mCheck.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                //call http API untuk register
                ArrayList<HashMap<String, String>> reqLogin = RequestTestConn("Test");
                Log.d("Logchecker",reqLogin.toString());
                if (Objects.equals(reqLogin.get(0).get("success"), "1")){
                    Toast.makeText(MainActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                } else if (Objects.equals(reqLogin.get(0).get("success"), "-1")){
                    //koneksi error
                    Toast.makeText(MainActivity.this, reqLogin.get(0).get("message"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, reqLogin.get(0).get("message"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<HashMap<String, String>> RequestTestConn(String payload) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        CheckCallback login_req = new CheckCallback(MainActivity.this);
        //Log.d("CEKIDBOOK",id_book);
        try {
            arrayList = login_req.execute(
                    payload
            ).get();
        }catch (Exception e){
            Log.d("Error Message",e.getMessage());
        }

        return arrayList;
    }
}
