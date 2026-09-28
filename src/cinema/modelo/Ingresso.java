package cinema.modelo;

public class Ingresso {
    public int id;
    public int idSessao;
    public int idCliente;
    public String assento;
    public String formaPagamento;
    public double valorPago;
    public String dataCompra;

    public String tituloFilme;
    public String dataHoraSessao;
    public String nomeCliente;

    public Ingresso() { }

    public Ingresso(int id, int idSessao, int idCliente, String assento,
                     String formaPagamento, double valorPago, String dataCompra) {
        this.id = id;
        this.idSessao = idSessao;
        this.idCliente = idCliente;
        this.assento = assento;
        this.formaPagamento = formaPagamento;
        this.valorPago = valorPago;
        this.dataCompra = dataCompra;
    }

    @Override
    public String toString() {
        return String.format("#%d | Sessão: %-25s (%s) | Cliente: %-20s | Assento: %-4s | %-9s | R$ %.2f",
                id, tituloFilme == null ? ("id=" + idSessao) : tituloFilme,
                dataHoraSessao == null ? "" : dataHoraSessao,
                nomeCliente == null ? ("id=" + idCliente) : nomeCliente,
                assento, formaPagamento, valorPago);
    }
}
