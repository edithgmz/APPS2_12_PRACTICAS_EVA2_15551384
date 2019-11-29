package edith.example.eva2_10_object_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    TextView txtVwDatos;
    EditText edtTxtNom, edtTxtApe;
    RadioButton rdBtnMas, rdBtnFem, rdBtnOtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwDatos = findViewById(R.id.txtVwDatos);
        edtTxtNom = findViewById(R.id.edtTxtNom);
        edtTxtApe = findViewById(R.id.edtTxtApe);
        rdBtnMas = findViewById(R.id.rdBtnMas);
        rdBtnFem = findViewById(R.id.rdBtnFem);
        rdBtnOtro = findViewById(R.id.rdBtnOtro);
    }

    public void onClick(View v){
        guardar();
        leer();
    }

    public void guardar(){
        String iGen = "";
        if(rdBtnMas.isChecked())
            iGen = "Masculino";
        else if(rdBtnFem.isChecked())
            iGen = "Femenino";
        else if(rdBtnOtro.isChecked())
            iGen = "Otro";
        Personas pObj = new Personas(edtTxtNom.getText().toString(), edtTxtApe.getText().toString(), iGen);

        try{
            FileOutputStream fos = openFileOutput("datos.xxx",0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pObj);
            oos.writeObject(new Personas());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void leer() {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = openFileInput("datos.xxx");
            ois = new ObjectInputStream(fis);
            Personas pObj = (Personas) ois.readObject();
            while(true){
                txtVwDatos.append("Nombre: " + pObj.getNombre() + "\n");
                txtVwDatos.append("Apellido: " + pObj.getApellido() + "\n");
                txtVwDatos.append("Genero: " + pObj.getGenero() + "\n");
                txtVwDatos.append("----------------------------------------\n");
                pObj = (Personas) ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try{
                    ois.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Personas implements Serializable {
    private String nombre, apellido;
    private String genero;

    public Personas() {
        this.nombre = "María Luisa";
        this.apellido = "Morales Pérez";
        this.genero = "Femenino";
    }

    public Personas(String nombre, String apellido, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
