package com.business;

import com.business.frontend.CarRental;

import javax.swing.*;

public class CarRentalApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarRental app = new CarRental();
            app.setVisible(true);
        });
    }
}