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
        supplierDataStore.add(bananaStore);
        Supplier bananaToysInc = new Supplier("Banana Toys Inc.", "Supplier specializing in banana-themed toys.");
        supplierDataStore.add(bananaToysInc);
        Supplier yellowCafe = new Supplier("Yellow Cafe", "Pastry shop providing banana-based confectionery.");
        supplierDataStore.add(yellowCafe);
        Supplier banananson = new Supplier("Bana N. Anson's Wholesale Market", "Wholesale market for fruits and ingredients.");
        supplierDataStore.add(banananson);
        Supplier musaCatering = new Supplier("Musa Catering", "Catering service");
        supplierDataStore.add(musaCatering);
        Supplier cavendishInstruments = new Supplier("Cavendish Instruments", "Instruments company");
        supplierDataStore.add(cavendishInstruments);
        Supplier pnbClothing = new Supplier("P & B Clothing", "Fashion company with many banana-themed clothes");
        supplierDataStore.add(pnbClothing);
        Supplier tropicalFarmerCo = new Supplier("Tropical Farmer Co.", "Company specializing in cultivating tropical plants.");
        supplierDataStore.add(tropicalFarmerCo);
        Supplier bMerch = new Supplier("B-Merch", "Company specializing in banana-themed merchandise");
        supplierDataStore.add(bMerch);
        Supplier other = new Supplier("Other", "Products from other sources.");
        supplierDataStore.add(other);

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
        productDataStore.add(new Product("Banaan", new BigDecimal("3.49"), "USD", "Naan is a Western/Southern Asian flatbread, now with added bananas. Mostly for the name.", foods, musaCatering));
        productDataStore.add(new Product("Beanie", new BigDecimal("25"), "USD", "A nice warm beanie with banana badge.", merchandise, pnbClothing));
        productDataStore.add(new Product("Inflatable Banana Boat", new BigDecimal("30"), "USD", "All aboard the USS Banana!", merchandise, bMerch));
        productDataStore.add(new Product("Boxer", new BigDecimal("15.50"), "USD", "If you want to wear bananas even where people can't see it.", merchandise, pnbClothing));
        productDataStore.add(new Product("Banana Bread", new BigDecimal("4.25"), "USD", "Bread made from mashed bananas.", foods, musaCatering));
        productDataStore.add(new Product("Banana Cake", new BigDecimal("6.39"), "USD", "There are various types of banana cakes, and we don't always have the same, so it's a bit of a lottery. But it's guaranteed to contain banana.", foods, yellowCafe));
        productDataStore.add(new Product("Cap", new BigDecimal("27.99"), "USD", "Show your support for the Budapest Bananas.", merchandise, pnbClothing));
        productDataStore.add(new Product("Banana Chips", new BigDecimal("5.10"), "USD", "Crispy, fried banana slices. Can be prepared in both sweet and salty variants.", foods, musaCatering));
        productDataStore.add(new Product("Banana Bundle", new BigDecimal("10"), "USD", "For those who just simply can't have enough of banana. 10 USD/kg", specialOffers, banananson));
        productDataStore.add(new Product("Costume", new BigDecimal("34.99"), "USD", "Become the banana.", merchandise, bananaToysInc));
        productDataStore.add(new Product("Fe'i", new BigDecimal("5"), "USD", "Striking combination of red-on-the-outside and yellow-on-the-inside from the Pacific Islands.", variants, banananson));
        productDataStore.add(new Product("Banana Cream-Filled Banana", new BigDecimal("7.49"), "USD", "We heard you liked bananas so we put a banana in your banana so you can eat banana while you're eating banana.", foods, yellowCafe));
        productDataStore.add(new Product("Fried Banana", new BigDecimal("8.50"), "USD", "Deep-fried banana snack made from cooking bananas.", foods, musaCatering));
        productDataStore.add(new Product("Banana Flower", new BigDecimal("2.50"), "USD", "Banana flower, used as vegetable in Southeast Asian cuisine.", ingredients, banananson));
        productDataStore.add(new Product("Banana For Scale", new BigDecimal("2"), "USD", "Handy tool to illustrate size in pictures.", merchandise, cavendishInstruments));
        productDataStore.add(new Product("Geiger Counter", new BigDecimal("79.9"), "USD", "It's always nice to have a Geiger counter around to check your favourite banana's radioactivity.", merchandise, cavendishInstruments));
        productDataStore.add(new Product("Gloves", new BigDecimal("14.99"), "USD", "Part of the legendary set Attire of the Ancient Banana Consumer. Piece: Hands.", merchandise, pnbClothing));
        productDataStore.add(new Product("Green", new BigDecimal("2"), "USD", "Green bananas, commonly known as cooking bananas or plantains. Cook them before eating. Or don't. No pressure.", variants, banananson));
        productDataStore.add(new Product("Hoodie", new BigDecimal("28"), "USD", "Part of the epic set Bananamancer Garment. Piece: Chest.", merchandise, pnbClothing));
        productDataStore.add(new Product("Banana Ice Cream", new BigDecimal("10"), "USD", "Banana flavoured ice cream.", foods, yellowCafe));
        productDataStore.add(new Product("Jacket", new BigDecimal("29.99"), "USD", "Cool Banana Jacket.", merchandise, pnbClothing));
        productDataStore.add(new Product("Blue Java", new BigDecimal("4"), "USD", "Vanilla flavoured instance of the banana, encapsulated in blue.", variants, banananson));
        productDataStore.add(new Product("Banana Leaf", new BigDecimal("2"), "USD", "Banana leaf. Used as part of certain cooking methods in Southeast Asian cuisine. Can be also used as eco-friendly disposable plates. Oh, and it's also waterproof, so it's truly multifunctional.", ingredients, banananson));
        productDataStore.add(new Product("Banana Leaf Umbrella", new BigDecimal("17.99"), "USD", "May not contain actual banana leaves, but just as waterproof as the real thing.", merchandise, bMerch));
        productDataStore.add(new Product("Banana Mousse", new BigDecimal("6.39"), "USD", "Banana flavoured mousse.", foods, yellowCafe));
        productDataStore.add(new Product("Pants", new BigDecimal("26.50"), "USD", "We have all pieces of banana-themed clothes, so why not this one?", merchandise, pnbClothing));
        productDataStore.add(new Product("Banana Peel", new BigDecimal("0.2"), "USD", "At this price, you don't want to let this one slip by.", miscellaneous, other));
        productDataStore.add(new Product("Pink", new BigDecimal("3"), "USD", "Pink coloured wild banana variant. Small, and tricky to eat because of the seeds, but it looks nice.", variants, banananson));
        productDataStore.add(new Product("Banana Plush", new BigDecimal("32.50"), "USD", "A great chance to hug your favourite banana.", merchandise, bananaToysInc));
        productDataStore.add(new Product("Red", new BigDecimal("4"), "USD", "Red banana, sometimes with some raspberry flavour. Apparently it's also an aphrodisiac but we don't guarantee anything.", variants, banananson));
        productDataStore.add(new Product("Ripe Banana", new BigDecimal("2"), "USD", "A bit overripe, but still good.", specialOffers, banananson));
        productDataStore.add(new Product("Banana Shake", new BigDecimal("8.49"), "USD", "Banana flavoured shake.", foods, yellowCafe));
        productDataStore.add(new Product("Classic", new BigDecimal("2"), "USD", "Well, what can we say about it? It's a banana.", variants, banananson));
        productDataStore.add(new Product("Socks", new BigDecimal("2"), "USD", "Every shoe is more comfortable if you have bananas on.", merchandise, pnbClothing));
        productDataStore.add(new Product("Squishy Banana Toy", new BigDecimal("9.50"), "USD", "If you've ever felt the need to squeeze bananas when nervous or stressed, we've got you covered.", merchandise, bananaToysInc));
        productDataStore.add(new Product("Banana Sticker", new BigDecimal("5"), "USD", "Just a plain old banana sticker.", merchandise, bMerch));
        productDataStore.add(new Product("The Banana Sticker", new BigDecimal("15"), "USD", "There are banana stickers and Banana Stickers. This is the latter one. Guarantees attention for 15 minutes.", merchandise, bMerch));
        productDataStore.add(new Product("Banana Plant", new BigDecimal("20"), "USD", "For those who want to make a head start in cultivating bananas.", cultivation, tropicalFarmerCo));
        productDataStore.add(new Product("Banana Seeds", new BigDecimal("5"), "USD", "Seeds to start growing bananas from scratch.", cultivation, tropicalFarmerCo));
        productDataStore.add(new Product("T-Shirt", new BigDecimal("19.99"), "USD", "Even a plain T-Shirt like this looks better with bananas.", merchandise, pnbClothing));
        productDataStore.add(new Product("Unripe Banana", new BigDecimal("1.50"), "USD", "It's not quite there yet, but edible. We cater to all tastes.", specialOffers, banananson));
        productDataStore.add(new Product("Used Banana", new BigDecimal("1"), "USD", "Adopt a discarded banana.", specialOffers, other));
        productDataStore.add(new Product("Waste Banana", new BigDecimal("100"), "USD", "Waste banana for feeding livestock. 100 USD/100 kg.", miscellaneous, tropicalFarmerCo));
        productDataStore.add(new Product("Wild Banana", new BigDecimal("3"), "USD", "The banana before it went mainstream. Way too heavy on seeds and not much substance to it, but hey, at least you liked it before it was cool, right?", variants, banananson));
        productDataStore.add(new Product("Peeled Banana", new BigDecimal("3"), "USD", "For those who can't bother to peel their bananas. Service price included.", specialOffers, musaCatering));
        productDataStore.add(new Product("Banana Yogurt", new BigDecimal("4.59"), "USD", "Banana flavoured yogurt.", foods, yellowCafe));
        productDataStore.add(new Product("Banana Joe Action Figure", new BigDecimal("34.99"), "USD", "Licence included.", merchandise, bananaToysInc));
    }
}
