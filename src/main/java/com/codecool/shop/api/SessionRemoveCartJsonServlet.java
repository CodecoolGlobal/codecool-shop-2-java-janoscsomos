package com.codecool.shop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "SessionRemoveCartJsonServlet", urlPatterns = "/api/session/removeAll")
public class SessionRemoveCartJsonServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SessionRemoveCartJsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        logger.info("{} request on route: /api/session/removeAll", request.getMethod());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");
        cart.clear();

    }
}
