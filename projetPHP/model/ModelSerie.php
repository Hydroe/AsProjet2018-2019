<?php

require_once('Model.php');

class ModelSerie {

	private $nom_serie;
	private $serie_duree;
    private $type_duree;
	private $serie_type = array();


	//Constructeur de série
	public function __construct($data) {

		$this->nom_serie=$data["nom_serie"];;
		$this->serie_type = $data["serie_type"];
        $this->serie_duree = $data['serie_duree'];

        //Affectation de Short/Médium/Long/Extra Long en fonction de la durée de la série

		if ($data['serie_duree'] >= 5 && $data['serie_duree'] <= 25)
        	$this->type_duree = "Short";
		if ($data['serie_duree'] > 25 && $data['serie_duree'] <= 45)
			$this->type_duree = "Medium";
		if ($data['serie_duree'] > 45 && $data['serie_duree'] <= 150)
            $this->type_duree = "Long";
        else
            $this->type_duree = "Extra Long";
	}
        
    public function save() {


        // Insertion du nom et de la durée de la série dans la table Série  
        $insert = "	INSERT INTO Serie (nom_serie, duree)
        			VALUES (:nom_serie_tag, :duree_tag)";
            
        $req_prep = Model::$pdo->prepare($insert);
        $values = array(
			"nom_serie_tag" => $this->nom_serie,
            "duree_tag" => $this->serie_duree);
            
        $req_prep->execute($values);
            
        $id_serie = ModelSerie::getIdByName($this->nom_serie);
        $id_genre = ModelSerie::getIdGenreByName($this->serie_type['0']);

        // Insertion de l'association id_genre/id_serie dans la table serie_genre
        $insertGenreSerie = "	INSERT INTO serie_genre (id_serie, id_genre)
        						VALUES (:id_serie_tag, :id_genre_tag)";
            
        $prep = Model::$pdo->prepare($insertGenreSerie);
        $values = array(
			"id_serie_tag" => $id_serie,
            "id_genre_tag" => $id_genre);
            
        $prep->execute($values);
    }
        
        public static function getIdGenreByName($nom_genre) {
            
            $requete = "SELECT id_genre FROM Genre WHERE nom_genre = :genre_tag";
            
            $rep = Model::$pdo->prepare($requete);
            
            $values = array(
                "genre_tag" => $nom_genre);
            
            $rep->execute($values);
            $res = $rep->fetch();
            
            return $res['id_genre'];            
        }

	public static function getIdByName($nom_serie) {

		$requete = "SELECT id_serie FROM Serie WHERE nom_serie = :serie_tag";

		$rep = Model::$pdo->prepare($requete);
                
                $values=array(
                    "serie_tag" => $nom_serie);

		$rep->execute($values);
		$res = $rep->fetch();

		return $res['id_serie'];

	}
        
