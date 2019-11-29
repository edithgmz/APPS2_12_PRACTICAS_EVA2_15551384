package edith.example.eva2_11_lista_clima.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import edith.example.eva2_11_lista_clima.R;
import edith.example.eva2_11_lista_clima.data.BaseDatos;

import static edith.example.eva2_11_lista_clima.data.BaseDatos.imageToByteArray;

public class CapturarActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTxtCiudad, edtTxtTemp, edtTxtClima, edtTxtDesc;
    private ImageView imgVwImg;
    private BaseDatos baseDatos;
    private static final int GALERIA = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturar);
        //Vincular componentes
        edtTxtCiudad = findViewById(R.id.edtTxtCiudad);
        edtTxtClima = findViewById(R.id.edtTxtClima);
        edtTxtDesc = findViewById(R.id.edtTxtDesc);
        edtTxtTemp = findViewById(R.id.edtTxtTemp);
        imgVwImg = findViewById(R.id.imgVwImg);
        ImageButton imgBtnSelecImg = findViewById(R.id.imgBtnSelecImg);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        //Crear base de datos
        baseDatos = new BaseDatos(this);
        //Añadir escuchadores
        imgBtnSelecImg.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALERIA){
            if((resultCode == RESULT_OK) && (data != null)){
                Uri uriImagen = data.getData();
                try{
                    InputStream isImagen = null;
                    if (uriImagen != null) {
                        isImagen = getContentResolver().openInputStream(uriImagen);
                    }
                    Bitmap bitImagen = BitmapFactory.decodeStream(isImagen);
                    imgVwImg.setImageBitmap(bitImagen);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else{
                Toast.makeText(this, "No ha seleccionado ninguna imagen.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        //Dependiendo del botón presionado realiza una acción
        switch(v.getId()){
            case R.id.imgBtnSelecImg:
                Intent inGaleria = new Intent(Intent.ACTION_PICK);
                inGaleria.setType("image/*");
                startActivityForResult(inGaleria, GALERIA);
                break;
            case R.id.btnCancelar:
                finish();
                break;
            case R.id.btnGuardar:
                //Obtener datos del usuario y registrarlos en la base de datos
                String ciudad = edtTxtCiudad.getText().toString();
                String clima = edtTxtClima.getText().toString();
                String descripcion = edtTxtDesc.getText().toString();
                int temperatura = Integer.valueOf(edtTxtTemp.getText().toString());
                byte[] imagen = null;
                if(imgVwImg.getDrawable() != null){
                    imagen = imageToByteArray(imgVwImg);
                }
                baseDatos.ciudadNueva(ciudad, temperatura, clima, descripcion, imagen);
                baseDatos.close();
                //Mostrar mensaje de confirmación
                Toast.makeText(this, "Ciudad registrada.", Toast.LENGTH_SHORT).show();
                //Salir de la actividad
                finish();
                break;
        }
    }
}
