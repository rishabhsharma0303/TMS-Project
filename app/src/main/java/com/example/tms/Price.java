package com.example.tms;

public class Price {
    String item_name;
    int rupees;

    public Price(String item_name, int rupees) {
        this.item_name = item_name;
        this.rupees = rupees;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getRupees() {
        return rupees;
    }

    public void setRupees(int rupees) {
        this.rupees = rupees;
    }
}
