package com.codecool.shop.dbapi;


import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.DataUtil;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "SessionDbGetJsonServlet", urlPatterns = "/api/session/get")
public class SessionDbGetJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");
        List<Product> output = new LinkedList<>();
        try {
            extractProducts(out, gson, cart, output);
        } catch (NullPointerException error) {
            HashMap<String, Integer> newCart = new HashMap<>();
            request.getSession().setAttribute("shoppingCart", newCart);
            extractProducts(out, gson, newCart, output);
        }
    }

    private void extractProducts(
            PrintWriter out,
            Gson gson,
            HashMap<String, Integer> cart,
            List<Product> output
    ) {
        DatabaseManager databaseManager = DataUtil.initDatabaseManager();
        for (String product : cart.keySet()) {
            output.add(databaseManager.getProductByName(product));
        }
        out.println(gson.toJson(output));
    }
}

