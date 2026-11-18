package adapter;

import model.Order;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TxtOrderReaderAdapter implements OrderSource {

    private final SimpleFileReader reader = new SimpleFileReader();
    private final String filePath;

    public TxtOrderReaderAdapter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Order> readOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        for (String line : reader.loadLines(filePath)) {   // зовём чужой метод
            String[] parts = line.split("\\|");
            LocalDateTime createDateTime = LocalDateTime.parse(parts[0]);
            String kontrName = parts[1];
            int cementCount = Integer.parseInt(parts[2]);
            orders.add(new Order(createDateTime, kontrName, cementCount)); // переводим в наш формат
        }
        return orders;
    }
}
