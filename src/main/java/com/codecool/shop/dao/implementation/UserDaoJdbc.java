package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private final DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, email, password FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
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
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with id " + id + ". Error type: ", e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<User> output = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                user.setId(rs.getInt(1));
                output.add(user);
            }
            return output;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all users: ", e);
        }
    }
}
