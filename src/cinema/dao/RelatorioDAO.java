package cinema.dao;

import cinema.Conexao;

import java.sql.*;

public class RelatorioDAO {

    public void relatorioSessoesPorFilmeEPeriodo(String tituloBusca, String dataInicio, String dataFim) throws SQLException {
        String sql =
                "SELECT f.titulo, sa.numero AS sala, se.data_hora, se.preco_ingresso, fu.nome AS responsavel " +
                "FROM sessao se " +
                "JOIN filme f        ON f.id_filme = se.id_filme " +
                "JOIN sala sa        ON sa.id_sala = se.id_sala " +
                "JOIN funcionario fu ON fu.id_funcionario = se.id_funcionario " +
                "WHERE f.titulo LIKE ? AND se.data_hora BETWEEN ? AND ? " +
                "ORDER BY se.data_hora";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tituloBusca + "%");
            ps.setString(2, dataInicio);
            ps.setString(3, dataFim);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n--- Sessões do período por filme ---");
                boolean vazio = true;
                while (rs.next()) {
                    vazio = false;
                    System.out.printf("Filme: %-25s | Sala %d | %s | R$ %.2f | Responsável: %s%n",
                            rs.getString("titulo"), rs.getInt("sala"), rs.getString("data_hora"),
                            rs.getDouble("preco_ingresso"), rs.getString("responsavel"));
                }
                if (vazio) System.out.println("Nenhuma sessão encontrada para os filtros informados.");
            }
        }
    }

    public void relatorioFaturamentoPorSessao() throws SQLException {
        String sql =
                "SELECT f.titulo, se.data_hora, COUNT(i.id_ingresso) AS qtd_ingressos, " +
                "       COALESCE(SUM(i.valor_pago), 0) AS total_arrecadado " +
                "FROM sessao se " +
                "JOIN filme f ON f.id_filme = se.id_filme " +
                "LEFT JOIN ingresso i ON i.id_sessao = se.id_sessao " +
                "GROUP BY se.id_sessao " +
                "ORDER BY total_arrecadado DESC";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Faturamento por sessão ---");
            while (rs.next()) {
                System.out.printf("Filme: %-25s | %s | Ingressos vendidos: %2d | Total: R$ %.2f%n",
                        rs.getString("titulo"), rs.getString("data_hora"),
                        rs.getInt("qtd_ingressos"), rs.getDouble("total_arrecadado"));
            }
        }
    }

    public void relatorioClientesQueMaisCompraram() throws SQLException {
        String sql =
                "SELECT c.nome, COUNT(i.id_ingresso) AS qtd_ingressos, SUM(i.valor_pago) AS total_gasto " +
                "FROM cliente c " +
                "JOIN ingresso i ON i.id_cliente = c.id_cliente " +
                "GROUP BY c.id_cliente " +
                "ORDER BY qtd_ingressos DESC";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Clientes que mais compraram ingressos ---");
            while (rs.next()) {
                System.out.printf("Cliente: %-25s | Ingressos comprados: %2d | Total gasto: R$ %.2f%n",
                        rs.getString("nome"), rs.getInt("qtd_ingressos"), rs.getDouble("total_gasto"));
            }
        }
    }
    public void relatorioOcupacaoDeSalas() throws SQLException {
        String sql =
                "SELECT sa.numero AS sala, f.titulo, se.data_hora, sa.capacidade, " +
                "       COUNT(i.id_ingresso) AS ingressos_vendidos " +
                "FROM sessao se " +
                "JOIN sala sa ON sa.id_sala = se.id_sala " +
                "JOIN filme f ON f.id_filme = se.id_filme " +
                "LEFT JOIN ingresso i ON i.id_sessao = se.id_sessao " +
                "GROUP BY se.id_sessao " +
                "ORDER BY sa.numero, se.data_hora";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Ocupação das salas por sessão ---");
            while (rs.next()) {
                int capacidade = rs.getInt("capacidade");
                int vendidos = rs.getInt("ingressos_vendidos");
                double ocupacao = capacidade == 0 ? 0 : (100.0 * vendidos / capacidade);
                System.out.printf("Sala %d | Filme: %-25s | %s | Ocupação: %d/%d (%.1f%%)%n",
                        rs.getInt("sala"), rs.getString("titulo"), rs.getString("data_hora"),
                        vendidos, capacidade, ocupacao);
            }
        }
    }
}
