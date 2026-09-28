package cinema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarBanco {

    public static void main(String[] args) throws Exception {
        Path arquivoBanco = Path.of("banco/cinema.db");
        Files.deleteIfExists(arquivoBanco);

        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement()) {

            executarScript(st, Path.of("banco/schema.sql"));
            executarScript(st, Path.of("banco/dados.sql"));
        }

        System.out.println("Banco de dados criado e populado com sucesso em: " + arquivoBanco.toAbsolutePath());
    }

    private static void executarScript(Statement st, Path caminho) throws IOException, SQLException {
        String conteudo = Files.readString(caminho);
        String[] comandos = conteudo.replaceAll("--.*", "").split(";");
        for (String comando : comandos) {
            String sql = comando.trim();
            if (!sql.isEmpty()) {
                st.execute(sql);
            }
        }
    }
}
