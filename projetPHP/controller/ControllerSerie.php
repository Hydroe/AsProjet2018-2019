<?php

require_once ('../model/ModelSerie.php');

class ControllerSerie {
    
    public static function readAll() {

        // Récupération de la liste des séries pour les afficher dans la view list
        
        $tab_serie = ModelSerie::listeSerie();
        require ('../view/serie/list.php');
    }
    
    public static function read() {


        // Récupération des infos de la série
        $serie = ModelSerie::getSerieById($_GET['id_serie']);

        // Récupération des genres de la série
        $tab_genres = ModelSerie::getGenresBySerie($_GET['id_serie']);
        
        require ('../view/serie/detail.php');

    }

    // Suggestion d'une série à regarder
    public static function suggest() {


        // Récupération de la liste des séries
        $liste_serie = ModelSerie::listeSerie();


        // On envoie la liste dans le formulaire de suggestion de série
        require ('../view/serie/suggest.php');

        // On attend qu'une série soit sélectionnée dans le formulaire
        if (isset($_POST['nom_serie'])) {

            $serie = $_POST['nom_serie'];


            // Récupération de la série avec le plus de corrélations
            $data = ModelSerie::maxCorrelation($serie);

            echo "Voici les séries qui peuvent vous intéresser :</br>";

            echo "<ul>";

            foreach ($data as $value)
                echo "<li>$value</li>";

            echo "</ul>";
        }
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
                    echo "Les personnes étant dans la tranche d'âge $tranche ans et consommant le produit $produit ne regardent pas de série.";
                    break;
                default:
                    echo "Les personnes étant dans la tranche d'âge $tranche ans et consommant le produit $produit ont pour habitude de regarder :</br>";
                    break;
            }

            echo "<ul>";

            foreach ($data as $value)
                echo "<li>$value</li>";

            echo "</ul>";
        }
    }
    
    public static function create() {
        $liste_genre = ModelSerie::listeGenre();
        require ('../view/serie/create.php');
    }
    
    public static function created() {
        
        $type = array($_POST['nom_genre']);
        
        $data = array(
            "nom_serie" => $_POST['nom_serie'],
            "serie_duree" => $_POST['duree'],
            "serie_type" => $type);
        
        $serie = new ModelSerie($data);
        $serie->save();
        
        ControllerSerie::readAll();
    }
}
