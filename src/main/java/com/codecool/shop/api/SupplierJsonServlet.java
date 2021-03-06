package com.codecool.shop.api;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.DataUtil;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "SupplierJsonServlet", urlPatterns = "/api/supplier")
public class SupplierJsonServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SupplierJsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("{} request on route: /api/supplier", request.getMethod());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        String supplierId = request.getParameter("supplier");

        if (DataUtil.getDatabaseConfig().equals("memory")) {
            out.println(gson.toJson(productService.getProductsForSupplier(Integer.parseInt(supplierId))));
        }

        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            DatabaseManager databaseManager = DataUtil.initDatabaseManager();
            out.println(gson.toJson(databaseManager.getProductsForSupplier(Integer.parseInt(supplierId))));
        }

    }
}

