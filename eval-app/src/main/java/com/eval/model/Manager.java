package com.eval.model;

public class Manager {
    private int id;
    private int userId;
    private String email;
    private String fullName;
    private String department;

    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; } public void setUserId(int u) { this.userId = u; }
    public String getEmail() { return email; } public void setEmail(String e) { this.email = e; }
    public String getFullName() { return fullName; } public void setFullName(String n) { this.fullName = n; }
    public String getDepartment() { return department; } public void setDepartment(String d) { this.department = d; }
}
