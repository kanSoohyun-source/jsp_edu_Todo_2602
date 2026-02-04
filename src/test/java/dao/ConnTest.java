package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnTest {
    @Test
    public void connectionTest() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mariadb://localhost:3306/jsp_edu_todo_2602";
        String user = "todo_user";
        String pass = "6800";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            // Null인지 확인
            Assertions.assertNotNull(connection);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/jsp_edu_todo_2602");
        config.setUsername("todo_user");
        config.setPassword("6800");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource dataSource = new HikariDataSource(config);
        try (Connection connection = dataSource.getConnection()) {

            System.out.println("Connection Success : " + connection);
            Assertions.assertNotNull(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
