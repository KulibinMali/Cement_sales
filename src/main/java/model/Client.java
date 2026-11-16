package model;

public class Client {
    private String name; // Наименование контрагента
    private double purchaseSumTotal; // Общая сумма заказов, руб

    public Client(String name) {
        this.name = name;
        this.purchaseSumTotal = 0;
    }

    public String getName() {
        return name;
    }

    public double getPurchaseSumTotal() {
        return purchaseSumTotal;
    }

    public void addToSum(double sum) {
        this.purchaseSumTotal += sum;
    }

    @Override
    public String toString() {
        return "model.Client{name=" + name +
                ", totalSum=" + purchaseSumTotal;
    }
}