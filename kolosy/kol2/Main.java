import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        City.parseFile(Path.of("strefy.csv"));

        try {
            AnalogClock analogClock = new AnalogClock();
            analogClock.setCurrentTime();
            analogClock.toSvg(Path.of("skibidi.svg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}