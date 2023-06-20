package com.example.petranationwidemovies.model;

public class User {
    private int id;
    private String name;
    private String nrp;
    private String password;
    private int role;

    public User(int id, String name, String nrp, String password, int role) {
        this.id = id;
        this.name = name;
        this.nrp = nrp;
        this.password = password;
        this.role = role;
    }

    public User(String name, String nrp, String password, int role) {
        this.name = name;
        this.nrp = nrp;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", name: " + name + ", nrp: " + nrp + ", password: " + password + "}";
    }
}
