package com.codecool.shop.controller;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.ProductServiceDB;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //ProductDao productDataStore = ProductDaoMem.getInstance();
        //ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        //SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        //ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDaoMem);

        DatabaseManager databaseManager = new DatabaseManager();
        try {
            databaseManager.setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }





        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("category", databaseManager.findProductCategory(1));

        //context.setVariable("products", productService.getAllProducts());
        context.setVariable("products", databaseManager.allProducts());

        //context.setVariable("allCategory", productCategoryDataStore.getAll());
        context.setVariable("allCategory", databaseManager.allProductCategories());

        //context.setVariable("allSuppliers", supplierDaoMem.getAll());
        context.setVariable("allSuppliers", databaseManager.allSuppliers());

        engine.process("product/index.html", context, resp.getWriter());
    }
}
