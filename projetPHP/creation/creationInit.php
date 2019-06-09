<?php

require_once('../model/Model.php');

$requete = "

TRUNCATE TABLE Produit;
TRUNCATE TABLE Serie;
TRUNCATE TABLE Genre;
TRUNCATE TABLE serie_genre;

INSERT INTO Produit (nom_produit) VALUES ('Sandwich');
INSERT INTO Produit (nom_produit) VALUES ('Pizza');
INSERT INTO Produit (nom_produit) VALUES ('Tacos');
INSERT INTO Produit (nom_produit) VALUES ('Kebab');
INSERT INTO Produit (nom_produit) VALUES ('Burger');
INSERT INTO Produit (nom_produit) VALUES ('Chips');
INSERT INTO Produit (nom_produit) VALUES ('Bière');
INSERT INTO Produit (nom_produit) VALUES ('Sushi');
INSERT INTO Produit (nom_produit) VALUES ('Coca-Cola');
INSERT INTO Produit (nom_produit) VALUES ('Eau');
INSERT INTO Produit (nom_produit) VALUES ('Fanta');
INSERT INTO Produit (nom_produit) VALUES ('Ice Tea');
INSERT INTO Produit (nom_produit) VALUES ('Salade');
INSERT INTO Produit (nom_produit) VALUES ('Crêpes');
INSERT INTO Produit (nom_produit) VALUES ('Lasagnes');

INSERT INTO Genre (nom_genre) VALUES ('Drame');
INSERT INTO Genre (nom_genre) VALUES ('Historique');
INSERT INTO Genre (nom_genre) VALUES ('Action');
INSERT INTO Genre (nom_genre) VALUES ('Espionnage');
INSERT INTO Genre (nom_genre) VALUES ('Thriller');
INSERT INTO Genre (nom_genre) VALUES ('Médicale');
INSERT INTO Genre (nom_genre) VALUES ('Comédie');
INSERT INTO Genre (nom_genre) VALUES ('Fantaisie médiévale');
INSERT INTO Genre (nom_genre) VALUES ('Policier');
INSERT INTO Genre (nom_genre) VALUES ('Braquage');
INSERT INTO Genre (nom_genre) VALUES ('Science-Fiction');
INSERT INTO Genre (nom_genre) VALUES ('Horreur');
INSERT INTO Genre (nom_genre) VALUES ('Satire');
INSERT INTO Genre (nom_genre) VALUES ('Humour');
INSERT INTO Genre (nom_genre) VALUES ('Animation');
INSERT INTO Genre (nom_genre) VALUES ('Sitcom');

INSERT INTO Serie (nom_serie, duree) VALUES ('Vikings', 45);
INSERT INTO Serie (nom_serie, duree) VALUES ('Person of Interest', 43);
INSERT INTO Serie (nom_serie, duree) VALUES ('Docteur House', 42);
INSERT INTO Serie (nom_serie, duree) VALUES ('Game of Thrones', 58);
INSERT INTO Serie (nom_serie, duree) VALUES ('Breaking Bad', 55);
INSERT INTO Serie (nom_serie, duree) VALUES ('La Casa de Papel', 75);
INSERT INTO Serie (nom_serie, duree) VALUES ('Black Mirror', 90);
INSERT INTO Serie (nom_serie, duree) VALUES ('Walking Dead', 60);
INSERT INTO Serie (nom_serie, duree) VALUES ('Kaamelott', 5);
INSERT INTO Serie (nom_serie, duree) VALUES ('Family Guy', 20);
INSERT INTO Serie (nom_serie, duree) VALUES ('The Big Bang Theory', 22);
INSERT INTO Serie (nom_serie, duree) VALUES ('The Good Doctor', 42);
INSERT INTO Serie (nom_serie, duree) VALUES ('Orange is the new black', 55);
INSERT INTO Serie (nom_serie, duree) VALUES ('NCIS', 40);

INSERT INTO serie_genre (id_serie, id_genre) VALUES (1,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (1,2);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (2,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (2,3);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (2,4);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (2,5);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (3,6);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (3,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (3,7);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (4,8);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (4,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (5,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (5,9);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (5,5);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (5,7);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (6,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (6,5);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (6,10);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (7,11);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (7,12);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (7,13);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (7,5);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (7,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (8,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (8,12);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (9,14);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (9,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (9,8);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (10,14);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (10,15);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (10,13);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (10,16);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (11,16);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (11,14);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (12,6);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (12,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (13,1);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (13,7);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (14,5);
INSERT INTO serie_genre (id_serie, id_genre) VALUES (14,9);";

Model::$pdo->query($requete);

