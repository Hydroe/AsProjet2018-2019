public class Combinaison {

    private int id;
    private Profil profil;
    private Netflix netflix;
    private Consommation[] panier;
    private static Combinaison[] tabCombinaison = new Combinaison[1000];
    private static int nbCombinaison = 0;

    public Combinaison (Profil p) {

	Consommation conso;

	this.id=nbCombinaison;
	this.profil=p;
	this.netflix=Netflix.getSerieById(Ut.randomMinMax(0,Netflix.getNbSerie()-1));
	this.panier = new Consommation[Ut.randomMinMax(1,5)];
	this.panier[0]=Consommation.getConsommationById(Ut.randomMinMax(0,Consommation.getNbConsommation()-1));
	
	for (int i=1; i<panier.length; i++) {
	    conso=Consommation.getConsommationById(Ut.randomMinMax(0,Consommation.getNbConsommation()-1));
	    while (this.contenirProduit(conso,i)) {
		conso=Consommation.getConsommationById(Ut.randomMinMax(0,Consommation.getNbConsommation()-1));
	    }
	    this.panier[i]=conso;
	}
	tabCombinaison[nbCombinaison]=this;
	nbCombinaison++;
    }

    public static Combinaison getCombinaisonById (int identifiant) {
	return tabCombinaison[identifiant];
    }

    public boolean contenirProduit(Consommation c, int limite) {

	boolean b=false;

	for (int i=0; i<limite; i++) {
	    if (this.panier[i].getNom().equals(c.getNom())) {
		b=true;
		break;
	    }
	}
	return b;
    }

    public boolean contenirProduit(String nomProduit) {
	
	boolean b=false;

	for (int i=0; i<panier.length; i++) {
	    if (this.panier[i].getNom().equals(nomProduit)) {
		b=true;
		break;
	    }
	}
	return b;
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

    public Consommation[] getPanier() {
	return this.panier;
    }

    public String toStringPanier() {

	String ch = ""+this.panier[this.panier.length-1];

	for (int i=0; i<this.panier.length-1; i++) {
	    ch=this.panier[i]+", "+ch;
	}

	return ch;
    }

    public String toString() {

	return (this.profil+" | "+this.netflix+" | "+this.toStringPanier());
    }
}
