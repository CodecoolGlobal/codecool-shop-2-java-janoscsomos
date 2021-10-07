package com.codecool.shop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/api/order")
public class OrderServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    LogIO logIO = new LogIO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("{} request on route: /api/order", request.getMethod());
        String payload = request.getParameter("order");
        try {
            logIO.exportOrder(payload);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("payload: " + payload);
    }

}
