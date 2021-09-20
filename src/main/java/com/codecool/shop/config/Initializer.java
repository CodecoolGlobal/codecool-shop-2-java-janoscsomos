package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier bananaStore = new Supplier("Banana Store", "Placeholder supplier for initial product creation");

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory variants = new ProductCategory("Banana variants", "Basics", "Bananas in all shapes and sizes.");
        ProductCategory specialOffers = new ProductCategory("Special Offers", "Basics", "Bananas for special needs.");
        ProductCategory ingredients = new ProductCategory("Ingredients", "Food", "Banana is not just about the fruit, here you can find other parts that can be used in foods.");
        ProductCategory cultivation = new ProductCategory("Cultivation", "Horticulture", "Wanna grow your own bananas? Look no further, here's what you need to start");
        ProductCategory foods = new ProductCategory("Banana foods", "Food", "Wide selection of banana based dishes, desserts and sweets.");
        ProductCategory merchandise = new ProductCategory("Merchandise", "Merchandise", "All kinds of banana-themed fun stuff from plushes to apparel.");
        ProductCategory miscellaneous = new ProductCategory("Miscellaneous", "Miscellaneous", "Haven't found what you're looking for anywhere else? Try here!");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(variants);
        productCategoryDataStore.add(specialOffers);
        productCategoryDataStore.add(ingredients);
        productCategoryDataStore.add(cultivation);
        productCategoryDataStore.add(foods);
        productCategoryDataStore.add(merchandise);
        productCategoryDataStore.add(miscellaneous);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Banaan", new BigDecimal("10"), "USD", "Banaan", foods, bananaStore));
        productDataStore.add(new Product("Beanie", new BigDecimal("10"), "USD", "Beanie", merchandise, bananaStore));
        productDataStore.add(new Product("Boat", new BigDecimal("10"), "USD", "Boat", merchandise, bananaStore));
        productDataStore.add(new Product("Boxer", new BigDecimal("10"), "USD", "Boxer", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Bread", new BigDecimal("10"), "USD", "Banana Bread", foods, bananaStore));
        productDataStore.add(new Product("Banana Cake", new BigDecimal("10"), "USD", "Banana Cake", foods, bananaStore));
        productDataStore.add(new Product("Cap", new BigDecimal("10"), "USD", "Cap", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Chips", new BigDecimal("10"), "USD", "Banana Chips", foods, bananaStore));
        productDataStore.add(new Product("Banana Bundle", new BigDecimal("10"), "USD", "Banana Bundle", specialOffers, bananaStore));
        productDataStore.add(new Product("Costume", new BigDecimal("10"), "USD", "Costume", merchandise, bananaStore));
        productDataStore.add(new Product("Fe'i", new BigDecimal("10"), "USD", "Fe'i", variants, bananaStore));
        productDataStore.add(new Product("Filled Banana", new BigDecimal("10"), "USD", "Filled Banana", foods, bananaStore));
        productDataStore.add(new Product("Fried Banana", new BigDecimal("10"), "USD", "Fried Banana", foods, bananaStore));
        productDataStore.add(new Product("Banana Flower", new BigDecimal("10"), "USD", "Banana Flower", ingredients, bananaStore));
        productDataStore.add(new Product("Banana For Scale", new BigDecimal("10"), "USD", "Banana For Scale", merchandise, bananaStore));
        productDataStore.add(new Product("Geiger Counter", new BigDecimal("10"), "USD", "Geiger Counter", merchandise, bananaStore));
        productDataStore.add(new Product("Gloves", new BigDecimal("10"), "USD", "Gloves", merchandise, bananaStore));
        productDataStore.add(new Product("Green", new BigDecimal("10"), "USD", "Green", variants, bananaStore));
        productDataStore.add(new Product("Hoodie", new BigDecimal("10"), "USD", "Hoodie", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Ice Cream", new BigDecimal("10"), "USD", "Banana Ice Cream", foods, bananaStore));
        productDataStore.add(new Product("Jacket", new BigDecimal("10"), "USD", "Jacket", merchandise, bananaStore));
        productDataStore.add(new Product("Java Blue", new BigDecimal("10"), "USD", "Java Blue", variants, bananaStore));
        productDataStore.add(new Product("Banana Leaf", new BigDecimal("10"), "USD", "Banana Leaf", ingredients, bananaStore));
        productDataStore.add(new Product("Banana Leaf Umbrella", new BigDecimal("10"), "USD", "Banana Leaf Umbrella", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Mousse", new BigDecimal("10"), "USD", "Banana Mousse", foods, bananaStore));
        productDataStore.add(new Product("Pants", new BigDecimal("10"), "USD", "Pants", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Peel", new BigDecimal("10"), "USD", "Banana Peel", miscellaneous, bananaStore));
        productDataStore.add(new Product("Pink", new BigDecimal("10"), "USD", "Pink", variants, bananaStore));
        productDataStore.add(new Product("Banana Plush", new BigDecimal("10"), "USD", "Banana Plush", merchandise, bananaStore));
        productDataStore.add(new Product("Red", new BigDecimal("10"), "USD", "Red", variants, bananaStore));
        productDataStore.add(new Product("Ripe Banana", new BigDecimal("10"), "USD", "Ripe Banana", specialOffers, bananaStore));
        productDataStore.add(new Product("Banana Shake", new BigDecimal("10"), "USD", "Banana Shake", foods, bananaStore));
        productDataStore.add(new Product("Classic", new BigDecimal("10"), "USD", "Classic", variants, bananaStore));
        productDataStore.add(new Product("Socks", new BigDecimal("10"), "USD", "Socks", merchandise, bananaStore));
        productDataStore.add(new Product("Squishy Banana Toy", new BigDecimal("10"), "USD", "Squishy Banana Toy", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Sticker", new BigDecimal("10"), "USD", "Banana Sticker", merchandise, bananaStore));
        productDataStore.add(new Product("The Banana Sticker", new BigDecimal("10"), "USD", "The Banana Sticker", merchandise, bananaStore));
        productDataStore.add(new Product("Banana Tree", new BigDecimal("10"), "USD", "Banana Tree", cultivation, bananaStore));
        productDataStore.add(new Product("Banana Seeds", new BigDecimal("10"), "USD", "Banana Seeds", cultivation, bananaStore));
        productDataStore.add(new Product("T-Shirt", new BigDecimal("10"), "USD", "T-Shirt", merchandise, bananaStore));
        productDataStore.add(new Product("Unripe Banana", new BigDecimal("10"), "USD", "Unripe Banana", specialOffers, bananaStore));
        productDataStore.add(new Product("Used Banana", new BigDecimal("10"), "USD", "Used Banana", specialOffers, bananaStore));
        productDataStore.add(new Product("Waste Banana", new BigDecimal("10"), "USD", "Waste Banana", miscellaneous, bananaStore));
        productDataStore.add(new Product("Wild Banana", new BigDecimal("10"), "USD", "Wild Banana", variants, bananaStore));
        productDataStore.add(new Product("Peeled Banana", new BigDecimal("10"), "USD", "Peeled Banana", specialOffers, bananaStore));
        productDataStore.add(new Product("Banana Yogurt", new BigDecimal("10"), "USD", "Banana Yogurt", foods, bananaStore));
        productDataStore.add(new Product("Banana Joe Action Figure", new BigDecimal("10"), "USD", "Banana Joe Action Figure", merchandise, bananaStore));
    }
}
