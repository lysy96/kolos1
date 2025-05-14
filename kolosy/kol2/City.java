import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class City {

    private String name;
    private int timeZone;
    private double x, y;

    public City(String name, int timeZone, double x, double y) {
        this.name = name;
        this.timeZone = timeZone;
        this.x = x;
        this.y = y;
    }


    //Abu Dhabi,4,24.4539 N, 54.3773 E
    //Bratysława,2,48.1486 N, 17.1077 E

    //      0       1       2           3
    //["Abu Dhabi", "4", "24.4539 N", " 54.3773 E"]
    // linijka ^
    private static City parseLine(String line){
        //String name, int timeZone, double x, double y
        String name;
        int timeZone;
        double x;
        double y;
        // ^ wczytać!!!!
        String[] linijka = line.split(",");
        name = linijka[0];
        timeZone = Integer.parseInt(linijka[1]);
        String[] xSplit = linijka[2].split(" ");
        String[] ySplit = linijka[3].trim().split(" ");
        x = Double.parseDouble(xSplit[0]);
        if(xSplit[1].equals("S")){
            x = -x;
        }
        y = Double.parseDouble(ySplit[0]);
        if(ySplit[1].equals("W")){
            y = -y;
        }

        return new City(name, timeZone, x, y);
    }

    public static Map<String, City> parseFile(Path path){

        Map<String, City> mapa = new HashMap<>();
        try {
            Scanner scanner = new Scanner(path);
            scanner.nextLine(); // skip 1 linii useless
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                City city = parseLine(line);
                mapa.put(city.name, city);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mapa;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTimeZone() {
        return timeZone;
    }
}
