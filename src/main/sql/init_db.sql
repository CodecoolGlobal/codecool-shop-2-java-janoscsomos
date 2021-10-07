DROP TABLE IF EXISTS public.supplier CASCADE ;
CREATE TABLE public.supplier (
    id serial NOT NULL PRIMARY KEY,
    supplier_name text,
    supplier_description text
);


DROP TABLE IF EXISTS public.category CASCADE ;
CREATE TABLE public.category (
    id serial NOT NULL PRIMARY KEY,
    category_name text,
    department text,
    category_description text
);



ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
DROP TABLE IF EXISTS public.products CASCADE;
CREATE TABLE public.products (
    id serial NOT NULL PRIMARY KEY,
    product_name text,
    price numeric,
    currency text,
    product_description text,
    category_id integer NOT NULL,
    supplier_id integer NOT NULL
);


DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users (
    id serial NOT NULL PRIMARY KEY,
    name text,
    email varchar(100),
    password varchar (100)
);


DROP TABLE IF EXISTS public.order CASCADE;
CREATE TABLE public.order (
    id serial NOT NULL PRIMARY KEY,
    order_id text,
    first_name text,
    last_name text,
    email text,
    address text,
    country text,
    city text,
    total_amount numeric,
    order_complete boolean
);

ALTER TABLE IF EXISTS ONLY public.shopping_cart DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.shopping_cart DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
DROP TABLE IF EXISTS public.shopping_cart CASCADE;
CREATE TABLE public.shopping_cart (
    id serial NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    amount integer
);


ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);

ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.shopping_cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.products(id);


INSERT INTO supplier VALUES (1, 'Bana N. Anson''s Wholesale Market', 'Wholesale Market for fruits and ingredients');
INSERT INTO supplier VALUES (2, 'Banana Toys Inc.', 'Supplier specializing in banana-themed toys.');
INSERT INTO supplier VALUES (3, 'B-Merch', 'Company specializing in banana-themed merchandise.');
INSERT INTO supplier VALUES (4, 'Cavendish Instruments', 'Instruments company.');
INSERT INTO supplier VALUES (5, 'Musa Catering', 'Catering service.');
INSERT INTO supplier VALUES (6, 'Other', 'Products from other sources.');
INSERT INTO supplier VALUES (7, 'P & B Clothing', 'Fashion company with many banana-themed clothes.');
INSERT INTO supplier VALUES (8, 'Tropical Farmer Co.', 'Company specializing in cultivating tropical plants.');
INSERT INTO supplier VALUES (9, 'Yellow Cafe', 'Pastry shop providing banana-based confectionery.');


INSERT INTO category VALUES (1, 'Banana foods', 'Food', 'Wide selection of banana based dishes, desserts and sweets.');
INSERT INTO category VALUES (2, 'Banana variants', 'Basics', 'Bananas in all shapes and sizes.');
INSERT INTO category VALUES (3, 'Cultivation', 'Horticulture', 'Wanna grow your own bananas? Look no further, here''s what you need to start.');
INSERT INTO category VALUES (4, 'Ingredients', 'Food', 'Banana is not just about the fruit, here you can find other parts that can be used in foods.');
INSERT INTO category VALUES (5, 'Merchandise', 'Merchandise', 'All kinds of banana-themed fun stuff from plushes to apparel.');
INSERT INTO category VALUES (6, 'Miscellaneous', 'Miscellaneous', 'Haven''t found what you''re looking for anywhere else? Try here!');
INSERT INTO category VALUES (7, 'Special Offers', 'Basics', 'Bananas for special needs.');


