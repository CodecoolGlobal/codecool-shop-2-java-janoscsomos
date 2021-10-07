package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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

        logger.info("Get request on main page.");

        engine.process("product/index.html", context, resp.getWriter());
    }
}
