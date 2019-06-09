<?php

require_once('Model.php');

class ModelProfil {

	private $nom;
	private $prenom;
	private $nbTranche;
	private $tabTrancheAge;
	private $tranche;

	public function __construct($nom, $prenom, $age) {

		$this->nom=$nom;
		$this->prenom=$prenom;
		$this->nbTranche=floor(($age-1)/10);
		$this->tabTrancheAge = array('1 - 10', '11 - 20', '21 - 30', '31 - 40', '41 - 50', '51 - 60', '61 - 70', '71 - 80');
		$this->tranche=$this->getTrancheByAge($this->nbTranche);		
	}

	public function get($attribut) {
		return $this->$attribut;
	}

	public function getTrancheByAge($nbTranche) {
		return $this->tabTrancheAge[$nbTranche];
	}

	public static function definirPrenom() {
		$tabPrenom=array("Jean","Guillaume","Vincent","Julien","Denis","Francis","Thibault","Robin","Théo","Francesco","Victoria","Madalina","Evi","Jade","Emma","Juliette","Océane","Anna","Marie","Lucie");
		$i=rand(0,count($tabPrenom)-1);
		return $tabPrenom[$i];
	}

	public static function definirNom() {
		$tabNom=array("LETOURMY","GARCIA","BECLE","DUPONT","JOHNSON","ALAUZET","DUFOUR","MATTHEWS","VICHET","CROITORU","RAOUL","PANCHAUD");
		$i=rand(0,count($tabNom)-1);
		return $tabNom[$i];
	}

	public function save() {
		$insert="INSERT INTO Profil (nom,prenom,tranche) VALUES (:nom_tag, :prenom_tag, :tranche_tag)";

		$req_prep = Model::$pdo->prepare($insert);

		$values = array(
			"nom_tag" => $this->nom,
			"prenom_tag" => $this->prenom,
			"tranche_tag" => $this->tranche,
			);

		$req_prep->execute($values);
	}
        
        public static function savePlateauTele($id_profil, $id_serie, $id_produit) {
            
            $insert="INSERT INTO plateau_tele (id_profil, id_serie, id_produit) VALUES (:id_profil_tag, :id_serie_tag, :id_produit_tag)";

            $req_prep = Model::$pdo->prepare($insert);
            $values=array(
		"id_profil_tag" => $id_profil,
		"id_serie_tag" => $id_serie,
		"id_produit_tag" => $id_produit);
            
            $res = $req_prep->execute($values);
            print_r($res);
	
            return $res;
            
        }

	public static function nbProfil() {
		$requete = "SELECT COUNT(*) AS nb FROM Profil";
        $rep = Model::$pdo->query($requete);
        $res=$rep->fetch();
        return $res['nb'];
	}
        
    public static function listeTranche() {

		$requete = "SELECT DISTINCT tranche FROM Profil ORDER BY tranche";

		$rep = Model::$pdo->query($requete);

		$i = 0;

		while ($donnees = $rep->fetch()) {
			$res[$i]=$donnees['tranche'];
			$i++;
		}

		return $res;
	}
        
    public static function chercherProfil($serie, $produit) {
    	
		$requete = "SELECT t1.tranche as reponse
					FROM 
					(SELECT p.tranche, COUNT(*) AS nb
					FROM Profil p
					JOIN plateau_tele pt ON p.id_profil=pt.id_profil
					JOIN Serie s ON s.id_serie=pt.id_serie
					JOIN Produit prod ON prod.id_produit=pt.id_produit
					WHERE s.nom_serie=:serie_tag AND prod.nom_produit=:produit_tag
					GROUP BY p.tranche) t1
					WHERE t1.nb IN
					(SELECT MAX(t.nb) as nb_max
					FROM
					(SELECT p.tranche, COUNT(*) AS nb
					FROM Profil p
					JOIN plateau_tele pt ON p.id_profil=pt.id_profil
					JOIN Serie s ON s.id_serie=pt.id_serie
					JOIN Produit prod ON prod.id_produit=pt.id_produit
					WHERE s.nom_serie=:serie_tag AND prod.nom_produit=:produit_tag
					GROUP BY p.tranche) t)";

		$rep = Model::$pdo->prepare($requete);
                
                $values=array(
			"serie_tag" => $serie,
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


}