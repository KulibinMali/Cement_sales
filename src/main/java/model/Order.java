package model;

import java.time.LocalDateTime;

public class Order {
    private LocalDateTime createDateTime; // Дата и время создания заказа
    private String kontrName; // Наименование контрагента
    private int cementCount; // Кол-во цемента, кг

    public Order(LocalDateTime createDateTime, String kontrName, int cementCount) {
        this.createDateTime = createDateTime;
        this.kontrName = kontrName;
        this.cementCount = cementCount;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public String getKontrName() {
        return kontrName;
    }

    public int getCementCount() {
        return cementCount;
    }

    @Override
    public String toString() {
        return "Дата и время заказа: " + createDateTime +
                ", Контрагент: " + kontrName +
                ", Кол-во цемента, кг: " + cementCount;
    }
}
