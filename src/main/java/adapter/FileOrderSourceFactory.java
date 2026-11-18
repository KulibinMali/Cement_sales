package adapter;

import model.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOrderSourceFactory {

    public List<Order> readOrdersFromDirectory(String directoryPath) throws IOException {
        Path dirPath = Paths.get(directoryPath);

        List<Order> allOrders = new ArrayList<>();

        // Получаем список всех файлов в папке
        try (Stream<Path> paths = Files.list(dirPath)) {
            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    // Исключаем файл с результатом
                    .filter(p -> !p.getFileName().toString().equals("result.txt"))
                    .collect(Collectors.toList());

            // Получаем полный путь к файлу
            for (Path file : files) {
                String fullPath = file.toString();

                try {
                    // Определяем формат - по расширению или первой строке
                    String fileName = file.getFileName().toString();
                    String firstLine = Files.lines(file).findFirst().orElse("");

                    if (fileName.endsWith(".txt")) {
                        allOrders.addAll(new TxtOrderReaderAdapter(fullPath).readOrders());
                    } else if (firstLine.contains("#")) {
                        allOrders.addAll(new NotFormatOrderReaderAdapter(fullPath).readOrders());
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка чтения " + file.getFileName() + ": " + e.getMessage());
                }
            }
        }
        return allOrders;
    }
}