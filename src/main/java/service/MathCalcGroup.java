package service;

import model.Client;
import model.Order;
import model.PriceConfig;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MathCalcGroup {
    public static List<Client> aggregateClients(List<Order> sortedOrders, PriceConfig config) {
        Map<String, Client> clientsMap = new LinkedHashMap<>();

        for (int i = 0; i < sortedOrders.size(); i++) {
            Order order = sortedOrders.get(i);
            double cost = MathCalc.calculateOrderCost(order, i, config);
            clientsMap.putIfAbsent(order.getKontrName(), new Client(order.getKontrName()));
            clientsMap.get(order.getKontrName()).addToSum(cost);
        }

        return new ArrayList<>(clientsMap.values());
    }
}
