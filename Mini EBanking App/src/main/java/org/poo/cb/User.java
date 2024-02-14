package org.poo.cb;

import java.util.ArrayList;

public class User {
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final ArrayList<User> friends;
    private final Portfolio portfolio;
    private boolean hasPremium;
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////



    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////
    public User(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.friends = new ArrayList<>();
        this.portfolio = new Portfolio(this);
        this.hasPremium = false;
    }
    public String getEmail() {
        return this.email;
    }
    public ArrayList<User> getFriends() {
        return this.friends;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getAddress() {
        return this.address;
    }
    public ArrayList<Account> getAccounts() {
        return this.portfolio.getAccounts();
    }
    public Portfolio getPortfolio() {
        return this.portfolio;
    }
    public void setPremium(boolean hasPremium) {
        this.hasPremium = hasPremium;
    }
    public boolean getPremiumState() {
        return this.hasPremium;
    }
    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////



    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
    public void addFriend(User friend) {
        this.friends.add(friend);
    }
    public void addAccount(Account account) {
        this.portfolio.addAccount(account);
    }
    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
}
