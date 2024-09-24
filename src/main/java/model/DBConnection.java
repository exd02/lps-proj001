package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:academico.db";

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setupTables() {
        String sql = "CREATE TABLE IF NOT EXISTS Aluno ("
                + "nome TEXT NOT NULL, "
                + "idade INTEGER NOT NULL, "
                + "sexo TEXT NOT NULL, "
                + "matricula TEXT PRIMARY KEY)";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
