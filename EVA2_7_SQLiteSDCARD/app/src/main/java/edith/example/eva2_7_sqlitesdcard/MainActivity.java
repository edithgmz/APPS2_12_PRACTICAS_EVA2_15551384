package edith.example.eva2_7_sqlitesdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtener ruta de la SD Card
        String sRuta = Environment.getExternalStorageDirectory().getPath();
        Toast.makeText(this, sRuta, Toast.LENGTH_LONG).show();
        //Establecer directorio
        String sDir = "eva2_7_sqlite_sdcard";
        String sDB = "prueba";
        File fPath = new File(sRuta + "/" + sDir + "/");
        if(!fPath.exists()){
            fPath.mkdir(); // Primera vez, se crea la ruta
        }
        //Base de datos
        String sPath = sRuta + "/" + sDir + "/" + sDB;
        SQLiteDatabase sqlDB = SQLiteDatabase.openDatabase(sPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Toast.makeText(this, "Ruta: " + sqlDB.getPath(), Toast.LENGTH_LONG).show();
    }
}
