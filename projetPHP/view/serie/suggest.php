<!DOCTYPE html>
<html>
    <head>
        <?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Suggestion d'une série</title>
    </head>
   
    <body>
        <?php require "../view/general/navigation.php";
        echo "<h3> Suggestion d'une série à regarder </h3> ";
        ?>

       
        <form method="post" action="routeur.php?controller=serie&&action=suggest">
            <fieldset>
                <legend></legend>
                    <p>

                        <label for="serie">Quelle série avez-vous regardé ?</label>
                        <SELECT name="nom_serie">
                            <optgroup label="Séries">

                            <?php

                            foreach ($liste_serie as $value)
                                echo "<OPTION>$value</OPTION></br>";

                            ?>

                        </SELECT>
                    </p>
                    <p>
                        <input type="submit" value="Envoyer" />
                    </p>
            </fieldset>
        </form>
        
        <input type='hidden' name='action' value='suggest'>

    </body>
</html> 