INSERT INTO products VALUES (1, 'Banaan', 3.49, 'USD', 'Naan is a Western/Southern Asian flatbread, now with added bananas. Mostly for the name.', 1, 5);
INSERT INTO products VALUES (2, 'Beanie', 25, 'USD', 'A nice warm beanie with banana badge.', 5, 7);
INSERT INTO products VALUES (3, 'Inflatable Banana Boat', 30, 'USD', 'All aboard the USS Banana!', 5, 3);
INSERT INTO products VALUES (4, 'Boxer', 15.50, 'USD', 'If you want to wear bananas even where people cant see it.', 5, 7);
INSERT INTO products VALUES (5, 'Banana Bread', 4.25, 'USD', 'Bread made from mashed bananas.', 1, 5);
INSERT INTO products VALUES (6, 'Banana Cake', 6.39, 'USD', 'There are various types of banana cakes, and we dont always have the same, so it''s a bit of a lottery. But it''s guaranteed to contain banana.', 1, 9);
INSERT INTO products VALUES (7, 'Cap', 27.99, 'USD', 'Show your support for the Budapest Bananas.', 5, 7);
INSERT INTO products VALUES (8, 'Banana Chips', 5.10, 'USD', 'Crispy, fried banana slices. Can be prepared in both sweet and salty variants.', 1, 5);
INSERT INTO products VALUES (9, 'Banana Bundle', 10, 'USD', 'For those who just simply cant have enough of banana. 10 USD/kg', 2, 1);
INSERT INTO products VALUES (10, 'Costume', 34.99, 'USD', 'Become the banana.', 5, 2);
INSERT INTO products VALUES (11, 'Fe''i', 5, 'USD', 'Striking combination of red-on-the-outside and yellow-on-the-inside from the Pacific Islands.', 2, 1);
INSERT INTO products VALUES (12, 'Banana Cream-Filled Banana', 7.49, 'USD', 'We heard you liked bananas so we put a banana in your banana so you can eat banana while your''e eating banana.', 1, 9);
INSERT INTO products VALUES (13, 'Fried Banana', 8.50, 'USD', 'Deep-fried banana snack made from cooking bananas.', 1, 5);
INSERT INTO products VALUES (14, 'Banana Flower', 2.50, 'USD', 'Banana flower, used as vegetable in Southeast Asian cuisine.', 4, 1);
INSERT INTO products VALUES (15, 'Banana For Scale', 2, 'USD', 'Handy tool to illustrate size in pictures.', 5, 4);
INSERT INTO products VALUES (16, 'Geiger Counter', 79.9, 'USD', 'It''s always nice to have a Geiger counter around to check your favourite bananas radioactivity.', 5, 4);
INSERT INTO products VALUES (17, 'Gloves', 14.99, 'USD', 'Part of the legendary set Attire of the Ancient Banana Consumer. Piece: Hands.', 5, 7);
INSERT INTO products VALUES (18, 'Green', 2, 'USD', 'Green bananas, commonly known as cooking bananas or plantains. Cook them before eating. Or dont. No pressure.', 2, 1);
INSERT INTO products VALUES (19, 'Hoodie', 28, 'USD', 'Part of the epic set Bananamancer Garment. Piece: Chest.', 5, 7);
INSERT INTO products VALUES (20, 'Banana Ice Cream', 10, 'USD', 'Banana flavoured ice cream.', 1, 9);
INSERT INTO products VALUES (21, 'Jacket', 29.99, 'USD', 'Cool Banana Jacket.', 5, 7);
INSERT INTO products VALUES (22, 'Blue Java', 4, 'USD', 'Vanilla flavoured instance of the banana, encapsulated in blue.', 2, 1);
INSERT INTO products VALUES (23, 'Banana Leaf', 2, 'USD', 'Banana leaf. Used as part of certain cooking methods in Southeast Asian cuisine. Can be also used as eco-friendly disposable plates. Oh, and it''s also waterproof, so it''s truly multifunctional.', 4, 1);
INSERT INTO products VALUES (24, 'Banana Leaf Umbrella', 17.99, 'USD', 'May not contain actual banana leaves, but just as waterproof as the real thing.', 5, 3);
INSERT INTO products VALUES (25, 'Banana Mousse', 6.39, 'USD', 'Banana flavoured mousse.', 1, 9);
INSERT INTO products VALUES (26, 'Pants', 26.50, 'USD', 'We have all pieces of banana-themed clothes, so why not this one?', 5, 7);
INSERT INTO products VALUES (27, 'Banana Peel', 0.2, 'USD', 'At this price, you don''t want to let this one slip by.', 6, 6);
INSERT INTO products VALUES (28, 'Pink', 3, 'USD', 'Pink coloured wild banana variant. Small, and tricky to eat because of the seeds, but it looks nice.', 2, 1);
INSERT INTO products VALUES (29, 'Banana Plush', 32.50, 'USD', 'A great chance to hug your favourite banana.', 5, 2);
INSERT INTO products VALUES (30, 'Red', 4, 'USD', 'Red banana, sometimes with some raspberry flavour. Apparently it''s also an aphrodisiac but we dont guarantee anything.', 2, 1);
INSERT INTO products VALUES (31, 'Ripe Banana', 2, 'USD', 'A bit overripe, but still good.', 7, 1);
INSERT INTO products VALUES (32, 'Banana Shake', 8.49, 'USD', 'Banana flavoured shake.', 1, 9);
INSERT INTO products VALUES (33, 'Classic', 2, 'USD', 'Well, what can we say about it? Its a banana.', 2, 1);
INSERT INTO products VALUES (34, 'Socks', 2, 'USD', 'Every shoe is more comfortable if you have bananas on.', 5, 7);
INSERT INTO products VALUES (35, 'Squishy Banana Toy', 9.50, 'USD', 'If you''ve ever felt the need to squeeze bananas when nervous or stressed, we''ve got you covered.', 5, 2);
INSERT INTO products VALUES (36, 'Banana Sticker', 5, 'USD', 'Just a plain old banana sticker.', 5, 3);
INSERT INTO products VALUES (37, 'The Banana Sticker', 15, 'USD', 'There are banana stickers and Banana Stickers. This is the latter one. Guarantees attention for 15 minutes.', 5, 3);
INSERT INTO products VALUES (38, 'Banana Plant', 20, 'USD', 'For those who want to make a head start in cultivating bananas.', 3, 8);
INSERT INTO products VALUES (39, 'Banana Seeds', 5, 'USD', 'Seeds to start growing bananas from scratch.', 3, 8);
INSERT INTO products VALUES (40, 'T-Shirt', 19.99, 'USD', 'Even a plain T-Shirt like this looks better with bananas.', 5, 7);
INSERT INTO products VALUES (41, 'Unripe Banana', 1.50, 'USD', 'It''s not quite there yet, but edible. We cater to all tastes.', 7, 1);
INSERT INTO products VALUES (42, 'Used Banana', 1, 'USD', 'Adopt a discarded banana.', 7, 6);
INSERT INTO products VALUES (43, 'Waste Banana', 100, 'USD', 'Waste banana for feeding livestock. 100 USD/100 kg.', 6, 8);
INSERT INTO products VALUES (44, 'Wild Banana', 3, 'USD', 'The banana before it went mainstream. Way too heavy on seeds and not much substance to it, but hey, at least you liked it before it was cool, right?', 2, 1);
INSERT INTO products VALUES (45, 'Peeled Banana', 3, 'USD', 'For those who cant bother to peel their bananas. Service price included.', 7, 5);
INSERT INTO products VALUES (46, 'Banana Yogurt', 4.59, 'USD', 'Banana flavoured yogurt.', 1, 9);
INSERT INTO products VALUES (47, 'Banana Joe Action Figure', 34.99, 'USD', 'Licence included.', 5, 2);
