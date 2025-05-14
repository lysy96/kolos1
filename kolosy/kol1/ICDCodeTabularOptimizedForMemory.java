import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ICDCodeTabularOptimizedForMemory implements ICDCodeTabular{

    private final Path path;
    private Pattern PATTERN = Pattern.compile("^[a-zA-Z][0-9]{2}");

    public ICDCodeTabularOptimizedForMemory(Path path) {
        this.path = path;
    }

    @Override
    public String getDescription(String code) throws IndexOutOfBoundsException{
        if (!PATTERN.matcher(code).find()) {
            throw new IndexOutOfBoundsException();
        }
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNext()){
                String line = scanner.nextLine().trim();
                String[] split = line.split(" ", 2);
                if (split[0].startsWith(code)) {
                    return split[1];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new IndexOutOfBoundsException();
    }
}
