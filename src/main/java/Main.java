import adapter.NotFormatOrderReaderAdapter;
import adapter.OrderSource;
import adapter.TxtOrderReaderAdapter;
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

        try {
            OrderSource txtSource = new TxtOrderReaderAdapter();
            OrderSource notFormatSource = new NotFormatOrderReaderAdapter();

            // Читаем заказы из источников
            List<Order> allOrders;
            try {
                allOrders = new ArrayList<>(txtSource.readOrders());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                allOrders.addAll(notFormatSource.readOrders());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

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
