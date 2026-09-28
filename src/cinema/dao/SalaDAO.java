package cinema.dao;

import cinema.Conexao;
import cinema.modelo.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    public void inserir(Sala s) throws SQLException {
        String sql = "INSERT INTO sala (numero, capacidade, tipo_sala) VALUES (?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.numero);
            ps.setInt(2, s.capacidade);
            ps.setString(3, s.tipoSala);
            ps.executeUpdate();
        }
    }

    public List<Sala> listarTodos() throws SQLException {
        List<Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM sala ORDER BY id_sala";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Sala buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM sala WHERE id_sala = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public void atualizar(Sala s) throws SQLException {
        String sql = "UPDATE sala SET numero=?, capacidade=?, tipo_sala=? WHERE id_sala=?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.numero);
            ps.setInt(2, s.capacidade);
            ps.setString(3, s.tipoSala);
            ps.setInt(4, s.id);
            ps.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM sala WHERE id_sala = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Sala mapear(ResultSet rs) throws SQLException {
        return new Sala(
                rs.getInt("id_sala"),
                rs.getInt("numero"),
                rs.getInt("capacidade"),
                rs.getString("tipo_sala"));
    }
}
