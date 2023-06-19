package com.example.petranationwidemovies.repositories;

import com.example.petranationwidemovies.model.Booking;
import com.example.petranationwidemovies.model.Movie;
import com.example.petranationwidemovies.model.PaymentMethod;
import com.example.petranationwidemovies.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository extends AbstractRepository {
    private String getQuery = "select * from bookings";
    private String insertQuery = "insert into bookings (user_id, movie_id, payment_method_id, total_price, booked_seat) values (?, ?, ?, ?, ?)";

    @Override
    public int add(Object element) throws SQLException {
        Booking booking = (Booking) element;
        PreparedStatement ps = super.conn.prepareStatement(insertQuery);
        ps.setInt(1, booking.getUser().getId());
        ps.setInt(2, booking.getMovie().getId());
        ps.setInt(3, booking.getPaymentMethod().getId());
        ps.setDouble(4, booking.getTotal_price());
        ps.setInt(5, booking.getBooked_seat());
        return ps.executeUpdate();
    }

    @Override
    public Object get() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery);
        return executeQuery(ps);
    }

    private Object executeQuery(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<Booking> ls = new ArrayList<>();

        while (rs.next()) {
            Booking booking = new Booking();
            booking.setId(rs.getInt("id"));
            booking.setBooked_seat(rs.getInt("booked_seat"));
            booking.setTotal_price(rs.getDouble("total_price"));

            UserRepository userRepository = new UserRepository();
            booking.setUser((User) userRepository.get(rs.getInt("user_id")));

            MovieRepository movieRepository = new MovieRepository();
            booking.setMovie((Movie) movieRepository.get(rs.getInt("movie_id")));

            PaymentMethodRepository paymentMethodRepository = new PaymentMethodRepository();
            booking.setPaymentMethod((PaymentMethod) paymentMethodRepository.get(rs.getInt("payment_method_id")));


            ls.add(booking);
        }
        return ls;
    }

    @Override
    public int update(Object element) throws SQLException {
        return 0;
    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
