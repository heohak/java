package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Database {
    public static Database instance;
    private final Map<Integer, Component> components = new HashMap<>();

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        }
        components.put(component.getId(), component);
    }

    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        components.remove(id);
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        Component component = components.get(id);

        component.setAmount(component.getAmount() + amount);


    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        Component component = components.get(id);
        if (component == null) {
            throw new ProductNotFoundException();
        }
        if (component.getAmount() < amount) {
            throw new OutOfStockException();
        }
        component.setAmount(component.getAmount() - amount);
    }

    public Map<Integer, Component> getComponents() {
      return components;
    }

    public void resetEntireDatabase() {
        components.clear();
        Component.resetID();
    }

    public void saveToFile(String location) {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try (FileWriter writer = new FileWriter(location)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void loadFromFile(String location) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(location)));
            Gson gson = new Gson();
            instance = gson.fromJson(json, Database.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}