package cn.edu.zju.dbutils;

import cn.edu.zju.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

public class DBUtils {

    private static final Logger log = LoggerFactory.getLogger(DBUtils.class);

    public static Connection getConnection() {
        AppConfig appConfig = AppConfig.getInstance();

        String url = appConfig.getJdbcUrl();
        String user = appConfig.getJdbcUsername();
        String pwd = appConfig.getJdbcPassword();

        System.out.println("[DB] url=" + url);
        System.out.println("[DB] user=" + user);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB connect failed. url=" + url + ", user=" + user, e);
        }
    }


    public static void execSQL(Consumer<Connection> consumer) {
        Connection connection = null;
        try {
            connection = getConnection();
            consumer.accept(connection);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.info("", e);
                }
            }
        }
    }
}