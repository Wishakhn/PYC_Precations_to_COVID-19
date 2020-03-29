package com.oktwohundred.corona.preventcorona.Model;

public class insights {
    String type;
    String title;
    String descrip;
    String url;

    public insights(String type, String title, String descrip, String url) {
        this.type = type;
        this.title = title;
        this.descrip = descrip;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
