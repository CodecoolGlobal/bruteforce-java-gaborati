package com.codecool.bruteforce.users.repository;

import com.codecool.bruteforce.logger.Logger;
import com.codecool.bruteforce.users.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String dbFile;
    private Logger logger;

    public UserRepositoryImpl(String dbFile, Logger logger) {
        this.dbFile = dbFile;
        this.logger = logger;
    }

 private  Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + dbFile;
            conn = DriverManager.getConnection(url);

            logger.logInfo("Connection to SQLite has been established.");

            return conn;

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

        return null;
    }

    public void add(String userName, String password) {
        String sql = "INSERT INTO users(user_name, password) VALUES (?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            logger.logInfo("New user Added to the Database");

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

    }

    public void update(int id, String userName, String password) {
        String sql = "UPDATE users SET user_name = ?, password = ? WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

            logger.logInfo("User Updated");

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

            logger.logInfo("Deleted");

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }


    }

    public void deleteAll() {
        String sql = "DELETE FROM users";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();

            logger.logInfo("All users deleted");

        } catch (SQLException e) {
            logger.logError(e.getMessage());
        }

    }

    public User get(int id) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }
}
