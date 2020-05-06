package com.example.myapp1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Master {
    String bittergourd,onion,potato;


    public Master() {
        this.bittergourd = "0";
        this.onion = "0";
        this.potato = "0";
    }

    public Master(String bittergourd, String onion, String potato) {
        this.bittergourd = bittergourd;
        this.onion = onion;
        this.potato = potato;
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
}
