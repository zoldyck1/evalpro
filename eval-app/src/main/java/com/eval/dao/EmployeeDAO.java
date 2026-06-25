package com.eval.dao;

import com.eval.model.Employee;
import com.eval.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private static final String BASE_SELECT =
        "SELECT e.id, e.user_id, u.email, e.full_name, e.position, e.department, " +
        "       e.hire_date, e.manager_id, m.full_name AS manager_name " +
        "FROM employees e " +
        "JOIN users u ON u.id = e.user_id " +
        "LEFT JOIN managers m ON m.id = e.manager_id ";

    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "ORDER BY e.full_name");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    public List<Employee> findByManagerId(int managerId) {
        List<Employee> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "WHERE e.manager_id = ? ORDER BY e.full_name")) {
            ps.setInt(1, managerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    public Employee findById(int id) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "WHERE e.id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public Employee findByUserId(int userId) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + "WHERE e.user_id = ?")) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    public int create(Employee e) {
        String sql = "INSERT INTO employees (user_id, full_name, position, department, hire_date, manager_id) " +
                     "VALUES (?,?,?,?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getUserId());
            ps.setString(2, e.getFullName());
            ps.setString(3, e.getPosition());
            ps.setString(4, e.getDepartment());
            if (e.getHireDate() != null) ps.setDate(5, e.getHireDate()); else ps.setNull(5, Types.DATE);
            if (e.getManagerId() != null) ps.setInt(6, e.getManagerId()); else ps.setNull(6, Types.INTEGER);
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) {
                if (k.next()) return k.getInt(1);
            }
        } catch (SQLException ex) { throw new RuntimeException(ex); }
        return -1;
    }

    public void update(Employee e) {
        String sql = "UPDATE employees SET full_name=?, position=?, department=?, hire_date=?, manager_id=? WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getPosition());
            ps.setString(3, e.getDepartment());
            if (e.getHireDate() != null) ps.setDate(4, e.getHireDate()); else ps.setNull(4, Types.DATE);
            if (e.getManagerId() != null) ps.setInt(5, e.getManagerId()); else ps.setNull(5, Types.INTEGER);
            ps.setInt(6, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { throw new RuntimeException(ex); }
    }

    
    public void delete(int id) {
        try (Connection c = DBConnection.get()) {
            int userId = -1;
            try (PreparedStatement ps = c.prepareStatement("SELECT user_id FROM employees WHERE id=?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) { if (rs.next()) userId = rs.getInt(1); }
            }
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM employees WHERE id=?")) {
                ps.setInt(1, id); ps.executeUpdate();
            }
            if (userId > 0) {
                try (PreparedStatement ps = c.prepareStatement("DELETE FROM users WHERE id=?")) {
                    ps.setInt(1, userId); ps.executeUpdate();
                }
            }
        } catch (SQLException ex) { throw new RuntimeException(ex); }
    }

    private Employee map(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setUserId(rs.getInt("user_id"));
        e.setEmail(rs.getString("email"));
        e.setFullName(rs.getString("full_name"));
        e.setPosition(rs.getString("position"));
        e.setDepartment(rs.getString("department"));
        e.setHireDate(rs.getDate("hire_date"));
        int mid = rs.getInt("manager_id");
        e.setManagerId(rs.wasNull() ? null : mid);
        e.setManagerName(rs.getString("manager_name"));
        return e;
    }
}
