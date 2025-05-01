package com.mock_trello.dbconnection;
import java.sql.Connection;

public interface DatabaseConnection {
    Connection connect();
    void disconnect(Connection connection);
}
