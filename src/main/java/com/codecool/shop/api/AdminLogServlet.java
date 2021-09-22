package com.codecool.shop.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet (urlPatterns = "/api/adminlog")
public class AdminLogServlet extends HttpServlet {

    LogIO logIO = new LogIO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String payload = request.getParameter("logoutput");
        try {
            logIO.exportLog(payload);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(payload);
    }
}
