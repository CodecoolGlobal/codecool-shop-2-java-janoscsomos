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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "SessionRemoveJsonServlet", urlPatterns = "/api/session/remove")
public class SessionRemoveJsonServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SessionRemoveJsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("{} request on route: /api/session/remove", request.getMethod());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");



        if (DataUtil.getDatabaseConfig().equals("memory")) {
            Product currentProduct = productService.getProductByName(request.getParameter("item"));
            currentProduct.setAmount(1);
        }


        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            DatabaseManager databaseManager = DataUtil.initDatabaseManager();
            Product currentProduct = databaseManager.getProductByName(request.getParameter("item"));
        }


        cart.remove(request.getParameter("item"));
        List<Product> output = new LinkedList<>();
        for (String product : cart.keySet()) {

            if (DataUtil.getDatabaseConfig().equals("memory")) {
                output.add(productService.getProductByName(product));
            }

            if (DataUtil.getDatabaseConfig().equals("jdbc")) {
                DatabaseManager databaseManager = DataUtil.initDatabaseManager();
                output.add(databaseManager.getProductByName(product));
            }
        }
        out.println(gson.toJson(output));
    }
}
