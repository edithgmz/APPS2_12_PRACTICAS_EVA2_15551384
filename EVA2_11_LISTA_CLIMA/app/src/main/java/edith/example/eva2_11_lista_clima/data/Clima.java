package edith.example.eva2_11_lista_clima.data;

/*
 * Created by Edith on 24-Oct-19.
 */

public class Clima {
    private String ciudad, clima, desc_clima;
    private int temp;
    private byte[] imagen_clima;

    public Clima(String ciudad, String clima, String desc_clima, int temp, byte[] imagen_clima) {
        this.ciudad = ciudad;
        this.clima = clima;
        this.desc_clima = desc_clima;
        this.temp = temp;
        this.imagen_clima = imagen_clima;
    }

    public byte[] getImagen_clima() {
        return imagen_clima;
    }

    public void setImagen_clima(byte[] imagen_clima) {
        this.imagen_clima = imagen_clima;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getDesc_clima() {
        return desc_clima;
    }

    public void setDesc_clima(String desc_clima) {
        this.desc_clima = desc_clima;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
