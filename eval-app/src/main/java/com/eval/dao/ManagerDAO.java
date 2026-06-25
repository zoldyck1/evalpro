package com.eval.dao;

import com.eval.model.Manager;
import com.eval.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    private static final String BASE_SELECT =
        "SELECT m.id, m.user_id, u.email, m.full_name, m.department " +
        "FROM managers m JOIN users u ON u.id = m.user_id ";

    public List<Manager> findAll() {
        List<Manager> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "ORDER BY m.full_name");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    public Manager findById(int id) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "WHERE m.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public Manager findByUserId(int userId) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "WHERE m.user_id = ?")) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public int create(Manager m) {
        String sql = "INSERT INTO managers (user_id, full_name, department) VALUES (?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getUserId());
            ps.setString(2, m.getFullName());
            ps.setString(3, m.getDepartment());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { if (k.next()) return k.getInt(1); }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return -1;
    }

    public void update(Manager m) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(
                 "UPDATE managers SET full_name=?, department=? WHERE id=?")) {
            ps.setString(1, m.getFullName());
            ps.setString(2, m.getDepartment());
            ps.setInt(3, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public void delete(int id) {
        try (Connection c = DBConnection.get()) {
            int userId = -1;
            try (PreparedStatement ps = c.prepareStatement("SELECT user_id FROM managers WHERE id=?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) userId = rs.getInt(1); }
            }
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM managers WHERE id=?")) {
                ps.setInt(1, id); ps.executeUpdate();
            }
            if (userId > 0) {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM users WHERE id=?")) {
                    ps.setInt(1, userId); ps.executeUpdate();
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    private Manager map(ResultSet rs) throws SQLException {
        Manager m = new Manager();
        m.setId(rs.getInt("id"));
        m.setUserId(rs.getInt("user_id"));
        m.setEmail(rs.getString("email"));
        m.setFullName(rs.getString("full_name"));
        m.setDepartment(rs.getString("department"));
        return m;
    }
}
