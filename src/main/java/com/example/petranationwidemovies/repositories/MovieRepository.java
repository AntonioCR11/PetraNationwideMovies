package com.example.petranationwidemovies.repositories;

import com.example.petranationwidemovies.model.Location;
import com.example.petranationwidemovies.model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository extends AbstractRepository {

    private String getQuery = "select * from movies";
    private String insertQuery = "insert into movies (name, image, start_date, end_date, playing_time, price, location_id) values (?,?,?,?,?,?,?);";
    private String updateQuery = "update movies set name = ?, image = ?, start_date = ?, end_date = ?, playing_time = ?, price = ?, location_id = ? where id = ?";
    private String deleteQuery = "delete from movies where id = ?";

    @Override
    public int add(Object element) throws SQLException {
        Movie movie = (Movie) element;
        PreparedStatement ps = super.conn.prepareStatement(insertQuery);
        ps.setString(1, movie.getName());
        ps.setString(2, movie.getImage());
        ps.setTimestamp(3, movie.getStart_date());
        ps.setTimestamp(4, movie.getEnd_date());
        ps.setTime(5, movie.getPlaying_time());
        ps.setDouble(6, movie.getPrice());
        ps.setInt(7, movie.getLocation().getId());
        return ps.executeUpdate();
    }

    @Override
    public Object get() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery);
        return executeQuery(ps);
    }

    public Object get(int id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(getQuery + " where id = ?");
        ps.setInt(1, id);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        Movie movie = new Movie();
        while (rs.next()) {
            movie.setId(rs.getInt("id"));
            movie.setImage(rs.getString("image"));
            movie.setName(rs.getString("name"));
            movie.setStart_date(rs.getTimestamp("start_date"));
            movie.setEnd_date(rs.getTimestamp("end_date"));
            movie.setPlaying_time(rs.getTime("playing_time"));
            movie.setPrice(rs.getDouble("price"));

            LocationRepository locationRepository = new LocationRepository();
            movie.setLocation((Location) locationRepository.get(rs.getInt("location_id")));
        }
        return movie;
    }

    private Object executeQuery(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<Movie> ls = new ArrayList<>();

        while (rs.next()) {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setImage(rs.getString("image"));
            movie.setName(rs.getString("name"));
            movie.setStart_date(rs.getTimestamp("start_date"));
            movie.setEnd_date(rs.getTimestamp("end_date"));
            movie.setPlaying_time(rs.getTime("playing_time"));
            movie.setPrice(rs.getDouble("price"));

            LocationRepository locationRepository = new LocationRepository();
            movie.setLocation((Location) locationRepository.get(rs.getInt("location_id")));

            ls.add(movie);
        }
        return ls;
    }

    @Override
    public int update(Object element) throws SQLException {
        Movie movie = (Movie) element;
        PreparedStatement ps = super.conn.prepareStatement(updateQuery);
        ps.setString(1, movie.getName());
        ps.setString(2, movie.getImage());
        ps.setTimestamp(3, movie.getStart_date());
        ps.setTimestamp(4, movie.getEnd_date());
        ps.setTime(5, movie.getPlaying_time());
        ps.setDouble(6, movie.getPrice());
        ps.setInt(7, movie.getLocation().getId());
        ps.setInt(8, movie.getId());
        return ps.executeUpdate();
    }

    @Override
    public Object delete(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(deleteQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
        return null;
    }
}
