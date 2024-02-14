package org.poo.cb;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args == null) {
            System.out.println("Running Main");
            return;
        }

        App eBankingApp = App.appInstance();

        String exchangeRatesFile = "src/main/resources/" + args[0];
        String stockValuesFile = "src/main/resources/" + args[1];
        String commandsFile = "src/main/resources/" + args[2];

        BufferedReader exchangeRatesReader = new BufferedReader(new FileReader(exchangeRatesFile));
        BufferedReader stockValuesReader = new BufferedReader(new FileReader(stockValuesFile));
        BufferedReader commandsReader = new BufferedReader(new FileReader(commandsFile));

        String[][] exchangeRates = new String[6][6];
        int k = 0;
        String exchangeRatesLine;
        while ((exchangeRatesLine = exchangeRatesReader.readLine()) != null) {
            String[] parts = exchangeRatesLine.split(",");
            System.arraycopy(parts, 0, exchangeRates[k], 0, parts.length);
            k++;
        }

        String[][] stockValues = new String[30][11];
        int companyCount = 0;
        String stockValuesLine;
        while ((stockValuesLine = stockValuesReader.readLine()) != null) {
            String[] parts = stockValuesLine.split(",");
            System.arraycopy(parts, 0, stockValues[companyCount], 0, parts.length);
            companyCount++;
        }

        String commandsLine;
        while ((commandsLine = commandsReader.readLine()) != null) {
            String[] parts = commandsLine.split(" ");
            if (parts[0].equals("CREATE") && parts[1].equals("USER")) {
                String email = parts[2];
                String firstName = parts[3];
                String lastName = parts[4];
                StringBuilder address = new StringBuilder();
                for (int i = 5; i < parts.length; i++) {
                    address.append(parts[i]);
                    if (i != parts.length - 1) {
                        address.append(" ");
                    }
                }
                Command createUserCommand = new CreateUserCommand(eBankingApp, email, firstName, lastName, address.toString());
                createUserCommand.execute();
            } else if (parts[0].equals("ADD") && parts[1].equals("FRIEND")) {
                String myEmail = parts[2];
                String friendEmail = parts[3];
                Command addFriendCommand = new AddFriendCommand(eBankingApp, myEmail, friendEmail);
                addFriendCommand.execute();
            } else if (parts[0].equals("ADD") && parts[1].equals("ACCOUNT")) {
                String email = parts[2];
                String currency = parts[3];
                Command addAccountCommand = new AddAccountCommand(eBankingApp, email, currency);
                addAccountCommand.execute();
            } else if (parts[0].equals("ADD") && parts[1].equals("MONEY")) {
                String email = parts[2];
                String currency = parts[3];
                float money = Float.parseFloat(parts[4]);
                Command addMoneyCommand = new AddMoneyCommand(eBankingApp, email, currency, money);
                addMoneyCommand.execute();
            } else if (parts[0].equals("LIST") && parts[1].equals("USER")) {
                String email = parts[2];
                Command listUserCommand = new ListUserCommand(eBankingApp, email);
                listUserCommand.execute();
            } else if (parts[0].equals("LIST") && parts[1].equals("PORTFOLIO")) {
                String email = parts[2];
                Command listPortfolioCommand = new ListPortfolioCommand(eBankingApp, email);
                listPortfolioCommand.execute();
            } else if (parts[0].equals("EXCHANGE") && parts[1].equals("MONEY")) {
                String email = parts[2];
                String sourceCurrency = parts[3];
                String destinationCurrency = parts[4];
                float destinationMoney = Float.parseFloat(parts[5]);
                Command exchangeMoneyCommand = new ExchangeMoneyCommand(eBankingApp, email, sourceCurrency, destinationCurrency, destinationMoney, exchangeRates);
                exchangeMoneyCommand.execute();
            } else if (parts[0].equals("TRANSFER") && parts[1].equals("MONEY")) {
                String myEmail = parts[2];
                String friendEmail = parts[3];
                String currency = parts[4];
                float money = Float.parseFloat(parts[5]);
                Command transferMoneyCommand = new TransferMoneyCommand(eBankingApp, myEmail, friendEmail, currency, money);
                transferMoneyCommand.execute();
            } else if (parts[0].equals("RECOMMEND") && parts[1].equals("STOCKS")) {
                Command recommendStocksCommand = new RecommendStocksCommand(eBankingApp, companyCount, stockValues);
                recommendStocksCommand.execute();
            } else if (parts[0].equals("BUY") && parts[1].equals("STOCKS")) {
                String email = parts[2];
                String stockName = parts[3];
                int numberOfStocks = Integer.parseInt(parts[4]);
                Command buyStocksCommand = new BuyStocksCommand(eBankingApp, email, stockName, numberOfStocks, stockValues, companyCount);
                buyStocksCommand.execute();
            } else if (parts[0].equals("BUY") && parts[1].equals("PREMIUM")) {
                String email = parts[2];
                Command buyPremiumCommand = new BuyPremiumCommand(eBankingApp, email);
                buyPremiumCommand.execute();
            }
        }
        eBankingApp.removeAppData();
    }
}