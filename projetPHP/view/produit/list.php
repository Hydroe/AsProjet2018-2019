<!DOCTYPE html>

<html>
    <head>
    	<?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Liste des produits</title>
    </head>
    <body>
    	<?php require "../view/general/navigation.php"; ?>
        <?php

        echo "<h3> Liste des produits disponibles </h3> ";

        echo "<ul>";

        foreach ($tab_p as $value)
        	echo "<li>$value</li>";

        echo "</ul>";

        ?>
    </body>
</html>