        public static function getGenresBySerie($id_serie) {
            
            $requete = "SELECT nom_genre
                        FROM Genre g
                        JOIN serie_genre sg ON sg.id_genre = g.id_genre
                        WHERE id_serie = :id_serie_tag";
            
            $rep = Model::$pdo->prepare($requete);
            $values = array (
                "id_serie_tag" => $id_serie);
            $rep->execute($values);
            
            $i = 0;

            while ($donnees = $rep->fetch()) {
		$res[$i]=$donnees['nom_genre'];
		$i++;
            }
		
            return $res;            
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
    
    public static function listeGenre() {
        
        $requete = "SELECT nom_genre FROM Genre ORDER BY nom_genre";
        $rep = Model::$pdo->query($requete);
        
        $i = 0;
        
        while ($donnees = $rep->fetch()) {
            $res[$i]=$donnees['nom_genre'];
            $i++;
	}
		
	return $res;
    }
        
    public static function chercherSerie($tranche, $produit) {

		$requete = "SELECT t1.nom_serie as reponse
					FROM 
					(SELECT s.nom_serie, COUNT(*) AS nb
					FROM Profil p
					JOIN plateau_tele pt ON pt.id_profil=p.id_profil
					JOIN Serie s ON s.id_serie=pt.id_serie
					JOIN Produit prod ON prod.id_produit=pt.id_produit
					WHERE p.tranche=:tranche_tag AND prod.nom_produit=:produit_tag
					GROUP BY s.nom_serie) t1
					WHERE t1.nb IN
					(SELECT MAX(t.nb) as nb_max
					FROM
					(SELECT s.nom_serie, COUNT(*) AS nb
					FROM Profil p
					JOIN plateau_tele pt ON p.id_profil=pt.id_profil
					JOIN Serie s ON s.id_serie=pt.id_serie
					JOIN Produit prod ON prod.id_produit=pt.id_produit
					WHERE p.tranche=:tranche_tag AND prod.nom_produit=:produit_tag
					GROUP BY s.nom_serie) t)";
                
        $rep = Model::$pdo->prepare($requete);
                
        $values=array(
        	"tranche_tag" => $tranche,
            "produit_tag" => $produit);

		$rep->execute($values);

		$i = 0;

		$res = array();

		while ($donnees = $rep->fetch()) {
			$res[$i]=$donnees['reponse'];
			$i++;
		}
		
		return $res;
	}


	// Compare deux séries et renvoie une note adéquate
	public function compare($other_serie) {

		$note = 10;


		// Changement de la note en fonction de la durée
		if ($this->type_duree == "Medium" xor $other_serie->type_duree == "Medium")
        	$note++;
		elseif ($this->type_duree != $other_serie->type_duree)
            $note += 2;
        else
        	$note--;

        // Insersection des genres des deux séries                
        $genres_commun = array_intersect($this->serie_type, $other_serie->serie_type);
                
        // S'il n'y aucun genre en commun, on éloigne les deux séries, sinon on fait
        // baisser la note de la taille de l'insersection
        if (count($genres_commun) == 0)
        	$note+=2;
        else
            $note+=count($genres_commun);

		return $note;
	}

	public static function baseToData($nom_serie) {

		$requete = "SELECT id_serie, duree FROM Serie WHERE nom_serie = :nom_tag";

		$rep = Model::$pdo->prepare($requete);

		$values = array (
			"nom_tag" => $nom_serie);

		$rep->execute($values);

		while ($donnees = $rep->fetch()) {
			$res['0']=$donnees['id_serie'];
			$res2['0']=$donnees['duree'];
		}

		$id_serie = $res['0'];

		$requete2 = "SELECT nom_genre FROM Genre g JOIN serie_genre sg ON sg.id_genre = g.id_genre WHERE id_serie = $id_serie";

		$rep = Model::$pdo->query($requete2);

		$i = 0;

		while ($donnees = $rep->fetch()) {
			$genres[$i]=$donnees['nom_genre'];
			$i++;
		}

		$data = array(
			"nom_serie" => $nom_serie,
			"serie_type" => $genres,
			"serie_duree" => $res2['0']);

		$serie = new ModelSerie ($data);

		return $serie;

	}


	// Récupération des séries avec le plus de corrélation
	public static function maxCorrelation($nom_serie) {

		$tab_correlation = array();


		// Transformation d'une ligne de la table Série en objet Serie
		$serie_base = ModelSerie::baseToData($nom_serie);

		$liste_serie = ModelSerie::listeSerie();

		$note_min = 20;

		// Comparaison à toutes les séries de la base
		foreach ($liste_serie as $value) {

			$serie_actual = ModelSerie::baseToData($value);

			if ($serie_actual->nom_serie != $nom_serie) {
				
				$note_actual = $serie_base->compare($serie_actual);

				$tab_correlation["$value"] = $note_actual;

				if ($note_actual < $note_min)
					$note_min = $note_actual;
			}
		}

		$res = array();

		// Récupération des séries ayant la note la plus basse
		foreach ($tab_correlation as $key => $value) {

			if ($value == $note_min)
				array_push($res, $key);
		}

		return $res;
	}
        
        public static function getSerieById($id_serie) {
            
            $requete = "SELECT nom_serie, duree FROM Serie WHERE id_serie = :id_serie_tag";
            
            $rep = Model::$pdo->prepare($requete);
            
            $values = array(
                "id_serie_tag" => $id_serie);
            
            $rep->execute($values);
            
            while ($donnees = $rep->fetch()) {
			$res['nom_serie']=$donnees['nom_serie'];
			$res['duree']=$donnees['duree'];
            }
            
            return $res;
        }
        
        



		
}