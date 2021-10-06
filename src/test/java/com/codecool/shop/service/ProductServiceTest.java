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
    private final String nameToSearchFor = "banana";
    // Declare classes to test or mock:
    public ProductService productService;
    public ProductCategory mockCategory;
    public Product mockProduct;
    public Supplier mockSupplier;
    // Declare Dao classes:
    public ProductCategoryDaoMem categoryDaoMem;
    public ProductDaoMem productDaoMem;
    public SupplierDaoMem supplierDaoMem;

    @BeforeEach
    void build() {
        //Mocking classes:
        mockCategory = Mockito.mock(ProductCategory.class);
        mockProduct = Mockito.mock(Product.class);
        mockSupplier = Mockito.mock(Supplier.class);
        // Get instance of containers:
        categoryDaoMem = ProductCategoryDaoMem.getInstance();
        productDaoMem = ProductDaoMem.getInstance();
        supplierDaoMem = SupplierDaoMem.getInstance();
        // Instantiate ProductService:
        productService = new ProductService(productDaoMem, categoryDaoMem, supplierDaoMem);
    }

    @AfterEach
    void tearDown() {
        // Removing category from its DaoMem:
        categoryDaoMem.getAll().clear();
        // Remove product(s) from their from DaoMem:
        productDaoMem.getAll().clear();
        // Removing supplier from its DaoMem:
        supplierDaoMem.getAll().clear();
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
        // Create and add mock Product to product Dao:
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
        // Adding mock item to the product Dao and mock its category's getter:
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
    void getProductsForSupplier_throwsNullPointerExceptionWithNonExistentSupplier() {
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
    void getProductsByName_returnsProductsWithCorrectName() {
        // Set first product's name and add it to memory:
        Mockito.when(mockProduct.getName()).thenReturn(nameToSearchFor + " boat");
        productDaoMem.add(mockProduct);
        // Add additional product:
        Product mockProduct2 = Mockito.mock(Product.class);
        Mockito.when(mockProduct2.getName()).thenReturn(
                nameToSearchFor.substring(0, 1).toUpperCase() +
                        nameToSearchFor.substring(1) +
                        " Joe"
        );
        productDaoMem.add(mockProduct2);
        // Set expectation:
        List<Product> expected = new LinkedList<>();
        expected.add(mockProduct);
        expected.add(mockProduct2);
        // Assert:
        assertEquals(expected, productService.getProductsByName(nameToSearchFor));
    }

    @Test
    void getProductsByNameReturnsEmptyListWithNonExistentProductNameSearch() {
        // Set expectation:
        List<Product> expectation = new LinkedList<>();
        // Add and set mock item:
        Mockito.when(mockProduct.getName()).thenReturn(nameToSearchFor);
        productDaoMem.add(mockProduct);
        // Assert:
        assertEquals(expectation, productService.getProductsByName("noneExistentName"));
    }

    @Test
    void getProductByName_returnProductIfSearchedForExistingName() {
        // Set product name and add it to memory:
        Mockito.when(mockProduct.getName()).thenReturn(nameToSearchFor);
        productDaoMem.add(mockProduct);
        // Assert:
        assertEquals(mockProduct, productService.getProductByName(nameToSearchFor));
    }

    @Test
    void getProductByNameReturnsNullWithNonExistentProductNameSearch() {
        // Add and set mock item:
        Mockito.when(mockProduct.getName()).thenReturn(nameToSearchFor);
        productDaoMem.add(mockProduct);
        // Assert:
        assertNull(productService.getProductByName("noneExistentName"));
    }

    @Test
    void getAllProducts() {
        productDaoMem.add(mockProduct);
        // Add additional products:
        Product mockProduct2 = Mockito.mock(Product.class);
        productDaoMem.add(mockProduct2);
        Product mockProduct3 = Mockito.mock(Product.class);
        productDaoMem.add(mockProduct3);
        // Set expectation:
        List<Product> expected = new LinkedList<>();
        expected.add(mockProduct);
        expected.add(mockProduct2);
        expected.add(mockProduct3);
        // Assert:
        assertEquals(expected, productService.getAllProducts());
    }

    @AfterAll
    static void finish() {
        System.out.println("Service tests finished!\nResult:");
    }
}