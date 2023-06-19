package com.example.petranationwidemovies.model;

public class SelectedMovie {

    private static Movie movie;

    public static Movie getMovie() {
        return movie;
    }

    public static void setMovie(Movie movie) {
        SelectedMovie.movie = movie;
    }

    public static void unselectMovie() {
        movie = null;
    }
}
