package edith.example.eva2_3_content_values;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase slDB;
    EditText edtTxtNom, edtTxtApe;
    TextView txtVwDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vincular componentes
        edtTxtNom = findViewById(R.id.edtTxtNom);
        edtTxtApe = findViewById(R.id.edtTxtApe);
        txtVwDatos = findViewById(R.id.txtVwDatos);
        //Crear base de datos
        slDB = openOrCreateDatabase("db_prueba", MODE_PRIVATE, null);
        //Crear tabla
        try{
            slDB.execSQL("create table datos(datosid integer primary key autoincrement, nombre text, apellidos text)");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClick(View v){
        ContentValues cvDatos = new ContentValues();
        cvDatos.put("nombre", edtTxtNom.getText().toString());
        cvDatos.put("apellidos", edtTxtApe.getText().toString());
        slDB.insert("datos", null, cvDatos);
        //cvDatos.clear(); No es necesario en este caso
        txtVwDatos.append(edtTxtNom.getText().toString() + " " + edtTxtApe.getText().toString() + "\n");
    }
}
