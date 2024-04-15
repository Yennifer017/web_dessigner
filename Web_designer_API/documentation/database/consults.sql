INSERT INTO site (name) VALUES (?);
SELECT id FROM site WHERE name = ?;
DELETE FROM site WHERE id = ? ;

INSERT INTO page (name, id_site) VALUES (?, ?);
INSERT INTO page (name, id_site, id_father) VALUES (?, ?, ?);


SELECT page.id, page.name, page.id_site FROM page_label 
JOIN label ON label.id = page_label.id_label 
JOIN page ON page.id = page_label.id_page 
WHERE (label.name = "$nueva_etiqueta") 
AND page.id_father = 8;


SELECT page.id, page.name, page.id_site, site.name FROM page_label 
JOIN label ON label.id = page_label.id_label 
JOIN page ON page.id = page_label.id_page 
JOIN site ON page.id_site = site.id
WHERE (label.name = "$nueva_etiqueta);

SELECT page.id, page.name, page.id_site, site.name FROM page_label JOIN label ON label.id = page_label.id_label JOIN page ON page.id = page_label.id_page JOIN site ON page.id_site = site.id WHERE (label.name = "$nueva_etiqueta");
