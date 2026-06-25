package com.eval.dao;

import com.eval.model.Evaluation;
import com.eval.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluationDAO {

    private static final String BASE_SELECT =
        "SELECT ev.id, ev.employee_id, ev.manager_id, ev.score, ev.comments, " +
        "       ev.status, ev.created_at, ev.validated_at, " +
        "       e.full_name AS employee_name, m.full_name AS manager_name " +
        "FROM evaluations ev " +
        "JOIN employees e ON e.id = ev.employee_id " +
        "JOIN managers  m ON m.id = ev.manager_id ";

    public List<Evaluation> findAll() {
        return query(BASE_SELECT + "ORDER BY ev.created_at DESC", null);
    }

    public List<Evaluation> findByEmployeeId(int employeeId) {
        return query(BASE_SELECT + "WHERE ev.employee_id = ? ORDER BY ev.created_at DESC", employeeId);
    }

    public List<Evaluation> findByManagerId(int managerId) {
        return query(BASE_SELECT + "WHERE ev.manager_id = ? ORDER BY ev.created_at DESC", managerId);
    }

    public Evaluation findById(int id) {
        List<Evaluation> r = query(BASE_SELECT + "WHERE ev.id = ?", id);
        return r.isEmpty() ? null : r.get(0);
    }

    public int create(Evaluation e) {
        String sql = "INSERT INTO evaluations (employee_id, manager_id, score, comments, status) " +
                     "VALUES (?,?,?,?,?)";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getEmployeeId());
            ps.setInt(2, e.getManagerId());
            if (e.getScore() != null) ps.setInt(3, e.getScore()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, e.getComments());
            ps.setString(5, e.getStatus() == null ? "DRAFT" : e.getStatus());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { if (k.next()) return k.getInt(1); }
        } catch (SQLException ex) { throw new RuntimeException(ex); }
        return -1;
    }

    public void update(Evaluation e) {
        String sql = "UPDATE evaluations SET score=?, comments=?, status=? WHERE id=?";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            if (e.getScore() != null) ps.setInt(1, e.getScore()); else ps.setNull(1, Types.INTEGER);
            ps.setString(2, e.getComments());
            ps.setString(3, e.getStatus());
            ps.setInt(4, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { throw new RuntimeException(ex); }
    }

    public void validate(int id) {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(
                 "UPDATE evaluations SET status='VALIDATED', validated_at=NOW() WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { throw new RuntimeException(ex); }
    }

    
    public List<String[]> avgScoreByDepartment() {
        List<String[]> rows = new ArrayList<>();
        String sql = "SELECT e.department, ROUND(AVG(ev.score),1) AS avg_score, COUNT(*) AS n " +
                     "FROM evaluations ev JOIN employees e ON e.id = ev.employee_id " +
                     "WHERE ev.score IS NOT NULL GROUP BY e.department ORDER BY avg_score DESC";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                rows.add(new String[]{
                    rs.getString("department") == null ? "(none)" : rs.getString("department"),
                    rs.getString("avg_score"),
                    rs.getString("n")
                });
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return rows;
    }

    public int[] statusCounts() {
        int[] r = {0, 0};
        String sql = "SELECT status, COUNT(*) c FROM evaluations GROUP BY status";
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if ("DRAFT".equals(rs.getString("status")))     r[0] = rs.getInt("c");
                if ("VALIDATED".equals(rs.getString("status"))) r[1] = rs.getInt("c");
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return r;
    }

    private List<Evaluation> query(String sql, Integer param) {
        List<Evaluation> list = new ArrayList<>();
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            if (param != null) ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    private Evaluation map(ResultSet rs) throws SQLException {
        Evaluation e = new Evaluation();
        e.setId(rs.getInt("id"));
        e.setEmployeeId(rs.getInt("employee_id"));
        e.setManagerId(rs.getInt("manager_id"));
        int s = rs.getInt("score"); e.setScore(rs.wasNull() ? null : s);
        e.setComments(rs.getString("comments"));
        e.setStatus(rs.getString("status"));
        e.setCreatedAt(rs.getTimestamp("created_at"));
        e.setValidatedAt(rs.getTimestamp("validated_at"));
        e.setEmployeeName(rs.getString("employee_name"));
        e.setManagerName(rs.getString("manager_name"));
        return e;
    }
}
