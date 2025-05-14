import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeathCauseStatisticList {

    private final List<DeathCauseStatistic> deaths = new ArrayList<>();

    public void repopulate(Path path) {
        deaths.clear();
        // czytanie linia po lini
        try {
            Scanner scanner = new Scanner(path);
            scanner.nextLine();
            scanner.nextLine();
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                DeathCauseStatistic deathCauseStatistic = DeathCauseStatistic.fromCsvLine(line);
                deaths.add(deathCauseStatistic);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n){
        List<DeathCauseStatistic> mostDeadly = new ArrayList<>(deaths);
        // Sortowanie tekstu jak coś
        // String str;
        // str.compareTo() - poruwnuje
        // substring - wycionga czesc z tekstu <...;...)
        mostDeadly.sort((c1, c2) -> Integer.compare(c1.getAgeBracket(age).deathCount, c2.getAgeBracket(age).deathCount));
        // żeby odwrócić Collections.reverse(mostDeadly)
        return mostDeadly.subList(0, n);
    }
}
