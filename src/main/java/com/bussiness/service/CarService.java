package com.bussiness.service;

import com.bussiness.model.Car;
import com.bussiness.repository.CarRepository;

import java.util.List;

public class CarService {
    private final CarRepository carRepository = new CarRepository();

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(long id) {
        return carRepository.findById(id);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public void update(Car car) {
        carRepository.update(car);
    }

    public void delete(long carId) {
        carRepository.delete(carId);
    }
}
