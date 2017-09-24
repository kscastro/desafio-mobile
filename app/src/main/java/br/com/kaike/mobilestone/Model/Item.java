package br.com.kaike.mobilestone.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kaike on 22/09/2017.
 */

public class Item implements Parcelable {

    @SerializedName("title")
    String titulo;
    @SerializedName("price")
    Double valor;
    @SerializedName("zipcode")
    String cep;
    @SerializedName("seller")
    String loja;
    @SerializedName("thumbnailHd")
    String urlFoto;
    @SerializedName("date")
    String data;


    public Item() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Item{" +
                "titulo='" + titulo + '\'' +
                ", valor=" + valor +
                ", cep='" + cep + '\'' +
                ", loja='" + loja + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titulo);
        dest.writeValue(this.valor);
        dest.writeString(this.cep);
        dest.writeString(this.loja);
        dest.writeString(this.urlFoto);
        dest.writeString(this.data);
    }

    protected Item(Parcel in) {
        this.titulo = in.readString();
        this.valor = (Double) in.readValue(Double.class.getClassLoader());
        this.cep = in.readString();
        this.loja = in.readString();
        this.urlFoto = in.readString();
        this.data = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
