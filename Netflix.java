public class Netflix {

    private int id;
    private String nom;
    private static int nbSerie = 0;
    private static Netflix[] tabSerie = new Netflix[5];

    public Netflix (String nom) {

	this.id=nbSerie;
	this.nom=nom;
	tabSerie[nbSerie]=this;
	nbSerie++;
	
    }
    public int getId() {
	return this.id;
    }

    public static Netflix getSerieById(int identifiant) {
	return tabSerie[identifiant];
    }

    public static int getIdByNom(String nomSerie) {

	int indice=-1;

	for (int i=0; i<tabSerie.length; i++) {
	    if (tabSerie[i].nom.equals(nomSerie)) {
		indice=i;
		break;
	    }
	}
	return indice;
    }

    public static Netflix[] getTabSerie() {
	return tabSerie;
    }

    public static int getNbSerie() {
	return nbSerie;
    }

    public String getNom() {
	return this.nom;
    }
    
    public String toString() {
	return this.nom;
    }
}
