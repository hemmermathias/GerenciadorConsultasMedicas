package com.example.consultasmedicas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consultasmedicas.dbschemes.MedicScheme;
import com.example.consultasmedicas.util.DatabaseHelper;
import com.example.consultasmedicas.util.FormState;
import com.example.consultasmedicas.util.MessageConstants;

public class MedicListActivity extends AppCompatActivity {

    SQLiteDatabase dbContext;

    ListView lsvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medic);

        lsvList = findViewById(R.id.lsvLista);

        lsvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lsvList.getChildAt(position);

                TextView lblID = v.findViewById(R.id.lblID);

                Intent i = new Intent(getApplicationContext(), MedicEditActivity.class);

                i.putExtra(MessageConstants.entityID, lblID.getText().toString());
                i.putExtra(MessageConstants.btnOkLabel, "Confirmar");
                i.putExtra(MessageConstants.formState, FormState.edit);

                startActivity(i);
            }
        });

        dbContext = new DatabaseHelper(this).getWritableDatabase();
        PopulateListView();
    }

    @Override
    protected  void onRestart() {
        super.onRestart();
        this.PopulateListView();
    }

    private void PopulateListView(){
        if(dbContext == null) return;

        Cursor data = dbContext.rawQuery(String.format("SELECT * FROM %s", MedicScheme.T_NAME), null);

        String[] from = {"_id", "nome"};
        int[] to = {R.id.lblID, R.id.lblName};

        SimpleCursorAdapter scAdapter =
                new SimpleCursorAdapter(getApplicationContext(), R.layout.activity_list_medic_item, data, from, to, 0);

        lsvList.setAdapter(scAdapter);
    }
}
