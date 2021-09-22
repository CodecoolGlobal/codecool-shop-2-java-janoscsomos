package com.codecool.shop.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogIO {

    public LogIO() {};

    public void exportLog(String logOutput)
            throws IOException, ClassNotFoundException {

        LocalDateTime date = LocalDateTime.now();
        FileOutputStream fileOutputStream
                = new FileOutputStream(date.toString() +".json");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(logOutput);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
