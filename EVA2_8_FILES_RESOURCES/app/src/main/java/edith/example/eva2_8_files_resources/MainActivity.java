package edith.example.eva2_8_files_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView txtVwMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwMostrar = findViewById(R.id.txtVwMostrar);

        InputStream is = getResources().openRawResource(R.raw.mi_texto);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        try{
            String sCade;
            while((sCade = br.readLine()) != null){
                txtVwMostrar.append(sCade);
                txtVwMostrar.append("\n");
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
