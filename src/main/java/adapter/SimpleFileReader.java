package adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SimpleFileReader {
    public List<String> loadLines(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }
}
