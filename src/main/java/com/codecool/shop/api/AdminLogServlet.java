package com.codecool.shop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/api/adminlog")
public class AdminLogServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger((AdminLogServlet.class));

    LogIO logIO = new LogIO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String payload = request.getParameter("logoutput");
        logger.info("{} request on route: /api/adminlog", request.getMethod());
        try {
            logIO.exportLog(payload);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("ClassNotFound on servlet: {}", "AdminLogServlet");
        }
        System.out.println(payload);
    }
}
