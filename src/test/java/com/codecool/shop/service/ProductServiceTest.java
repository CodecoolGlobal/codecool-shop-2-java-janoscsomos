package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private final int initialIndex = 0;
    private final int impossibleIndex = 1000;

    public ProductService productService;
    public ProductCategory mockCategory;
    public Product mockProduct;
    public Supplier mockSupplier;

    public ProductCategoryDaoMem categoryDaoMem;
    public ProductDaoMem productDaoMem;
    public SupplierDaoMem supplierDaoMem;

    @BeforeEach
    void build() {
        mockCategory = Mockito.mock(ProductCategory.class);
        mockProduct = Mockito.mock(Product.class);
        mockSupplier = Mockito.mock(Supplier.class);

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
        assertEquals(mockCategory, productService.getProductCategory(initialIndex));
    }

    @Test
    void getProductCategory_throwsIllegalArgExceptionWithNonExistentId() {
        // Assert for exception check:
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductCategory(impossibleIndex)
        );
    }

    @Test
    void getProductsForCategory_returnsRightProductsWithExistingCategory() {
        // Add mock category to category DAO:
        categoryDaoMem.add(mockCategory);
        // Create and add mock Product to product DAO:
        Mockito.when(mockProduct.getProductCategory()).thenReturn(mockCategory);
        productDaoMem.add(mockProduct);
        // Created expectation:
        List<Product> expected = new LinkedList<>();
        expected.add(mockProduct);
        // Assert:
        assertEquals(expected, productService.getProductsForCategory(initialIndex));
    }

    @Test
    void getProductsForCategory_throwsIllegalArgumentExceptionWithNonExistingCategory() {
        // Adding mock item to the product Dao:
        Mockito.when(mockProduct.getProductCategory()).thenReturn(mockCategory);
        productDaoMem.add(mockProduct);
        // Running search without adding the product category to the relevant DaoMem:
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductsForCategory(initialIndex)
        );
    }

    @Test
    void getProductsForSupplier_returnsTheRightProductListWithExistingSupplier() {
        supplierDaoMem.add(mockSupplier);
        // Adding mock item to the product Dao and set its supplier:
        Mockito.when(mockProduct.getSupplier()).thenReturn(mockSupplier);
        productDaoMem.add(mockProduct);
        // Build expectation:
        List<Product> expected = new LinkedList<>();
        expected.add(mockProduct);
        // Running search with mocked supplier:
        assertEquals(expected, productService.getProductsForSupplier(initialIndex));
    }

    @Test
    void getProductsForSupplier_throwsIllegalArgumentExceptionWithNonExistentSupplier() {
        // Adding mock item to the product Dao and set its supplier:
        Mockito.when(mockProduct.getSupplier()).thenReturn(mockSupplier);
        productDaoMem.add(mockProduct);
        // Assert without adding supplier to relevant Dao:
        assertThrows(
                NullPointerException.class,
                () -> productService.getProductsForCategory(initialIndex)
        );
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
        categoryDaoMem.remove(initialIndex);
        productDaoMem.remove(initialIndex);
        supplierDaoMem.remove(initialIndex);
    }

    @AfterAll
    static void finish() {
        System.out.println("Service tests finished!\nResult:");
    }
}