package com.example.stammana.weeksix;

import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.example.stammana.weeksix.R.layout.dialog;

public class MainActivity extends AppCompatActivity{

    Map<String, String> credentials = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            loadUserContents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserContents() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getApplicationContext().getAssets().open("userCredentials.txt")));
        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            String[] words = mLine.split("\t");
            credentials.put(words[0], words[1]); // process line
            mLine = reader.readLine();
        }
        reader.close();
    }


    public void login(View view) {
        String userName = ((EditText)findViewById(R.id.userName)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String passwordFromMap = credentials.get(userName);
        ((EditText)findViewById(R.id.userName)).setText("");
        ((EditText)findViewById(R.id.password)).setText("");
        if(passwordFromMap != "" && passwordFromMap != null && passwordFromMap.equals(password)){
            builder.setMessage("Login successful.");
        }
        else{
            builder.setMessage("Login failed");
        }
        builder.setCancelable(true);
        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}
