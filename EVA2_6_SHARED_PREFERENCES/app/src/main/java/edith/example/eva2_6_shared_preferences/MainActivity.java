package edith.example.eva2_6_shared_preferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent inLanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inLanzar = new Intent(this, Secundaria.class);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String opcion1 = sp.getString("opcion_1", "SIN ASIGNAR");
        Boolean opcion2 = sp.getBoolean("opcion_2", false);
        Boolean opcion3 = sp.getBoolean("opcion_3", false);

        Toast.makeText(this, opcion1 + " " + opcion2 + " " + opcion3, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mis_menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.config){
            //Lanzar secundaria
            startActivity(inLanzar);
        }
        return super.onOptionsItemSelected(item);
    }
}
