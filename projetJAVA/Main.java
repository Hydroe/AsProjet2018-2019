import java.util.*;
import java.lang.*;

class Main {

    public static void main(String[] args) {

	Ut.clearConsole();
	Scanner sc = new Scanner(System.in);
	System.out.print("Nombre de profils : ");
	int n=sc.nextInt();

	String temp;

		
	Netflix n1 = new Netflix("Vikings");
	Netflix n2 = new Netflix("Person of Interest");
	Netflix n3 = new Netflix("Docteur House");
	Netflix n4 = new Netflix("Game of Thrones");
	Netflix n5 = new Netflix("Breaking Bad");
	Netflix n6 = new Netflix("La Casa de Papel");
	Netflix n7 = new Netflix("Black Mirror");
	Netflix n8 = new Netflix("Walking Dead");
	Netflix n9 = new Netflix("Kaamelott");
	Netflix n10 = new Netflix("Family Guy");
	Netflix n11 = new Netflix("The Big Bang Theory");
	Netflix n12 = new Netflix("The Good Doctor");
	

	Consommation c1 = new Consommation("Sandwich");
	Consommation c2 = new Consommation("Pizza");
	Consommation c3 = new Consommation("Tacos");
	Consommation c4 = new Consommation("Kebab");
	Consommation c5 = new Consommation("Burger");
	Consommation c6 = new Consommation("Chips");
	Consommation c7 = new Consommation("Bière");
	Consommation c8 = new Consommation("Sushi");
	Consommation c9 = new Consommation("Coca");
	Consommation c10 = new Consommation("Eau");
	Consommation c11 = new Consommation("Fanta");
	Consommation c12 = new Consommation("Ice Tea");
	Consommation c13 = new Consommation("Salade");
	Consommation c14 = new Consommation("Crêpes");
	Consommation c15 = new Consommation("Lasagnes");	

	Base b = new Base(n);
	//System.out.println(b);
	int m=-1;
	
	do {
	    //Ut.clearConsole();
	    System.out.println(b);
	    m=menu();
	    Ut.clearConsole();
	    requete(m,b);
	    //temp=sc.nextLine();
	   
	}while (m!=0);
 
    }

    public static int menu() {

	int m=-1;

	do {		
	    Scanner sc = new Scanner(System.in);
	    System.out.print("\n\nQue voulez-vous faire ?\n\n1 pour chercher un profil\n2 pour chercher une série\n3 pour chercher un produit\n\n0 pour quitter\n\nVotre choix : ");
	    m=sc.nextInt();
	}while (m!=1 && m!=2 && m!=3 && m!=0);

	return m;
    }

    
	

    public static void requete(int choix, Base b) {

	Scanner sc = new Scanner(System.in);
	String p1, p2, temp;
	
	if (choix!=0) {
	    System.out.println("Entrez la paire de données que vous voulez analyser");
	}
	
	if (choix==1) {
	    System.out.print("Entrez une série : ");
	    p1=sc.nextLine();
	    System.out.print("Entrez un produit : ");
	    p2=sc.nextLine();
	    
	    chercherProfil(p1,p2,b);
	}
	if (choix==2) {
	    System.out.print("Entrez une tranche d'âge : ");
	    p1=sc.nextLine();
	    System.out.print("Entrez un produit : ");
	    p2=sc.nextLine();
	    
	    chercherSerie(p1,p2,b);
	}
	if (choix==3) {
	    System.out.print("Entrez une tranche d'âge : ");
	    p1=sc.nextLine();
	    System.out.print("Entrez une série  : ");
	    p2=sc.nextLine();
	    
	    chercherProduitV2(p1,p2,b);
	}

	temp=sc.nextLine();
    }

    public static void chercherProfil(String p1, String p2, Base b) {

	int[] tab = new int[8];
	boolean[] boolTab;

	for (int i=0; i<b.getBase().length; i++) {
	    if (b.getBase()[i].contenirProduit(p2) && b.getBase()[i].getNetflix().getNom().equals(p1)) {
		tab[b.getBase()[i].getProfil().getTranche()]++;
	    }
	}

	if (estNul(tab)) {
	    System.out.println("\nAucun résultat associé à "+p1+" / "+p2);
	}
	else {	    
	    boolTab=maxTab(tab);

	    System.out.println("\nLe(s) profil(s) le(s) plus souvent associé(s) à "+p1+" / "+p2+" sont :\n");

	    for(int k=0; k<boolTab.length; k++) {
		if (boolTab[k]) {
		    System.out.println(b.getBase()[0].getProfil().getTrancheAge()[k]);
		}
	    }
	}
    }

    public static void chercherSerie(String p1, String p2, Base b) {

	int[] tab = new int[Netflix.getNbSerie()];
	boolean[] boolTab;

	for (int i=0; i<b.getBase().length; i++) {
	    if (b.getBase()[i].contenirProduit(p2) && b.getBase()[i].getProfil().getTrancheAgeByTranche(b.getBase()[i].getProfil().getTranche()).equals(p1)) {
		tab[b.getBase()[i].getNetflix().getId()]++;
	    }
	}
	
	if (estNul(tab)) {
	    System.out.println("\nAucun résultat associé à "+p1+" / "+p2);
	}
	else {	    
	    boolTab=maxTab(tab);
	    
	    System.out.println("\nLe(s) série(s) le(s) plus souvent associée(s) à "+p1+" / "+p2+" sont :\n");
	    
	    for (int k=0; k<boolTab.length; k++) {
		if (boolTab[k]) {
		    System.out.println(b.getBase()[0].getNetflix().getTabSerie()[k]);
		}
	    }
	}       
    }

    public static void chercherProduitV2(String p1, String p2, Base b) {

	int[] tab = new int[Consommation.getNbConsommation()];
	boolean[] boolTab;

	for (int i=0; i<b.getBase().length; i++) {
	    if (b.getBase()[i].getProfil().getTrancheAgeByTranche(b.getBase()[i].getProfil().getTranche()).equals(p1) && b.getBase()[i].getNetflix().getNom().equals(p2)) {
		for (int j=0; j<b.getBase()[i].getPanier().length; j++) {
		    tab[b.getBase()[i].getPanier()[j].getId()]++;
		}
	    }
	}
	
	if (estNul(tab)) {
	    System.out.println("\nAucun résultat associé à "+p1+" / "+p2);
	}
	else {	    
	    boolTab=maxTab(tab);
	    System.out.println("\nLe(s) produit(s) le(s) plus souvent associé(s) à "+p1+" / "+p2+" sont :\n");
	    
	    for (int k=0; k<boolTab.length; k++) {
		if (boolTab[k]) {
		    System.out.println(Consommation.getTabConsommation()[k]);
		    //System.out.println(b.getBase()[0].getPanier().getTabConsommation()[k]);
		}
	    }
	}
    }

    public static boolean[] maxTab(int[] tab) {

	int max=tab[0];

	for (int i=1; i<tab.length; i++) {
	    if (tab[i]>max) {
		max=tab[i];
	    }
	}
       
	boolean[] boolTab = new boolean[tab.length];

	for (int j=0; j<boolTab.length; j++) {
	    if (tab[j]==max) {
		boolTab[j]=true;
	    }
	}

	return boolTab;
    }

    public static boolean estNul(int[] tab) {

	boolean b=true;

	for (int i=0; i<tab.length; i++) {
	    if (tab[i]!=0) {
		b=false;
		break;
	    }
	}

	return b;
    }
}
