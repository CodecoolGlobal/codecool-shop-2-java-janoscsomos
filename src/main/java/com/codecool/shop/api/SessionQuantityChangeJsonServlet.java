
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "SessionQuantityChangeJsonServlet", urlPatterns = "/api/session/quantity")
public class SessionQuantityChangeJsonServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SessionQuantityChangeJsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("{} request on route: /api/session/quantity", request.getMethod());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
        Product currentItem = null;
        Integer currentAmount = 0;
        Map<String, Integer> shoppingCart = null;

        if (DataUtil.getDatabaseConfig().equals("memory")) {
            currentItem = productService.getProductByName(request.getParameter("item"));
        }

        if (DataUtil.getDatabaseConfig().equals("jdbc")) {
            DatabaseManager databaseManager = DataUtil.initDatabaseManager();
            currentItem = databaseManager.getProductByName(request.getParameter("item"));
            HttpSession session = request.getSession();
            shoppingCart = (HashMap<String, Integer>) session.getAttribute("shoppingCart");
            currentAmount = shoppingCart.get(currentItem.getName());
        }


        if (request.getParameter("relation").equals("add")) {
            if (DataUtil.getDatabaseConfig().equals("memory")) {
                currentItem.setAmount(currentItem.getAmount() + 1);
            }

            if (DataUtil.getDatabaseConfig().equals("jdbc")) {
                shoppingCart.put(currentItem.getName(), currentAmount + 1);
            }

        } else {
            if (DataUtil.getDatabaseConfig().equals("memory")) {
                if (currentItem.getAmount() > 1) {
                    currentItem.setAmount(currentItem.getAmount() - 1);
                }
            }

            if (currentAmount > 1) {
                if (DataUtil.getDatabaseConfig().equals("jdbc")) {
                    shoppingCart.put(currentItem.getName(), currentAmount - 1);
                }
            }
        }
    }
}
