package edith.example.eva2_11_lista_clima.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edith.example.eva2_11_lista_clima.R;
import edith.example.eva2_11_lista_clima.data.BaseDatos;
import edith.example.eva2_11_lista_clima.data.Clima;
import edith.example.eva2_11_lista_clima.data.ClimaAdapter;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private Intent inCapturar, inDetalle;
    private ListView lstVwCiudades;
    private ArrayList<Clima> lstCiudades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vincular componentes
        FloatingActionButton fabAgregar = findViewById(R.id.fabAgregar);
        lstVwCiudades = findViewById(R.id.lstVwCiudades);
        //Intentos a otras actividades
        inCapturar = new Intent(this, CapturarActivity.class);
        inDetalle = new Intent(this, DetalleActivity.class);
        //Añadir escuchadores
        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inCapturar);
            }
        });
        lstVwCiudades.setOnItemClickListener(this);
    }

    @Override protected void onResume() {
        super.onResume();
        //Crear y abrir base de datos
        BaseDatos baseDatos = new BaseDatos(this);
        lstCiudades = baseDatos.datosCiudad();
        baseDatos.close();
        //Añadir adaptador
        lstVwCiudades.setAdapter(new ClimaAdapter(this, R.layout.layout_clima, lstCiudades));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Enviar datos del objeto seleccionado a la actividad Detalle
        Bundle bDatos = new Bundle();
        bDatos.putByteArray("IMG", lstCiudades.get(position).getImagen_clima());
        bDatos.putString("CD", lstCiudades.get(position).getCiudad());
        bDatos.putString("CLIMA", lstCiudades.get(position).getClima());
        bDatos.putInt("TEMP", lstCiudades.get(position).getTemp());
        bDatos.putString("DESC", lstCiudades.get(position).getDesc_clima());
        inDetalle.putExtras(bDatos);
        startActivity(inDetalle);
    }
}
