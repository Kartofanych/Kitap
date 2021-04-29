package com.example.irekonelove4.Models;

import android.widget.ImageView;

public class Kniga {

    private String Name;
    private String Autor;
    private ImageView KitapImage;


    public Kniga(String name, String Autor, ImageView kitapImage) {
        this.Name = name;
        this.Autor = Autor;
        this.KitapImage = kitapImage;
    }

    public Kniga(int i, int i1) {

    }

    public Kniga() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ImageView getKitapImage() {
        return KitapImage;
    }

    public void setKitapImage(ImageView kitapImage) {
        this.KitapImage = kitapImage;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        this.Autor = autor;
    }

}