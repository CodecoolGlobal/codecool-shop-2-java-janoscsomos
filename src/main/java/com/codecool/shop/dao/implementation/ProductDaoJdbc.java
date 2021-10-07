package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private final DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public Product find(String name) {
        return null;
    }

    @Override
    public Product findOne(String name) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.product_name, products.price, products.currency, products.product_description, category.category_name, category.department, category.category_description, supplier.supplier_name, supplier.supplier_description FROM products FULL OUTER JOIN category ON category.id = products.category_id FULL OUTER JOIN supplier ON supplier.id = products.supplier_id;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getString(6), rs.getString(7), rs.getString(8));
                Supplier supplier = new Supplier(rs.getString(9), rs.getString(10));
                Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5), productCategory, supplier);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {

            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.product_name, products.price, products.currency, products.product_description, category.category_name, category.department, category.category_description, supplier.supplier_name, supplier.supplier_description FROM products FULL OUTER JOIN category ON category.id = products.category_id FULL OUTER JOIN supplier ON supplier.id = products.supplier_id WHERE supplier.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getString(6), rs.getString(7), rs.getString(8));
                Supplier supplierFromDb = new Supplier(rs.getString(9), rs.getString(10));
                Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5), productCategory, supplierFromDb);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {

            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.product_name, products.price, products.currency, products.product_description, category.category_name, category.department, category.category_description, supplier.supplier_name, supplier.supplier_description FROM products FULL OUTER JOIN category ON category.id = products.category_id FULL OUTER JOIN supplier ON supplier.id = products.supplier_id WHERE category.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategoryFromDb = new ProductCategory(rs.getString(6), rs.getString(7), rs.getString(8));
                Supplier supplier = new Supplier(rs.getString(9), rs.getString(10));
                Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5), productCategoryFromDb, supplier);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getBy(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.product_name, products.price, products.currency, products.product_description, category.category_name, category.department, category.category_description, supplier.supplier_name, supplier.supplier_description FROM products FULL OUTER JOIN category ON category.id = products.category_id FULL OUTER JOIN supplier ON supplier.id = products.supplier_id WHERE products.product_name ILIKE ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            String namePart = "%" + name + "%";
            System.out.println(namePart);
            st.setString(1, namePart);
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getString(6), rs.getString(7), rs.getString(8));
                Supplier supplier = new Supplier(rs.getString(9), rs.getString(10));
                Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5), productCategory, supplier);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public Product getByOne(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.product_name, products.price, products.currency, products.product_description, category.category_name, category.department, category.category_description, supplier.supplier_name, supplier.supplier_description FROM products FULL OUTER JOIN category ON category.id = products.category_id FULL OUTER JOIN supplier ON supplier.id = products.supplier_id WHERE products.product_name = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory productCategory = new ProductCategory(rs.getString(6), rs.getString(7), rs.getString(8));
            Supplier supplier = new Supplier(rs.getString(9), rs.getString(10));
            Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(4), rs.getString(5), productCategory, supplier);
            product.setId(rs.getInt(1));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
