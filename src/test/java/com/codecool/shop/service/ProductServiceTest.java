package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;
    private ProductDao productDao;
    private ProductCategoryDao categoryDao;
    private SupplierDao supplierDao;

    @BeforeEach
    void build() {
        ProductDao productDao = Mockito.mock(ProductDao.class);
        ProductCategoryDao categoryDao = Mockito.mock(ProductCategoryDao.class);
        SupplierDao supplierDao = Mockito.mock(SupplierDao.class);
        productService = new ProductService(productDao, categoryDao, supplierDao);
    }

    @Test
    void getProductCategory_returnsCategoryWithRightId() {

    }

    @Test
    void getProductCategory_throwsIllegalArgumentExceptionWithWrongId() {
    }

    @Test
    void getProductsForSupplier() {
    }

    @Test
    void getProductsForCategory() {
    }

    @Test
    void getProductsByName() {
    }

    @Test
    void getProductByName() {
    }

    @Test
    void getAllProducts() {
    }
}