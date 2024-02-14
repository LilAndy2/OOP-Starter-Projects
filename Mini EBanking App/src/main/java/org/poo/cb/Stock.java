package org.poo.cb;

public class Stock {
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////
    private final String name;
    private final int amount;
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////



    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////
    public Stock(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
    public String getName() {
        return this.name;
    }
    public int getAmount() {
        return this.amount;
    }
    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////
}