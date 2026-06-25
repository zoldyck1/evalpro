package com.eval.model;

import java.sql.Date;

public class Employee {
    private int id;
    private int userId;
    private String email;
    private String fullName;
    private String position;
    private String department;
    private Date hireDate;
    private Integer managerId;
    private String managerName;

    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; } public void setUserId(int u) { this.userId = u; }
    public String getEmail() { return email; } public void setEmail(String e) { this.email = e; }
    public String getFullName() { return fullName; } public void setFullName(String n) { this.fullName = n; }
    public String getPosition() { return position; } public void setPosition(String p) { this.position = p; }
    public String getDepartment() { return department; } public void setDepartment(String d) { this.department = d; }
    public Date getHireDate() { return hireDate; } public void setHireDate(Date d) { this.hireDate = d; }
    public Integer getManagerId() { return managerId; } public void setManagerId(Integer m) { this.managerId = m; }
    public String getManagerName() { return managerName; } public void setManagerName(String m) { this.managerName = m; }
}
