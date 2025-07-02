/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    double cash = 10000;
    Map<String, Integer> holdings = new HashMap<>();

    void buy(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost > cash) {
            System.out.println("Not enough cash.");
            return;
        }
        cash -= cost;
        holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " of " + stock.symbol);
    }

    void sell(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.symbol, 0);
        if (quantity > owned) {
            System.out.println("Not enough shares.");
            return;
        }
        cash += stock.price * quantity;
        holdings.put(stock.symbol, owned - quantity);
        System.out.println("Sold " + quantity + " of " + stock.symbol);
    }

    void show() {
        System.out.println("Cash: $" + cash);
        for (String symbol : holdings.keySet()) {
            System.out.println(symbol + ": " + holdings.get(symbol) + " shares");
        }
    }
}

public class SimpleTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Simple market
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150));
        market.put("GOOG", new Stock("GOOG", 2800));

        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("\n1.View Market 2.Buy 3.Sell 4.Portfolio 5.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                for (Stock stock : market.values()) {
                    System.out.println(stock.symbol + ": $" + stock.price);
                }
            } else if (choice == 2) {
                System.out.print("Stock symbol: ");
                String symbol = sc.next().toUpperCase();
                if (!market.containsKey(symbol)) {
                    System.out.println("Invalid stock.");
                    continue;
                }
                System.out.print("Quantity: ");
                int qty = sc.nextInt();
                portfolio.buy(market.get(symbol), qty);
            } else if (choice == 3) {
                System.out.print("Stock symbol: ");
                String symbol = sc.next().toUpperCase();
                if (!market.containsKey(symbol)) {
                    System.out.println("Invalid stock.");
                    continue;
                }
                System.out.print("Quantity: ");
                int qty = sc.nextInt();
                portfolio.sell(market.get(symbol), qty);
            } else if (choice == 4) {
                portfolio.show();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
