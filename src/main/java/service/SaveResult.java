package service;

import model.Client;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveResult {
    public static void saveResults(List<Client> clients, String outputPath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            for (Client client : clients) {
                writer.println(client.getName() + " - " + client.getPurchaseSumTotal());
            }
            System.out.println("Результаты успешно сохранены в " + outputPath);
            // Вывод в консоль для наглядности
            for (Client client : clients) {
                System.out.println(client.getName() + " - " + client.getPurchaseSumTotal());
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
