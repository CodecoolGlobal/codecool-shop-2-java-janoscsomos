package com.codecool.shop.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LogIO {

    public LogIO() {};

    public void exportLog(String logOutput)
            throws IOException, ClassNotFoundException {

        String[] splitLog= logOutput.split(",");
        StringBuilder formattedLog = new StringBuilder();
        LocalDateTime date = LocalDateTime.now();

        formattedLog.append("CHECKOUT LOG START AT " + date.toString() + "\n");
        for (int i = 0; i < splitLog.length; i++) {
            formattedLog.append(splitLog[i]);
            formattedLog.append("\n");
        }
        formattedLog.append("CHECKOUT LOG END");


        FileOutputStream fileOutputStream
                = new FileOutputStream(date.toString() +".json");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(formattedLog.toString());
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
