package com.example.petranationwidemovies.model;

public class PaymentMethod {
    private int id;
    private String name;

    public PaymentMethod(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentMethod(String name) {
        this.name = name;
    }

    public PaymentMethod() {
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

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
