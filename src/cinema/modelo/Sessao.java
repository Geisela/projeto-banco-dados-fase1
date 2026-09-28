package cinema.modelo;

public class Sessao {
    public int id;
    public int idFilme;
    public int idSala;
    public int idFuncionario;
    public String dataHora;
    public double precoIngresso;

    public String tituloFilme;
    public int numeroSala;
    public String nomeFuncionario;

    public Sessao() { }

    public Sessao(int id, int idFilme, int idSala, int idFuncionario, String dataHora, double precoIngresso) {
        this.id = id;
        this.idFilme = idFilme;
        this.idSala = idSala;
        this.idFuncionario = idFuncionario;
        this.dataHora = dataHora;
        this.precoIngresso = precoIngresso;
    }

    @Override
    public String toString() {
        return String.format("#%d | Filme: %-25s | Sala: %-3s | %s | R$ %.2f | Responsável: %s",
                id, tituloFilme == null ? ("id=" + idFilme) : tituloFilme,
                numeroSala == 0 ? String.valueOf(idSala) : String.valueOf(numeroSala),
                dataHora, precoIngresso,
                nomeFuncionario == null ? ("id=" + idFuncionario) : nomeFuncionario);
    }
}
