package com.bussiness;

import com.bussiness.frontend.CarRentalApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarRentalApp app = new CarRentalApp();
            app.setVisible(true);
        });
    }
}