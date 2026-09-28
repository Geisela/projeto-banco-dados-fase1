package cinema.modelo;

public class Sala {
    public int id;
    public int numero;
    public int capacidade;
    public String tipoSala;

    public Sala() { }

    public Sala(int id, int numero, int capacidade, String tipoSala) {
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
        this.tipoSala = tipoSala;
    }

    @Override
    public String toString() {
        return String.format("#%d | Sala %d | Capacidade: %3d | Tipo: %s",
                id, numero, capacidade, tipoSala);
    }
}
