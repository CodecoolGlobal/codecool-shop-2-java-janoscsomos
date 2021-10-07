package com.codecool.shop.api;


import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.implementation.DataUtil;
import com.codecool.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registrationServlet", urlPatterns = {"/register"})
public class RegistrationServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("{} request on route: /register", request.getMethod());
        DatabaseManager databaseManager = DataUtil.initDatabaseManager();
        User freshUser = new User(
                request.getParameter("name"),
                request.getParameter("email"),
                DataUtil.hashPassword(request.getParameter("password"))
        );
        databaseManager.addNewUser(freshUser);
    }
}
