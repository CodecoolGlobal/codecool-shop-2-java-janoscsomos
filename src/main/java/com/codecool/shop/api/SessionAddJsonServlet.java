package com.codecool.shop.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


@WebServlet(name = "SessionAddJsonServlet", urlPatterns = "/api/session/add")
public class SessionAddJsonServlet extends HttpServlet {
    Map<String, String> shoppingCart;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



   /*
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        cart.addToCart("Banana", 2);

    */
        HttpSession session = request.getSession();
        if (session.isNew()) {
            shoppingCart = new HashMap<String, String>();
            session.setAttribute("shoppingCart", shoppingCart);
        } else {
            shoppingCart = (HashMap<String, String>) session.getAttribute("shoppingCart");
        }
        shoppingCart.put("banana", "cake");


    }
}
