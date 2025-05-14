import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular{

    private Pattern PATTERN = Pattern.compile("^[a-zA-Z][0-9]{2}");
    private final Map<String, String> codeToDescriptionMap = new HashMap<>();
    //kod wczytuje choroby wraz z opisami
    public ICDCodeTabularOptimizedForTime(Path path) {
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNext()){
                String line = scanner.nextLine().trim();
                String[] split = line.split(" ", 2);
                if (PATTERN.matcher(split[0]).find()) {
                    String code = split[0];
                    String description = split[1];
                    codeToDescriptionMap.put(code, description);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDescription(String code) throws IndexOutOfBoundsException{
        if (!PATTERN.matcher(code).find()) {
            throw new IndexOutOfBoundsException();
        }
        String description = codeToDescriptionMap.get(code);
        if(description == null){
            throw new IndexOutOfBoundsException();
        }
        return description;
    }

}
