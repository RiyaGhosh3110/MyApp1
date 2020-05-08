package com.example.myapp1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Master {
    String bittergourd,onion,potato,tomato,pumpkin,pepper,ginger,garlic,cabbage;


    public Master() {
        this.bittergourd = "0";
        this.onion = "0";
        this.potato = "0";
        this.tomato = "0";
        this.pumpkin = "0";
        this.pepper = "0";
        this.ginger = "0";
        this.garlic = "0";
        this.cabbage = "0";
    }

    public Master(String bittergourd, String onion, String potato, String tomato, String pumpkin, String pepper, String ginger, String garlic, String cabbage) {
        this.bittergourd = bittergourd;
        this.onion = onion;
        this.potato = potato;
        this.tomato = tomato;
        this.pumpkin = pumpkin;
        this.pepper = pepper;
        this.ginger = ginger;
        this.garlic = garlic;
        this.cabbage = cabbage;
    }

    public String getBittergourd() {
        return bittergourd;
    }

    public void setBittergourd(String bittergourd) {
        this.bittergourd = bittergourd;
    }

    public String getOnion() {
        return onion;
    }

    public void setOnion(String onion) {
        this.onion = onion;
    }

    public String getPotato() {
        return potato;
    }

    public void setPotato(String potato) {
        this.potato = potato;
    }

    public String getTomato() {
        return tomato;
    }

    public void setTomato(String tomato) {
        this.tomato = tomato;
    }

    public String getPumpkin() {
        return pumpkin;
    }

    public void setPumpkin(String pumpkin) {
        this.pumpkin = pumpkin;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(String pepper) {
        this.pepper = pepper;
    }

    public String getGinger() {
        return ginger;
    }

    public void setGinger(String ginger) {
        this.ginger = ginger;
    }

    public String getGarlic() {
        return garlic;
    }

    public void setGarlic(String garlic) {
        this.garlic = garlic;
    }

    public String getCabbage() {
        return cabbage;
    }

    public void setCabbage(String cabbage) {
        this.cabbage = cabbage;
    }
}

