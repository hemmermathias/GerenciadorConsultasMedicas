package com.example.consultasmedicas;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consultasmedicas.util.BaseDataControllerActivity;
import com.example.consultasmedicas.util.DatabaseHelper;
import com.example.consultasmedicas.util.FormState;
import com.example.consultasmedicas.util.MessageConstants;

enum GrupoSanguineo{
    A,
    B,
    AB,
    O
}

public class PatientEditActivity extends BaseDataControllerActivity {

    SQLiteDatabase dbContext;

    EditText txtName;
    Spinner spnGrpSangue;
    ArrayAdapter spnGrpSangueAdapter;
    EditText txtAddress;
    EditText txtAddressNumber;
    EditText txtCity;
    Spinner spnUf;
    ArrayAdapter spnUFAdapter;
    EditText txtCellphone;
    EditText txtPhone;
    Button btnOk;
    Button btnRemove;
    TextView lblOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        txtName         = findViewById(R.id.txtName);
        spnGrpSangue    = findViewById(R.id.spnGrpSangue);
        txtAddress      = findViewById(R.id.txtAddres);
        txtAddressNumber = findViewById(R.id.txtAddressNumber);
        txtCity         = findViewById(R.id.txtCity);
        txtCellphone    = findViewById(R.id.txtCellphone);
        txtPhone        = findViewById(R.id.txtPhone);
        spnUf           = findViewById(R.id.spnUF);
        btnOk           = findViewById(R.id.btnAdd);
        lblOperation    = findViewById(R.id.lblOperation);
        btnRemove       = findViewById((R.id.btnExcluir));

        spnUFAdapter = ArrayAdapter.createFromResource(this, R.array.BaseUfList, R.layout.support_simple_spinner_dropdown_item);
        spnUf.setAdapter(spnUFAdapter);

        spnGrpSangueAdapter = ArrayAdapter.createFromResource(this, R.array.BaseUfList, R.layout.support_simple_spinner_dropdown_item);

        dbContext = new DatabaseHelper(this).getWritableDatabase();

        btnOk.setText(this.intentMessage != null ? this.intentMessage.getString(MessageConstants.btnOkLabel, "Adicionar") : "Adicionar");

        lblOperation.setText(this.GetFormStateName());
        if(this.formState == FormState.edit){
            this.entityIdOnFocus = this.intentMessage != null ? this.intentMessage.getString(MessageConstants.entityID, null) : null;
            if(this.entityIdOnFocus == null) {
                this.formState = FormState.add;
                return;
            }

            btnRemove.setVisibility(View.VISIBLE);
        }
    }
}