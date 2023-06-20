package com.example.petranationwidemovies.repositories;

import com.example.petranationwidemovies.model.PaymentMethod;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRepository extends AbstractRepository {

    private String getQuery = "select * from payment_methods";

    @Override
    public int add(Object element) throws SQLException {
        /*String query = "insert into payment_methods (name) values (?)";
        PaymentMethod paymentMethod = (PaymentMethod) element;
        PreparedStatement ps = super.conn.prepareStatement(query);
        ps.setString(1, paymentMethod.getName());
        return ps.executeUpdate();*/
        throw new SQLException("Not implemented");
    }

    @Override
    public Object get() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(getQuery);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        List<PaymentMethod> ls = new ArrayList<>();

        while (rs.next()) {
            PaymentMethod pm = new PaymentMethod();
            pm.setId(rs.getInt("id"));
            pm.setName(rs.getString("name"));
            ls.add(pm);
        }
        return ls;
    }

    public Object get(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement( getQuery + " where id = ?");
        ps.setInt(1, id);
        ps.executeQuery();
        ResultSet rs = ps.executeQuery();
        PaymentMethod pm = new PaymentMethod();
        while (rs.next()) {
            pm.setId(rs.getInt("id"));
            pm.setName(rs.getString("name"));
        }
        return pm;
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
