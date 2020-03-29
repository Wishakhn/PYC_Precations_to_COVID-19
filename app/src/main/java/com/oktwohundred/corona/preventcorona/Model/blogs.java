package com.oktwohundred.corona.preventcorona.Model;

public class blogs {
    int image;
    String articleby;
    String blogger;
    String title;
    String descrip;
    String url;

    public blogs(int image, String articleby, String blogger, String title, String descrip, String url) {
        this.image = image;
        this.articleby = articleby;
        this.blogger = blogger;
        this.title = title;
        this.descrip = descrip;
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getArticleby() {
        return articleby;
    }

    public void setArticleby(String articleby) {
        this.articleby = articleby;
    }

    public String getBlogger() {
        return blogger;
    }

    public void setBlogger(String blogger) {
        this.blogger = blogger;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
