package model;

public class PriceConfig {
    private final int pricePerKg; // Цена за кг
    private final int startDiscount; // Максимальная скидка
    private final int discountStep; // Шаг скидки

    public PriceConfig(int pricePerKg, int startDiscount, int discountStep) {
        this.pricePerKg = pricePerKg;
        this.startDiscount = startDiscount;
        this.discountStep = discountStep;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getStartDiscount() {
        return startDiscount;
    }

    public int getDiscountStep() {
        return discountStep;
    }
}