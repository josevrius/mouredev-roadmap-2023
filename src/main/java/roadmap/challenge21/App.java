package roadmap.challenge21;

import java.sql.*;

public final class App {

    private static final String HEADER = """
            
            CHALLENGES
            ==========""";

    private static final String DB_HOST = "mysql-5707.dinaserver.com";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "moure_test";
    private static final String DB_USER = "mouredev_read";
    private static final String DB_PASS = "mouredev_pass";

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println(HEADER);
            showChallenges(conn);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void showChallenges(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM challenges")) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.println(md.getColumnName(i) + ": " + rs.getString(i));
                }
                if (!rs.isLast()) {
                    System.out.println();
                }
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        return DriverManager.getConnection(url, DB_USER, DB_PASS);
    }
}
