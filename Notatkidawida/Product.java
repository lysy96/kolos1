import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Product {
    private String name;
    private static List<Product> products = new ArrayList<>();

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public abstract double getPrice(int year, int month);

    public static void clearProducts() {
        products.clear();
    }

    public static void addProducts(Function<Path, Product> factory, Path dir) {
        try (var stream = java.nio.file.Files.list(dir)) {
            stream.filter(p -> p.toString().endsWith(".csv")).forEach(p -> {
                try {
                    Product prod = factory.apply(p);
                    if (prod != null) {
                        products.add(prod);
                    }
                } catch (Exception e) {
                    System.err.println("Błąd wczytywania pliku: " + p.getFileName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Product getProduct(String prefix) throws AmbigiousProductException {
        List<Product> matched = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().startsWith(prefix)) {
                matched.add(p);
            }
        }

        if (matched.size() == 0) {
            throw new IndexOutOfBoundsException("Brak produktów o prefiksie: " + prefix);
        } else if (matched.size() == 1) {
            return matched.get(0);
        } else {
            List<String> names = new ArrayList<>();
            for (Product p : matched) names.add(p.getName());
            throw new AmbigiousProductException(names);
        }
    }
}