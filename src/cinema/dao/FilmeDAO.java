package cinema.dao;

import cinema.Conexao;
import cinema.modelo.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    public void inserir(Filme f) throws SQLException {
        String sql = "INSERT INTO filme (titulo, genero, duracao_minutos, classificacao_indicativa, sinopse) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.titulo);
            ps.setString(2, f.genero);
            ps.setInt(3, f.duracaoMinutos);
            ps.setString(4, f.classificacaoIndicativa);
            ps.setString(5, f.sinopse);
            ps.executeUpdate();
        }
    }

    public List<Filme> listarTodos() throws SQLException {
        List<Filme> lista = new ArrayList<>();
        String sql = "SELECT * FROM filme ORDER BY id_filme";
        try (Connection con = Conexao.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public Filme buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM filme WHERE id_filme = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public void atualizar(Filme f) throws SQLException {
        String sql = "UPDATE filme SET titulo=?, genero=?, duracao_minutos=?, classificacao_indicativa=?, sinopse=? " +
                     "WHERE id_filme=?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.titulo);
            ps.setString(2, f.genero);
            ps.setInt(3, f.duracaoMinutos);
            ps.setString(4, f.classificacaoIndicativa);
            ps.setString(5, f.sinopse);
            ps.setInt(6, f.id);
            ps.executeUpdate();
        }
    }

    public void remover(int id) throws SQLException {
        String sql = "DELETE FROM filme WHERE id_filme = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Filme mapear(ResultSet rs) throws SQLException {
        return new Filme(
                rs.getInt("id_filme"),
                rs.getString("titulo"),
                rs.getString("genero"),
                rs.getInt("duracao_minutos"),
                rs.getString("classificacao_indicativa"),
                rs.getString("sinopse"));
    }
}
