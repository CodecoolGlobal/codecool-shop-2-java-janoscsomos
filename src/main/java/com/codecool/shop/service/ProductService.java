package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductService{
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        ProductCategory outputCategory = productCategoryDao.find(categoryId);
        if (outputCategory != null)
            return outputCategory;
        else
            throw new IllegalArgumentException("Non existent category with this ID!");
    }

    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = supplierDao.find(supplierId);
        return productDao.getBy(supplier);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(category);
        if (products.size() > 0)
            return productDao.getBy(category);
        else throw new IllegalArgumentException("No products by this category!");
    }

    public List<Product> getProductsByName(String name){
        var product = productDao.find(name);
        return productDao.getBy(name);
    }

    public Product getProductByName(String name) {
        var product = productDao.findOne(name);
        return productDao.getByOne(name);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

}
