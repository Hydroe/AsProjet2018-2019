/** Algo.java
* Créer le 28 Décembre 2018
* Fichier qui utilise Algo.java, TestRandom.java et Informations.java en liens tous les trois.
* Structure de la BDD: Type de la série, Nom de la série, Durée d'un épisode, Id de la commande, Age, Sexe, Contenus de la commande
* Modification: Date/Initiales/Choses_modifiées
* 28 Décembre 2018/MT/Création du fichier, mise en place des sections 0, 1, 2 et 3.
* A FAIRE: 	Lors du résultat, il y a des occurences "," n'impactent pas le résultat mais à l'affichage ca ne rend pas bon.
			Créer une fonction pour choisir une région aléatoire (es-ce vraiment nécessaire ?).
*/

///////////////////////////////////////////////////////////////////
//SECTION 0 - Déclaration des bibliothèque utilise 	 			 //
///////////////////////////////////////////////////////////////////
import java.util.*;
import java.util.Random;
import java.util.Arrays;
///////////////////////////////////////////////////////////////////
//SECTION 0 - Fin 												 //
///////////////////////////////////////////////////////////////////



public class AlgoPlus {
///////////////////////////////////////////////////////////////////
//SECTION 1 - main 												 //
///////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
    	String[][] db = initBdd(50); //Création de la Bdd;
    	for(int i = 0; i < db.length; i++){
			System.out.println("{" + db[i][0] + "/" + db[i][1] + "/" + db[i][2] + "/" + db[i][3] + "/" + db[i][4] + "/" + db[i][5] +  "/" + db[i][6] + "}");
		}
    	//Déclaration des constantes et variables
    	String[] produitNom = initProduitNom(1000);														// Variable ou seront écrit les combinaisons qui sont faite au moins  fois (ex: "Cocal-cola,Pizza")
    	int[] produitNombre = initProduitNombre(1000);													// Varaible qui dira combien de fois la combinaisons produitNom[x] est fait pour produitNombre[x]
    	boolean[] bit = {false, false, false, false, false, false, false, false, false, false}; 		// Variable qui sert d'incrémentation binaire
    	boolean existe; 																				//Permet de montrer si le String est existant dans produitNom ou pas
    	int caseAuDessus = 0; 																			//En gros permet d'avoir la taille du tableau
    	int choixOccurence = nbOccurence(); 															// Variable pour stocker le nombre d'occurence minimumales que l'ont veut saisir.
    	//Fin de la déclaration des constantes et variables

    	//Tris par occurences
    	for(int h = 0;  h < db.length; h++){
    		String strDeDb = produitDeLaTuple(h, db);
    		int longStrDeDb = (int)strDeDb.split(",").length;
    	
	        boolean[] bit2 = bit;
	    	for (int j = 0; j < (int)Math.pow(2,longStrDeDb) ; j++) {
	    		bit2 = incrementationBinaire(bit2, longStrDeDb - 1, j);
		    	if(deuxBitEtatTrue(bit2) > 0){
		    		existe = false;
		    		for(int k = 0; k < produitNombre.length; k++){
		    			if(produitNom[k].equals(ordreAlphabetique(uneDesPossibilite(bit2, strDeDb)))){
		    				existe = true;
		    				produitNombre[k]++;
		    			}
		    		}
		    		if(existe == false){
		    			produitNom[caseAuDessus] = ordreAlphabetique(uneDesPossibilite(bit2, strDeDb));
		    			produitNombre[caseAuDessus] = 1;
		    			caseAuDessus++;
		    		}
		    		existe = false;
		    	}
		    }
		}
		for(int i = 0; i < produitNombre.length; i++){
		   	if(produitNombre[i] != 0 && produitNombre[i] >= choixOccurence && produitNom.equals(",") == false){
		   		System.out.println("Pour la chaine : " + produitNom[i] + " il y a " + produitNombre[i] + " occurences");
		   	}
		}
		System.out.println("Il y a en tout " + caseAuDessus + " Possibilités");
		//Fin du tris par occurence
		System.out.println(informations(db,1 ,4,"Moyenne" , "Stargate"));
		System.out.println(informations(db,1 ,4,"Médiane" , "Stargate"));
		System.out.println(informations(db,1 ,4,"Pourcentage" , "Stargate"));
	}
