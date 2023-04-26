package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;


import javax.xml.crypto.Data;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;
    public Store(String name, BigDecimal balance, BigDecimal profitMargin) throws IllegalArgumentException {
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.balance = balance;
        this.profitMargin = profitMargin;

    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Database database = Database.getInstance();
        Component component = database.getComponents().get(id);
        if (component == null) {
            throw new ProductNotFoundException();
        }
        if (component.getAmount() <= 0) {
            throw new OutOfStockException();
        }
        BigDecimal finalPrice = component.getPrice().multiply(profitMargin);
        if (customer.getBalance().compareTo(finalPrice) < 0) {
            throw new NotEnoughMoneyException();
        }
        database.decreaseComponentStock(id, 1);
        customer.setBalance(customer.getBalance().subtract(finalPrice));
        balance = balance.add(finalPrice);
        customer.addComponent(component);
        return component;
    }

    public List<Component> getAvailableComponents() {
        return Database.getInstance().getComponents().values().stream()
                .filter(c -> c.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByAmount() {
        return Database.getInstance().getComponents().values().stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return Database.getInstance().getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return Database.getInstance().getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    public List<Component> filterByType(Component.Type type) {
        return Database.getInstance().getComponents().values().stream()
                .filter(c -> c.getType() == type)
                .collect(Collectors.toList());
    }

    public BigDecimal getInventoryValue() {
        return Database.getInstance().getComponents().values().stream()
                .map(c -> c.getPrice().multiply(profitMargin).multiply(BigDecimal.valueOf(c.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        if (profitMargin.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }
}