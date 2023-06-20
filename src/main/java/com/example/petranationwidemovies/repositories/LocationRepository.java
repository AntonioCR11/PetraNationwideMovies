package com.example.petranationwidemovies.repositories;

import com.example.petranationwidemovies.model.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository extends AbstractRepository {


    private String getQuery = "select * from locations";

    @Override
    public int add(Object element) throws SQLException {
        /*String query = "insert into locations (building, room, total_seat) values (?, ?, ?)";
        Location location = (Location) element;
        PreparedStatement ps = super.conn.prepareStatement(query);
        ps.setString(1, location.getBuilding());
        ps.setString(2, location.getRoom());
        ps.setInt(3, location.getTotal_seat());
        return ps.executeUpdate();*/
        throw new SQLException("Not implemented");
    }

    @Override
    public Object get() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        List<Location> ls = new ArrayList<>();

        while (rs.next()) {
            Location location = new Location();
            location.setId(rs.getInt("id"));
            location.setBuilding(rs.getString("building"));
            location.setRoom(rs.getString("room"));
            location.setTotal_seat(rs.getInt("total_seat"));
            ls.add(location);
        }
        return ls;
    }

    public Object get(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement( getQuery + " where id = ?");
        ps.setInt(1, id);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        Location location = new Location();
        while (rs.next()) {
            location.setId(rs.getInt("id"));
            location.setBuilding(rs.getString("building"));
            location.setRoom(rs.getString("room"));
            location.setTotal_seat(rs.getInt("total_seat"));
        }
        return location;
    }

    @Override
    public int update(Object element) throws SQLException {
        throw new SQLException("Not implemented");
    }

    @Override
    public Object delete(int id) throws SQLException {
        throw new SQLException("Not implemented");
    }
}
