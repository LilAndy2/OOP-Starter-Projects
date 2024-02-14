package org.poo.cb;

import java.util.ArrayList;

public class Portfolio {
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////
    private final User owner;
    private final ArrayList<Account> accounts;
    private final ArrayList<Stock> stocks;
    ////////////////////////////////////////////////////// FIELDS //////////////////////////////////////////////////////



    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////
    public Portfolio(User owner) {
        this.owner = owner;
        this.accounts = new ArrayList<>();
        this.stocks = new ArrayList<>();
    }
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }
    public ArrayList<Stock> getStocks() {
        return this.stocks;
    }
    ///////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS /////////////////////////////////////////



    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
    public void addAccount(Account account) {
        this.accounts.add(account);
    }
    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }
    public Iterator<Stock> createIterator() {
        return new PortfolioIterator(this.stocks);
    }
    ////////////////////////////////////////////////////// OTHERS //////////////////////////////////////////////////////
}


/////////////////////////////////////////// USING THE ITERATOR DESIGN PATTERN //////////////////////////////////////////
interface Iterator<T> {
    boolean hasNext();
    T next();
}

class PortfolioIterator implements Iterator<Stock> {
    private final ArrayList<Stock> stocks;
    private int index;

    public PortfolioIterator(ArrayList<Stock> stocks) {
        this.stocks = stocks;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.stocks.size();
    }

    @Override
    public Stock next() {
        if (!this.hasNext()) {
            return null;
        }
        return this.stocks.get(this.index++);
    }
}
/////////////////////////////////////////// USING THE ITERATOR DESIGN PATTERN //////////////////////////////////////////
