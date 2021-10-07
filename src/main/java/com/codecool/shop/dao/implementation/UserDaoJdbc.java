package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private final DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {

            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding user \"" + user.getName() + "\". Error type: ", e);
        }
    }

    @Override
    public User find(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, email, password FROM users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new User(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email \"" + email + "\". Error type: ", e);
        }
    }

    @Override
    public String check(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT password FROM users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading category with id: ", e);
        }
    }
}
