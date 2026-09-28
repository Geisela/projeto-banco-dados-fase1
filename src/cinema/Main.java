package cinema;

import cinema.dao.*;
import cinema.modelo.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static final FilmeDAO filmeDAO = new FilmeDAO();
    private static final SalaDAO salaDAO = new SalaDAO();
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final SessaoDAO sessaoDAO = new SessaoDAO();
    private static final IngressoDAO ingressoDAO = new IngressoDAO();
    private static final RelatorioDAO relatorioDAO = new RelatorioDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===================== SISTEMA DE CINEMA =====================");
            System.out.println("1 - Funcionários (CRUD)");
            System.out.println("2 - Filmes (CRUD)");
            System.out.println("3 - Salas (CRUD)");
            System.out.println("4 - Clientes (CRUD)");
            System.out.println("5 - Sessões (agendar exibição de filme em sala)");
            System.out.println("6 - Ingressos (vender ingresso para uma sessão)");
            System.out.println("7 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInt();

            try {
                switch (opcao) {
                    case 1 -> menuFuncionario();
                    case 2 -> menuFilme();
                    case 3 -> menuSala();
                    case 4 -> menuCliente();
                    case 5 -> menuSessao();
                    case 6 -> menuIngresso();
                    case 7 -> menuRelatorios();
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }
        } while (opcao != 0);

        sc.close();
    }

    private static void menuFuncionario() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Funcionários ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = lerTexto();
                    System.out.print("CPF: ");
                    String cpf = lerTexto();
                    System.out.print("Cargo: ");
                    String cargo = lerTexto();
                    System.out.print("Telefone: ");
                    String telefone = lerTexto();
                    funcionarioDAO.inserir(new Funcionario(0, nome, cpf, cargo, telefone));
                    System.out.println("Funcionário cadastrado com sucesso!");
                }
                case 2 -> {
                    List<Funcionario> lista = funcionarioDAO.listarTodos();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do funcionário a atualizar: ");
                    int id = lerInt();
                    Funcionario f = funcionarioDAO.buscarPorId(id);
                    if (f == null) { System.out.println("Não encontrado."); break; }
                    System.out.print("Novo nome (" + f.nome + "): ");
                    f.nome = lerTextoOuManter(f.nome);
                    System.out.print("Novo CPF (" + f.cpf + "): ");
                    f.cpf = lerTextoOuManter(f.cpf);
                    System.out.print("Novo cargo (" + f.cargo + "): ");
                    f.cargo = lerTextoOuManter(f.cargo);
                    System.out.print("Novo telefone (" + f.telefone + "): ");
                    f.telefone = lerTextoOuManter(f.telefone);
                    funcionarioDAO.atualizar(f);
                    System.out.println("Funcionário atualizado com sucesso!");
                }
                case 4 -> {
                    System.out.print("ID do funcionário a remover: ");
                    int id = lerInt();
                    funcionarioDAO.remover(id);
                    System.out.println("Funcionário removido (se existia).");
                }
            }
        } while (opcao != 0);
    }

    private static void menuFilme() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Filmes ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = lerTexto();
                    System.out.print("Gênero: ");
                    String genero = lerTexto();
                    System.out.print("Duração (minutos): ");
                    int duracao = lerInt();
                    System.out.print("Classificação indicativa (Livre, 12, 14, 16, 18): ");
                    String classificacao = lerTexto();
                    System.out.print("Sinopse: ");
                    String sinopse = lerTexto();
                    filmeDAO.inserir(new Filme(0, titulo, genero, duracao, classificacao, sinopse));
                    System.out.println("Filme cadastrado com sucesso!");
                }
                case 2 -> {
                    List<Filme> lista = filmeDAO.listarTodos();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do filme a atualizar: ");
                    int id = lerInt();
                    Filme f = filmeDAO.buscarPorId(id);
                    if (f == null) { System.out.println("Não encontrado."); break; }
                    System.out.print("Novo título (" + f.titulo + "): ");
                    f.titulo = lerTextoOuManter(f.titulo);
                    System.out.print("Novo gênero (" + f.genero + "): ");
                    f.genero = lerTextoOuManter(f.genero);
                    System.out.print("Nova duração (" + f.duracaoMinutos + "): ");
                    f.duracaoMinutos = lerIntOuManter(f.duracaoMinutos);
                    System.out.print("Nova classificação (" + f.classificacaoIndicativa + "): ");
                    f.classificacaoIndicativa = lerTextoOuManter(f.classificacaoIndicativa);
                    System.out.print("Nova sinopse (" + f.sinopse + "): ");
                    f.sinopse = lerTextoOuManter(f.sinopse);
                    filmeDAO.atualizar(f);
                    System.out.println("Filme atualizado com sucesso!");
                }
                case 4 -> {
                    System.out.print("ID do filme a remover: ");
                    int id = lerInt();
                    filmeDAO.remover(id);
                    System.out.println("Filme removido (se existia).");
                }
            }
        } while (opcao != 0);
    }

    private static void menuSala() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Salas ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todas");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Número da sala: ");
                    int numero = lerInt();
                    System.out.print("Capacidade: ");
                    int capacidade = lerInt();
                    System.out.print("Tipo (2D, 3D, IMAX): ");
                    String tipo = lerTexto();
                    salaDAO.inserir(new Sala(0, numero, capacidade, tipo));
                    System.out.println("Sala cadastrada com sucesso!");
                }
                case 2 -> {
                    List<Sala> lista = salaDAO.listarTodos();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID da sala a atualizar: ");
                    int id = lerInt();
                    Sala s = salaDAO.buscarPorId(id);
                    if (s == null) { System.out.println("Não encontrada."); break; }
                    System.out.print("Novo número (" + s.numero + "): ");
                    s.numero = lerIntOuManter(s.numero);
                    System.out.print("Nova capacidade (" + s.capacidade + "): ");
                    s.capacidade = lerIntOuManter(s.capacidade);
                    System.out.print("Novo tipo (" + s.tipoSala + "): ");
                    s.tipoSala = lerTextoOuManter(s.tipoSala);
                    salaDAO.atualizar(s);
                    System.out.println("Sala atualizada com sucesso!");
                }
                case 4 -> {
                    System.out.print("ID da sala a remover: ");
                    int id = lerInt();
                    salaDAO.remover(id);
                    System.out.println("Sala removida (se existia).");
                }
            }
        } while (opcao != 0);
    }

    private static void menuCliente() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Clientes ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = lerTexto();
                    System.out.print("CPF: ");
                    String cpf = lerTexto();
                    System.out.print("E-mail: ");
                    String email = lerTexto();
                    System.out.print("Telefone: ");
                    String telefone = lerTexto();
                    clienteDAO.inserir(new Cliente(0, nome, cpf, email, telefone));
                    System.out.println("Cliente cadastrado com sucesso!");
                }
                case 2 -> {
                    List<Cliente> lista = clienteDAO.listarTodos();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do cliente a atualizar: ");
                    int id = lerInt();
                    Cliente c = clienteDAO.buscarPorId(id);
                    if (c == null) { System.out.println("Não encontrado."); break; }
                    System.out.print("Novo nome (" + c.nome + "): ");
                    c.nome = lerTextoOuManter(c.nome);
                    System.out.print("Novo CPF (" + c.cpf + "): ");
                    c.cpf = lerTextoOuManter(c.cpf);
                    System.out.print("Novo e-mail (" + c.email + "): ");
                    c.email = lerTextoOuManter(c.email);
                    System.out.print("Novo telefone (" + c.telefone + "): ");
                    c.telefone = lerTextoOuManter(c.telefone);
                    clienteDAO.atualizar(c);
                    System.out.println("Cliente atualizado com sucesso!");
                }
                case 4 -> {
                    System.out.print("ID do cliente a remover: ");
                    int id = lerInt();
                    clienteDAO.remover(id);
                    System.out.println("Cliente removido (se existia).");
                }
            }
        } while (opcao != 0);
    }

    private static void menuSessao() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Sessões (Filme + Sala + Funcionário) ---");
            System.out.println("1 - Agendar nova sessão");
            System.out.println("2 - Listar todas as sessões");
            System.out.println("3 - Cancelar sessão");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Filmes disponíveis:");
                    filmeDAO.listarTodos().forEach(System.out::println);
                    System.out.print("ID do filme: ");
                    int idFilme = lerInt();

                    System.out.println("Salas disponíveis:");
                    salaDAO.listarTodos().forEach(System.out::println);
                    System.out.print("ID da sala: ");
                    int idSala = lerInt();

                    System.out.println("Funcionários responsáveis disponíveis:");
                    funcionarioDAO.listarTodos().forEach(System.out::println);
                    System.out.print("ID do funcionário responsável: ");
                    int idFuncionario = lerInt();

                    System.out.print("Data e hora (AAAA-MM-DD HH:MM): ");
                    String dataHora = lerTexto();
                    System.out.print("Preço do ingresso: ");
                    double preco = lerDouble();

                    try {
                        sessaoDAO.agendar(new Sessao(0, idFilme, idSala, idFuncionario, dataHora, preco));
                        System.out.println("Sessão agendada com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Não foi possível agendar (sala já ocupada nesse horário ou dado inválido): "
                                + e.getMessage());
                    }
                }
                case 2 -> sessaoDAO.listarTodas().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID da sessão a cancelar: ");
                    int id = lerInt();
                    sessaoDAO.cancelar(id);
                    System.out.println("Sessão cancelada (e ingressos vinculados removidos), se existia.");
                }
            }
        } while (opcao != 0);
    }

    private static void menuIngresso() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Ingressos (Sessão + Cliente) ---");
            System.out.println("1 - Vender ingresso");
            System.out.println("2 - Listar todos os ingressos");
            System.out.println("3 - Cancelar ingresso");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Sessões disponíveis:");
                    sessaoDAO.listarTodas().forEach(System.out::println);
                    System.out.print("ID da sessão: ");
                    int idSessao = lerInt();

                    System.out.println("Clientes cadastrados:");
                    clienteDAO.listarTodos().forEach(System.out::println);
                    System.out.print("ID do cliente: ");
                    int idCliente = lerInt();

                    System.out.print("Assento (ex: F10): ");
                    String assento = lerTexto();

                    if (ingressoDAO.assentoOcupado(idSessao, assento)) {
                        System.out.println("Este assento já foi vendido para essa sessão!");
                        break;
                    }

                    Sessao sessao = sessaoDAO.buscarPorId(idSessao);
                    if (sessao == null) { System.out.println("Sessão não encontrada."); break; }

                    System.out.print("Forma de pagamento (Dinheiro/Cartão/Pix): ");
                    String formaPagamento = lerTexto();

                    Ingresso ingresso = new Ingresso(0, idSessao, idCliente, assento, formaPagamento,
                            sessao.precoIngresso, java.time.LocalDateTime.now().toString().substring(0, 16));
                    ingressoDAO.vender(ingresso);
                    System.out.printf("Ingresso vendido com sucesso! Valor: R$ %.2f%n", sessao.precoIngresso);
                }
                case 2 -> ingressoDAO.listarTodos().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID do ingresso a cancelar: ");
                    int id = lerInt();
                    ingressoDAO.cancelar(id);
                    System.out.println("Ingresso cancelado (se existia).");
                }
            }
        } while (opcao != 0);
    }

    private static void menuRelatorios() throws SQLException {
        int opcao;
        do {
            System.out.println("\n--- Relatórios ---");
            System.out.println("1 - Sessões de um filme em um período");
            System.out.println("2 - Faturamento por sessão");
            System.out.println("3 - Clientes que mais compraram ingressos");
            System.out.println("4 - Ocupação das salas por sessão");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Parte do título do filme: ");
                    String titulo = lerTexto();
                    System.out.print("Data início (AAAA-MM-DD): ");
                    String inicio = lerTexto();
                    System.out.print("Data fim (AAAA-MM-DD): ");
                    String fim = lerTexto();
                    relatorioDAO.relatorioSessoesPorFilmeEPeriodo(titulo, inicio + " 00:00", fim + " 23:59");
                }
                case 2 -> relatorioDAO.relatorioFaturamentoPorSessao();
                case 3 -> relatorioDAO.relatorioClientesQueMaisCompraram();
                case 4 -> relatorioDAO.relatorioOcupacaoDeSalas();
            }
        } while (opcao != 0);
    }

    private static int lerInt() {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, digite um número: ");
            }
        }
    }

    private static String lerTexto() {
        return sc.nextLine().trim();
    }

    private static String lerTextoOuManter(String atual) {
        String linha = sc.nextLine().trim();
        return linha.isEmpty() ? atual : linha;
    }

    private static int lerIntOuManter(int atual) {
        String linha = sc.nextLine().trim();
        if (linha.isEmpty()) return atual;
        try {
            return Integer.parseInt(linha);
        } catch (NumberFormatException e) {
            return atual;
        }
    }
}
