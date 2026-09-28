package cinema.modelo;

public class Funcionario {
    public int id;
    public String nome;
    public String cpf;
    public String cargo;
    public String telefone;

    public Funcionario() { }

    public Funcionario(int id, String nome, String cpf, String cargo, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return String.format("#%d | %-25s | CPF: %-15s | %-18s | %s",
                id, nome, cpf, cargo, telefone == null ? "-" : telefone);
    }
}
