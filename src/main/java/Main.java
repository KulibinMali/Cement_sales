import adapter.*;
import model.Client;
import model.Order;
import model.PriceConfig;
import service.MathCalcGroup;
import service.SaveResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Настройка конфигурации (Цена, стартовая скидка, шаг скидки)
        PriceConfig config = new PriceConfig(10, 50, 5);

        FileOrderSourceFactory factory = new FileOrderSourceFactory();

        try {
            // Указываем путь к папке с файлами заказов
            String inputDirectory = "src/main";

            List<Order> allOrders = factory.readOrdersFromDirectory(inputDirectory);

            // Сортируем все заказы по времени подачи (от раннего к позднему)
            allOrders.sort(Comparator.comparing(Order::getCreateDateTime));

            System.out.println("Построчный вывод заказов:");
            for (Order order : allOrders) {
                System.out.println(order);
            }

            // Считаем суммы по клиентам
            List<Client> clients = MathCalcGroup.aggregateClients(allOrders, config);

            // Сохраняем в файл
            SaveResult.saveResults(clients, "src/main/result.txt");

        } catch (
                Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
