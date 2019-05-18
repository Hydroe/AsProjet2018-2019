<?php
require_once ('../model/ModelProduit.php');

class ControllerProduit {

    public static function readAll() {
        
        $tab_p = ModelProduit::listeProduit();
        require ('../view/produit/list.php');
    }

    public static function search() {
        $liste_tranche = ModelProfil::listeTranche();
        $liste_serie = ModelSerie::listeSerie();

        require ('../view/produit/search.php');

        if (isset($_POST['tranche']) && isset($_POST['nom_serie'])) {

            $tranche = $_POST['tranche'];
            $serie = $_POST['nom_serie'];

            $data = ModelProduit::chercherProduit($tranche, $serie);

            switch (count($data)) {
                case '0':
                    echo "Aucun résultat pour la recherche $tranche / $serie";
                    break;
                case '1':
                    echo "Le résultat pour la recherche $tranche / $serie est :</br>";
                    break;
                default:
                    echo "Les résultats pour la recherche $tranche / $serie sont :</br>";
                    break;
            }

            echo "<ul>";

            foreach ($data as $value)
                echo "<li>$value</li>";

            echo "</ul>";
        }
    }

    public static function create() {
        require ('../view/produit/create.php');
    }

    public static function created() {
        $produit = new ModelProduit ($_POST['produit']);
        $produit->save();

        ControllerProduit::readAll();
    }
}
