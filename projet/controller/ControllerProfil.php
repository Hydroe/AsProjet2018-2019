<?php
require_once ('../model/ModelProfil.php'); // chargement du modèle

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
                    echo "Aucun résultat pour la recherche $serie / $produit";
                    break;
                case '1':
                    echo "Le résultat pour la recherche $serie / $produit est :</br>";
                    break;
                default:
                    echo "Les résultats pour la recherche $serie / $produit sont :</br>";
                    break;
            }

            echo "<ul>";

            foreach ($data as $value)
                echo "<li>$value</li>";

            echo "</ul>";
        }

    }
    
    public static function create() {
        require ('../view/profil/create.php');
    }
    
    public static function created() {        
    }
}

?>