///////////////////////////////////////////////////////////////////
//SECTION 1 - Fin 												 //
///////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////
//SECTION 2 - Génération de la base de donnée					 //
///////////////////////////////////////////////////////////////////
	/** Fonction qui permet l'initialisation aléatoire d'une base de donnée (spécifique aux choix de notre projet).
	* @param integer nb
	* @return String[][]
	*/
    public static String[][] initBdd(int nb){
    	int val;
    	String bd[][] = new String[nb][7];
    	for(int i = 0; i < bd.length; i++){
    		String[] str = randomSerie().split(",");
    		val = trancheAge(randomGene(0, 100));
    		bd[i][0] = str[1];													// Type de la série
    		bd[i][1] = str[0];													// Nom de la série
    		bd[i][2] = randomDuree();											// Durée de la série
    		bd[i][3] = String.valueOf(i);										// Numéro ID de la commande
    		bd[i][4] = String.valueOf(randomGene(ageMin(val), ageMax(val)));	// Age aléatoire
    		bd[i][5] = (randomGene(0, 2) == 1) ? "F" : "M";						// Sexe de la personne			
    		bd[i][6] = randomFood();											// Liste des achats
    	}
    	return bd;
    }

    /** Fonction qui retourne un nombre aléatoire en fonction d'un maximum et d'un minimum choisi.
    * Exemple pour min = 0 et max = 3 renvois soit 1, 2 ou 3
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
	* Cette fonction sert à avoir dans l'ordre décroissant un nombre de personne par tranche afin que nombre de persoone dans la tranche 1 > 2 > 3
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
	* Cette fonction permet la modification des ages minimaux et du nombre de catégorie plus facilement
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
	* Cette fonction permet la modification des ages maximaux et du nombre de catégorie plus facilement
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
	* Pour l'ajout de série, il suffi de l'ajouter
	* Pour l'ajout de Type, ajouter un tableau et agrandir la valeur max pour randomGene et d'ajouter la possibiltié dans un case
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
			retour = scienceFiction[randomGene(0, scienceFiction.length) - 1] + "," + "ScienceFiction";
			break;

			case 2:
			retour = fantasy[randomGene(0, fantasy.length) - 1] + "," + "Fantasy";
			break;

			case 3:
			retour = thriller[randomGene(0, thriller.length) - 1] + "," + "Thriller";
			break;

			default:
			retour = "Erreur";
		}
		return retour;
	}

	/** Fonction qui choisi de manière aléatoire une durée de la série
	* Short +- 20 minutes, Medium +- 45 Minutes, Long +- 90 minutes ou plus
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
///////////////////////////////////////////////////////////////////
//SECTION 2 - Fin 												 //
///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////
//SECTION 3 - Tris des occurences possibles des achats clients	 //
///////////////////////////////////////////////////////////////////

	/** Cette fonction retourne un nombre entier stictement positif choisi par l'utilisateur qui symbolise le nombre d'occurence minimal qu'il veux afficher
	* @return integer 
	* ETAT = Vérifiée
	*/
    public static int nbOccurence(){
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Veuillez saisir le nombre d'occurence minimum que vous souhaitez");
    	return sc.nextInt();

    /** Permet d'initialiser la variable produitNom pour toutes les cas à "VIDE"
    * @param int val (taille du tableau)
    * @return String[] Tableau de string initialisé
    * ETAT = Vérifiée
    */
    }
    public static String[] initProduitNom(int val){
    	String[] produitNom = new String[val];
    	for(int i = 0; i < val; i++){
    		produitNom[i] = "VIDE";
    	}
    	return produitNom;
    }

   	/** Permet d'initialiser la variable produitNom pour toutes les cas à 
    * @param int val (taille du tableau)
    * @return int Tableau d'entier initialisés
    * ETAT = Vérifiée
    */
    public static int[] initProduitNombre(int val){
    	int[] produitNombre = new int[val];
    	for(int i = 0; i < val; i++){
    		produitNombre[i] = 0;
    	}
    	return produitNombre;
    }

    /** Renvois un String contenant la liste des produits
    * @param integer nbTuples
    * @param String[][] db
    * @return String 
    * ETAT = Vérifiée
    */
    public static String produitDeLaTuple(int nbTuple, String[][] db){
    	return db[nbTuple][6];
    }

    /** Retourne la valeur binaire au dessus
    * @param boolean[] bit Valeur actuelle de la variable bit[]
    * @param integer nbPuissance Le nombre de produits dans le String choisi
    * @return boolean[] nouvelle variable bit[] incrémentée de 1
    * ETAT = A vérifier, il faudrait qu'il commence à all false tout le temps. Problème de l'affichage d'une ligne ",", résolu à l'affichage mais pas très bien.
    */
    public static boolean[] incrementationBinaire(boolean[] bit, int nbPuissance, int numero){
    	boolean[] bit2 = bit;
    	for(int i = nbPuissance; i >= 0 ; i--){
    		if(numero % (int)Math.pow(2,i) == 0 && numero != 0){
    			bit2[nbPuissance - i] = !bit2[nbPuissance - i];
    		}
    	}
		return bit2;
    }

    /** Permet de savoir s'il y a uen combinaison possible
    *@param boolean[] bit True si c'est une possibilité
    *@return integer compteur nombre de produits sélectionnés
    * ETAT = Vérifiée
    */
    public static int deuxBitEtatTrue(boolean[] bit){
    	int compteur = 0;
    	for(int i = 0; i < bit.length; i++){
    		if(bit[i]){
    			compteur++;
    		}
    	}
    	return compteur;
    }
	/** Retourne une chaine de caractere avec les produits, ceci correspond à une possibilitée
	*@param boolean bit, permet de tester toutes les possibilités
	*@param String str, chaine de caractère avec les produits
	*@return String une chaine de caractère contenant une possibilité
	* ETAT = Vérifiée
	*/
    public static String uneDesPossibilite(boolean[] bit, String str){
    	String resultat = "";
    	String[] strSplit = str.split(",");
    	for(int i = 0; i < strSplit.length; i++){
    		if(bit[i]){
    			resultat = resultat + strSplit[i] + ",";
    		}
    	}
    	resultat = (resultat.lastIndexOf(",") > 0) ? (resultat.substring(0, resultat.lastIndexOf(","))) : resultat;	//Suppression de la dernière virgule
    	return resultat;
    }

    /** Permet de retouner la chaine de caractère triée dans l'ordre alphabétique
    *@param String str chaine de caractère à trier
    *@return String retourne, chaine de caractère dans l'ordre alphabétique
    * ETAT = Vérifiée
    */
    public static String ordreAlphabetique(String str){
    	String retourne = "";
    	int z = 0;
		int y = 0;
		boolean plusPetit = false;
		boolean place = false;
		boolean ok = false;
		String chaineDuDebut = str;
		String[] chaineDeDebutDecoupee;
		chaineDeDebutDecoupee = chaineDuDebut.split(",");
		String[] tabDansOrdre = new String[chaineDeDebutDecoupee.length];
		tabDansOrdre[0] = chaineDeDebutDecoupee[0];
		/*Pour les autres mots présents  dans la chaine du début qui sont encore à trier*/
		for(int q = 1; q < chaineDeDebutDecoupee.length; q++){
		  	y = 0;
		   	place = false;
		   	/*Tant qu'il reste le mot à placer et autant de fois qu'il y à de valeurs dans résultat*/
		   	while(y < q && place == false){
		   		z = 0;
		   		plusPetit = false;
		   		ok = false;
		   		char[] motAAjouterDecoupe = chaineDeDebutDecoupee[q].toCharArray();	// Avoir le mot à ajouter caractère par caractère
		   		char[] motAComparerDecoupe = tabDansOrdre[y].toCharArray();			// Avoir le mot à comparer caractère par caractère
		   		/*Tant que z est inférieur au nombre lettre du mot le plus petit*/
		   		while((z < ((chaineDeDebutDecoupee[q].length() > tabDansOrdre[y].length()) ? tabDansOrdre[y].length() : chaineDeDebutDecoupee[q].length())) && ok == false){
		   			/*Si la lettre du mot à ajouter est avant(par ordre alphabétique) que la lettre du mot comparé*/
		   			if((int)motAAjouterDecoupe[z] < (int)motAComparerDecoupe[z]){
		   				ok = true;
		   				plusPetit = true;
					}
					else if((int)motAAjouterDecoupe[z] > (int)motAComparerDecoupe[z]){
						ok = true;
					}
					z++;
		   		}
		   		if(ok == false && motAAjouterDecoupe.length < motAComparerDecoupe.length){
			        ok = true;
			        plusPetit = true;
			    }
			    /*Si le mot à placer est plus petit que le mot comparé*/
			    if(plusPetit == true){
			    	/*Décaler les case vers la droite à partir de la fin jusqu'à la case "y" comprise et y ajouter le mot à placer à la case "y"*/
			    	for(int k = tabDansOrdre.length - 1; k > y; k--){
			    		tabDansOrdre[k] = tabDansOrdre[k - 1];
			    	}
			    	tabDansOrdre[y] = chaineDeDebutDecoupee[q];
			    	place = true;
			    }
			    /*Si le mot à placer est plus grand que le mot comparé*/
			    else{
			    	tabDansOrdre[q] = chaineDeDebutDecoupee[q];
			    }
			    y++;
		   	}
		}
		for(int i = 0; i < tabDansOrdre.length; i++){
			retourne = retourne + tabDansOrdre[i] + ",";
	    }
	    retourne = (retourne.lastIndexOf(",") > 0) ? (retourne.substring(0, retourne.lastIndexOf(","))) : retourne;	//Suppression de la dernière virgule
	    return retourne;
    }
