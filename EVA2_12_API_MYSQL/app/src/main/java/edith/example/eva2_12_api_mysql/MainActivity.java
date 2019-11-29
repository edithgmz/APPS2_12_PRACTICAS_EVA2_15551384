package edith.example.eva2_12_api_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstJSON;
    ArrayList miLista = new ArrayList<JSONObject>();
    //TextView txtVwDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstJSON = findViewById(R.id.lstJSON);
        //txtVwDatos = findViewById(R.id.txtVwDatos);

        MYSQLAPIconnection mysqlapi = new MYSQLAPIconnection();
        mysqlapi.execute();
    }

    /*public void onClick(View v){
        new MYSQLAPIconnection().execute();
    }*/

    //Crear conexión
    class MYSQLAPIconnection extends AsyncTask<Void, Void, String>{
        final String url = "http://10.8.17.16:3000/Tasks/";

        @Override
        protected String doInBackground(Void... voids) {
            String sResu = null;
            try {
                //GET ==> Select
                /*URL ruta = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) ruta.openConnection();
                //Verificar conexión
                if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    sResu = br.readLine();
                }*/
                //POST ==> Insert
                URL ruta = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) ruta.openConnection();
                //Definir tipo de solicitud
                httpCon.setRequestMethod("POST");
                httpCon.setDoInput(true); //Entrada
                httpCon.setDoOutput(true); //Resultado
                httpCon.setRequestProperty("Content-Type", "application/json;charset=utf-8"); //Tipo
                httpCon.connect();
                //Objeto con los datos a enviar
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Productname", "JJJJJ");
                jsonObject.put("Unitprice", 555);
                //Enviar datos
                DataOutputStream escribir = new DataOutputStream(httpCon.getOutputStream());
                escribir.write(jsonObject.toString().getBytes());
                escribir.flush();
                escribir.close();
                //Leer resultado
                InputStream inputStream = httpCon.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer resu = new StringBuffer();
                while((sResu = br.readLine()) != null){
                    resu.append(sResu);
                    resu.append("\n");
                }
                br.close();
                sResu = resu.toString();
                //DELETE ==> Delete

                //PUT ==> Update

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "onPostExecute: " + s, Toast.LENGTH_LONG).show();
            if(!s.equals("")){
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsoProduct = jsonArray.getJSONObject(i);
                        miLista.add(jsoProduct);
                        /*txtVwDatos.append("ID: " + jsoProduct.getString("ProductID") + "\n");
                        txtVwDatos.append("Product name: " + jsoProduct.getString("ProductName") + "\n");
                        txtVwDatos.append("Quantity per unit: " + jsoProduct.getString("QuantityPerUnit") + "\n");
                        txtVwDatos.append("Unit price: " + jsoProduct.getInt("UnitPrice") + "\n\n");*/
                    }
                    lstJSON.setAdapter(new JsonAdapter(MainActivity.this, R.layout.layout_json, miLista));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //txtVwDatos.setText(s);
            }
        }
    }
}
