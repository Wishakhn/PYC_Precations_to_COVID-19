package com.oktwohundred.corona.preventcorona.Model;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class Item {

    private final int id;
    private final String name;

    private final int image;

    public Item(int id, String name, int image) {
        this.id = id;
        this.name = name;

        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getImage() {
        return image;
    }
}
