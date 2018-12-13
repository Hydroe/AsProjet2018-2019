/** Algo.java
* Créer le 8 Décembre 2018
* Fichier de test pour l'aglorithmique de recherche des correspondances de consommation des utilisateurs dans une base de donnée
* Structure de la BDD: Age, Sexe, Film, Id_commande, Contenus_commande
* Modification: Date/Initiales/Choses_modifiées
* 8 Décembre 2018/MT/Création du fichier
* A FAIRE: Lors du résultat, il y a des occurences "," n'impactent pas le résultat mais à l'affichage ca ne rend pas bon.
*/

import java.util.*;

public class Algo {

    public static void main(String[] args) {
    	///////////////////////////////////////////////////////////////////
        //SECTION 0 - Déclaration des variables et constantes 			 //
        ///////////////////////////////////////////////////////////////////
    	String db[][] = {{"30", "M", "Stargate", "1", "Biére,Pizza"},
    					{"28", "F", "Game of thrones", "5", "Frite,Pizza,Coca-cola"},
    					{"24", "M", "Stargate", "2", "Pizza,Coca-cola"},
    					{"16", "M", "Game of thrones", "3", "Wisky,Pizza,Coca-cola"},
    					{"21", "F", "Game of thrones", "4", "Poulet,Pizza"},
    					{"35", "F", "Stargate", "7", "Pizza"},
    					{"28", "F", "Stargate", "6", "Coca-cola,Pizza"},
    					{"31", "F", "Stargate", "8", "Pizza,Coca-cola,Sushi,brownies"},
    					{"54", "M", "Des racines et des ailes", "9", "Sushi,Pierrier,Donuts"}};
    	String[] produitNom = initProduitNom(100);		// Variable ou seront écrit les combinaisons qui sont faite au moins  fois (ex: "Cocal-cola,Pizza")
    	int[] produitNombre = initProduitNombre(100);	// Varaible qui dira combien de fois la combinaisons produitNom[x] est fait pour produitNombre[x]
    	boolean[] bit = {false, false, false, false, false, false, false, false, false, false}; // Variable qui sert d'incrémentation binaire
    	boolean existe; //Permet de montrer si le String est existant dans produitNom ou pas
    	int caseAuDessus = 0;
    	int choixOccurence = nbOccurence();
		///////////////////////////////////////////////////////////////////
        //SECTION 0 - Fin 												 //
        ///////////////////////////////////////////////////////////////////

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
		    	if(produitNombre[i] != 0 && produitNombre[i] >= choixOccurence && !produitNom.equals(",")){
		    		System.out.println("Pour la chaine : " + produitNom[i] + " il y a " + produitNombre[i] + " occurences");
		    	}
		    }
    }
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
    	String[] produitNom = new String[100];
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
    	int[] produitNombre = new int[100];
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
    	return db[nbTuple][4];
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
}