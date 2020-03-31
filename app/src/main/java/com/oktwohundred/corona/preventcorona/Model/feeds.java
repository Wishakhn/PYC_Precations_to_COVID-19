package com.oktwohundred.corona.preventcorona.Model;

import java.util.List;

public class feeds {
    String feedId;
    String feedType;
    String feedName;
    String feedIntro;
    String feedDescrip;
    String feedRating;
    String isSaved;
    List<child> Ings;

    public String getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(String isSaved) {
        this.isSaved = isSaved;
    }

    public List<child> getIngs() {
        return Ings;
    }

    public void setIngs(List<child> ings) {
        Ings = ings;
    }

    public feeds(String feedId, String feedType, String feedName, String feedIntro, String feedDescrip, String feedRating, String isSaved) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.feedName = feedName;
        this.feedIntro = feedIntro;
        this.feedDescrip = feedDescrip;
        this.feedRating = feedRating;
        this.isSaved = isSaved;
    }

    public feeds() {
    }

    public feeds(String feedId, String feedType, String feedName, String feedIntro, String feedDescrip, String feedRating, String isSaved, List<child> ings) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.feedName = feedName;
        this.feedIntro = feedIntro;
        this.feedDescrip = feedDescrip;
        this.feedRating = feedRating;
        this.isSaved = isSaved;
        Ings = ings;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
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

    public String getFeedRating() {
        return feedRating;
    }

    public void setFeedRating(String feedRating) {
        this.feedRating = feedRating;
    }


}
