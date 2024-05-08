package ar.com.problems.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Clients place orders to a stockbroker as strings. The order can be simple or multiple or empty.
 *
 * Type of a simple order: Quote/white-space/Quantity/white-space/Price/white-space/Status
 *
 * where Quote is formed of non-whitespace character, Quantity is an int, Price a double (with mandatory decimal point "." ), Status is represented by the letter B (buy) or the letter S (sell).
 *
 * Example:
 * "GOOG 300 542.0 B"
 *
 * A multiple order is the concatenation of simple orders with a comma between each.
 *
 * Example:
 * "ZNGA 1300 2.66 B, CLH15.NYM 50 56.32 B, OWW 1000 11.623 B, OGG 20 580.1 B"
 *
 * or
 *
 * "ZNGA 1300 2.66 B,CLH15.NYM 50 56.32 B,OWW 1000 11.623 B,OGG 20 580.1 B"
 *
 * To ease the stockbroker your task is to produce a string of type
 *
 * "Buy: b Sell: s" where b and s are 'double' formatted with no decimal, b representing the total price of bought stocks and s the total price of sold stocks.
 *
 * Example:
 * "Buy: 294990 Sell: 0"
 *
 * Unfortunately sometimes clients make mistakes. When you find mistakes in orders, you must pinpoint these badly formed orders and produce a string of type:
 *
 * "Buy: b Sell: s; Badly formed nb: badly-formed 1st simple order ;badly-formed nth simple order ;"
 *
 * where nb is the number of badly formed simple orders, b representing the total price of bought stocks with correct simple order and s the total price of sold stocks with correct simple order.
 *
 * Examples:
 * "Buy: 263 Sell: 11802; Badly formed 2: CLH16.NYM 50 56 S ;OWW 1000 11 S ;"
 *
 * "Buy: 100 Sell: 56041; Badly formed 1: ZNGA 1300 2.66 ;"
 *
 * Notes:
 * If the order is empty, Buy is 0 and Sell is 0 hence the return is: "Buy: 0 Sell: 0".
 *
 */
public class OrdersSummary {

    private static String account = "Buy: %d Sell: %d";
    private static String badorther = "Badly formed %d: %s ;";
    public static String balanceStatements(String lst) {

        String regularSTR = "[\\w\\.]+\\s[0-9]+\\s[0-9]*\\.[0-9]+\\s[BS]";

        List<String> itemsBuy = Arrays.stream(lst.split(","))
                .filter( s -> s.trim().matches(regularSTR))
                .filter( s -> s.trim().endsWith("B"))
                .map(s -> s.trim().split(" ", 2)[1].replace("B", ""))
                .collect(Collectors.toList());

        List<String> itemsSold = Arrays.stream(lst.split(","))
                .filter( s -> s.trim().matches(regularSTR))
                .filter( s -> s.trim().endsWith("S"))
                .map(s -> s.trim().split(" ", 2)[1].replace("S", ""))
                .collect(Collectors.toList());

        List<String> faildOrderStr = Arrays.stream(lst.split(","))
                .filter( s -> !s.trim().matches(regularSTR))
                .map(s -> s.trim())
                .collect(Collectors.toList());

        long cantErrors = faildOrderStr.stream().count();
        String ordersWithError = faildOrderStr.stream().collect(Collectors.joining(" ;"));

        double totalBuy = itemsBuy.stream().mapToDouble(s -> {
            String[] values = s.split(" ");
            int quantity = Integer.parseInt(values[0]);
            double price = Double.parseDouble(values[1]);

            return price * quantity;
        }).sum();

        double totalSold = itemsSold.stream().mapToDouble(s -> {
            String[] values = s.split(" ");
            int quantity = Integer.parseInt(values[0]);
            double price = Double.parseDouble(values[1]);

            return price * quantity;
        }).sum();


        StringBuilder strFinal = new StringBuilder();
        strFinal.append(String.format(account, Math.round(totalBuy), Math.round(totalSold)));

        if(cantErrors > 0){
            strFinal.append("; ").append(String.format(badorther, cantErrors, ordersWithError));
        }

        return strFinal.toString();
    }

}
