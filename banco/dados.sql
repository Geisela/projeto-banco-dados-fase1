
INSERT INTO funcionario (nome, cpf, cargo, telefone) VALUES
('Carlos Mendes',  '111.111.111-11', 'Gerente',          '(11) 91111-1111'),
('Ana Beatriz',    '222.222.222-22', 'Operador de Caixa','(11) 92222-2222'),
('João Pereira',   '333.333.333-33', 'Projecionista',    '(11) 93333-3333');

INSERT INTO filme (titulo, genero, duracao_minutos, classificacao_indicativa, sinopse) VALUES
('Duna: Parte Dois',        'Ficção Científica', 166, '14', 'Paul Atreides une-se aos Fremen para vingar sua família.'),
('Divertida Mente 2',       'Animação',          96,  'Livre', 'Riley enfrenta novas emoções na adolescência.'),
('Oppenheimer',             'Drama/Biografia',   180, '16', 'A história do físico J. Robert Oppenheimer.'),
('Um Lugar Silencioso: Dia Um', 'Terror',        99,  '16', 'O início da invasão alienígena silenciosa em Nova York.');

INSERT INTO sala (numero, capacidade, tipo_sala) VALUES
(1, 80,  '2D'),
(2, 60,  '3D'),
(3, 120, 'IMAX');

INSERT INTO cliente (nome, cpf, email, telefone) VALUES
('Mariana Souza',   '444.444.444-44', 'mariana.souza@email.com', '(11) 94444-4444'),
('Pedro Henrique',  '555.555.555-55', 'pedro.henrique@email.com','(11) 95555-5555'),
('Larissa Costa',   '666.666.666-66', 'larissa.costa@email.com', '(11) 96666-6666');

INSERT INTO sessao (id_filme, id_sala, id_funcionario, data_hora, preco_ingresso) VALUES
(1, 3, 1, '2026-09-10 19:30', 32.00),
(2, 1, 2, '2026-09-10 16:00', 22.00),
(3, 2, 1, '2026-09-11 20:00', 28.00),
(1, 1, 3, '2026-09-11 22:00', 25.00);

INSERT INTO ingresso (id_sessao, id_cliente, assento, forma_pagamento, valor_pago, data_compra) VALUES
(1, 1, 'F10', 'Pix',      32.00, '2026-09-09 10:15'),
(1, 2, 'F11', 'Cartão',   32.00, '2026-09-09 10:20'),
(2, 3, 'C05', 'Dinheiro', 22.00, '2026-09-09 11:00'),
(3, 1, 'D02', 'Pix',      28.00, '2026-09-09 14:30'),
(4, 2, 'A01', 'Cartão',   25.00, '2026-09-09 15:00');
