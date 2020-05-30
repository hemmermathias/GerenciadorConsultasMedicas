package com.example.consultasmedicas.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.consultasmedicas.dbschemes.MedicScheme;
import com.example.consultasmedicas.dbschemes.PatientScheme;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int db_version = 1;
    public static final String db_name  = "consulta.db";

    public static final String TABLE_PATIENT = "paciente";
    public static final String TABLE_APPOINTMENT = "consulta";

    public DatabaseHelper(@Nullable Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.CreateTables(db);
    }

    private void CreateTables(SQLiteDatabase db){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(MedicScheme.T_NAME).append(" (");
        sql.append(MedicScheme.C_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append(MedicScheme.C_NAME).append(" VARCHAR(50), ");
        sql.append(MedicScheme.C_CRM).append(" VARCHAR(20), ");
        sql.append(MedicScheme.C_ADDRESS).append(" VARCHAR(100), ");
        sql.append(MedicScheme.C_ADDRESS_NUMBER).append(" MEDIUMINT(8), ");
        sql.append(MedicScheme.C_CITY).append(" VARCHAR(30), ");
        sql.append(MedicScheme.C_UF).append(" VARCHAR(2), ");
        sql.append(MedicScheme.C_CELLPHONE).append(" VARCHAR(20), ");
        sql.append(MedicScheme.C_PHONE).append(" VARCHAR(20)");
        sql.append(");");
        db.execSQL(sql.toString());

        sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(PatientScheme.T_NAME).append(" (");
        sql.append(PatientScheme.C_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append(PatientScheme.C_NAME).append(" VARCHAR(50), ");
        sql.append(PatientScheme.C_GRPSANGUE).append(" TINYINT(1), ");
        sql.append(PatientScheme.C_ADDRESS).append(" VARCHAR(100), ");
        sql.append(PatientScheme.C_ADDRESS_NUMBER).append(" MEDIUMINT(8), ");
        sql.append(PatientScheme.C_CITY).append(" VARCHAR(30), ");
        sql.append(PatientScheme.C_UF).append(" VARCHAR(2), ");
        sql.append(PatientScheme.C_CELLPHONE).append(" VARCHAR(20), ");
        sql.append(PatientScheme.C_PHONE).append(" VARCHAR(20)");
        sql.append(");");
        db.execSQL(sql.toString());

        sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(TABLE_APPOINTMENT).append(" (");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("paciente_id INTEGER NOT NULL, ");
        sql.append("medico_id INTEGER NOT NULL, ");
        sql.append("data_hora_inicio DATETIME, ");
        sql.append("data_hora_fim DATETIME, ");
        sql.append("observacao VARCHAR(200), ");
        sql.append("FOREIGN KEY(paciente_id) REFERENCES paciente(id), ");
        sql.append("FOREIGN KEY(medico_id) REFERENCES medico(id)");
        sql.append(");");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql = new StringBuilder();
        sql.append("DROP TABLE IF EXISTS ").append(MedicScheme.T_NAME);
        db.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("DROP TABLE IF EXISTS ").append(TABLE_PATIENT);
        db.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("DROP TABLE IF EXISTS ").append(TABLE_APPOINTMENT);
        db.execSQL(sql.toString());

        this.CreateTables(db);
    }
}
