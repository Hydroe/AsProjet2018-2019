public class Consommation {

    private int id;
    private String nom;
    private static Consommation[] tabConsommation = new Consommation[5];
    private static int nbConsommation = 0;

    public Consommation (String nom) {

	this.id=nbConsommation;
	this.nom=nom;
	tabConsommation[nbConsommation]=this;
	nbConsommation++;
    }

    public int getId() {
	return this.id;
    }

    public static int getNbConsommation() {
	return nbConsommation;
    }

    public static Consommation getConsommationById(int identifiant) {
	return tabConsommation[identifiant];
    }

    public String getNom() {
	return this.nom;
    }

    public static Consommation[] getTabConsommation() {
	return tabConsommation;
    }
    
    public String toString() {
	return this.nom;
    }
}
