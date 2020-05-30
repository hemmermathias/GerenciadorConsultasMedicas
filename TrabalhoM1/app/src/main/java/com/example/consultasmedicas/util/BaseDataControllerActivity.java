package com.example.consultasmedicas.util;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseDataControllerActivity extends AppCompatActivity {
    protected SQLiteDatabase dbContext = null;
    public FormState formState = null;
    public String entityIdOnFocus = "";
    protected Bundle intentMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbContext = new DatabaseHelper(this).getWritableDatabase();

        this.intentMessage = getIntent().getExtras();
        if(intentMessage == null) return;
        Object formState = intentMessage.get(MessageConstants.formState);
        this.formState = formState  == null ? FormState.view : formState instanceof FormState ? (FormState) formState : FormState.view;
    }

    protected String GetFormStateName(){
        switch (this.formState){
            case add: return "Adicionando";
            case edit: return "Editando";
            case view: return "Visualizando";
            default: return "NULL";
        }
    }
}

