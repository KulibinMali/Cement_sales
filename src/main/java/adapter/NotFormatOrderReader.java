package adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NotFormatOrderReader {
    public List<String> loadLines() throws IOException {
        return Files.readAllLines(Path.of("src/main/discount_day_without_ext"));
    }
}
