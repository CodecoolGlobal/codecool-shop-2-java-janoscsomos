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
        DataSource dataSource = null;
        try {
            dataSource = databaseManager.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        ProductDao productDataStore = new ProductDaoJdbc(dataSource);
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc(dataSource);
        SupplierDao supplierDao = new SupplierDaoJdbc(dataSource);
        ProductServiceDB productServiceDB = new ProductServiceDB(productDataStore, productCategoryDataStore, supplierDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("category", productServiceDB.getProductCategory(1));

        context.setVariable("products", productService.getAllProducts());
        context.setVariable("allCategory", productCategoryDataStore.getAll());
        context.setVariable("allSuppliers", supplierDaoMem.getAll());
        //Hashtable<String, Integer> cart = new Hashtable<>();

        /*
        ShoppingCart shoppingCart = new ShoppingCart();
        req.getSession().setAttribute("cart", shoppingCart);
         */

        // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }
}
