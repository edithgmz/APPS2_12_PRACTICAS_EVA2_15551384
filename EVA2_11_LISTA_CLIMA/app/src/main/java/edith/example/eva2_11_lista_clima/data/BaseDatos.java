package edith.example.eva2_11_lista_clima.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/*
 * Created by Edith on 24-Oct-19.
 */

public class BaseDatos extends SQLiteOpenHelper {
    private static final int BD_VERSION = 1;
    private static final String BD_NOMBRE = "clima.bd";
    private static final String NOMBRE_TABLA = "ciudades";
    private static final String COL_CIUDAD = "ciudad";
    private static final String COL_CLIMA = "clima";
    private static final String COL_DESC = "descripcion";
    private static final String COL_IMAGEN = "imagen";
    private static final String COL_TEMP = "temperatura";
    private static final String CREAR_TABLA =
            "CREATE TABLE " + NOMBRE_TABLA + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CIUDAD + " TEXT, " + COL_TEMP + " INTEGER, " +
            COL_CLIMA + " TEXT, " + COL_DESC + " TEXT, " + COL_IMAGEN + " BLOB)";
    private static final String LEER_TABLA = "SELECT ciudad, temperatura, clima, descripcion, imagen FROM ciudades";
    private SQLiteDatabase bd;

    public BaseDatos(Context context) {
        super(context, BD_NOMBRE, null, BD_VERSION);
        //Obtener base de datos editable
        bd = this.getWritableDatabase();
    }

    public static byte[] imageToByteArray(ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap byteArrayToImage(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    //Ejecuta query al crear la base de datos
    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insertar registro en ciudades
    public void ciudadNueva(String ciudad, int temperatura, String clima, String descripcion, @Nullable byte[] imagen) {
        ContentValues cv = new ContentValues();
        cv.put(COL_CIUDAD, ciudad);
        cv.put(COL_TEMP, temperatura);
        cv.put(COL_CLIMA, clima);
        cv.put(COL_DESC, descripcion);
        cv.put(COL_IMAGEN, imagen);
        bd.insert(NOMBRE_TABLA, null, cv);
    }

    /*MÉTODOS PARA CONVERTIR ARREGLO DE BYTES EN BITMAP Y VICEVERSA*/

    //Eliminar registro en ciudades
    public void eliminarCiudad(String ciudad, String clima, String descripcion) {
        String condicion = COL_CIUDAD + "=? AND " + COL_CLIMA + "=? AND " + COL_DESC + "=?";
        String[] argumentos = new String[]{ciudad, clima, descripcion};
        bd.delete(NOMBRE_TABLA, condicion, argumentos);
    }

    //Obtener lista de ciudades
    public ArrayList<Clima> datosCiudad() {
        ArrayList<Clima> alCiudades = new ArrayList<>();
        //Cursor para leer datos de la tabla
        Cursor c = bd.rawQuery(LEER_TABLA, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                //Asignar valores y crear objeto
                String ciudad = c.getString(c.getColumnIndex(COL_CIUDAD));
                int temperatura = c.getInt(c.getColumnIndex(COL_TEMP));
                String clima = c.getString(c.getColumnIndex(COL_CLIMA));
                String descripcion = c.getString(c.getColumnIndex(COL_DESC));
                byte[] imagen = c.getBlob(c.getColumnIndex(COL_IMAGEN));
                alCiudades.add(new Clima(ciudad, clima, descripcion, temperatura, imagen));
            } while (c.moveToNext());
        }
        //Cerrar el cursor
        if (c != null) {
            c.close();
        }
        return alCiudades;
    }
}
