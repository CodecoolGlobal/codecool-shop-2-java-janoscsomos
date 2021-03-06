package com.codecool.shop.api;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.DataUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.google.gson.Gson;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;


@WebServlet(name = "CategoryJsonServlet", urlPatterns = "/api/category")
public class CategoryJsonServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CategoryJsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("{} request on route: /api/category", request.getMethod());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);

        String categoryId = request.getParameter("category");



        if (DataUtil.getDatabaseConfig().equals("memory")) {
            out.println(gson.toJson(productService.getProductsForCategory(Integer.parseInt(categoryId))));
        }

        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            DatabaseManager databaseManager = DataUtil.initDatabaseManager();
            out.println(gson.toJson(databaseManager.getProductsForCategory(Integer.parseInt(categoryId))));
        }
    }
}
