import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Dodajemy produkty z katalogów
            Product.clearProducts();
            Product.addProducts(NonFoodProduct::fromCsv, Path.of("data/nonfood"));
            Product.addProducts(FoodProduct::fromCsv, Path.of("data/food"));

            // 2. Pobieramy produkty po prefixie
            try {
                Product p = Product.getProduct("Bur"); // jednoznaczny
                System.out.println("Znaleziono: " + p.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Product.getProduct("XYZ"); // nieistniejący
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Product.getProduct("Ja"); // wieloznaczny
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 3. Koszyk i inflacja
            Cart koszyk = new Cart();
            koszyk.addProduct(Product.getProduct("Bur"), 5);
            koszyk.addProduct(Product.getProduct("Jab"), 2);

            System.out.println("Cena koszyka w 01.2011: " + koszyk.getPrice(2011, 1));
            System.out.println("Cena koszyka w 01.2020: " + koszyk.getPrice(2020, 1));
            System.out.println("Inflacja: " + koszyk.getInflation(2011, 1, 2020, 1) + " %");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}