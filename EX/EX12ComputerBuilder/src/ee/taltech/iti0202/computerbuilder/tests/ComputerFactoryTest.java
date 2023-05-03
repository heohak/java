package ee.taltech.iti0202.computerbuilder.tests;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.ComputerFactory;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.CantMakeComputerException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerFactoryTest {
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



    @BeforeEach
    void setUp() throws ProductAlreadyExistsException {
        ComputerFactory computerFactory = new ComputerFactory();
        database = Database.getInstance();
        customer = new Customer("John Doe", new BigDecimal("30000"));
        database.resetEntireDatabase();

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
    void testGetAvailableComponents() {
        assertEquals(11, database.getComponents().size());
        assertEquals(11, ComputerFactory.getAvailableComponents().size());

    }

    @Test
    void testOrderGamingLaptop() throws CantMakeComputerException {
        Computer computer = ComputerFactory.order(Computer.Type.LAPTOP, Computer.UseCase.GAMING, customer);
        List<Component> components = List.of(component1, component2, component3, component4, component5, component6, component7, component8, component9, component10, component11);
        assertTrue(computer.getComponents().containsAll(components));
        database.resetEntireDatabase();
    }

    @Test
    void testOrderWorkLaptop() throws CantMakeComputerException {
        Computer computer = ComputerFactory.order(Computer.Type.LAPTOP, Computer.UseCase.WORK, customer);
        List<Component> components = List.of(component1, component2, component3, component4, component5, component6, component7, component8, component9, component10, component11);
        assertTrue(computer.getComponents().containsAll(components));
        database.resetEntireDatabase();

    }

    @Test
    void testOrderGamingDesktop() throws CantMakeComputerException {
        Computer computer = ComputerFactory.order(Computer.Type.DESKTOP, Computer.UseCase.GAMING, customer);
        List<Component> components = List.of(component1, component2, component3, component4, component5, component6, component7);
        assertTrue(computer.getComponents().containsAll(components));
        database.resetEntireDatabase();
    }

    @Test
    void testOrderWorkDesktop() throws CantMakeComputerException {
        Computer computer = ComputerFactory.order(Computer.Type.DESKTOP, Computer.UseCase.WORK, customer);
        List<Component> components = List.of(component1, component2, component3, component4, component5, component6, component7);
        assertTrue(computer.getComponents().containsAll(components));
        database.resetEntireDatabase();
    }




}

