<html>
    <head>
    	<?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Détail d'une série</title>
    </head>
    <body>
    	<?php require "../view/general/navigation.php"; ?>
        <?php

        echo "<h3> Liste des séries disponibles </h3> ";

        $nom_serie = $serie['nom_serie'];
        $duree = $serie['duree'];
        
        echo "Nom de la série : $nom_serie</br>";
        echo "Durée de la série : $duree minutes</br>";
        echo "Genres : ";
        
        echo "<ul>";
        
        foreach ($tab_genres as $value)
            echo "<li>$value</li>";
        
        echo "</ul>";

        ?>
    </body>
</html>
