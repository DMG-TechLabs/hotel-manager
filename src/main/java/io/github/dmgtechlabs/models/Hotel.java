package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Hotel implements Dao {
    private String name;
    private String address;
    private int id;

    public Hotel(){}
    // For writing
    public Hotel(String name, String address){
        this.name = name;
        this.address = address;
    }
    // For loading
    public Hotel(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
    @Override
    public boolean insert() {
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("insert_hotel", name, address);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Hotel failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Object... values) {
        if(values.length != 2)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected 2", values.length));

        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("update_hotel", Utils.appendFront(id, values));
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update hotel failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()){
            conn.callProcedure("delete_hotel", id);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Hotel failed", e);
            return false;
        }
        return true;
    }

    private static List<Hotel> select(String function, Object... values){
        assert(function != null);
        assert(!function.isBlank());
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            ResultSet rs = conn.callFunction(function, values);
            return Adapter.load(rs, Hotel.class);
        } catch (Exception e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
            return null;
        }
    }

    public static List<Hotel> selectById(int id){
        return select("select_hotel_by_id", id);
    }

    public static List<Hotel> selectByName(String name) {
        return select("select_hotel_by_name", name);
    }
}
