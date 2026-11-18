package service;

import model.Order;
import model.PriceConfig;

public class MathCalc {
    public static double calculateOrderCost(Order order, int queueNumber, PriceConfig config) {

        // Вычисляем текущую скидку: стартовая - (номер заказа * шаг)
        int currentDiscount = config.getStartDiscount() - (queueNumber * config.getDiscountStep());

        // Скидка не может быть меньше 0
        if (currentDiscount < 0) {
            currentDiscount = 0;
        }

        int priceWithoutDiscount = order.getCementCount() * config.getPricePerKg();
        double discountAmount = priceWithoutDiscount * (currentDiscount / 100.0);

        return priceWithoutDiscount - discountAmount;
    }
}
