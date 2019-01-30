public class Combinaison {

    private int id;
    private Profil profil;
    private Netflix netflix;
    private Consommation panier;
    private static Combinaison[] tabCombinaison = new Combinaison[1000];
    private static int nbCombinaison = 0;

    public Combinaison (Profil p) {

	this.id=nbCombinaison;
	this.profil=p;
	this.netflix=Netflix.getSerieById(Ut.randomMinMax(0,Netflix.getNbSerie()-1));
	this.panier=Consommation.getConsommationById(Ut.randomMinMax(0,Consommation.getNbConsommation()-1));
	tabCombinaison[nbCombinaison]=this;
	nbCombinaison++;
    }

    public static Combinaison getCombinaisonById (int identifiant) {
	return tabCombinaison[identifiant];
    }

    public static int getNbCombinaison() {
	return nbCombinaison;
    }

    public int getId() {
	return this.id;
    }

    public Profil getProfil() {
	return this.profil;
    }

    public Netflix getNetflix() {
	return this.netflix;
    }

    public Consommation getPanier() {
	return this.panier;
    }

    public String toString() {

	return (this.profil+" / "+this.netflix+" / "+this.panier);
    }
}
