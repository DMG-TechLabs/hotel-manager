package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Dao {

    // Fields corresponding to the customer table
    private int id;
    private String fName;
    private String lName;
    private long phone; //Must become biginteger
    private String email;

    // Default constructor
    public Customer() {}

    // Parameterized constructor
    public Customer(int id, String firstName, String lastName, long phone, String email) {
        this.id = id;
        this.fName = firstName;
        this.lName = lastName;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return fName;
    }

    public void setFirstName(String firstName) {
        this.fName = firstName;
    }

    public String getLastName() {
        return lName;
    }

    public void setLastName(String lastName) {
        this.lName = lastName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Insert a new customer
	@Override
    public boolean insert() {
        try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("insert_customer", fName, lName, phone, email);
            return true;
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Customer failed", e);
            return false;
        }
    }

    // Update an existing customer
	@Override
    public boolean update(Object... values) {
        final int expectedParams = 4; // Including all fields
        if (values.length != expectedParams)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected %d", values.length, expectedParams));

        try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("update_customer", Utils.appendFront(id, values));
            return true;
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update Customer failed", e);
            return false;
        }
    }

    // Delete an existing customer
	@Override
    public boolean delete() {
        try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("delete_customer", id);
            return true;
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Customer failed", e);
            return false;
        }
    }

    // Select customers by id
    public static List<Customer> selectById(int id) {
        return select("select_customers_by_id", id);
    }

    // Select customers by email
    public static List<Customer> selectByEmail(String email) {
        return select("select_customer_by_email", email);
    }

    // Helper method for selection queries
    private static List<Customer> select(String function, Object... values) {
        List<Customer> customers = new ArrayList<>();
        try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            ResultSet rs = conn.callFunction(function, values);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("fname"));
                customer.setLastName(rs.getString("lname"));
                customer.setPhone(rs.getLong("phone"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
            rs.close();
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
        }
        return customers;
    }

    // Select all customers
    public static List<Customer> selectAll() {
        List<Customer> customers = new ArrayList<>();
        try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            ResultSet rs = conn.callFunction("select_all_customers");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstName(rs.getString("fname"));
                customer.setLastName(rs.getString("lname"));
                customer.setPhone(rs.getLong("phone"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
            rs.close();
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select All Customers failed", e);
        }
        return customers;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + fName + '\'' +
                ", lastName='" + lName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
