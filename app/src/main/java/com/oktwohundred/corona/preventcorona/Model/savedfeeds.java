package com.oktwohundred.corona.preventcorona.Model;

public class savedfeeds {
    String feedType;
    String feedName;
    String feedIntro;
    String feedDescrip;
    String feedRating;

    public String getFeedRating() {
        return feedRating;
    }

    public void setFeedRating(String feedRating) {
        this.feedRating = feedRating;
    }

    public savedfeeds(String feedType, String feedName, String feedIntro, String feedDescrip, String feedRating) {
        this.feedType = feedType;
        this.feedName = feedName;
        this.feedIntro = feedIntro;
        this.feedDescrip = feedDescrip;
        this.feedRating = feedRating;
    }

    public savedfeeds() {
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


}
