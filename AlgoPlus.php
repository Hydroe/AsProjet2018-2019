<?php
/** Fonction qui permet de choisir une série dans la liste de manière aléatoire
*/
function randomSerie(){
	$seriesList = array();
	$seriesList["scienceFiction"] 	= array('Stargate',
										'Doctor who',
										'The walking dead',
										'Black mirror');
	$seriesList["fantasy"] 			= array('Game of thrones',
										'Outlander',
										'Once upon a time',
										'Supernatural',
										'Lucifer');
	$seriesList["thriller"]			= array('Breaking bad',
										'La casa de papel',
										'Gotham',
										'Prison break');
	$randomTypeSerie = rand(0, sizeof($seriesList) - 1); // Choix du Type de série
	$i =  0;
	foreach ($seriesList as $value) {
		if ($i == $randomTypeSerie) {
			$randomNomSerie = rand(0, sizeof($value) - 1); // Choix de la série
			return $value[$randomNomSerie];
		}
		$i++;
	}
}

function randomLength(){
	$lengthList = array('Short',
					'Medium',
					'Long');
	$randomLength = rand(0, sizeof($lengthList) - 1);
	$serieLength = $lengthList[$randomLength];
	return $serieLength;
}

function randomFood(){
	$FoodMaxPerCommand = 5;
	$retour = "";
	$foodList = array(	'Bière',				'Pizza',		'Vegan Steak',
						'Potato',				'Mineral Water','Barbecue meat',
						'chocolate religieuse',	'Fries',		'Coca-cola',
						'Wishy',				'Chiken',		'Sushi',
						'Brownies',				'Perrier',		"Donut");
	$command = array(	NULL, NULL, NULL, NULL, NULL);
	$numberOfFoodTaken = rand(1, $FoodMaxPerCommand);

	for ($i=0; $i < $numberOfFoodTaken; $i++) { 
		do {
			$valTabFood = rand(0, sizeof($foodList) - 1);
		} while (in_array($valTabFood, $command));
		$retour = $retour . $foodList[$valTabFood] . (($i == $numberOfFoodTaken - 1) ? "" : ",");
		$command[$i] = $valTabFood;
	}
	return $retour;
}

echo randomSerie();
echo "<br/>";
echo randomLength();
echo "<br/>";
echo randomFood();