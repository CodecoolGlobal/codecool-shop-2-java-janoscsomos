package com.codecool.shop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "SessionAddJsonServlet", urlPatterns = "/api/session/add")
public class SessionAddJsonServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SessionAddJsonServlet.class);

    Map<String, Integer> shoppingCart;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("{} request on route: /api/session/add", request.getMethod());
        HttpSession session = request.getSession();
        if (session.getAttribute("shoppingCart") == null) {
            shoppingCart = new HashMap<String, Integer>();
            session.setAttribute("shoppingCart", shoppingCart);
        } else {
            shoppingCart = (HashMap<String, Integer>) session.getAttribute("shoppingCart");
        }
        shoppingCart.put(request.getParameter("productName"), 1);
    }
}