///////////////////////////////////////////////////////////////////
//SECTION 3 - Fin 												 //
///////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////
//SECTION 4 - Informations utiles que l'on peut en tirer		 //
///////////////////////////////////////////////////////////////////

    /** Fonction qui permet à l'utilisateur d'avoir des informations plus claires quant à ce qu'il recherche.
    * @param String[][] db
    * @param String pourCetteVal
    * @param String jeVeuxCetteVal
    * @param String deTelleManiere
    * @param String str
    * @return String
    */
    public static String informations(String[][] db, int pourCetteVal, int jeVeuxCetteVal, String deTelleManiere, String str){
    	String retour = "";
    	switch(deTelleManiere){

    		case "Moyenne":
    		retour = moyenne(db, pourCetteVal, jeVeuxCetteVal, str);
    		break;

    		case "Médiane":
			retour = mediane(db, pourCetteVal, jeVeuxCetteVal, str);
    		break;

    		case "Pourcentage":
    		retour = pourcentage(db, pourCetteVal, str);
    		break;

    		case "Informations détaillées":
			retour = details(db, str);
    		break;

    		/*case "Le moins":

    		break;*/

    		default:
    		retour = "erreur";
    	}
    	return retour;
    }

    /** Fonction d'information qui renvois un message indiquant une moyenne
    * @param String[][] db
    * @param integer pourCetteVal
    * @param integer jeVeuxCetteVal
    * @param String str
    * @return String
    */
    public static String moyenne(String[][] db, int pourCetteVal, int jeVeuxCetteVal, String str){
    	String retour;
    	float moyenne = 0;
		int cpt = 0;
		for(int i = 0; i < db.length; i++){
			if(db[i][pourCetteVal].equals(str)){
				moyenne = moyenne + Integer.parseInt(db[i][jeVeuxCetteVal]);
				cpt++;
			}
		}
		retour = Float.toString(moyenne / cpt);
		return "Pour vos choix la moyenne est de " + retour;
    }

    /** Fonction d'information qui renvois un message indiquant une médiane
    * @param String[][] db
    * @param integer pourCetteVal
    * @param integer jeVeuxCetteVal
    * @param String str
    * @return String
    */
    public static String mediane(String[][] db, int pourCetteVal, int jeVeuxCetteVal, String str){
    	String retour;
    	int cpt = 0;
		int cptTaille = 0;
		for(int i = 0; i < db.length; i++){
			if(db[i][pourCetteVal].equals(str)){
				cptTaille++;
			}
		}
		int[] tris = new int[cptTaille];
		for(int i = 0; i < cptTaille; i++){
			tris[i] = 0;
		}
		for(int i = 0; i < db.length; i++){
			if(db[i][pourCetteVal].equals(str)){
				tris[cpt] = Integer.parseInt(db[i][jeVeuxCetteVal]);
				cpt++;
			}
		}
		Arrays.sort(tris);
		retour = Integer.toString(tris[tris.length/2]);
		return "Pour vos choix la médiane est de " + retour;
    }

    /** Fonction d'information qui renvois un message indiquant un pourcentage
    * @param String[][] db
    * @param integer pourCetteVal
    * @param String str
    * @return String
    */
    public static String pourcentage(String[][] db, int pourCetteVal, String str){
    	String retour;
    	float cpt = 0;
    	for(int i = 0; i < db.length; i++){
			if(db[i][pourCetteVal].equals(str)){
				cpt++;
			}
		}
		retour = Float.toString(cpt * 100 / db.length);
		return "Pour vos choix, la moyenne des personnes l'ayant pris est d'environs " + retour + " pourcent.";
    }

    /** Fonction qui renvois un rapport détaillé en fonction de ce que demande l'utilisateur
    * @param String[][]db
    * @param String str
    * @return String
    */
    public static String details(String[][] db, String str){
    	return "En attente";
    }

///////////////////////////////////////////////////////////////////
//SECTION 4 - Fin 												 //
///////////////////////////////////////////////////////////////////
}


