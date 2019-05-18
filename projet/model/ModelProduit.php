<?php

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
        
        public static function chercherProduit($tranche, $serie) {

		$requete = "SELECT t1.nom_produit as reponse
					FROM 
					(SELECT prod.nom_produit, COUNT(*) AS nb
					FROM Profil p
					JOIN Regarder r ON p.id_profil=r.id_profil
					JOIN Serie s ON s.id_serie=r.id_serie
					JOIN Produit prod ON prod.id_produit=r.id_produit
					WHERE p.tranche=:tranche_tag AND s.nom_serie=:serie_tag
					GROUP BY prod.nom_produit) t1
					WHERE t1.nb IN
					(SELECT MAX(t.nb) as nb_max
					FROM
					(SELECT prod.nom_produit, COUNT(*) AS nb
					FROM Profil p
					JOIN Regarder r ON p.id_profil=r.id_profil
					JOIN Serie s ON s.id_serie=r.id_serie
					JOIN Produit prod ON prod.id_produit=r.id_produit
					WHERE p.tranche=:tranche_tag AND s.nom_serie=:serie_tag
					GROUP BY prod.nom_produit) t)";
                
                $rep = Model::$pdo->prepare($requete);
                
                $values=array(
			"tranche_tag" => $tranche,
                        "serie_tag" => $serie);

		$rep->execute($values);

		$i = 0;

		while ($donnees = $rep->fetch()) {
			$res[$i]=$donnees['reponse'];
			$i++;
		}
		
		return $res;

	}
}