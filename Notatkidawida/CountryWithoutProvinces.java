import java.time.LocalDate;
import java.util.*;

public class CountryWithoutProvinces extends Country {
    private final Map<LocalDate, Integer> confirmedMap = new HashMap<>();
    private final Map<LocalDate, Integer> deathsMap = new HashMap<>();

    public CountryWithoutProvinces(String name) {
        super(name);
    }

    public void addDailyStatistic(LocalDate date, int confirmed, int deaths) {
        confirmedMap.put(date, confirmed);
        deathsMap.put(date, deaths);
    }

    @Override
    public int getConfirmedCases(LocalDate date) {
        return confirmedMap.getOrDefault(date, 0);
    }

    @Override
    public int getDeaths(LocalDate date) {
        return deathsMap.getOrDefault(date, 0);
    }
}