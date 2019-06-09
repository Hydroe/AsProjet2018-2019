<!DOCTYPE html>
<html>
    <head>
        <?php require_once "../view/general/inclusions.php"; ?>
        <meta charset="UTF-8">
        <title>Ajouter un produit</title>
    </head>
   
    <body>
        <?php require "../view/general/navigation.php";
        echo "<h3> Ajouter un produit </h3> ";
        ?>

       
        <form method="post" action="routeur.php?controller=produit&&action=created">
            <fieldset>
                <legend><b></b></legend>
                <p>
                    <label for="login">Nom du produit </label> :
                    <input type="text" placeholder="Ex : Chocapic" name="produit" id="produit" required/>
                </p>
                <p>
                    <input type="submit" value="Valider" />
                </p>
            </fieldset> 
        </form>
        
        <input type='hidden' name='action' value='created'>

    </body>
</html> 