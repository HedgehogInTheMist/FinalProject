package com.epam.finalProject.db.pool;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created by Hedgehog on 09.04.2016.
 */
public class DataSource {

    static JdbcConnectionPool pool = new JdbcConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = pool.getConnectionFromPool();
        return connection;
    }

    public static Connection returnConnection(Connection connection) {
        pool.returnConnectionToPool(connection);
        return connection;
    }
}
