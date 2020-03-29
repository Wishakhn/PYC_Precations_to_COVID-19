package com.oktwohundred.corona.preventcorona.Model;

public class feeds {
    String feedType;
    String feedName;
    String feedIntro;
    String feedDescrip;
    float feedRating;
    boolean isSaved;

    public feeds(String feedType, String feedName, String feedIntro, String feedDescrip, float feedRating, boolean isSaved) {
        this.feedType = feedType;
        this.feedName = feedName;
        this.feedIntro = feedIntro;
        this.feedDescrip = feedDescrip;
        this.feedRating = feedRating;
        this.isSaved = isSaved;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedIntro() {
        return feedIntro;
    }

    public void setFeedIntro(String feedIntro) {
        this.feedIntro = feedIntro;
    }

    public String getFeedDescrip() {
        return feedDescrip;
    }

    public void setFeedDescrip(String feedDescrip) {
        this.feedDescrip = feedDescrip;
    }

    public float getFeedRating() {
        return feedRating;
    }

    public void setFeedRating(float feedRating) {
        this.feedRating = feedRating;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
