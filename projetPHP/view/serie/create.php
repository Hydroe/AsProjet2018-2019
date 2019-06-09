<!DOCTYPE html>
<html>
    <head>
        <?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Ajouter une série</title>
    </head>
   
    <body>
        <?php require "../view/general/navigation.php";
        echo "<h3> Ajouter une série </h3> ";
        ?>

       
        <form method="post" action="routeur.php?controller=serie&&action=created">
            <fieldset>
                <legend><b></b></legend>
                <p>
                    <label for="nom_serie">Nom de la série</label> :
                    <input type="text" placeholder="Ex : Adibou" name="nom_serie" id="nom_serie" required/>
                    </br>
                    <label for="duree">Durée </label> :
                    <input type="text" placeholder="Ex : 40" name="duree" id="duree" required/> (en minutes)
                    </br>
                    <label for="nom_genre"> Genre de la série</label> :
                    <SELECT name="nom_genre">
                            <optgroup label="Genres">
                            <?php
                            
                            foreach ($liste_genre as $value)
                                echo "<OPTION>$value</OPTION></br>";
                            
                            ?>
                            
                    </SELECT>
                </p>
                <p>
                    <input type="submit" value="Valider" />
                </p>
            </fieldset> 
        </form>
        
        <input type='hidden' name='action' value='created'>
        
        

    </body>
</html> 