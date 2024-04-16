CREATE TABLE `emprestimo` (
  `emprestimo_id` varchar(10) NOT NULL,
  `livro_titulo` varchar(200) DEFAULT NULL,
  `usuario_cpf` varchar(11) DEFAULT NULL,
  `data_emprestimo` varchar(20) DEFAULT NULL,
  `data_devolucao` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `livro`
--

CREATE TABLE `livro` (
  `titulo` varchar(200) NOT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `ano_lancamento` varchar(10) DEFAULT NULL,
  `editora` varchar(100) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `idade_recomendada` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `livro`
--

INSERT INTO `livro` (`titulo`, `autor`, `ano_lancamento`, `editora`, `genero`, `idade_recomendada`) VALUES
('A psicologia financeira', 'Morgan Housel', '2021', 'HarperCollins', 'Negócios', 10),
('As 48 leis do poder', 'Robert Greene', '2021', ' Rocco', 'Autoajuda', 18),
('As armas da persuasão', 'Robert B. Cialdini', '2012', 'Sextante', 'Desenvolvimento Pessoal', 0),
('Entendendo Algoritmos', 'Aditya Bhargava', '2017', 'Novatec', 'Informática', 0),
('Essencialismo: A disciplinada busca por menos', 'Greg McKeown', '2015', 'Sextante', 'Desenvolvimento Pessoal', 0),
('Hábitos atômicos', 'James Clear', '2019', 'Alta Life', 'Desenvolvimento Pessoal', 10),
('Mindset: A nova psicologia do sucesso', 'Carol S. Dweck, ph.D', '2017', 'Objetiva', 'Pscologia', 10),
('Nada pode me ferir', 'David Goggins', '2023', 'Sextante', 'Desenvolvimento Pessoal', 14),
('Nunca é hora de parar: Liberte sua mente e desenvolva seu potencial inexplorado', 'David Goggins', '2023', 'Sextante', 'Desenvolvimento Pessoal', 0),
('O homem mais rico da Babilônia', 'George S. Clason', '2017', 'Harper Collins', 'Negócios', 10),
('Os segredos da mente milionária', 'T. Harv Eker', '1992', 'Sextante', 'Negócios', 0),
('Pai rico, pai pobre', 'Robert Kiyosaki', '2018', 'Alta Books', 'Negócios', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `login`
--

CREATE TABLE `login` (
  `email` varchar(30) DEFAULT NULL,
  `senha` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `login`
--

INSERT INTO `login` (`email`, `senha`) VALUES
('leonardo@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `data_nascimento` varchar(40) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `uf` char(2) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`cpf`, `nome`, `data_nascimento`, `endereco`, `uf`, `cidade`, `complemento`, `email`, `telefone`) VALUES
('12456987521', 'Vitor', '12/05/2003', 'R. Bel Aliance', 'SP', 'São Caetano do Sul', 'Sem complemento', 'Vitor@gmail.com', '11945875969'),
('12545698745', 'Calebe', '11/12/2004', 'Rua Bristol', 'SP', 'São Paulo', 'Ap 21 bloco 2', 'Calebe@gmail.com', '45412554'),
('44010502825', 'Leonardo', '09/11/2003', 'Av Winston Churchill', 'SP', 'São Bernardo do Campo', 'Bloco C AP 111', 'leonardo@gmail.com', '11945493869'),
('55215632547', 'Lucas', '14/05/2002', 'Av Doutor Rudge Ramos', 'SP', 'São Bernardo do Campo', 'Sem complemento', 'Lucas@gmail.com', '1145453459');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `emprestimo`
--
ALTER TABLE `emprestimo`
  ADD PRIMARY KEY (`emprestimo_id`),
  ADD KEY `livro_titulo` (`livro_titulo`),
  ADD KEY `usuario_cpf` (`usuario_cpf`);

--
-- Índices de tabela `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`titulo`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cpf`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `emprestimo`
--
ALTER TABLE `emprestimo`
  ADD CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`livro_titulo`) REFERENCES `livro` (`titulo`),
  ADD CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`usuario_cpf`) REFERENCES `usuario` (`cpf`);
COMMIT;

