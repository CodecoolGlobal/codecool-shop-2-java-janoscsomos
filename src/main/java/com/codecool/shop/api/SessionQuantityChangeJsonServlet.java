
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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "SessionQuantityChangeJsonServlet", urlPatterns = "/api/session/quantity")
public class SessionQuantityChangeJsonServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        // Singleton usage -->
        //Product currentItem = productService.getProductByName(request.getParameter("item"));

        // Database usage -->
        DatabaseManager databaseManager = DataUtil.initDatabaseManager();
        Product currentItem = databaseManager.getProductByName(request.getParameter("item"));

        if (request.getParameter("relation").equals("add")) {
            currentItem.setAmount(currentItem.getAmount() + 1);
        } else {
            if (currentItem.getAmount() > 1) {
                currentItem.setAmount(currentItem.getAmount() - 1);
            }
        }
    }
}
