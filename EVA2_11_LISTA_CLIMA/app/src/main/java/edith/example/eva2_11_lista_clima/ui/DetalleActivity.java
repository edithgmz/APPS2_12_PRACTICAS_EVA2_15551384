package edith.example.eva2_11_lista_clima.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edith.example.eva2_11_lista_clima.R;
import edith.example.eva2_11_lista_clima.data.BaseDatos;

import static edith.example.eva2_11_lista_clima.data.BaseDatos.byteArrayToImage;

public class DetalleActivity extends AppCompatActivity implements Button.OnClickListener {
    private ImageView imgVwClima2;
    private TextView txtVwCd2, txtVwClima2, txtVwDesc2, txtVwTemp2;
    private Button btnCerrar, btnEliminar;
    private Bundle bDatos;
    private BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        //Vincular componentes
        btnCerrar = findViewById(R.id.btnCerrar);
        btnEliminar = findViewById(R.id.btnEliminar);
        imgVwClima2 = findViewById(R.id.imgVwClima2);
        txtVwCd2 = findViewById(R.id.txtVwCd2);
        txtVwClima2 = findViewById(R.id.txtVwClima2);
        txtVwDesc2 = findViewById(R.id.txtVwDesc2);
        txtVwTemp2 = findViewById(R.id.txtVwTemp2);
        //Crear base de datos
        baseDatos = new BaseDatos(this);
        //Añadir escuchadores
        btnCerrar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        //Obtener datosde la actividad padre
        Intent inDatos = getIntent();
        bDatos = inDatos.getExtras();
    }

    @Override protected void onResume() {
        super.onResume();
        //Verificar que hay valores
        if(bDatos != null){
            byte[] imagen = bDatos.getByteArray("IMG");
            String ciudad = bDatos.getString("CD");
            String clima = bDatos.getString("CLIMA");
            String temperatura = bDatos.getInt("TEMP") + " °C";
            String descripcion = bDatos.getString("DESC");
            //Asignar valores obtenidos
            imgVwClima2.setImageBitmap(byteArrayToImage(imagen));
            txtVwCd2.setText(ciudad);
            txtVwClima2.setText(clima);
            txtVwTemp2.setText(temperatura);
            txtVwDesc2.setText(descripcion);
        }
    }

    @Override
    public void onClick(View v) {
        //Dependiendo del botón presionado realiza una acción
        switch(v.getId()){
            case R.id.btnCerrar:
                finish();
                break;
            case R.id.btnEliminar:
                //Obtener datos y enviarlos como parámetros para eliminar
                String ciudad = txtVwCd2.getText().toString();
                String clima = txtVwClima2.getText().toString();
                String descripcion = txtVwDesc2.getText().toString();
                baseDatos.eliminarCiudad(ciudad, clima, descripcion);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Ciudad eliminada.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
        }
    }
}
