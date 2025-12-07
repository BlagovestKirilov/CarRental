package com.business.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.business.enums.Constants.DATABASE;
import static com.business.enums.Constants.HOST;
import static com.business.enums.Constants.PASSWORD;
import static com.business.enums.Constants.PORT;
import static com.business.enums.Constants.URL;
import static com.business.enums.Constants.USER;

public class DatabaseUtil {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format(URL.getValue(), HOST.getValue(), PORT.getValue(),
                DATABASE.getValue()), USER.getValue(), PASSWORD.getValue());
    }

}
