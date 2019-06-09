<!DOCTYPE html>
<html>
    <head>
        <?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Formulaire profil</title>
    </head>
   
    <body>
        <?php require "../view/general/navigation.php";
        echo "<h3> Chercher un profil </h3> ";
        ?>

       
        <form method="post" action="routeur.php?controller=profil&&action=search">
            <fieldset>
                <legend></legend>
                    <p>
                        <label for="produit">Sélectionnez un produit :</label>
                        <SELECT name="nom_produit">
                            <optgroup label="Produits">

                            <?php

                            foreach ($liste_produit as $value)
                                echo "<OPTION>$value</OPTION></br>";

                            ?>

                        </SELECT>
                        </br>
                        <label for="serie">Sélectionnez une série :</label>
                        <SELECT name="nom_serie">
                            <optgroup label="Séries">
                            <?php

                            foreach ($liste_serie as $value)
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