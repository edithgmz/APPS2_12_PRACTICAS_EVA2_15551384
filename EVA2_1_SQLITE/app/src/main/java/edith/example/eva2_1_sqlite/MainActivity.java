package edith.example.eva2_1_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = this.openOrCreateDatabase("basedatos.sqlite", MODE_PRIVATE, null);
        db.execSQL("create table hola(id integer, nombre text)");
        db.execSQL("insert into hola values(1, 'Edith')");
    }
}
