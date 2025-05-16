import java.util.*;

public class Cart {
    private final Map<Product, Integer> contents = new HashMap<>();

    public void addProduct(Product product, int amount) {
        contents.put(product, amount);
    }

    public double getPrice(int year, int month) {
        return contents.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice(year, month) * e.getValue())
                .sum();
    }

    public double getInflation(int y1, int m1, int y2, int m2) {
        double price1 = getPrice(y1, m1);
        double price2 = getPrice(y2, m2);

        int months = (y2 - y1) * 12 + (m2 - m1);
        if (months <= 0) throw new IllegalArgumentException("Niewłaściwy zakres miesięcy.");

        return ((price2 - price1) / price1) * 100.0 / months * 12;
    }
}