import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public abstract class Country {
    private final String name;
    private static String confirmedPath;
    private static String deathsPath;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void setFiles(String confirmed, String deaths) throws FileNotFoundException {
        if (!Files.isReadable(Path.of(confirmed))) {
            throw new FileNotFoundException(confirmed);
        }
        if (!Files.isReadable(Path.of(deaths))) {
            throw new FileNotFoundException(deaths);
        }
        confirmedPath = confirmed;
        deathsPath = deaths;
    }

    private static class CountryColumns {
        public final int firstColumnIndex;
        public final int columnCount;

        public CountryColumns(int firstColumnIndex, int columnCount) {
            this.firstColumnIndex = firstColumnIndex;
            this.columnCount = columnCount;
        }
    }

    private static CountryColumns getCountryColumns(String[] countries, String target) throws CountryNotFoundException {
        int start = -1;
        int count = 0;

        for (int i = 1; i < countries.length; i++) {
            if (countries[i].equals(target)) {
                if (start == -1) {
                    start = i;
                }
                count++;
            }
        }

        if (start == -1) throw new CountryNotFoundException(target);
        return new CountryColumns(start, count);
    }

    public static Country fromCsv(String target) throws Exception {
        BufferedReader confReader = new BufferedReader(new FileReader(confirmedPath));
        BufferedReader deathReader = new BufferedReader(new FileReader(deathsPath));

        String[] header1 = confReader.readLine().split(";");
        String[] header2 = confReader.readLine().split(";");

        CountryColumns columns = getCountryColumns(header1, target);

        if (columns.columnCount == 1) {
            CountryWithoutProvinces c = new CountryWithoutProvinces(target);
            String confLine, deathLine;
            while ((confLine = confReader.readLine()) != null && (deathLine = deathReader.readLine()) != null) {
                String[] confData = confLine.split(";");
                String[] deathData = deathLine.split(";");
                LocalDate date = LocalDate.parse(confData[0], java.time.format.DateTimeFormatter.ofPattern("M/d/yy"));
                int cases = Integer.parseInt(confData[columns.firstColumnIndex]);
                int deaths = Integer.parseInt(deathData[columns.firstColumnIndex]);
                c.addDailyStatistic(date, cases, deaths);
            }
            confReader.close();
            deathReader.close();
            return c;
        } else {
            Country[] provinces = new Country[columns.columnCount];
            for (int i = 0; i < columns.columnCount; i++) {
                provinces[i] = new CountryWithoutProvinces(header2[columns.firstColumnIndex + i]);
            }

            String confLine, deathLine;
            while ((confLine = confReader.readLine()) != null && (deathLine = deathReader.readLine()) != null) {
                String[] confData = confLine.split(";");
                String[] deathData = deathLine.split(";");
                LocalDate date = LocalDate.parse(confData[0], java.time.format.DateTimeFormatter.ofPattern("M/d/yy"));
                for (int i = 0; i < provinces.length; i++) {
                    int cases = Integer.parseInt(confData[columns.firstColumnIndex + i]);
                    int deaths = Integer.parseInt(deathData[columns.firstColumnIndex + i]);
                    ((CountryWithoutProvinces) provinces[i]).addDailyStatistic(date, cases, deaths);
                }
            }

            confReader.close();
            deathReader.close();
            return new CountryWithProvinces(target, provinces);
        }
    }
}