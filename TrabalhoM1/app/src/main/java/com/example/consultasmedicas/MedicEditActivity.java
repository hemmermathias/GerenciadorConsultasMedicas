package com.example.consultasmedicas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.consultasmedicas.dbschemes.MedicScheme;

public class MedicEditActivity extends AppCompatActivity {

    SQLiteDatabase dbContext;

    private boolean edit = false;
    private String editID = "";

    EditText txtName;
    EditText txtCrm;
    EditText txtAddress;
    EditText txtAddressNumber;
    EditText txtCity;
    Spinner  spnUf;
    ArrayAdapter spnUFAdapter;
    EditText txtCellphone;
    EditText txtPhone;
    Button btnOk;
    TextView lblOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medic);

        txtName         = findViewById(R.id.txtName);
        txtCrm          = findViewById(R.id.txtCRM);
        txtAddress      = findViewById(R.id.txtAddres);
        txtAddressNumber = findViewById(R.id.txtAddressNumber);
        txtCity         = findViewById(R.id.txtCity);
        txtCellphone    = findViewById(R.id.txtCellphone);
        txtPhone        = findViewById(R.id.txtPhone);
        spnUf           = findViewById(R.id.spnUF);
        btnOk           = findViewById(R.id.btnAdd);
        lblOperation    = findViewById(R.id.lblOperation);

        spnUFAdapter = ArrayAdapter.createFromResource(this, R.array.BaseUfList, R.layout.support_simple_spinner_dropdown_item);
        spnUf.setAdapter(spnUFAdapter);

        dbContext = new DatabaseHelper(this).getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String buttonName = extras.getString("btnOkName");
            btnOk.setText( buttonName != null ? buttonName : "ERROR");

            String currentWorkingId = extras.getString("id");
            if(currentWorkingId != null && currentWorkingId.length() > 0){
                this.edit = true;
                this.editID = currentWorkingId;
                FindMedic(editID);
            }
        }

        GetOperationName();
    }

    private boolean ValidateEntry(){
        try{
            if(txtName.getText().length() <= 0) throw new Exception("Nome em branco!");
            if(txtName.getText().length() > 50) throw new Exception("Nome muito longo!");

            if(txtCrm.getText().length() <= 0) throw new Exception("CRM em branco!");
            if(txtCrm.getText().length() > 20) throw new Exception("CRM muito longo!");

            if(txtAddress.getText().length() <= 0) throw new Exception("Logradouro em branco!");
            if(txtAddress.getText().length() > 100) throw new Exception("Logradouro muito longo!");

            if(txtAddressNumber.getText().length() <= 0) throw new Exception("Número Residencial em branco!");
            if(txtAddressNumber.getText().length() > 8) throw new Exception("Número Residencial muito longo!");

            if(txtCity.getText().length() <= 0) throw new Exception("Cidade em branco!");
            if(txtCity.getText().length() > 30) throw new Exception("Cidade muito longo!");

            if(txtCellphone.getText().length() <= 0) throw new Exception("Celular em branco!");
            if(txtCellphone.getText().length() > 20) throw new Exception("Celular muito longo!");

            if(txtPhone.getText().length() <= 0) throw new Exception("Fixo em branco!");
            if(txtPhone.getText().length() > 20) throw new Exception("Fixo muito longo!");

            if(spnUf.getSelectedItem().toString().length() <= 0) throw new Exception("UF em branco!");

            return true;
        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void FindMedic(String id){
        if(dbContext == null){
            Toast.makeText(this, "Contexto do DB nullo!", Toast.LENGTH_LONG).show();
            return;
        }

        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", MedicScheme.T_NAME, MedicScheme.C_ID, id);
        Cursor data = dbContext.rawQuery(sql, null);
        if(data != null && data.getCount() > 0){
            while(data.moveToNext()) {
                txtName.setText(data.getString(data.getColumnIndex(MedicScheme.C_NAME)));
                txtCrm.setText(data.getString(data.getColumnIndex(MedicScheme.C_CRM)));
                txtAddress.setText(data.getString(data.getColumnIndex(MedicScheme.C_ADDRESS)));
                txtAddressNumber.setText(data.getString(data.getColumnIndex(MedicScheme.C_ADDRESS_NUMBER)));
                txtCity.setText(data.getString(data.getColumnIndex(MedicScheme.C_CITY)));
                txtCellphone.setText(data.getString(data.getColumnIndex(MedicScheme.C_CELLPHONE)));
                txtPhone.setText(data.getString(data.getColumnIndex(MedicScheme.C_PHONE)));
                spnUf.setSelection(spnUFAdapter.getPosition(data.getString(data.getColumnIndex(MedicScheme.C_UF))));
            }
        }
    }

    public void AddMedic(View view) {
        if(!ValidateEntry()) return;
        if(dbContext == null){
            Toast.makeText(this, "Contexto do DB nullo!", Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(MedicScheme.C_NAME, txtName.getText().toString());
        values.put(MedicScheme.C_CRM, txtCrm.getText().toString());
        values.put(MedicScheme.C_ADDRESS, txtAddress.getText().toString());
        values.put(MedicScheme.C_ADDRESS_NUMBER, txtAddressNumber.getText().toString());
        values.put(MedicScheme.C_CITY, txtCity.getText().toString());
        values.put(MedicScheme.C_UF, spnUf.getSelectedItem().toString());
        values.put(MedicScheme.C_CELLPHONE, txtCellphone.getText().toString());
        values.put(MedicScheme.C_PHONE, txtPhone.getText().toString());

        if(!this.edit){
            long newRowId = dbContext.insert(MedicScheme.T_NAME, null, values);
            Toast.makeText(this, String.format("Médico inserido com sucesso! (ID: %d)", newRowId), Toast.LENGTH_LONG).show();
        }else{
            if(dbContext.update(MedicScheme.T_NAME, values, String.format("_id = '%s'", this.editID), null) > 0)
                Toast.makeText(this, String.format("Médico editado com sucesso! (ID: %s)", editID), Toast.LENGTH_LONG).show();
        }

        ClearEntry();
        return;
    }

    public void ClearEntry(){
        txtName.setText("");
        txtCrm.setText("");
        txtAddress.setText("");
        txtAddressNumber.setText("");
        txtCity.setText("");
        txtCellphone.setText("");
        txtPhone.setText("");
    }

    public void GetOperationName(){
        if(this.edit){
            lblOperation.setText("Editando");
        }else{
            lblOperation.setText("Adicionando");
        }
    }
}
