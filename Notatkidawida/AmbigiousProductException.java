import java.util.List;

public class AmbigiousProductException extends Exception {
    public AmbigiousProductException(List<String> names) {
        super("Znaleziono wiele produktów: " + String.join(", ", names));
    }
}