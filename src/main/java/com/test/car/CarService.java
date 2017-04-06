package com.test.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CarService {

    private static CarService ME = new CarService();
    private final Map<UUID, Car> database = new HashMap<>();

    public static CarService me() {
        return ME;
    }

    public List<Car> list() {
        return new ArrayList<>(database.values());
    }

    public Car save(Car car) {
        UUID carId = UUID.randomUUID();
        car.setId(carId);
        database.put(carId, car);
        return car;
    }

    public void update(Car car) {
        if (!database.containsKey(car.getId())) {
            throw new IllegalArgumentException("Car not found!");
        }
        database.put(car.getId(), car);
    }

    public void delete(UUID carId) {
        database.remove(carId);
    }
}
