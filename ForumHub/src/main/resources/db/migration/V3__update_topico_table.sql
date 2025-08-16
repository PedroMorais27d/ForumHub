ALTER TABLE topico ADD COLUMN autor_id INT;

ALTER TABLE topico ADD CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id);

ALTER TABLE topico DROP COLUMN autor;

ALTER TABLE topico MODIFY COLUMN datacriacao TIMESTAMP;

CREATE TABLE IF NOT EXISTS resposta (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mensagem TEXT,
    data_criacao TIMESTAMP,
    topico_id INT,
    autor_id INT,
    FOREIGN KEY (topico_id) REFERENCES topico(id),
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);