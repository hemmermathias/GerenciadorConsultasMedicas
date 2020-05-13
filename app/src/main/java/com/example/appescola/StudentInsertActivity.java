package com.example.appescola;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class StudentInsertActivity extends Activity {

    SQLiteDatabase dataBase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_insert);

        this.CreateDB();
    }

    private void CreateDB(){
        dataBase = openOrCreateDatabase("“escola.db", Context.MODE_PRIVATE, null);

        StringBuilder studentTable = new StringBuilder();
        studentTable.append("CREATE TABLE IF NOT EXISTS student(");
        studentTable.append("ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        studentTable.append("NAME VARCHAR(30),");
        studentTable.append("COURSE VARCHAR(30),");
        studentTable.append("AGE INTEGER)");

        try{
            dataBase.execSQL(studentTable.toString());
            Toast.makeText(getApplicationContext(), "Db criado/inicializado com sucesso", Toast.LENGTH_LONG).show();
        } catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}
