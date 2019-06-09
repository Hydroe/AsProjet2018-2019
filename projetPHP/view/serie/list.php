<!DOCTYPE html>

<html>
    <head>
    	<?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Liste des séries</title>
    </head>
    <body>
    	<?php require "../view/general/navigation.php"; ?>
        <?php

        echo "<h3> Liste des séries disponibles </h3> ";

        echo "<ul>";

        foreach ($tab_serie as $value) {
            $id_serie = ModelSerie::getIdByName($value);            
            echo '<li> <a href="routeur.php?controller=serie&&action=read&&id_serie='.$id_serie.'"> '.$value.' </li> ';
        }

        echo "</ul>";

        ?>
    </body>
</html>
