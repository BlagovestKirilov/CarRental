package com.business.repository;

import com.business.model.Car;
import com.business.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static com.business.enums.Constants.COLOR;
import static com.business.enums.Constants.DAILY_PRICE;
import static com.business.enums.Constants.DELETE_QUERY;
import static com.business.enums.Constants.FIND_ALL_QUERY;
import static com.business.enums.Constants.FIND_BY_ID_QUERY;
import static com.business.enums.Constants.ID;
import static com.business.enums.Constants.IS_RENTED;
import static com.business.enums.Constants.LICENSE_PLATE;
import static com.business.enums.Constants.MAKER;
import static com.business.enums.Constants.MODEL;
import static com.business.enums.Constants.SAVE_QUERY;
import static com.business.enums.Constants.UPDATE_QUERY;
import static com.business.enums.Constants.YEAR;

public class CarRepository {
    public List<Car> findAll() {
        List<Car> list = new ArrayList<>();
        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(FIND_ALL_QUERY.getValue());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(Car.builder()
                        .id(rs.getLong(ID.getValue()))
                        .maker(rs.getString(MAKER.getValue()))
                        .model(rs.getString(MODEL.getValue()))
                        .year(rs.getObject(YEAR.getValue()) == null ? null : rs.getInt(YEAR.getValue()))
                        .color(rs.getString(COLOR.getValue()))
                        .licensePlate(rs.getString(LICENSE_PLATE.getValue()))
                        .dailyPrice(rs.getObject(DAILY_PRICE.getValue()) == null ? null : rs.getBigDecimal(DAILY_PRICE.getValue()))
                        .isRented(rs.getBoolean(IS_RENTED.getValue()))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return list;
    }

    public void save(Car car) {
        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(SAVE_QUERY.getValue(), Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getMaker());
            ps.setString(2, car.getModel());
            if (car.getYear() == null) ps.setNull(3, Types.INTEGER);
            else ps.setInt(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getLicensePlate());
            if (car.getDailyPrice() == null) ps.setNull(6, Types.DECIMAL);
            else ps.setBigDecimal(6, car.getDailyPrice());
            ps.setBoolean(7, car.getIsRented());

            int affected = ps.executeUpdate();
            if (affected == 0) return;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void update(Car car) {
        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(UPDATE_QUERY.getValue())) {
            ps.setString(1, car.getMaker());
            ps.setString(2, car.getModel());
            if (car.getYear() == null) ps.setNull(3, Types.INTEGER);
            else ps.setInt(3, car.getYear());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getLicensePlate());
            if (car.getDailyPrice() == null) ps.setNull(6, Types.DECIMAL);
            else ps.setBigDecimal(6, car.getDailyPrice());
            ps.setBoolean(7, car.getIsRented());
            ps.setLong(8, car.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void delete(long id) {
        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(DELETE_QUERY.getValue())) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Car findById(Long id) {
        try (Connection c = DatabaseUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(FIND_BY_ID_QUERY.getValue())) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Car.builder()
                            .id(rs.getLong(ID.getValue()))
                            .maker(rs.getString(MAKER.getValue()))
                            .model(rs.getString(MODEL.getValue()))
                            .year(rs.getObject(YEAR.getValue()) == null ? null : rs.getInt(YEAR.getValue()))
                            .color(rs.getString(COLOR.getValue()))
                            .licensePlate(rs.getString(LICENSE_PLATE.getValue()))
                            .dailyPrice(rs.getObject(DAILY_PRICE.getValue()) == null ? null : rs.getBigDecimal(DAILY_PRICE.getValue()))
                            .isRented(rs.getBoolean(IS_RENTED.getValue()))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return null;
    }
}
