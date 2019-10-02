package edith.example.eva2_2_transacciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqDB = openOrCreateDatabase("mi_bd", MODE_PRIVATE, null);
        try{
            sqDB.execSQL("create table prueba(pruebaid integer primary key autoincrement, algo text)");
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        sqDB.beginTransaction();
        try {
            sqDB.execSQL("insert into prueba(algo) values('Wei Ying')");
            sqDB.execSQL("insert into prueba(algo) values('Lan Zhan')");
            //int i = 1 / 0;
            sqDB.execSQL("insert into prueba(algo) values('Xiao Zhan')");
            sqDB.execSQL("insert into prueba(algo) values('Wang YiBo')");
            sqDB.setTransactionSuccessful();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            sqDB.endTransaction();
        }

        //Cursor cursor = sqDB.rawQuery("select * from prueba", null);
        String[] args = {"Lan Zhan"};
        Cursor cursor = sqDB.rawQuery("select * from prueba where algo = ?", args);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Log.wtf("cursor", cursor.getString(cursor.getColumnIndex("algo")));
            cursor.moveToNext();
        }

    }
}
