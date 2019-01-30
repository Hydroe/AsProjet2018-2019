public class Base {

    private Combinaison[] base;

    public Base (int n) {

	this.base = new Combinaison[n];
	
	for (int i=0; i<n; i++) {
	    
	    Profil profil = new Profil(Ut.randomMinMax(1,80));
	    this.base[i] = new Combinaison(profil);
	}
    }

    public String toString() {

	String str="";
	for (int i=0; i<this.base.length; i++) {
	    str=str+"\n"+(i+1)+" : "+this.base[i];
	}
	return str;
    }

    public Combinaison[] getBase() {
	return this.base;
    }

    public boolean contient(String p, int i) {

	return (this.base[i].getProfil().getTrancheAge()[this.base[i].getProfil().getTranche()].equals(p) || this.base[i].getNetflix().getNom().equals(p) || this.base[i].getPanier().getNom().equals(p));
    }

}
