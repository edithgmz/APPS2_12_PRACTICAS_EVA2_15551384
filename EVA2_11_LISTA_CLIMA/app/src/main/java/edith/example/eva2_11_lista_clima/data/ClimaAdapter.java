package edith.example.eva2_11_lista_clima.data;

/*
 * Created by Edith on 24-Oct-19.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edith.example.eva2_11_lista_clima.R;

import static edith.example.eva2_11_lista_clima.data.BaseDatos.byteArrayToImage;

public class ClimaAdapter extends ArrayAdapter<Clima> {
    private Context context;
    private int resource;
    private ArrayList<Clima> objects;

    public ClimaAdapter(Context context, int resource, ArrayList<Clima> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull @Override public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        ImageView imgVwClima;
        TextView txtVwCd, txtVwTemp, txtVwClima, txtVwDesc;
        View vwMiLayout = convertView;
        //Si el layout no existe lo crea
        if (vwMiLayout == null) {
            LayoutInflater liCrearLayout = ((Activity) context).getLayoutInflater();
            vwMiLayout = liCrearLayout.inflate(resource, parent, false);
        }
        //Vincular componentes
        imgVwClima = vwMiLayout.findViewById(R.id.imgVwClima);
        txtVwCd = vwMiLayout.findViewById(R.id.txtVwCd);
        txtVwTemp = vwMiLayout.findViewById(R.id.txtVwTemp);
        txtVwClima = vwMiLayout.findViewById(R.id.txtVwClima);
        txtVwDesc = vwMiLayout.findViewById(R.id.txtVwDesc);
        //Crea lista de notas con los datos obtenidos
        ArrayList<Clima> alClimaCd = objects;
        //Verificar que la lista no se encuentre vacía
        if (alClimaCd != null) {
            //Se obtienen los datos de la lista
            String ciudad = alClimaCd.get(position).getCiudad();
            String temperatura = alClimaCd.get(position).getTemp() + " °C";
            String clima = alClimaCd.get(position).getClima();
            String descripcion = alClimaCd.get(position).getDesc_clima();
            byte[] imagen = alClimaCd.get(position).getImagen_clima();
            //Se colocan los datos en los componentes
            txtVwCd.setText(ciudad);
            txtVwTemp.setText(temperatura);
            txtVwClima.setText(clima);
            txtVwDesc.setText(descripcion);
            if (imagen != null) {
                imgVwClima.setImageBitmap(byteArrayToImage(imagen));
            }
        }
        //Devuelve el layout creado
        return vwMiLayout;
    }
}
