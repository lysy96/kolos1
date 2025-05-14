import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class AnalogClock extends Clock {

    public void toSvg(Path path) throws IOException {
        int rotateHour = (this.hh % 12) * (360 / 12);
        int rotateMinute = this.mm * (360 / 60);
        int rotateSecond = this.ss * (360 / 60);
        String svg = String.format("""
                <svg width="200" height="200" viewBox="-100 -100 200 200" xmlns="http://www.w3.org/2000/svg">
                  <!-- Tarcza zegara -->
                  <circle cx="0" cy="0" r="90" fill="none" stroke="black" stroke-width="2" />
                  <g text-anchor="middle">
                    <text x="0" y="-80" dy="6">12</text>
                    <text x="80" y="0" dy="4">3</text>
                    <text x="0" y="80" dy="6">6</text>
                    <text x="-80" y="0" dy="4">9</text>
                  </g>
                
                  <!-- Godziny -->
                  <line x1="0" y1="0" x2="0" y2="-50" stroke="black" stroke-width="4" transform="rotate(%s)" />
                
                  <!-- Minuty -->
                  <line x1="0" y1="0" x2="0" y2="-70" stroke="black" stroke-width="2" transform="rotate(%s)" />
                
                  <!-- Sekundy -->
                  <line x1="0" y1="0" x2="0" y2="-80" stroke="red" stroke-width="1" transform="rotate(%s)" />
                </svg>
                """, rotateHour, rotateMinute, rotateSecond);
        try (FileWriter fileWriter = new FileWriter(String.valueOf(path))) {
            fileWriter.write(svg);
        }
    }

}

