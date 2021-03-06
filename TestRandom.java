/** TestRandom.java
* Créer le 26 Décembre 2018
* Fichier de test afin de voir un possible création de données en fonction de critère. 
* On veut un aléatoir controlé (par exemple pour l'age on veut plus de jeune mais avec un age aléatoire).
* Modification: Date/Initiales/Choses_modifiées
* 26 Décembre 2018/MT/Création du fichier création des fonctions: main, randomGene, trancheAge, ageMin, ageMax, randomSerie, randomFood
* 27 Décembre 2018/MT/Modification de randomSerie (ajout du type de la série), ajout randomDuree
* A FAIRE: Créer une fonction pour choisir une région aléatoire (es-ce vraiment nécessaire ?)
*/

import java.util.Random;

public class TestRandom {


    public static void main(String[] args) {
    	int val;
    	String bd[][] = new String[100][7];
    	for(int i = 0; i < bd.length; i++){
    		String[] str = randomSerie().split(",");
    		val = trancheAge(randomGene(0, 100));
    		bd[i][0] = str[1];													// Type de la série
    		bd[i][1] = str[0];													// Nom de la série
    		bd[i][2] = randomDuree();											// Durée de la série
    		bd[i][3] = String.valueOf(i);										// Numéro ID du client
    		bd[i][4] = String.valueOf(randomGene(ageMin(val), ageMax(val)));	// Age aléatoire
    		bd[i][5] = (randomGene(0, 2) == 1) ? "F" : "M";						// Sexe de la personne			
    		bd[i][6] = randomFood();											// Liste des achats
    	}
    	for(int i = 1; i < bd.length; i++){
    		System.out.println("{" + bd[i][0] + "/" + bd[i][1] + "/" + bd[i][2] + "/" + bd[i][3] + "/" + bd[i][4] + "/" + bd[i][5] +  "/" + bd[i][6] + "}");
    	}
    }

    /** Fonction qui retourne en fonction d'une valeur maximale (val) de 0 à val.
    * @param integer val
    * @return integer
    */
    public static int randomGene(int min, int max){
		Random rand = new Random();
		int random;
		random = rand.nextInt(max - min);
		return max - random;
	}

	/** Fonction qui renvois en fonction de val, une autre valeur: 1 pour 85 ou moins, 2 pour 86 à 95, 3 pour 95 et plus
	* @param integer val
	* @return integer
	*/
	public static int trancheAge(int val){
		int retour;
		if(val <= 85){
			retour = 1;
		}else if(val > 85 && val <= 95){
			retour = 2;
		}
		else{
			retour = 3;
		}
		return retour;
	}

	/** Fonction qui retourne l'age minimale en fonction de la catégorie choisie (1 ou 2 ou 3)
	* @param integer val
	* @return integer
	*/
	public static int ageMin(int val){
		int retour = 0;
		switch(val){

			case 1:
			retour = 18;
			break;

			case 2:
			retour = 30;
			break;

			case 3:
			retour = 50;
			break;

			default:
			System.out.println("Erreur dans la fonction AgeMin");
		}
		return retour;
	}

	/** Fonction qui retourne l'age maximal en fonction de la catégorie choisie (1 ou 2 ou 3)
	* @param integer val
	* @return integer
	*/
	public static int ageMax(int val){
		int retour = 0;
		switch(val){

			case 1:
			retour = 29;
			break;

			case 2:
			retour = 49;
			break;

			case 3:
			retour = 70;
			break;

			default:
			System.out.println("Erreur dans la fonction AgeMax");
		}
		return retour;
	}

	/** Fonction qui choisi de manière aléatoire une série et son type
	* @return String
	*/
	public static String randomSerie(){
		String retour;
		String[] scienceFiction = {"Stargate", "Docteur Who", "The Walking Dead", "Black Mirror"};
		String[] fantasy = {"Game of thrones", "Outlander", "Once upon a time", "Supernatural", "Lucifer"};
		String[] thriller = {"Breaking bad", "La casa de papel", "Gotham", "Prison Break"};
		int val = randomGene(0, 3);
		switch(val){

			case 1:
			retour = scienceFiction[randomGene(0, scienceFiction.length - 1)] + "," + "ScienceFiction";
			break;

			case 2:
			retour = fantasy[randomGene(0, fantasy.length - 1)] + "," + "Fantasy";
			break;

			case 3:
			retour = thriller[randomGene(0, thriller.length - 1)] + "," + "Thriller";
			break;

			default:
			retour = "Erreur";
		}
		return retour;
	}

	/** Fonction qui choisi de manière aléatoire une durée de la série
	* @return String
	*/
	public static String randomDuree(){
		String retour;
		int val = randomGene(0, 3);
		switch(val){

			case 1:
			retour = "Short";
			break;

			case 2:
			retour = "Medium";
			break;

			case 3:
			retour = "Long";
			break;

			default:
			retour = "Erreur";
		}
		return retour;
	}

	/** Fonction qui fait le choix aléatoire du nombre d'élements que le client achette (entre 1 et 5 compris). Puis il choisi ce qu'il prend de manière unique (2 même food ne peuvent être présent 2 fois dans la même commande)
	* @return String
	*/
	public static String randomFood(){
		String[] food = {"Bière", "Pizza", "Steak végan", "Pommes de terres dauphines", "Eau plate", "Viande BBQ", "Religieuse au chocolat", "Frittes", "Coca-cola", "Wisky", "Poulet", "Sushi", "Brownies", "Perrier", "Donuts"};
		String retour = "";
		int[] choix = {-1,-1,-1,-1,-1};
		int valNbFood = randomGene(0, 5);
		int valFood;
		for(int i = 0; i < valNbFood; i++){
			valFood = randomGene(0, food.length - 1);
			while(valFood == choix[0] || valFood == choix[1] || valFood == choix[2] || valFood == choix[3]){
				valFood =randomGene(0, food.length - 1);
			}
			retour = retour + food[valFood] + ",";
			choix[i] = valFood;
		}
		valFood = randomGene(0, food.length - 1);
			while(valFood == choix[0] || valFood == choix[1] || valFood == choix[2] || valFood == choix[3]){
				valFood = randomGene(0, food.length - 1);
			}
		retour = retour + food[valFood];
		return retour;
	}
}