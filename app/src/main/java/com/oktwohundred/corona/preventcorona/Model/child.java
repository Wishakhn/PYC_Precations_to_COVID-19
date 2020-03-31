package com.oktwohundred.corona.preventcorona.Model;

public class child {
    String childName;
    String childDescrip;
    String childId;


    public child(String childName, String childDescrip) {
        this.childName = childName;
        this.childDescrip = childDescrip;
    }

    public child(String childName, String childDescrip, String childId) {
        this.childName = childName;
        this.childDescrip = childDescrip;
        this.childId = childId;
    }

    public child() {
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildDescrip() {
        return childDescrip;
    }

    public void setChildDescrip(String childDescrip) {
        this.childDescrip = childDescrip;
    }
}
