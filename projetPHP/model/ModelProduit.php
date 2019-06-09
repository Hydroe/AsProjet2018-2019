<?php

require_once('Model.php');

class ModelProduit {

	private $nom_produit;

	public function __construct($nom) {
		$this->nom_produit=$nom;
	}

	public function save() {
		$insert="INSERT INTO Produit (nom_produit) VALUES (:nom_produit_tag)";

		$req_prep = Model::$pdo->prepare($insert);
		$values=array(
			"nom_produit_tag" => $this->nom_produit);

		$req_prep->execute($values);
	}

	public static function getIdByName($nom_produit) {

		$requete = "SELECT id_produit FROM Produit WHERE nom_produit = :produit";

		$rep = Model::$pdo->prepare($requete);
                
                $values=array(
                    "produit" => $nom_produit);

		$rep->execute($values);
		$res = $rep->fetch();

		return $res['id_produit'];
	}

	public static function nbProduit() {
		$requete = "SELECT COUNT(*) AS nb FROM Produit";
                $rep = Model::$pdo->query($requete);
                $res=$rep->fetch();
                return $res['nb'];
	}
        
    public static function listeProduit() {

		$requete = "SELECT nom_produit FROM Produit ORDER BY nom_produit";

		$rep = Model::$pdo->query($requete);

		$i = 0;

		while ($donnees = $rep->fetch()) {
			$res[$i]=$donnees['nom_produit'];
			$i++;
		}

		return $res;
    }
    

    // Fonction permettant de retourner une liste des produits correspondant
    // aux paramètres
    public static function chercherProduit($tranche, $serie) {

    // Requete permettant de récupérer les produits
	$requete = "SELECT t1.nom_produit as reponse
				FROM 
                (SELECT prod.nom_produit, COUNT(*) AS nb
				FROM Profil p
				JOIN plateau_tele pt ON p.id_profil=pt.id_profil
				JOIN Serie s ON s.id_serie=pt.id_serie
				JOIN Produit prod ON prod.id_produit=pt.id_produit
				WHERE p.tranche=:tranche_tag AND s.nom_serie=:serie_tag
				GROUP BY prod.nom_produit) t1
				WHERE t1.nb IN
				(SELECT MAX(t.nb) as nb_max
				FROM
				(SELECT prod.nom_produit, COUNT(*) AS nb
				FROM Profil p
                JOIN plateau_tele pt ON p.id_profil=pt.id_profil
				JOIN Serie s ON s.id_serie=pt.id_serie
				JOIN Produit prod ON prod.id_produit=pt.id_produit
				WHERE p.tranche=:tranche_tag AND s.nom_serie=:serie_tag
				GROUP BY prod.nom_produit) t)";
                
    $rep = Model::$pdo->prepare($requete);
                
    $values=array(
		"tranche_tag" => $tranche,
        "serie_tag" => $serie);

	$rep->execute($values);

	$i = 0;

	$res = array();

	// Ajout des produits résultant de la requête dans un tableau
	while ($donnees = $rep->fetch()) {
		$res[$i]=$donnees['reponse'];
		$i++;
	}
		
	return $res;

	}
}