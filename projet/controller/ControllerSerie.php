<?php

require_once ('../model/ModelSerie.php');

class ControllerSerie {
    
    public static function readAll() {
        
        $tab_serie = ModelSerie::listeSerie();
        require ('../view/serie/list.php');
    }
    
    public static function read() {     
    }

    public static function search() {
        $liste_produit = ModelProduit::listeProduit();
        $liste_tranche = ModelProfil::listeTranche();

        require ('../view/serie/search.php');

        if (isset($_POST['nom_produit']) && isset($_POST['tranche'])) {

            $tranche = $_POST['tranche'];
            $produit = $_POST['nom_produit'];

            $data = ModelSerie::chercherSerie($tranche, $produit);

            switch (count($data)) {
                case '0':
                    echo "Aucun résultat pour la recherche $tranche / $produit";
                    break;
                case '1':
                    echo "Le résultat pour la recherche $tranche / $produit est :</br>";
                    break;
                default:
                    echo "Les résultats pour la recherche $tranche / $produit sont :</br>";
                    break;
            }

            echo "<ul>";

            foreach ($data as $value)
                echo "<li>$value</li>";

            echo "</ul>";
        }
    }
}
