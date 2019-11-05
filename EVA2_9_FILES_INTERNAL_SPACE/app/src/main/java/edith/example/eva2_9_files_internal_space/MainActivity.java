package edith.example.eva2_9_files_internal_space;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    final String NOMBRE_ARCHIVO = "mi_archivo.txt";
    EditText edtTxtDatos;
    TextView txtVwMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxtDatos = findViewById(R.id.edtTxtDatos);
        txtVwMostrar = findViewById(R.id.txtVwMostrar);

        txtVwMostrar.setText("");

        try {
            String sCade;
            InputStream is = openFileInput(NOMBRE_ARCHIVO);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((sCade = br.readLine()) != null) {
                txtVwMostrar.append(sCade);
                txtVwMostrar.append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        try {
            String[] asCade = edtTxtDatos.getText().toString().split("\n");
            OutputStream os = openFileOutput(NOMBRE_ARCHIVO, 0);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            for (int i = 0; i < asCade.length; i++) {
                bw.append(asCade[i]);
                bw.append("\n");
                txtVwMostrar.append(asCade[i]);
                txtVwMostrar.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
