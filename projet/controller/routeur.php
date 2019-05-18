<?php

require_once 'ControllerProduit.php';
require_once 'ControllerProfil.php';
require_once 'ControllerSerie.php';

$controller = $_GET['controller'];
$action = $_GET['action'];

switch ($controller) {
    
    case "produit":
        ControllerProduit::$action();
        break;
    case "profil":
        ControllerProfil::$action();
        break;
    case "serie":
        ControllerSerie::$action();
        break;
    case "accueil":
        break;
}

?>