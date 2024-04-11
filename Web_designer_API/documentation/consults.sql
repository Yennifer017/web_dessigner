INSERT INTO site (name) VALUES (?);
SELECT id FROM site WHERE name = ?;
DELETE FROM site WHERE id = ? ;

INSERT INTO page (name, id_site) VALUES (?, ?);
INSERT INTO page (name, id_site, id_father) VALUES (?, ?, ?);
