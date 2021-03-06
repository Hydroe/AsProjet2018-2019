<?php

require_once "../config/Conf.php";

class Model {

	public static $pdo;

	public static function Init() {
		$login = Conf::getLogin();
		$password = Conf::getPassword();
		$hostname = Conf::getHostname();
		$database_name = Conf::getDatabase();

		try {
			self::$pdo = new PDO("mysql:host=$hostname;dbname=$database_name",$login,$password,array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
			//self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		} catch (PDOException $e) {
			if (Conf::getDebug()) {
				echo $e->getMessage();
			}
			else {
				echo 'Une erreur est survenue <a href="https://www.google.fr"> retour sur Google </a>';
			}
			
			die();
		}
	}
}

Model::Init();

?>