package cinema.dao;

import cinema.Conexao;
import cinema.modelo.Ingresso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {

    private static final String SELECT_BASE =
            "SELECT i.*, f.titulo AS titulo_filme, s.data_hora AS data_hora_sessao, c.nome AS nome_cliente " +
            "FROM ingresso i " +
            "JOIN sessao s  ON s.id_sessao = i.id_sessao " +
            "JOIN filme f   ON f.id_filme = s.id_filme " +
            "JOIN cliente c ON c.id_cliente = i.id_cliente ";
    public boolean assentoOcupado(int idSessao, String assento) throws SQLException {
        String sql = "SELECT 1 FROM ingresso WHERE id_sessao = ? AND assento = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSessao);
            ps.setString(2, assento);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void vender(Ingresso i) throws SQLException {
        String sql = "INSERT INTO ingresso (id_sessao, id_cliente, assento, forma_pagamento, valor_pago, data_compra) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, i.idSessao);
            ps.setInt(2, i.idCliente);
            ps.setString(3, i.assento);
            ps.setString(4, i.formaPagamento);
            ps.setDouble(5, i.valorPago);
            ps.setString(6, i.dataCompra);
            ps.executeUpdate();
        }
    }

    public List<Ingresso> listarTodos() throws SQLException {
        List<Ingresso> lista = new ArrayList<>();
        String sql = SELECT_BASE + "ORDER BY i.id_ingresso";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Ingresso buscarPorId(int id) throws SQLException {
        String sql = SELECT_BASE + "WHERE i.id_ingresso = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public void cancelar(int id) throws SQLException {
        String sql = "DELETE FROM ingresso WHERE id_ingresso = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Ingresso mapear(ResultSet rs) throws SQLException {
        Ingresso i = new Ingresso(
                rs.getInt("id_ingresso"),
                rs.getInt("id_sessao"),
                rs.getInt("id_cliente"),
                rs.getString("assento"),
                rs.getString("forma_pagamento"),
                rs.getDouble("valor_pago"),
                rs.getString("data_compra"));
        i.tituloFilme = rs.getString("titulo_filme");
        i.dataHoraSessao = rs.getString("data_hora_sessao");
        i.nomeCliente = rs.getString("nome_cliente");
        return i;
    }
}
