<?php
require_once("DetailsNetflix.php");

$st = array('Action',
			'Aventure',
			'Sci-fi');

$data = array(	'serieLength' => 40,
				'serieType' => $st,
				'seriePopularite' => 0);

$dn1 = new DetailsNetflix($data);

$st = array('Policier',
			'Trhiller');

$data = array(	'serieLength' => 15,
				'serieType' => $st,
				'seriePopularite' => 10);

$dn2 = new DetailsNetflix($data);

$st = array('Fantasy',
			'Mediaval',
			'Magie');

$data = array(	'serieLength' => 23,
				'serieType' => $st,
				'seriePopularite' => 3);

$dn3 = new DetailsNetflix($data);

$st = array('Policier',
			'Action',
			'Thriller',
			'Amour',
			'Fantasy');

$data = array(	'serieLength' => 0,
				'serieType' => $st,
				'seriePopularite' => 7);

$dn4 = new DetailsNetflix($data);

print_r($dn1);
echo "<br/>";
print_r($dn2);
echo "<br/>";
print_r($dn3);
echo "<br/>";
print_r($dn4);
echo "<br/>";

echo $dn1->compare($dn2) . " Pour 1 et 2<br/>";
echo $dn1->compare($dn3) . " Pour 1 et 3<br/>";
echo $dn1->compare($dn4) . " Pour 1 et 4<br/>";
echo $dn2->compare($dn1) . " Pour 2 et 1<br/>";
echo $dn2->compare($dn3) . " Pour 2 et 3<br/>";
echo $dn2->compare($dn4) . " Pour 2 et 4<br/>";
echo $dn3->compare($dn1) . " Pour 3 et 1<br/>";
echo $dn3->compare($dn2) . " Pour 3 et 2<br/>";
echo $dn3->compare($dn4) . " Pour 3 et 4<br/>";
echo $dn4->compare($dn1) . " Pour 4 et 1<br/>";
echo $dn4->compare($dn2) . " Pour 4 et 2<br/>";
echo $dn4->compare($dn3) . " Pour 4 et 3<br/>";
echo $dn3->compare($dn3) . " Pour voir la meilleur note possible<br/>";