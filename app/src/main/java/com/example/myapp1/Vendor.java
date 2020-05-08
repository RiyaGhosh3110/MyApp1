package com.example.myapp1;


public class Vendor {
    Vegetable bittergourd,onion,potato,tomato,pumpkin,pepper,ginger,garlic,cabbage;

    public Vendor() {
        this.bittergourd = new Vegetable();
        this.onion = new Vegetable();
        this.potato = new Vegetable();
        this.tomato = new Vegetable();
        this.pumpkin = new Vegetable();
        this.pepper = new Vegetable();
        this.ginger = new Vegetable();
        this.garlic = new Vegetable();
        this.cabbage = new Vegetable();
    }

    public Vendor(Vegetable bittergourd, Vegetable onion, Vegetable potato, Vegetable tomato,
                  Vegetable pumpkin, Vegetable pepper, Vegetable ginger,
                  Vegetable garlic, Vegetable cabbage) {
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

    public Vegetable getBittergourd() {
        return bittergourd;
    }

    public void setBittergourd(Vegetable bittergourd) {
        this.bittergourd = bittergourd;
    }

    public Vegetable getOnion() {
        return onion;
    }

    public void setOnion(Vegetable onion) {
        this.onion = onion;
    }

    public Vegetable getPotato() {
        return potato;
    }

    public void setPotato(Vegetable potato) {
        this.potato = potato;
    }

    public Vegetable getTomato() {
        return tomato;
    }

    public void setTomato(Vegetable tomato) {
        this.tomato = tomato;
    }

    public Vegetable getPumpkin() {
        return pumpkin;
    }

    public void setPumpkin(Vegetable pumpkin) {
        this.pumpkin = pumpkin;
    }

    public Vegetable getPepper() {
        return pepper;
    }

    public void setPepper(Vegetable pepper) {
        this.pepper = pepper;
    }

    public Vegetable getGinger() {
        return ginger;
    }

    public void setGinger(Vegetable ginger) {
        this.ginger = ginger;
    }

    public Vegetable getGarlic() {
        return garlic;
    }

    public void setGarlic(Vegetable garlic) {
        this.garlic = garlic;
    }

    public Vegetable getCabbage() {
        return cabbage;
    }

    public void setCabbage(Vegetable cabbage) {
        this.cabbage = cabbage;
    }
}

class Vegetable{
    String weight,price;

    public Vegetable() {
        this.weight = "0";
        this.price = "0";
    }

    public Vegetable(String weight, String price) {
        this.weight = weight;
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}