package com.example.petranationwidemovies.model;

public class SelectedUser {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SelectedUser.user = user;
    }

    public static void unselectUser() {
        user = null;
    }
}
