package org.poo.cb;

public abstract class Account {
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////
    protected String currency;
    protected float money;
    protected User owner;
    protected int index;
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////



    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////
    public Account(User owner) {
        this.money = 0;
        this.owner = owner;
    }
    public String getCurrency() {
        return this.currency;
    }
    public int getIndex() {
        return this.index;
    }
    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////



    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
    public void addMoney(float money) {
        this.money += money;
    }
    public float getMoney() {
        return this.money;
    }
    public void withdrawMoney(float money) {
        this.money -= money;
    }
    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
}

class USDAccount extends Account {
    public USDAccount(User owner) {
        super(owner);
        this.currency = "USD";
        this.index = 5;
    }
}

class EURAccount extends Account {
    public EURAccount(User owner) {
        super(owner);
        this.currency = "EUR";
        this.index = 1;
    }
}

class GBPAccount extends Account {
    public GBPAccount(User owner) {
        super(owner);
        this.currency = "GBP";
        this.index = 2;
    }
}

class JPYAccount extends Account {
    public JPYAccount(User owner) {
        super(owner);
        this.currency = "JPY";
        this.index = 3;
    }
}

class CADAccount extends Account {
    public CADAccount(User owner) {
        super(owner);
        this.currency = "CAD";
        this.index = 4;
    }
}
/////////////////////////////////////////// USING THE FACTORY DESIGN PATTERN ///////////////////////////////////////////
class Factory {
    public Account createAccount(String currency, User owner) {
        return switch (currency) {
            case "USD" -> new USDAccount(owner);
            case "EUR" -> new EURAccount(owner);
            case "GBP" -> new GBPAccount(owner);
            case "JPY" -> new JPYAccount(owner);
            case "CAD" -> new CADAccount(owner);
            default -> null;
        };
    }
}
/////////////////////////////////////////// USING THE FACTORY DESIGN PATTERN ///////////////////////////////////////////