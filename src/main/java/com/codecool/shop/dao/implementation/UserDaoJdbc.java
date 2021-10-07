package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbc implements UserDao {

    private final DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User find(String email, String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, email, password FROM users WHERE email = ? AND password = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            User user = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
            user.setId(rs.getInt(1));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email \"" + email + "\". Error type: ", e);
        }
    }
}
