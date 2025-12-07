package com.bussiness;

import javax.swing.*;

public class CarRentalApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            com.bussiness.frontend.CarRentalApp app = new com.bussiness.frontend.CarRentalApp();
            app.setVisible(true);
        });
    }
}