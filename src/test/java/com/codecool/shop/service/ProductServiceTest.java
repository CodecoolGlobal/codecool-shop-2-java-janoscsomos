package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        productService = new ProductService(
                productDaoMem,
                categoryDaoMem,
                supplierDaoMem
        );
    }

    @Test
    void getProductCategory_returnsCategoryWithExistingId() {
        categoryDaoMem.add(mockCategory);

        assertEquals(mockCategory, productService.getProductCategory(0));
    }

    @Test
    void getProductsForSupplier_throwsIllegalArgExceptionWithNonExistentId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductCategory(1000)
        );
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

    @AfterAll
    static void finish() {
        System.out.println("Service tests finished!\nResult:");
    }
}