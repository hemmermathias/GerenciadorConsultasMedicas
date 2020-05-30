package com.example.consultasmedicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.consultasmedicas.util.FormState;
import com.example.consultasmedicas.util.MessageConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenAddMedic(View view) {
        Intent intent = new Intent(this, MedicEditActivity.class);
        intent.putExtra(MessageConstants.btnOkLabel, "Adicionar");
        intent.putExtra(MessageConstants.formState, FormState.add);
        startActivity(intent);
    }

    public void OpenListMedic(View view) {
        Intent intent = new Intent(this, MedicListActivity.class);
        startActivity(intent);
    }

    public void OpenEditPatient(View view){
        Intent intent = new Intent(this, PatientEditActivity.class);
        intent.putExtra(MessageConstants.btnOkLabel, "Adicionar");
        intent.putExtra(MessageConstants.formState, FormState.add);
        startActivity(intent);
    }

    public void OpenListPatient(View view) {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
    }
}
