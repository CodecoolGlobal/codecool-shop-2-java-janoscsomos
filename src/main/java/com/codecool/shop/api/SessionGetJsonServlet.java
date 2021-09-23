package com.codecool.shop.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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


@WebServlet(name = "SessionGetJsonServlet", urlPatterns = "/api/session/get")
public class SessionGetJsonServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");
        List<Product> output = new LinkedList<>();
        try {
            extractProducts(out, gson, productService, cart, output);
        } catch (NullPointerException error) {
            HashMap<String, Integer> newCart = new HashMap<>();
            request.getSession().setAttribute("shoppingCart", newCart);
            extractProducts(out, gson, productService, newCart, output);
        }
    }

    private void extractProducts(PrintWriter out, Gson gson, ProductService productService, HashMap<String, Integer> cart, List<Product> output) {
        for (String product : cart.keySet()) {
            output.add(productService.getProductByName(product));
        }
        out.println(gson.toJson(output));
    }
}
