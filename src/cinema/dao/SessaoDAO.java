package cinema.dao;

import cinema.Conexao;
import cinema.modelo.Sessao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {

    private static final String SELECT_BASE =
            "SELECT s.*, f.titulo AS titulo_filme, sa.numero AS numero_sala, fu.nome AS nome_funcionario " +
            "FROM sessao s " +
            "JOIN filme f       ON f.id_filme = s.id_filme " +
            "JOIN sala sa       ON sa.id_sala = s.id_sala " +
            "JOIN funcionario fu ON fu.id_funcionario = s.id_funcionario ";

    public void agendar(Sessao s) throws SQLException {
        String sql = "INSERT INTO sessao (id_filme, id_sala, id_funcionario, data_hora, preco_ingresso) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.idFilme);
            ps.setInt(2, s.idSala);
            ps.setInt(3, s.idFuncionario);
            ps.setString(4, s.dataHora);
            ps.setDouble(5, s.precoIngresso);
            ps.executeUpdate();
        }
    }

    public List<Sessao> listarTodas() throws SQLException {
        List<Sessao> lista = new ArrayList<>();
        String sql = SELECT_BASE + "ORDER BY s.data_hora";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Sessao buscarPorId(int id) throws SQLException {
        String sql = SELECT_BASE + "WHERE s.id_sessao = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public void atualizar(Sessao s) throws SQLException {
        String sql = "UPDATE sessao SET id_filme=?, id_sala=?, id_funcionario=?, data_hora=?, preco_ingresso=? " +
                     "WHERE id_sessao=?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.idFilme);
            ps.setInt(2, s.idSala);
            ps.setInt(3, s.idFuncionario);
            ps.setString(4, s.dataHora);
            ps.setDouble(5, s.precoIngresso);
            ps.setInt(6, s.id);
            ps.executeUpdate();
        }
    }

    public void cancelar(int id) throws SQLException {
        try (Connection con = Conexao.conectar()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM ingresso WHERE id_sessao = ?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM sessao WHERE id_sessao = ?")) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
                ps2.setInt(1, id);
                ps2.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    private Sessao mapear(ResultSet rs) throws SQLException {
        Sessao s = new Sessao(
                rs.getInt("id_sessao"),
                rs.getInt("id_filme"),
                rs.getInt("id_sala"),
                rs.getInt("id_funcionario"),
                rs.getString("data_hora"),
                rs.getDouble("preco_ingresso"));
        s.tituloFilme = rs.getString("titulo_filme");
        s.numeroSala = rs.getInt("numero_sala");
        s.nomeFuncionario = rs.getString("nome_funcionario");
        return s;
    }
}
