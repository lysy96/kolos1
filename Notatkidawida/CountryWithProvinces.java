import java.time.LocalDate;

public class CountryWithProvinces extends Country {
    private final Country[] provinces;

    public CountryWithProvinces(String name, Country[] provinces) {
        super(name);
        this.provinces = provinces;
    }

    @Override
    public int getConfirmedCases(LocalDate date) {
        int sum = 0;
        for (Country province : provinces) {
            sum += province.getConfirmedCases(date);
        }
        return sum;
    }

    @Override
    public int getDeaths(LocalDate date) {
        int sum = 0;
        for (Country province : provinces) {
            sum += province.getDeaths(date);
        }
        return sum;
    }
}