package com.epam.finalProject.db.pool;

import com.epam.finalProject.db.exception.ConnectionPoolException;
import com.epam.finalProject.util.DbResourceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hedgehog on 09.04.2016.
 */
public class JdbcConnectionPool {

    private static final String URL = DbResourceManager.getValue("db.url");

    private static final String DRIVER = DbResourceManager.getValue("db.driver");
    private static final String USER_DB_LOGIN = DbResourceManager.getValue("db.user.login");
    private static final String PASSWORD = DbResourceManager.getValue("db.user.password");
    private static final String MAX_POOL_CONNECTIONS = DbResourceManager.getValue("db.max.connections");

    private static List<Connection> availableConnections = new ArrayList<Connection>();

    JdbcConnectionPool() {
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {

        while (!checkWhetherConnectionPoolIsFull()) {
            try {
                availableConnections.add(createNewConnectionForPool());
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized boolean checkWhetherConnectionPoolIsFull() {

        if (availableConnections.size() < Integer.valueOf(MAX_POOL_CONNECTIONS)) {
            return false;
        }
        return true;
    }

    private Connection createNewConnectionForPool() throws ConnectionPoolException {
        try {
            Class.forName(DRIVER);
            Connection connection = (Connection) DriverManager.getConnection(URL, USER_DB_LOGIN, PASSWORD);
            return connection;
        } catch (ClassNotFoundException exception) {
            throw new ConnectionPoolException("It seems problem with db driver class", exception);
        } catch (SQLException exception) {
            throw new ConnectionPoolException("It seems SQLException in Connection Pool", exception);
        }
    }

    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;
        if (availableConnections.size() > 0) {
            connection = (Connection) availableConnections.get(0);
            availableConnections.remove(0);
        }
        return connection;
    }

    public static synchronized void returnConnectionToPool(Connection connection) {
        availableConnections.add(connection);
    }

    public void eliminate() {
        eraseConnectionList();
    }

    private void eraseConnectionList() {
        try {
            closeAllConnections(availableConnections);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeAllConnections(List<Connection> connections) throws SQLException {
        Connection connection;
        for(Connection element : connections) {
            if (!element.getAutoCommit()) {
                element.commit();
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection con, Statement st) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
