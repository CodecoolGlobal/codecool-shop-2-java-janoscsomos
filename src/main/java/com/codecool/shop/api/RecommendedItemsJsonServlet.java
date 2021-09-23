package com.codecool.shop.api;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "RecommendedItemsJsonServlet", urlPatterns = "api/session/recommend")
public class RecommendedItemsJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        // Init:
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ProductDao allProductsData = ProductDaoMem.getInstance();
        ProductCategoryDao categoryDao = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(
                allProductsData,
                categoryDao,
                supplierDao
        );
        HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("shoppingCart");
        if (cart == null)
            cart = new HashMap<>();
        HashMap<String, Integer> finalCart = cart;
        // Filter recommendations:
        List<Product> output = productService.getAllProducts().stream().filter(
                product -> !finalCart.containsKey(product.getName())
        ).collect(Collectors.toList());
        // Print output:
        out.println(gson.toJson(output));
    }
}
