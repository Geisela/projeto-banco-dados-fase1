package cinema.modelo;

public class Filme {
    public int id;
    public String titulo;
    public String genero;
    public int duracaoMinutos;
    public String classificacaoIndicativa;
    public String sinopse;

    public Filme() { }

    public Filme(int id, String titulo, String genero, int duracaoMinutos,
                  String classificacaoIndicativa, String sinopse) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracaoMinutos = duracaoMinutos;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return String.format("#%d | %-30s | %-18s | %3dmin | Classificação: %s",
                id, titulo, genero, duracaoMinutos, classificacaoIndicativa);
    }
}
