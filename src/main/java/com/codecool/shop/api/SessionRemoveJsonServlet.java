package com.codecool.shop.api;

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


@WebServlet(name = "SessionRemoveJsonServlet", urlPatterns = "/api/session/remove")
public class SessionRemoveJsonServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");
        // Singleton usage -->
        //Product currentProduct = productService.getProductByName(request.getParameter("item"));

        // Database usage -->
        DatabaseManager databaseManager = DataUtil.initDatabaseManager();
        Product currentProduct = databaseManager.getProductByName(request.getParameter("item"));
        currentProduct.setAmount(1);
        cart.remove(request.getParameter("item"));
        List<Product> output = new LinkedList<>();
        for (String product : cart.keySet()) {

            // Singleton usage -->
            //output.add(productService.getProductByName(product));

            // Database usage -->
            output.add(databaseManager.getProductByName(product));
        }
        out.println(gson.toJson(output));
    }
}
