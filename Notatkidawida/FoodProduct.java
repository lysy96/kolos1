import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FoodProduct extends Product {
    private final Map<String, Double[]> provincePrices = new HashMap<>();

    private FoodProduct() {}

    public static FoodProduct fromCsv(Path path) {
        FoodProduct product = new FoodProduct();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String nameLine = reader.readLine(); // nazwa produktu
            product.setName(nameLine);

            reader.readLine(); // nagłówek

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String province = parts[0];
                Double[] prices = new Double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    prices[i - 1] = Double.parseDouble(parts[i].replace(',', '.'));
                }
                product.provincePrices.put(province, prices);
            }
        } catch (IOException e) {
            return null;
        }

        return product;
    }

    public double getPrice(int year, int month, String province) {
        checkDate(year, month);
        Double[] prices = provincePrices.get(province);
        if (prices == null) throw new IndexOutOfBoundsException("Nie ma takiego województwa: " + province);
        int index = (year - 2010) * 12 + (month - 1);
        return prices[index];
    }

    @Override
    public double getPrice(int year, int month) {
        checkDate(year, month);
        int index = (year - 2010) * 12 + (month - 1);
        return provincePrices.values().stream()
                .mapToDouble(arr -> arr[index])
                .average()
                .orElseThrow(() -> new IndexOutOfBoundsException("Brak danych"));
    }

    private void checkDate(int year, int month) {
        if (year < 2010 || year > 2022 || month < 1 || month > 12 || (year == 2022 && month > 3)) {
            throw new IndexOutOfBoundsException("Niepoprawna data: " + month + "/" + year);
        }
    }
}