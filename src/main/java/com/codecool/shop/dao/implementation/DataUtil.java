package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;

import java.sql.SQLException;

public class DataUtil {


    public static DatabaseManager initDatabaseManager() {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            databaseManager.setup();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return databaseManager;
    }

}
