package com.eval.dao;

import com.eval.model.User;
import com.eval.util.DBConnection;
import com.eval.util.PasswordUtil;

import java.sql.*;


public class UserDAO {

    
    public User findByEmail(String email) {
        String sql = "SELECT u.id, u.email, u.password_hash, u.salt, u.role_id, r.name AS role_name " +
                     "FROM users u JOIN roles r ON r.id = u.role_id WHERE u.email = ?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public User findById(int id) {
        String sql = "SELECT u.id, u.email, u.password_hash, u.salt, u.role_id, r.name AS role_name " +
                     "FROM users u JOIN roles r ON r.id = u.role_id WHERE u.id = ?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    
    public int create(String email, String plainPassword, int roleId) {
        String salt = PasswordUtil.newSalt();
        String hash = PasswordUtil.hash(plainPassword, salt);
        String sql = "INSERT INTO users (email, password_hash, salt, role_id) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, email);
            ps.setString(2, hash);
            ps.setString(3, salt);
            ps.setInt(4, roleId);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return -1;
    }

    public void delete(int id) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setInt(1, id); ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public int findRoleIdByName(String name) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("SELECT id FROM roles WHERE name = ?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return -1;
    }

    private User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setSalt(rs.getString("salt"));
        u.setRoleId(rs.getInt("role_id"));
        u.setRoleName(rs.getString("role_name"));
        return u;
    }
}
