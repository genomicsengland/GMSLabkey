package co.uk.gel.proj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQueryExecuter {
//    static String DB_SERVER = "re-preprod-ovd-postgresql-01.gel.zone";
    static String DB_SERVER = "postgres-labkey.ci7meol4qvou.eu-west-2.rds.amazonaws.com";
    static String DB_NAME = "labkey";
    static String DB_USERNAME = "ssehgal";
    static String DB_PASSWORD = "8HD5acOP1";
    static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url;
        try {

            url = "jdbc:postgresql://" + DB_SERVER + ":5432/" + DB_NAME;
            Debugger.println("DB: connecting to " + url);
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            //MISDebugger.println("MIS.....Connection : "+connection);
            return connection;
        } catch (Exception exp) {
            Debugger.println("ERROR in getting Connection to " + DB_SERVER + " Database.\nException:" + exp);
            return null;
        }
    }

    public static void closeConnection() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception exp) {
            Debugger.println("ERROR in closing Connection to Database....\n" + exp);
        }
    }

    public static ResultSet executeQuery(String queryString) throws SQLException, ClassNotFoundException {
        try {
            if(connection == null) {
                connection = getConnection();
            }

            //Debugger.println("Executing Query:\n" + queryString + "\nConnection " + connection);
            ResultSet rs = connection.prepareStatement(queryString,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery();
            return rs;
        } catch (SQLException | ClassNotFoundException exp) {
            Debugger.println("Exception executing query:" + exp);
            //closeConnection();
            return null;
        }
    }

}
