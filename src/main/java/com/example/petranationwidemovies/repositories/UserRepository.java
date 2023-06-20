package com.example.petranationwidemovies.repositories;

import com.example.petranationwidemovies.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository {
    private String getQuery = "select * from users";
    private String insertQuery = "insert into users (nrp, name, password, role, phone) values (?, ?, ?, ?, ?)";
    private String updateQuery = "update users set nrp = ?, name = ?, password = ?, role = ?, phone = ? where id = ?";
    private String deleteQuery = "delete from users where id = ?";

    @Override
    public int add(Object element) throws SQLException {
        User user = (User) element;
        PreparedStatement ps = super.conn.prepareStatement(insertQuery);
        ps.setString(1, user.getNrp());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.setInt(4, user.getRole());
        ps.setString(5, user.getPhone());
        return ps.executeUpdate();
    }

    @Override
    public Object get() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery);
        return executeQuery(ps);
    }

    public Object getByNrp(String nrp) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery + " where nrp = ?");
        ps.setString(1, nrp);
        return executeQuery(ps);
    }

    public Object get(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery + " where id = ?");
        ps.setInt(1, id);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setRole(rs.getInt("role"));
            user.setNrp(rs.getString("nrp"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }
    public Object getByRole(int role) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery + " where role = ?");
        ps.setInt(1, role);
        return executeQuery(ps);
    }

    public Object getByNrpAndPassword(String nrp, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery + " where nrp = ? and password = ?");
        ps.setString(1, nrp);
        ps.setString(2, password);
        return executeQuery(ps);
    }

    private Object executeQuery(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<User> ls = new ArrayList<>();

        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setPhone(rs.getString("phone"));
            u.setRole(rs.getInt("role"));
            u.setNrp(rs.getString("nrp"));
            u.setPassword(rs.getString("password"));
            ls.add(u);
        }
        return ls;
    }

    @Override
    public int update(Object element) throws SQLException {
        User user = (User) element;
        PreparedStatement ps = super.conn.prepareStatement(updateQuery);
        ps.setString(1, user.getNrp());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.setInt(4, user.getRole());
        ps.setString(5, user.getPhone());
        ps.setInt(6, user.getId());
        return ps.executeUpdate();
    }

    @Override
    public Object delete(int id) throws SQLException {
        PreparedStatement ps = super.conn.prepareStatement(deleteQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
        return null;
    }
}
