<?php

require_once('../model/Model.php');
require_once('../model/ModelProfil.php');
require_once('../model/ModelProduit.php');
require_once('../model/ModelSerie.php');

$requeteTruncate = (
         "TRUNCATE TABLE Profil;
         TRUNCATE TABLE plateau_tele;");

Model::$pdo->query($requeteTruncate);

$nb_serie = ModelSerie::nbSerie();
$nb_produit = ModelProduit::nbProduit();


// Création des profils
for ($k = 0; $k < 1000; $k++) {
    $prenom = ModelProfil::definirPrenom();
    $nom = ModelProfil::definirNom();    
    $age = rand(1,80);
    $profil = new ModelProfil ($nom, $prenom, $age);
    $profil->save();
}

$nb_profil = ModelProfil::nbProfil();

for ($i = 1; $i <= $nb_profil; $i++) {
    // Sélection de la série aléatoirement 
    $rand_serie = rand(1, $nb_serie);   
    $rand_nbProduit = rand(1, 5);
    
    for ($j = 0; $j < $rand_nbProduit; $j++) {
        // Sélection d'un produit aléatoirement
        $rand_produit = rand(1, $nb_produit);
        $requete = "INSERT INTO plateau_tele (id_profil, id_serie, id_produit)
                    VALUES ($i, $rand_serie, $rand_produit)";
        Model::$pdo->query($requete);
    }   
}





