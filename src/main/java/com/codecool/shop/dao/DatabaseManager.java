package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;


public class DatabaseManager {
    private ProductCategoryDao productCategoryDao;
    private ProductDao productDao;
    private SupplierDao supplierDao;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
    }

    public Product getProductByName(String name) { return productDao.getByOne(name); }

    public ProductCategory findProductCategory(int id) { return productCategoryDao.find(id); }

    public List<ProductCategory> allProductCategories() {return productCategoryDao.getAll(); }

    public List<Product> allProducts() { return productDao.getAll(); }

    public List<Supplier> allSuppliers() {return supplierDao.getAll();}

    public List<Product> getProductsForSupplier(int supplierId) {
        var supplier = supplierDao.find(supplierId);
        List<Product> output = productDao.getBy(supplier);
        if (output.size() > 0) return output;
        else throw new IllegalArgumentException("Non existent supplier name!");
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(category);
        if (products.size() > 0) return products;
        else throw new IllegalArgumentException("No products by this category!");
    }


    public List<Product> getProductsByName(String name){
        return productDao.getBy(name);
    }

    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        logger.info("Trying to connect");
        dataSource.getConnection().close();
        logger.info("Connection ok.");

        return dataSource;
    }
}

