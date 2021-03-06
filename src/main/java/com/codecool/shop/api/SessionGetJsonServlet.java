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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "SessionGetJsonServlet", urlPatterns = "/api/session/get")
public class SessionGetJsonServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SessionGetJsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        logger.info("{} request on route: /api/session/get", request.getMethod());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDao);
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");
        List<Product> output = new LinkedList<>();
        try {
            extractProducts(out, gson, productService, cart, output);
        } catch (NullPointerException error) {
            HashMap<String, Integer> newCart = new HashMap<>();
            request.getSession().setAttribute("shoppingCart", newCart);
            extractProducts(out, gson, productService, newCart, output);
            logger.error("NullpointerException on servlet: {}", "SessionGetJsonServlet");
        }
    }

    private void extractProducts(
            PrintWriter out,
            Gson gson, ProductService productService,
            HashMap<String, Integer> cart,
            List<Product> output
    ) throws IOException {
        if (DataUtil.getDatabaseConfig().equals("memory")) {
            for (String product : cart.keySet()) {
                output.add(productService.getProductByName(product));
            }
        }
        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            DatabaseManager databaseManager = DataUtil.initDatabaseManager();
            for (String product : cart.keySet()) {
                Product currentProduct = databaseManager.getProductByName(product);
                int currentAmount = cart.get(product);
                currentProduct.setAmount(currentAmount);
                output.add(currentProduct);
            }
            out.println(gson.toJson(output));
        }
    }
}
