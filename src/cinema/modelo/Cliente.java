package cinema.modelo;

public class Cliente {
    public int id;
    public String nome;
    public String cpf;
    public String email;
    public String telefone;

    public Cliente() { }

    public Cliente(int id, String nome, String cpf, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return String.format("#%d | %-25s | CPF: %-15s | %-25s | %s",
                id, nome, cpf, email == null ? "-" : email, telefone == null ? "-" : telefone);
    }
}
