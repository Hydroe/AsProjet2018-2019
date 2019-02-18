import java.util.*;
import java.lang.*;

class Main {

    public static void main(String[] args) {
    	Netflix dn1 = new Netflix("Vikings",20, "Action/Aventure", 6);
    	Netflix dn2 = new Netflix("Person of Interest",41, "Historique/Drama", 1);
    	Netflix dn3 = new Netflix("Stargate",60, "Drame/Action", 2);
    	Netflix dn4 = new Netflix("Game Of Thrones",10, "Historique/Aventure", 9);
    	Netflix dn5 = new Netflix("Boruto",25, "Action/Aventure", 4);

		Ut.clearConsole();
		Scanner sc = new Scanner(System.in);
		System.out.print("Nombre de profils : ");
		int n=sc.nextInt();

		Consommation c1 = new Consommation("Sandwich");
		Consommation c2 = new Consommation("Pizza");
		Consommation c3 = new Consommation("Tacos");
		Consommation c4 = new Consommation("Kebab");
		Consommation c5 = new Consommation("Burger");

		Base b = new Base(n);
		//System.out.println(b);
		int m=-1;
		do {
		    //Ut.clearConsole();
	    	//System.out.println(b);
	    	m=menu();
	    	Ut.clearConsole();
	    	requete(m,b);
		}while (m!=0);
    }

    public static int menu() {
		int m=-1;
		do {		
		    Scanner sc = new Scanner(System.in);
		    System.out.print("\n\nQue voulez-vous faire ?\n\n1 pour chercher un profil\n2 pour chercher une série\n3 pour chercher un produit\n4 pour chercher une série à regarder\n\n0 pour quitter\n\nVotre choix : ");
		    m=sc.nextInt();
		}while (m!=1 && m!=2 && m!=3 && m!=4 && m!=0);
		return m;
    }

    public static void requete(int choix, Base b) {
		Scanner sc = new Scanner(System.in);
		String p1,p2;
		if (choix!=0) {
		    System.out.println("Entrez la paire de données que vous voulez analyser");
		}
		if (choix==1) {
		    System.out.print("Entrez une série : ");
		    p1=sc.nextLine();
		    System.out.print("Entrez un produit : ");
		    p2=sc.nextLine();
		    
		    chercheProfil(p1,p2,b);
		}
		if (choix==2) {
		    System.out.print("Entrez une tranche d'âge : ");
		    p1=sc.nextLine();
		    System.out.print("Entrez un produit : ");
		    p2=sc.nextLine();
		    
		    chercheSerie(p1,p2,b);
		}
		if (choix==3) {
		    System.out.print("Entrez une tranche d'âge : ");
		    p1=sc.nextLine();
		    System.out.print("Entrez une série  : ");
		    p2=sc.nextLine();
		    
		    chercheProduit(p1,p2,b);
		}
		if (choix==4) {
			System.out.println("Choisissez votre série : ");
			for(int i = 0; i < Netflix.getNbSerie(); i++){
				System.out.println(i + "- " + Netflix.getSerieById(i).getNom());
			}
			p1=sc.nextLine();
			System.out.println(TrouverAutreSerie(Integer.valueOf(p1)));
		}
    }

    public static void chercheProfil(String p1, String p2, Base b) {
		int[] tab = new int[8];
		for (int i=0; i<b.getBase().length; i++){
		    if (b.contient(p1,i) && b.contient(p2,i)){
			tab[b.getBase()[i].getProfil().getTranche()]++;
		    }
			}	
		boolean[] boolTab = maxTab(tab);
		System.out.println("\nLe(s) profil(s) le(s) plus souvent associé(s) à "+p1+" / "+p2+" sont :\n");
		for (int k=0; k<boolTab.length; k++){
		    if (boolTab[k]){
			System.out.println(b.getBase()[0].getProfil().getTrancheAge()[k]);
	    	}
		}
    }

    public static void chercheSerie(String p1, String p2, Base b) {
		int[] tab = new int[5];
		for (int i=0; i<b.getBase().length; i++) {
		    if (b.contient(p1,i) && b.contient(p2,i)) {
			tab[b.getBase()[i].getNetflix().getId()]++;
	    	}
		}
		boolean[] boolTab = maxTab(tab);
		System.out.println("\nLe(s) série(s) le(s) plus souvent associée(s) à "+p1+" / "+p2+" sont :\n");
	    
		for (int k=0; k<boolTab.length; k++) {
	    	if (boolTab[k]) {
			System.out.println(b.getBase()[0].getNetflix().getTabSerie()[k]);
	    	}
		}
    }
    
    public static void chercheProduit(String p1, String p2, Base b) {
		int[] tab = new int[5];
		for (int i=0; i<b.getBase().length; i++) {
		    if (b.contient(p1,i) && b.contient(p2,i)) {
			tab[b.getBase()[i].getPanier().getId()]++;
	    	}
		}
		boolean[] boolTab=maxTab(tab);
		System.out.println("\nLe(s) produit(s) le(s) plus souvent associé(s) à "+p1+" / "+p2+" sont :\n");    
		for (int k=0; k<boolTab.length; k++) {
		    if (boolTab[k]) {
			System.out.println(b.getBase()[0].getPanier().getTabConsommation()[k]);
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

    public static String TrouverAutreSerie(int idSerie){
    	String retourNom = "";
    	double retourNote = 1;
    	for(int i = 0; i < SerieProche.getNbCorrelation(); i++){
    		if(	Netflix.getNomEtNote().get(i).getNomSerie1().equals(Netflix.getSerieById(idSerie).getNom()) &&
    			Netflix.getNomEtNote().get(i).getNoteCorrelation() < retourNote){
    			retourNom = Netflix.getNomEtNote().get(i).getNomSerie2();
    			retourNote = Netflix.getNomEtNote().get(i).getNoteCorrelation();
    		}
    		else if(Netflix.getNomEtNote().get(i).getNomSerie1().equals(Netflix.getSerieById(idSerie).getNom()) &&
    				Netflix.getNomEtNote().get(i).getNoteCorrelation() == retourNote){
    			retourNom += "/" + Netflix.getNomEtNote().get(i).getNomSerie2();
    			retourNote = Netflix.getNomEtNote().get(i).getNoteCorrelation();
    		}
    		else if (Netflix.getNomEtNote().get(i).getNomSerie2().equals(Netflix.getSerieById(idSerie).getNom()) &&
    				Netflix.getNomEtNote().get(i).getNoteCorrelation() < retourNote){
    			retourNom = Netflix.getNomEtNote().get(i).getNomSerie1();
    			retourNote = Netflix.getNomEtNote().get(i).getNoteCorrelation();
    		}
    		else if (Netflix.getNomEtNote().get(i).getNomSerie2().equals(Netflix.getSerieById(idSerie).getNom()) &&
    				Netflix.getNomEtNote().get(i).getNoteCorrelation() == retourNote){
    			retourNom += "/" + Netflix.getNomEtNote().get(i).getNomSerie1();
    			retourNote = Netflix.getNomEtNote().get(i).getNoteCorrelation();
    		}
    	}
    	return "Nous vous conseillons " + retourNom + "\nAvec une note de " + retourNote;
    }
}
