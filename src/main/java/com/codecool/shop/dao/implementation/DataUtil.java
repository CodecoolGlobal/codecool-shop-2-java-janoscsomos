package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseManager;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DataUtil {

    public static String getDatabaseConfig() throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String connectionConfigPath = rootPath + "connection.properties";
        Properties connProps = new Properties();
        connProps.load(new FileInputStream(connectionConfigPath));
        return connProps.getProperty("dao");
    }


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

    public static String hashPassword(String password) {
        int logRounds = 10;
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public static boolean verifyPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
