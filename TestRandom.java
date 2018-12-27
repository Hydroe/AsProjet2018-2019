/** TestRandom.java
* Créer le 26 Décembre 2018
* Fichier de test afin de voir un possible création de données en fonction de critère. 
* On veut un aléatoir controlé (par exemple pour l'age on veut plus de jeune mais avec un age aléatoire).
* Modification: Date/Initiales/Choses_modifiées
* 26 Décembre 2018/MT/Création du fichier
* A FAIRE:
*/

import java.util.Random;

public class TestRandom {


    public static void main(String[] args) {
    	int val;
    	String bd[][] = new String[100][5];
    	for(int i = 0; i < bd.length; i++){
    		val = TrancheAge(RandomGene(0, 100));
    		bd[i][0] = String.valueOf(i);
    		bd[i][1] = String.valueOf(RandomGene(AgeMin(val), AgeMax(val)));
    		bd[i][2] = (RandomGene(0, 2) == 1) ? "F" : "M";
    		bd[i][3] = RandomSerie();
    		bd[i][4] = RandomFood();
    	}
    	for(int i = 1; i < bd.length; i++){
    		System.out.println("{" + bd[i][0] + "/ " + bd[i][1] + "/ " + bd[i][2] + "/ " + bd[i][3] + "/ " + bd[i][4] + "}");
    	}
    }

    /** Fonction qui retourne en fonction d'une valeur maximale (val) de 0 à val.
    * @param integer val
    * @return integer
    */
    public static int RandomGene(int min, int max){
		Random rand = new Random();
		int random;
		random = rand.nextInt(max - min);
		return max - random;
	}

	/** Fonction qui renvois en fonction de val, une autre valeur: 1 pour 85 ou moins, 2 pour 86 à 95, 3 pour 95 et plus
	* @param integer val
	* @return integer
	*/
	public static int TrancheAge(int val){
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
	public static int AgeMin(int val){
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
	public static int AgeMax(int val){
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

	/** Fonction qui choisi de manière aléatoire une série
	* @return String
	*/
	public static String RandomSerie(){
		String retour;
		int val = RandomGene(0, 3);
		switch(val){

			case 1:
			retour = "Stargate";
			break;

			case 2:
			retour = "Game of thrones";
			break;

			case 3:
			retour = "Breaking bad";
			break;

			default:
			retour = "Erreur";
		}
		return retour;
	}

	public static String RandomFood(){
		String[] food = {"Bière", "Pizza", "Steak végan", "Pommes de terres dauphines", "Eau plate", "Viande BBQ", "Religieuse au chocolat", "Frittes", "Coca-cola", "Wisky", "Poulet", "Sushi", "Brownies", "Perrier", "Donuts"};
		String retour = "";
		int[] choix = {-1,-1,-1,-1,-1};
		int valNbFood = RandomGene(0, 5);
		int valFood;
		for(int i = 0; i < valNbFood; i++){
			valFood = RandomGene(0, food.length - 1);
			while(valFood == choix[0] || valFood == choix[1] || valFood == choix[2] || valFood == choix[3]){
				valFood = RandomGene(0, food.length - 1);
			}
			retour = retour + food[valFood] + ",";
			choix[i] = valFood;
		}
		valFood = RandomGene(0, food.length - 1);
			while(valFood == choix[0] || valFood == choix[1] || valFood == choix[2] || valFood == choix[3]){
				valFood = RandomGene(0, food.length - 1);
			}
		retour = retour + food[valFood];
		return retour;
	}
}