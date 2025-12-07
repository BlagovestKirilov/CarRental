package com.business.service;

import com.business.model.Car;
import com.business.repository.CarRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.business.enums.LogMessages.CAR_DELETE_ERROR;
import static com.business.enums.LogMessages.CAR_DELETE_START;
import static com.business.enums.LogMessages.CAR_DELETE_SUCCESS;
import static com.business.enums.LogMessages.CAR_FIND_ALL_ERROR;
import static com.business.enums.LogMessages.CAR_FIND_ALL_START;
import static com.business.enums.LogMessages.CAR_FIND_ALL_SUCCESS;
import static com.business.enums.LogMessages.CAR_FIND_BY_ID_START;
import static com.business.enums.LogMessages.CAR_FIND_BY_ID_SUCCESS;
import static com.business.enums.LogMessages.CAR_NOT_FOUND;
import static com.business.enums.LogMessages.CAR_SAVE_ERROR;
import static com.business.enums.LogMessages.CAR_SAVE_START;
import static com.business.enums.LogMessages.CAR_SAVE_SUCCESS;
import static com.business.enums.LogMessages.CAR_UPDATE_ERROR;
import static com.business.enums.LogMessages.CAR_UPDATE_START;
import static com.business.enums.LogMessages.CAR_UPDATE_SUCCESS;
import static com.business.enums.LogMessages.ERROR_FETCHING_CAR;

@Log4j2
public class CarService {

    private final CarRepository carRepository;

    public CarService() {
        this.carRepository = new CarRepository();
    }

    public List<Car> findAll() {
        log.info(CAR_FIND_ALL_START.getValue());

        try {
            List<Car> result = carRepository.findAll();
            log.info(CAR_FIND_ALL_SUCCESS.getValue(), result.size());
            return result;
        } catch (Exception e) {
            log.error(CAR_FIND_ALL_ERROR.getValue(), e);
            throw e;
        }
    }

    public Car findById(long id) {
        log.info(CAR_FIND_BY_ID_START.getValue(), id);

        try {
            Car car = carRepository.findById(id);

            if (car == null) {
                log.warn(CAR_NOT_FOUND.getValue(), id);
                return null;
            }

            log.info(CAR_FIND_BY_ID_SUCCESS.getValue(), id);
            return car;

        } catch (Exception e) {
            log.error(ERROR_FETCHING_CAR.getValue(), id, e);
            throw new RuntimeException(e);
        }
    }

    public void save(Car car) {
        log.info(CAR_SAVE_START.getValue(), car.getMaker(), car.getModel());

        try {
            carRepository.save(car);
            log.info(CAR_SAVE_SUCCESS.getValue());
        } catch (Exception e) {
            log.error(CAR_SAVE_ERROR.getValue(), car.getMaker(), car.getModel(), e);
            throw new RuntimeException(e);
        }
    }

    public void update(Car car) {
        log.info(CAR_UPDATE_START.getValue(), car.getId());

        try {
            carRepository.update(car);
            log.info(CAR_UPDATE_SUCCESS.getValue(), car.getId());
        } catch (Exception e) {
            log.error(CAR_UPDATE_ERROR.getValue(), car.getId(), e);
            throw new RuntimeException(e);
        }
    }

    public void delete(long carId) {
        log.info(CAR_DELETE_START.getValue(), carId);

        try {
            carRepository.delete(carId);
            log.info(CAR_DELETE_SUCCESS.getValue(), carId);
        } catch (Exception e) {
            log.error(CAR_DELETE_ERROR.getValue(), carId, e);
            throw new RuntimeException(e);
        }
    }
}
