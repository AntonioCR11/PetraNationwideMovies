package com.example.petranationwidemovies.model;

public class UserLogin {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserLogin.user = user;
    }

    public static void logout() {
        user = null;
    }
}
