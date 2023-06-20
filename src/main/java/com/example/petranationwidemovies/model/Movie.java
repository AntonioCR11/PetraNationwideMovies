package com.example.petranationwidemovies.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Movie {
    private int id;
    private String name;
    private String image;
    private Timestamp start_date;
    private Timestamp end_date;
    private Time playing_time;
    private double price;
    private Location location;

    public Movie() {
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

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public Time getPlaying_time() {
        return playing_time;
    }

    public void setPlaying_time(Time playing_time) {
        this.playing_time = playing_time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", playing_time=" + playing_time +
                ", price=" + price +
                ", location=" + location +
                '}';
    }
}
