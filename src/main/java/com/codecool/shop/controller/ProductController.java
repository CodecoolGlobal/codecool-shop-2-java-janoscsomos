package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.DataUtil;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("{} request on route: /", request.getMethod());

        DatabaseManager databaseManager = new DatabaseManager();
        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            try {
                databaseManager.setup();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());


        if (DataUtil.getDatabaseConfig().equals("memory")) {
            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
            ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDaoMem);
            context.setVariable("category", productService.getProductCategory(1));
            context.setVariable("products", productService.getAllProducts());
            context.setVariable("allCategory", productCategoryDataStore.getAll());
            context.setVariable("allSuppliers", supplierDaoMem.getAll());
        }


        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            context.setVariable("category", databaseManager.findProductCategory(1));
            context.setVariable("products", databaseManager.allProducts());
            context.setVariable("allCategory", databaseManager.allProductCategories());
            context.setVariable("allSuppliers", databaseManager.allSuppliers());
        }

        context.setVariable("dao", DataUtil.getDatabaseConfig());

        engine.process("product/index.html", context, response.getWriter());
    }
}
