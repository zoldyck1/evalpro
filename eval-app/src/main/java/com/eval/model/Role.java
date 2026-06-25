package com.eval.model;

public class Role {
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String MANAGER  = "MANAGER";
    public static final String RH_ADMIN = "RH_ADMIN";

    private int id;
    private String name;

    public Role() {}
    public Role(int id, String name) { this.id = id; this.name = name; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
