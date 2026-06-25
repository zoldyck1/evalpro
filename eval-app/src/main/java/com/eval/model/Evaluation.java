package com.eval.model;

import java.sql.Timestamp;

public class Evaluation {
    public static final String DRAFT     = "DRAFT";
    public static final String VALIDATED = "VALIDATED";

    private int id;
    private int employeeId;
    private int managerId;
    private Integer score;
    private String comments;
    private String status;
    private Timestamp createdAt;
    private Timestamp validatedAt;
    private String employeeName;
    private String managerName;

    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public int getEmployeeId() { return employeeId; } public void setEmployeeId(int e) { this.employeeId = e; }
    public int getManagerId() { return managerId; } public void setManagerId(int m) { this.managerId = m; }
    public Integer getScore() { return score; } public void setScore(Integer s) { this.score = s; }
    public String getComments() { return comments; } public void setComments(String c) { this.comments = c; }
    public String getStatus() { return status; } public void setStatus(String s) { this.status = s; }
    public Timestamp getCreatedAt() { return createdAt; } public void setCreatedAt(Timestamp t) { this.createdAt = t; }
    public Timestamp getValidatedAt() { return validatedAt; } public void setValidatedAt(Timestamp t) { this.validatedAt = t; }
    public String getEmployeeName() { return employeeName; } public void setEmployeeName(String n) { this.employeeName = n; }
    public String getManagerName() { return managerName; } public void setManagerName(String n) { this.managerName = n; }
}
