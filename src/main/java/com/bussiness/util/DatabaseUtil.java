package com.bussiness.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.bussiness.constant.Constants.DATABASE;
import static com.bussiness.constant.Constants.HOST;
import static com.bussiness.constant.Constants.PASSWORD;
import static com.bussiness.constant.Constants.PORT;
import static com.bussiness.constant.Constants.URL;
import static com.bussiness.constant.Constants.USER;

public class DatabaseUtil {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format(URL.getValue(), HOST.getValue(), PORT.getValue(),
                DATABASE.getValue()), USER.getValue(), PASSWORD.getValue());
    }

}
