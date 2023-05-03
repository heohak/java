package ee.taltech.iti0202.computerbuilder.tests;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.ComputerFactory;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoreTest {
    private Database database;
    private Customer customer;

    private Component component1;
    private Component component2;

    private Component component3;

    private Component component4;

    private Component component5;
    private Component component6;

    private Component component7;

    private Component component8;

    private Component component9;

    private Component component10;

    private Component component11;

    private Store store1;

    @BeforeEach
    void setUp() throws ProductAlreadyExistsException {
        ComputerFactory computerFactory = new ComputerFactory();
        database = Database.getInstance();
        customer = new Customer("John Doe", new BigDecimal("30000"));
        database.resetEntireDatabase();

        store1 = new Store("Store", new BigDecimal("1000"), new BigDecimal("1.4"));

        component1 = new Component("CPU", Component.Type.CPU, new BigDecimal("500"), "Intel", 80, 100);
        component2 = new Component("GPU", Component.Type.GPU, new BigDecimal("600"), "Nvidia", 100, 200);
        component3 = new Component("Motherboard", Component.Type.MOTHERBOARD, new BigDecimal("200"), "Asus", 50, 50);
        component4 = new Component("RAM", Component.Type.RAM, new BigDecimal("150"), "Corsair", 60, 20);
        component5 = new Component("Case", Component.Type.CASE, new BigDecimal("100"), "NZXT", 30, 0);
        component6 = new Component("SSD", Component.Type.SSD, new BigDecimal("250"), "Samsung", 90, 0);
        component7 = new Component("PSU", Component.Type.PSU, new BigDecimal("300"), "EVGA", 7000, 500);
        component8 = new Component("Keyboard", Component.Type.KEYBOARD, new BigDecimal("100"), "Logitech", 40, 0);
        component9 = new Component("Screen", Component.Type.SCREEN, new BigDecimal("200"), "Dell", 50, 0);
        component10 = new Component("Touchpad", Component.Type.TOUCHPAD, new BigDecimal("50"), "HP", 30, 0);
        component11 = new Component("Battery", Component.Type.BATTERY, new BigDecimal("150"), "Toshiba", 60, 0);
        database.saveComponent(component1);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        database.saveComponent(component9);
        database.saveComponent(component10);
        database.saveComponent(component11);
    }

    @Test
    void purchaseComponent() throws OutOfStockException, NotEnoughMoneyException, ProductNotFoundException {
        Component comp1 = store1.purchaseComponent(2, customer);
        assertEquals("Motherboard", comp1.getName());
        database.resetEntireDatabase();

    }
    @Test
    void getAvailableComponents() {
        assertEquals(11, store1.getAvailableComponents().size());
        database.resetEntireDatabase();
    }

    @Test
    void getComponentsSortedByAmount() {
        assertEquals(11, store1.getComponentsSortedByAmount().size());
        database.resetEntireDatabase();
    }

    @Test
    void getComponentsSortedByName() {
        assertEquals("Battery", store1.getComponentsSortedByName().get(0).getName());
    }

    @Test
    void getComponentsSortedByPrice() {
        assertEquals("Touchpad", store1.getComponentsSortedByPrice().get(0).getName());
    }

    @Test
    void filterByType() {
        assertEquals(1, store1.filterByType(Component.Type.CASE).size());

    }
    @Test
    void getInventoryValue() {
        BigDecimal value = store1.getInventoryValue();
        assertTrue(value.compareTo(new BigDecimal(1000)) > 0);
    }

}
