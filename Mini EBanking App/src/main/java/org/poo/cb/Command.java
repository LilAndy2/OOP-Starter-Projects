package org.poo.cb;

import java.util.ArrayList;

/////////////////////////////////////////// USING THE COMMAND DESIGN PATTERN ///////////////////////////////////////////
public interface Command {
    void execute();
}

class CreateUserCommand implements Command {
    private final App eBankingApp;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;
    public CreateUserCommand(App eBankingApp, String email, String firstName, String lastName, String address) {
        this.eBankingApp = eBankingApp;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    @Override
    public void execute() {
        User user = new User(email, firstName, lastName, address);
        if (eBankingApp.checkIfUserExists(email)) {
            System.out.println("User with " + email + " already exists");
        } else {
            eBankingApp.addUser(user);
        }
    }
}

class AddFriendCommand implements Command {
    private final App eBankingApp;
    private final String myEmail;
    private final String friendEmail;

    public AddFriendCommand(App eBankingApp, String myEmail, String friendEmail) {
        this.eBankingApp = eBankingApp;
        this.myEmail = myEmail;
        this.friendEmail = friendEmail;
    }
    @Override
    public void execute() {
        if (!eBankingApp.checkIfUserExists(myEmail)) {
            System.out.println("User with " + myEmail + " doesn't exist");
        } else if (!eBankingApp.checkIfUserExists(friendEmail)) {
            System.out.println("User with " + friendEmail + " doesn't exist");
        } else if (eBankingApp.checkIfTheyAreFriends(myEmail, friendEmail)) {
            System.out.println("User with " + friendEmail + " is already a friend");
        } else {
            User myUser = eBankingApp.findUser(myEmail);
            User friendUser = eBankingApp.findUser(friendEmail);
            eBankingApp.addFriend(myUser, friendUser);
        }
    }
}

class AddAccountCommand implements Command {
    private final App eBankingApp;
    private final String email;
    private final String currency;
    public AddAccountCommand(App eBankingApp, String email, String currency) {
        this.eBankingApp = eBankingApp;
        this.email = email;
        this.currency = currency;
    }
    @Override
    public void execute() {
        User user = eBankingApp.findUser(email);
        if (eBankingApp.checkIfAccountExists(user, currency)) {
            System.out.println("Account in currency " + currency + " already exists for user");
        } else {
            Factory factory = new Factory();
            Account account = factory.createAccount(currency, user);
            eBankingApp.addAccount(user, account);
        }
    }
}

class AddMoneyCommand implements Command {
    private final App eBankingApp;
    private final String email;
    private final String currency;
    private final float money;
    public AddMoneyCommand(App eBankingApp, String email, String currency, float money) {
        this.eBankingApp = eBankingApp;
        this.email = email;
        this.currency = currency;
        this.money = money;
    }
    @Override
    public void execute() {
        User user = eBankingApp.findUser(email);
        ArrayList<Account> accounts = user.getAccounts();
        for (Account account : accounts) {
            if (account.getCurrency().equals(currency)) {
                account.addMoney(money);
                break;
            }
        }
    }
}

class ExchangeMoneyCommand implements Command {
    private final App eBankingApp;
    private final String email;
    private final String sourceCurrency;
    private final String destinationCurrency;
    private final float destinationMoney;
    private final String[][] exchangeRates;
    public ExchangeMoneyCommand(App eBankingApp, String email, String sourceCurrency, String destinationCurrency,
                                float destinationMoney, String[][] exchangeRates) {
        this.eBankingApp = eBankingApp;
        this.email = email;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.destinationMoney = destinationMoney;
        this.exchangeRates = exchangeRates;
    }
    @Override
    public void execute() {
        User user = eBankingApp.findUser(email);
        Account destinationAccount = eBankingApp.findAccount(user, destinationCurrency);
        Account sourceAccount = eBankingApp.findAccount(user, sourceCurrency);
        float money = destinationMoney * Float.parseFloat(exchangeRates[destinationAccount.getIndex()]
                [sourceAccount.getIndex()]);

        if (money > sourceAccount.getMoney()) {
            System.out.println("Insufficient amount in account " + sourceCurrency + " for exchange");
        } else if (money > sourceAccount.getMoney() / 2) {
            float commission = 0;
            if (!user.getPremiumState()) {
                commission = money / 100;
            }
            eBankingApp.withdrawMoneyFromAccount(sourceAccount, money + commission);
            eBankingApp.addMoneyToAccount(destinationAccount, destinationMoney);
        } else {
            eBankingApp.withdrawMoneyFromAccount(sourceAccount, money);
            eBankingApp.addMoneyToAccount(destinationAccount, destinationMoney);
        }
    }
}

class TransferMoneyCommand implements Command {
    private final App eBankingApp;
    private final String myEmail;
    private final String friendEmail;
    private final String currency;
    private final float money;
    public TransferMoneyCommand(App eBankingApp, String myEmail, String friendEmail, String currency, float money) {
        this.eBankingApp = eBankingApp;
        this.myEmail = myEmail;
        this.friendEmail = friendEmail;
        this.currency = currency;
        this.money = money;
    }
    @Override
    public void execute() {
        User myUser = eBankingApp.findUser(myEmail);
        User friendUser = eBankingApp.findUser(friendEmail);
        Account myAccount = eBankingApp.findAccount(myUser, currency);
        Account friendAccount = eBankingApp.findAccount(friendUser, currency);

        if (money > myAccount.getMoney()) {
            System.out.println("Insufficient amount in account " + currency + " for transfer");
        } else if (!eBankingApp.checkIfTheyAreFriends(myEmail, friendEmail)) {
            System.out.println("You are not allowed to transfer money to " + friendEmail);
        } else {
            eBankingApp.transferMoney(myAccount, friendAccount, money);
        }
    }
}

class BuyStocksCommand implements Command {
    private final App eBankingApp;
    private final String email;
    private final String stockName;
    private final int numberOfStocks;
    private final String[][] stockValues;
    private final int companyCount;
    public BuyStocksCommand(App eBankingApp, String email, String stockName, int numberOfStocks, String[][] stockValues,
                            int companyCount) {
        this.eBankingApp = eBankingApp;
        this.email = email;
        this.stockName = stockName;
        this.numberOfStocks = numberOfStocks;
        this.stockValues = stockValues;
        this.companyCount = companyCount;
    }
    @Override
    public void execute() {
        float price = 0;

        for (int i = 0; i < companyCount; i++) {
            if (stockValues[i][0].equals(stockName)) {
                price = Float.parseFloat(stockValues[i][10]);
                break;
            }
        }

        float finalPrice = price * numberOfStocks;
        if (eBankingApp.findUser(email).getPremiumState() && eBankingApp.getStocksWithDiscount().contains(stockName)) {
            finalPrice *= 0.95F;
        }

        User user = eBankingApp.findUser(email);
        Account account = eBankingApp.findAccount(user, "USD");
        if (finalPrice > account.getMoney()) {
            System.out.println("Insufficient amount in account for buying stock");
        } else {
            eBankingApp.withdrawMoneyFromAccount(account, finalPrice);
            Portfolio portfolio = user.getPortfolio();
            Stock stock = new Stock(stockName, numberOfStocks);
            eBankingApp.addStockToPortfolio(portfolio, stock);
        }
    }
}

class RecommendStocksCommand implements Command {
    private final App eBankingApp;
    private final int companyCount;
    private final String[][] stockValues;
    public RecommendStocksCommand(App eBankingApp, int companyCount, String[][] stockValues) {
        this.eBankingApp = eBankingApp;
        this.companyCount = companyCount;
        this.stockValues = stockValues;
    }
    public void execute() {
        StringBuilder finalString = new StringBuilder();
        ArrayList<String> recommendedStocks = new ArrayList<>();
        finalString.append("{\"stocksToBuy\":[");
        for (int i = 1; i < companyCount; i++) {
            String stock = stockValues[i][0];
            float shortTermSMA = 0;
            float longTermSMA = 0;
            for (int j = 6; j < 11; j++) {
                shortTermSMA += Float.parseFloat(stockValues[i][j]);
            }
            for (int j = 1; j < 11; j++) {
                longTermSMA += Float.parseFloat(stockValues[i][j]);
            }
            if (shortTermSMA / 5 > longTermSMA / 10) {
                recommendedStocks.add(stock);
            }
        }

        for (int i = 0; i < recommendedStocks.size(); i++) {
            eBankingApp.addStockWithDiscount(recommendedStocks.get(i));
            finalString.append("\"").append(recommendedStocks.get(i)).append("\"");
            if (i != recommendedStocks.size() - 1) {
                finalString.append(",");
            }
        }
        finalString.append("]}");
        System.out.println(finalString);
    }
}

class ListUserCommand implements Command {
    private final App eBankingApp;
    private final String email;
    public ListUserCommand(App eBankingApp, String email) {
        this.eBankingApp = eBankingApp;
        this.email = email;
    }
    @Override
    public void execute() {
        if (!eBankingApp.checkIfUserExists(email)) {
            System.out.println("User with email " + email + " doesn't exist");
        } else {
            User user = eBankingApp.findUser(email);
            System.out.print("{\"email\":\"" + user.getEmail() + "\",\"firstname\":\"" + user.getFirstName() +
                    "\",\"lastname\":\"" + user.getLastName() + "\",\"address\":\"" + user.getAddress() +
                    "\",\"friends\":[");
            ArrayList<User> friends = user.getFriends();
            for (User friend : friends) {
                System.out.print("\"" + friend.getEmail() + "\"");
            }
            System.out.println("]}");
        }
    }
}

class ListPortfolioCommand implements Command {
    private final App eBankingApp;
    private final String email;
    public ListPortfolioCommand(App eBankingApp, String email) {
        this.eBankingApp = eBankingApp;
        this.email = email;
    }
    @Override
    public void execute() {
        if (!eBankingApp.checkIfUserExists(email)) {
            System.out.println("User with " + email + " doesn't exist");
        } else {
            User user = eBankingApp.findUser(email);
            Portfolio portfolio = user.getPortfolio();
            System.out.print("{\"stocks\":[");
            Iterator<Stock> stocksIterator = portfolio.createIterator();
            while (stocksIterator.hasNext()) {
                Stock stock = stocksIterator.next();
                System.out.print("{\"stockName\":\"" + stock.getName() + "\",\"amount\":" + stock.getAmount() + "}");
                if (stocksIterator.hasNext()) {
                    System.out.print(",");
                }
            }
            System.out.print("],\"accounts\":[");
            ArrayList<Account> accounts = portfolio.getAccounts();
            for (Account account : accounts) {
                System.out.print("{\"currencyName\":\"" + account.getCurrency() + "\",\"amount\":\"" +
                        eBankingApp.formatMoney(account.getMoney()) + "\"}");
                if (accounts.indexOf(account) != accounts.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println("]}");
        }
    }
}

class BuyPremiumCommand implements Command {
    private final App eBankingApp;
    private final String email;
    public BuyPremiumCommand(App eBankingApp, String email) {
        this.eBankingApp = eBankingApp;
        this.email = email;
    }
    @Override
    public void execute() {
        if (!eBankingApp.checkIfUserExists(email)) {
            System.out.println("User with " + email + " doesn't exist");
        } else {
            User user = eBankingApp.findUser(email);
            Account account = eBankingApp.findAccount(user, "USD");
            if (account.getMoney() < 100) {
                System.out.println("Insufficient amount in account for buying premium option");
            } else {
                account.withdrawMoney(100);
                user.setPremium(true);
            }
        }
    }
}
/////////////////////////////////////////// USING THE COMMAND DESIGN PATTERN ///////////////////////////////////////////
