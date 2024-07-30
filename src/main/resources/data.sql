
-- Inserção de Gestores
INSERT INTO Funcionario (nome, email, is_Gestor, gestor_id) VALUES
('Gestor 1', 'gestor1@example.com', TRUE, NULL),
('Gestor 2', 'gestor2@example.com', TRUE, NULL);

-- Inserção de Subordinados para o Gestor 1
INSERT INTO Funcionario (nome, email, is_Gestor, gestor_id) VALUES
('Subordinado 1', 'sub1@example.com', FALSE, 1),
('Subordinado 2', 'sub2@example.com', FALSE, 1),
('Subordinado 3', 'sub3@example.com', FALSE, 1),
('Subordinado 4', 'sub4@example.com', FALSE, 1),
('Subordinado 5', 'sub5@example.com', FALSE, 1),
('Subordinado 6', 'sub6@example.com', FALSE, 1),
('Subordinado 7', 'sub7@example.com', FALSE, 1),
('Subordinado 8', 'sub8@example.com', FALSE, 1),
('Subordinado 9', 'sub9@example.com', FALSE, 1),
('Subordinado 10', 'sub10@example.com', FALSE, 1);

-- Inserção de Subordinados para o Gestor 2
INSERT INTO Funcionario (nome, email, is_Gestor, gestor_id) VALUES
('Subordinado 11', 'sub11@example.com', FALSE, 2),
('Subordinado 12', 'sub12@example.com', FALSE, 2),
('Subordinado 13', 'sub13@example.com', FALSE, 2),
('Subordinado 14', 'sub14@example.com', FALSE, 2),
('Subordinado 15', 'sub15@example.com', FALSE, 2),
('Subordinado 16', 'sub16@example.com', FALSE, 2),
('Subordinado 17', 'sub17@example.com', FALSE, 2),
('Subordinado 18', 'sub18@example.com', FALSE, 2),
('Subordinado 19', 'sub19@example.com', FALSE, 2);

-- Inserção de Férias (opcional para teste)
-- Para simplificar, vamos adicionar algumas férias para os subordinados
INSERT INTO Ferias (funcionario_id, data_inicio, data_termino, aprovado) VALUES
(1, '2024-08-01', '2024-08-15', FALSE),
(2, '2024-09-01', '2024-09-10', FALSE),
(3, '2024-10-01', '2024-10-07', FALSE);