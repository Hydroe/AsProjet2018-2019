<?php

class ModelSerie {

	private $nom_serie;
	private $serie_length;
	private $serie_type = array();
	private $serie_popularite;

	public function __construct($data) {

		$this->nom_serie=$data["nom_serie"];;
		$this->serie_type = $data["serie_type"];
		$this->serie_popularite = $data["serie_popularite"];

		if ($data['serie_length'] > 5 && $data['serie_length'] <= 25)
			$this->serie_length = "Short";
		if ($data['serie_length'] > 25 && $data['serie_length'] <= 45)
			$this->serie_length = "Medium";
		if ($data['serie_length'] > 45 && $data['serie_length'] <= 150)
			$this->serie_length = "Long";		
	}

	public static function nbSerie() {
		$requete = "SELECT COUNT(*) AS nb FROM Serie";
        $rep = Model::$pdo->query($requete);
        $res=$rep->fetch();
        return $res['nb'];
	}
        
    public static function listeSerie() {

		$requete = "SELECT nom_serie FROM Serie ORDER BY nom_serie";
		$rep = Model::$pdo->query($requete);

		$i = 0;

		while ($donnees = $rep->fetch()) {
			$res[$i]=$donnees['nom_serie'];
			$i++;
		}
		
		return $res;
	}
        
    public static function chercherSerie($tranche, $produit) {

		$requete = "SELECT t1.nom_serie as reponse
					FROM 
					(SELECT s.nom_serie, COUNT(*) AS nb
					FROM Profil p
					JOIN Regarder r ON p.id_profil=r.id_profil
					JOIN Serie s ON s.id_serie=r.id_serie
					JOIN Produit prod ON prod.id_produit=r.id_produit
					WHERE p.tranche=:tranche_tag AND prod.nom_produit=:produit_tag
					GROUP BY s.nom_serie) t1
					WHERE t1.nb IN
					(SELECT MAX(t.nb) as nb_max
					FROM
					(SELECT s.nom_serie, COUNT(*) AS nb
					FROM Profil p
					JOIN Regarder r ON p.id_profil=r.id_profil
					JOIN Serie s ON s.id_serie=r.id_serie
					JOIN Produit prod ON prod.id_produit=r.id_produit
					WHERE p.tranche=:tranche_tag AND prod.nom_produit=:produit_tag
					GROUP BY s.nom_serie) t)";
                
        $rep = Model::$pdo->prepare($requete);
                
        $values=array(
        	"tranche_tag" => $tranche,
            "produit_tag" => $produit);

		$rep->execute($values);

		$i = 0;

		while ($donnees = $rep->fetch()) {
			$res[$i]=$donnees['reponse'];
			$i++;
		}
		
		return $res;
	}

	public function compare($other_serie) {

		$note = 10;

		if ($this->serie_length == "Medium" xor $other_serie->serie_length == "Medium")
			$note++;
		elseif ($this->serie_length == $other_serie->serie_length)
			$note += 2;

		foreach ($this->serie_type as $key => $value) {
			if (in_array($type, $other_serie->serie_type))
				$note--;
			else
				$note++;
		}

		$note += abs($this->serie_popularite - $other_serie->serie_popularite);

		return $note;
	}



		
}