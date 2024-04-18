DROP SCHEMA WEB_DESSIGNER;
CREATE SCHEMA WEB_DESSIGNER;
USE WEB_DESSIGNER;

CREATE TABLE site(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL, 
    CONSTRAINT PK_site PRIMARY KEY(id)
);

CREATE TABLE page(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL, 
    id_site INT NOT NULL,
    id_father INT,
    visits INT NOT NULL DEFAULT 0,
    CONSTRAINT PK_page PRIMARY KEY(id),
    FOREIGN KEY (id_site) REFERENCES site(id) ON DELETE CASCADE,
    FOREIGN KEY (id_father) REFERENCES page(id) ON DELETE CASCADE
);

CREATE TABLE component(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL, 
    id_page INT NOT NULL,
    class ENUM('TITULO', 'PARRAFO', 'IMAGEN', 'VIDEO', 'MENU') NOT NULL,
    CONSTRAINT PK_component PRIMARY KEY(id),
    FOREIGN KEY (id_page) REFERENCES page(id) ON DELETE CASCADE /*cuando se elimine la pagina tambien el componente*/
);

CREATE TABLE label(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL, 
    CONSTRAINT PK_site PRIMARY KEY(id)
);

CREATE TABLE page_label(
    id_label INT NOT NULL,
    id_page INT NOT NULL,
    CONSTRAINT PK_pagelabel PRIMARY KEY(id_label, id_page),
    FOREIGN KEY (id_label) REFERENCES label(id),
    FOREIGN KEY (id_page) REFERENCES page(id) ON DELETE CASCADE
);
