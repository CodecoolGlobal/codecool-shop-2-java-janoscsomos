package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    public ProductService productService;
    public ProductCategory mockCategory;

    public ProductCategoryDaoMem categoryDaoMem;
    public ProductDaoMem productDaoMem;
    public SupplierDaoMem supplierDaoMem;

    @BeforeEach
    void build() {
        mockCategory = Mockito.mock(ProductCategory.class);
        categoryDaoMem = ProductCategoryDaoMem.getInstance();
        productDaoMem = ProductDaoMem.getInstance();
        supplierDaoMem = SupplierDaoMem.getInstance();
        productService = new ProductService(productDaoMem, categoryDaoMem, supplierDaoMem);
    }

    @Test
    void getProductCategory_returnsCategoryWithExistingId() {
        // Add mock category to category DAO:
        categoryDaoMem.add(mockCategory);
        // Assert:
        assertEquals(mockCategory, productService.getProductCategory(0));
    }

    @Test
    void getProductCategory_throwsIllegalArgExceptionWithNonExistentId() {
        // Assert for exception check:
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductCategory(1000)
        );
    }

    @Test
    void getProductsForCategory_returnsRightProductsWithExistingCategory() {
        // Add mock category to category DAO:
        categoryDaoMem.add(mockCategory);
        // Create and add mock Product to product DAO:
        Product mockProduct = Mockito.mock(Product.class);
        Mockito.when(mockProduct.getProductCategory()).thenReturn(mockCategory);
        productDaoMem.add(mockProduct);
        // Created expectation:
        List<Product> expected = new LinkedList<>();
        expected.add(mockProduct);
        // Assert:
        assertEquals(expected, productService.getProductsForCategory(0));
    }

    @Test
    void getProductsForCategory_throwsIllegalArgumentExceptionWithNonExistingCategory() {
        // Adding mock item to the product Dao:
        Product mockProduct = Mockito.mock(Product.class);
        Mockito.when(mockProduct.getProductCategory()).thenReturn(mockCategory);
        productDaoMem.add(mockProduct);
        // Running search without adding the product category to the relevant DaoMem:
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductsForCategory(0)
        );
    }

    @Test
    void getProductsForSupplier() {
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

    @AfterEach
    void tearDown() {
        categoryDaoMem.remove(0);
        productDaoMem.remove(0);
    }

    @AfterAll
    static void finish() {
        System.out.println("Service tests finished!\nResult:");
    }
}