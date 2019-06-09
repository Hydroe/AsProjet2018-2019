<?php

require_once 'ControllerProduit.php';
require_once 'ControllerProfil.php';
require_once 'ControllerSerie.php';

$controller = $_GET['controller'];

switch ($controller) {
    
    case "produit":
        $action = $_GET['action'];
        ControllerProduit::$action();
        break;
    case "profil":
        $action = $_GET['action'];
        ControllerProfil::$action();
        break;
    case "serie":
        $action = $_GET['action'];
        ControllerSerie::$action();
        break;
    case "accueil":
        require ('../view/general/accueil.php');
        break;
}

?>