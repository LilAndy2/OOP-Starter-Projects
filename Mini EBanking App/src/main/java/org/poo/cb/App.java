package org.poo.cb;

import java.util.ArrayList;

public class App {
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////
    private static ArrayList<User> users;
    private static ArrayList<String> stocksWithDiscount;
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////



    //////////////////////////////////////// USING THE SINGLETON DESIGN PATTERN ////////////////////////////////////////
    private static App uniqueAppInstance;
    private App() {
        if (users == null)
            users = new ArrayList<>();
        if (stocksWithDiscount == null)
            stocksWithDiscount = new ArrayList<>();
    }
    public static App appInstance() {
        if (uniqueAppInstance == null) {
            uniqueAppInstance = new App();
        }
        return uniqueAppInstance;
    }
    //////////////////////////////////////// USING THE SINGLETON DESIGN PATTERN ////////////////////////////////////////



    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
    public void removeAppData() {
        users = null;
        uniqueAppInstance = null;
        stocksWithDiscount = null;
    }
    public boolean checkIfUserExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public void addUser(User user) {
        users.add(user);
    }
    public boolean checkIfTheyAreFriends(String myEmail, String friendEmail) {
        for (User user : users) {
            if (user.getEmail().equals(myEmail)) {
                for (User friend : user.getFriends()) {
                    if (friend.getEmail().equals(friendEmail)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public User findUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public void addFriend(User myUser, User friendUser) {
        myUser.addFriend(friendUser);
        friendUser.addFriend(myUser);
    }
    public void addAccount(User user, Account account) {
        user.addAccount(account);
    }
    public boolean checkIfAccountExists(User user, String currency) {
        ArrayList<Account> accounts = user.getAccounts();
        for (Account account : accounts) {
            if (account.getCurrency().equals(currency)) {
                return true;
            }
        }
        return false;
    }
    public Account findAccount(User user, String currency) {
        ArrayList<Account> accounts = user.getAccounts();
        for (Account account : accounts) {
            if (account.getCurrency().equals(currency)) {
                return account;
            }
        }
        return null;
    }
    public void withdrawMoneyFromAccount(Account account, float money) {
        account.withdrawMoney(money);
    }
    public void addMoneyToAccount(Account account, float money) {
        account.addMoney(money);
    }
    public String formatMoney(float money) {
        return String.format("%.2f", money).replace(",", ".");
    }
    public void transferMoney(Account fromAccount, Account toAccount, float money) {
        fromAccount.withdrawMoney(money);
        toAccount.addMoney(money);
    }
    public void addStockToPortfolio(Portfolio portfolio, Stock stock) {
        portfolio.addStock(stock);
    }
    public ArrayList<String> getStocksWithDiscount() {
        return stocksWithDiscount;
    }
    public void addStockWithDiscount(String stock) {
        stocksWithDiscount.add(stock);
    }
    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
}
