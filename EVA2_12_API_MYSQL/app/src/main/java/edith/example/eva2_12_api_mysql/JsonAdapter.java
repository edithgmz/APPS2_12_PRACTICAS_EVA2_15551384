package edith.example.eva2_12_api_mysql;

/*
 * Created by Edith on 31-Oct-19.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonAdapter extends ArrayAdapter<JSONObject> {
    Context context;
    int resource;
    List<JSONObject> misJsons;

    public JsonAdapter(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.misJsons = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView txtVwProducto, txtVwUnitPrice, txtVwQuantityPerUnit;
        if(convertView == null){
            LayoutInflater layout = ((Activity) context).getLayoutInflater();
            convertView = layout.inflate(resource, parent, false);
        }
        txtVwProducto = convertView.findViewById(R.id.txtVwProducto);
        txtVwUnitPrice = convertView.findViewById(R.id.txtVwUnitPrice);
        txtVwQuantityPerUnit = convertView.findViewById(R.id.txtVwQuantityPerUnit);
        try {
            txtVwProducto.setText(misJsons.get(position).getString("ProductName"));
            txtVwUnitPrice.setText("$" + misJsons.get(position).getInt("UnitPrice"));
            txtVwQuantityPerUnit.setText(misJsons.get(position).getString("QuantityPerUnit"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
