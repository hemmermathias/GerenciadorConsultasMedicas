package com.example.consultasmedicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenAddMedic(View view) {
        Intent intent = new Intent(this, MedicEditActivity.class);
        intent.putExtra("btnOkName", "Adicionar");
        startActivity(intent);
    }

    public void OpenListMedic(View view) {
        Intent intent = new Intent(this, MedicListActivity.class);
        startActivity(intent);
    }

    public void OpenEditPatient(View view){
        Intent intent = new Intent(this, PatientEditActivity.class);
        intent.putExtra("btnOkName", "Adicionar");
        startActivity(intent);
    }

    public void OpenListPatient(View view) {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
    }
}
