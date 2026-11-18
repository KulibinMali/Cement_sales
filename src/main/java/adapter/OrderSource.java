package adapter;

import model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderSource {
    List<Order> readOrders() throws IOException;
}