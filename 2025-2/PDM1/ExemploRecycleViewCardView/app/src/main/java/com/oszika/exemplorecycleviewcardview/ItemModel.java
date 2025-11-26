package com.oszika.exemplorecycleviewcardview;

public class ItemModel {
    String texto;
    int imageResId;

    public ItemModel(String texto, int imageResId) {
        this.texto = texto;
        this.imageResId = imageResId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImage() {
        return imageResId;
    }

    public void setImage(int imageResId) {
        this.imageResId = imageResId;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "texto='" + texto + '\'' +
                ", imageResId=" + imageResId +
                '}';
    }
}
