<?php
require_once ('../model/ModelProfil.php');

class ControllerProfil {
    
    public static function readAllTranche() {
        
        $tab_tranche = ModelProfil::listeTranche();
        require ('../view/profil/list.php');
    }

    public static function search() {
        $liste_produit = ModelProduit::listeProduit();
        $liste_serie = ModelSerie::listeSerie();

        require ('../view/profil/search.php');

        if (isset($_POST['nom_produit']) && isset($_POST['nom_serie'])) {

            $serie = $_POST['nom_serie'];
            $produit = $_POST['nom_produit'];

            $data = ModelProfil::chercherProfil($serie, $produit);

            switch (count($data)) {
                case '0':
                    echo "Personne ne regarde $serie en consommant le produit $produit.";
                    break;
                default:
                    echo "Les personnes regardant $serie et consommant le produit $produit sont dans la tranche d'âge :</br>";
                    break;
            }

            echo "<ul>";

            foreach ($data as $value)
                echo "<li>$value ans</li>";

            echo "</ul>";
        }

    }


    public static function create() {

        $liste_produit = ModelProduit::listeProduit();
        $liste_serie = ModelSerie::listeSerie();

        require ('../view/profil/create.php');
    }

    // Création d'un profil avec son habitude de consommation devant une série    
    public static function created() {

        $profil = new ModelProfil ($_POST['nom'], $_POST['prenom'], $_POST['age']);

        // Insertion d'un profil dans la table Profil
        $profil->save();

        $nb_profil = ModelProfil::nbProfil();

        $id_serie = ModelSerie::getIdByName($_POST['nom_serie']);
        $id_produit = ModelProduit::getIdByName($_POST['nom_produit']);


        // Insertion dans la table plateau_tele
        $requete = "INSERT INTO plateau_tele (id_profil, id_serie, id_produit)
                    VALUES ($nb_profil, :numero_serie, :numero_produit)";

        $rep = Model::$pdo->prepare($requete);

        $values = array(
            "numero_serie" => $id_serie,
            "numero_produit" => $id_produit);

        $rep->execute($values);
        
        require ('../view/profil/created.php');

    }
}

?>
