<!DOCTYPE html>
<html>
    <head>
        <?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Participer à l'étude</title>
    </head>
   
    <body>
        <?php require "../view/general/navigation.php";
        echo "<h3> Participer à l'étude </h3> ";
        ?>

       
        <form method="post" action="routeur.php?controller=profil&&action=created">
            <fieldset>
                <legend><b></b></legend>
                <p>
                    <label for="nom">Nom </label> :
                    <input type="text" placeholder="Ex : Milos" name="nom" id="nom" required/>
                    </br>
                    <label for="prenom">Prénom </label> :
                    <input type="text" placeholder="Ex : Ricardo" name="prenom" id="prenom" required/>
                    </br>
                    <label for="age">Âge </label> :
                    <input type="text" placeholder="Ex : 23" name="age" id="age" required/>
                    </br>
                    <label for="serie">Série regardée </label> :
                    <SELECT name="nom_serie">
                            <optgroup label="Séries">
                            <?php

                            foreach ($liste_serie as $value)
                                echo "<OPTION>$value</OPTION></br>";

                            ?>
                            
                    </SELECT>
                    </br>
                    <label for="produit">Produit consommé </label> :
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
                    <input type="submit" value="Valider" />
                </p>
            </fieldset> 
        </form>
        
        <input type='hidden' name='action' value='created'>

    </body>
</html> 