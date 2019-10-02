package edith.example.eva2_4_sqlite_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqlDB;
    ListView lstVwDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        lstVwDatos = findViewById(R.id.lstVwDatos);
        //Base de datos
        sqlDB = openOrCreateDatabase("bd_adaptador", MODE_PRIVATE, null);
        try{
            sqlDB.execSQL("create table prueba(id integer primary key autoincrement, dato text)");
        } catch (Exception e){
            e.printStackTrace();
        }
        sqlDB.execSQL("insert into prueba(dato) values ('Lan Zhan')");
        sqlDB.execSQL("insert into prueba(dato) values ('Wei Ying')");
        sqlDB.execSQL("insert into prueba(dato) values ('Wen Ning')");
        sqlDB.execSQL("insert into prueba(dato) values ('Wang Yibo')");
        sqlDB.execSQL("insert into prueba(dato) values ('Xiao Zhan')");
        sqlDB.execSQL("insert into prueba(dato) values ('Lan Xichen')");
        sqlDB.execSQL("insert into prueba(dato) values ('Lan Qiren')");
        Cursor cursor = sqlDB.rawQuery("select id as _id, dato from prueba order by dato", null);
        //
        lstVwDatos.setAdapter(new MiCursorAdapter(this, cursor));
    }
}
