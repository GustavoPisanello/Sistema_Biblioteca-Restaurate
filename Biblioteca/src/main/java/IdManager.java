import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IdManager {

    public int generateNewId(int lastId, String archiveName) throws IOException {
        if (lastId == 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archiveName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int currentId = Integer.parseInt(parts[0]);
                    if (currentId > lastId) {
                        lastId = currentId;
                    }
                }
            }
        }
        return ++lastId;
    }

}
