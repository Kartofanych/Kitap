package com.example.irekonelove4;

public class SliderItem {
    private int image;
    private String name,autor;
    public SliderItem(int image, String name, String autor){
        this.image = image;
        this.name = name;
        this.autor = autor;
    }
    public int getImage(){
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAutor() {
        return autor;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
