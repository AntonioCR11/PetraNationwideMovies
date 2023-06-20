package com.example.petranationwidemovies.model;

public class Booking {
    private int id;
    private double total_price;
    private int booked_seat;
    private PaymentMethod paymentMethod;
    private User user;
    private Movie movie;

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getBooked_seat() {
        return booked_seat;
    }

    public void setBooked_seat(int booked_seat) {
        this.booked_seat = booked_seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", total_price=" + total_price +
                ", booked_seat=" + booked_seat +
                ", paymentMethod=" + paymentMethod +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}
