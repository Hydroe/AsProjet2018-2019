<!DOCTYPE html>
<html>
    <head>
        <?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Formulaire Série</title>
    </head>
   
    <body>
        <?php require "../view/general/navigation.php";
        echo "<h3> Chercher une série </h3> ";
        ?>

       
        <form method="post" action="routeur.php?controller=serie&&action=search">
            <fieldset>
                <legend></legend>
                    <p>

                        <label for="trancheAge">Sélectionnez une tranche d'âge :</label>
                        <SELECT name="tranche">
                            <optgroup label="Tranches d'âge">

                            <?php

                            foreach ($liste_tranche as $value)
                                echo "<OPTION>$value</OPTION></br>";

                            ?>

                        </SELECT>
                        </br>
                        <label for="produit">Sélectionnez un produit :</label>
                        <SELECT name="nom_produit">
                            <optgroup label="Produits">
                            <?php

                            foreach ($liste_produit as $value)
                                echo "<OPTION>$value</OPTION></br>";

                            ?>
                            
                        </SELECT>
                        </br>
                    </p>
                    <p>
                        <input type="submit" value="Envoyer" />
                    </p>
            </fieldset>
        </form>
        
        <input type='hidden' name='action' value='search'>

    </body>
</html> 