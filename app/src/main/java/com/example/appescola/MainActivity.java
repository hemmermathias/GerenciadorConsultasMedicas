package com.example.appescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStudentInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToInsertStudent(View view){
        Intent studentActivity = new Intent(getApplicationContext(), StudentInsertActivity.class);
        startActivity(studentActivity);
    }
}
