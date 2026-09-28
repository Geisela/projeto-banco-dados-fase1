package cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String CAMINHO_BANCO = "banco/cinema.db";
    private static final String URL = "jdbc:sqlite:" + CAMINHO_BANCO;

    public static Connection conectar() throws SQLException {
        Connection con = DriverManager.getConnection(URL);
        try (var stmt = con.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }
        return con;
    }
}
