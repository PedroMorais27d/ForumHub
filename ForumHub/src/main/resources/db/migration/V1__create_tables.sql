-- Cria a tabela curso
CREATE TABLE curso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100)
);

-- Cria a tabela Usuario
CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(100)
);

-- Cria a tabela Perfil
CREATE TABLE Perfil (
    id INT PRIMARY KEY,
    nome VARCHAR(100)
);

-- Cria a tabela de junção Usuario_Perfil
CREATE TABLE Usuario_Perfil (
    usuario_id INT,
    perfil_id INT,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (perfil_id) REFERENCES Perfil(id)
);

-- Cria a tabela topico
CREATE TABLE topico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT,
    datacriacao DATE,
    status VARCHAR(50),
    autor VARCHAR(100),
    curso_id INT,
    respostas INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Cria a tabela resposta
CREATE TABLE resposta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mensagem TEXT,
    topico_id INT,
    datacriacao DATE,
    autor VARCHAR(100),
    solucao BOOLEAN DEFAULT false,
    FOREIGN KEY (topico_id) REFERENCES topico(id)
);