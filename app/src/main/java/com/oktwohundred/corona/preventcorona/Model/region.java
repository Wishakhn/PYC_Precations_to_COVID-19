package com.oktwohundred.corona.preventcorona.Model;

public class region {
    String risk;
    String chance;
    String country;
    String cities;
    String survivors;
    String victims;
    String death;

    public region() {
    }

    public region(String risk, String chance, String country, String cities, String survivors, String victims, String death) {
        this.risk = risk;
        this.chance = chance;
        this.country = country;
        this.cities = cities;
        this.survivors = survivors;
        this.victims = victims;
        this.death = death;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getChance() {
        return chance;
    }

    public void setChance(String chance) {
        this.chance = chance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getSurvivors() {
        return survivors;
    }

    public void setSurvivors(String survivors) {
        this.survivors = survivors;
    }

    public String getVictims() {
        return victims;
    }

    public void setVictims(String victims) {
        this.victims = victims;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }
}
