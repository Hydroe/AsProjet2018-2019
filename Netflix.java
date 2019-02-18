import java.util.*;

public class Netflix {

    private int id;
    private String nom;
    private char longueur;
    private int popularite;
    private  List<String> type = new ArrayList<String>();
    private static int nbSerie = 0;
    private static Netflix[] tabSerie = new Netflix[5];
    private static List<SerieProche> nomEtNote = new ArrayList<SerieProche>();

    public Netflix (String nom, int temps, String type, int popularite) {
    	this.id=nbSerie;
    	this.nom=nom;
        if(temps > 0 && temps <= 25) this.longueur = 's';
        if(temps > 25 && temps <= 45) this.longueur = 'm';
        if(temps > 45) this.longueur = 'l';
        String[] strtab = type.split("/");
        for(int i = 0; i < strtab.length; i++){
            this.type.add(strtab[i]);
        }
        this.popularite = popularite;
    	tabSerie[nbSerie]=this;
    	nbSerie++;
        int i = 0;
        if(Netflix.getNbSerie() > 1){
            SerieProche[] s = new SerieProche[nbSerie - 1];
            for(int j = 0; j < nbSerie - 1; j++){
                s[i] = new SerieProche(Netflix.getSerieById(i).getNom(), this.nom, Netflix.getSerieById(i).compare(this.getLongueur(), this.getType(), this.getPopularite()));
                nomEtNote.add(s[i]);
                i++;
            }
        }
	
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

    public char getLongueur(){
        return this.longueur;
    }

    public List<String> getType(){
        return type;
    }

    public int getPopularite(){
        return this.popularite;
    }

    public static List<SerieProche> getNomEtNote(){
        return nomEtNote;
    }

    public double compare(char longueur, List<String> type, int popularite){
        double retour = 0.1;
        if(this.getLongueur() == 'm' ^ longueur == 'm') retour += 0.1;
        else if(!(this.getLongueur() == longueur)) retour += 0.2;
        double tempo = 0.4;
        for(int i = 0; i < this.type.size(); i++){
            for(int j = i; j < type.size(); j++){
                if((this.getType().get(i)).equals(type)) tempo -= 0.2;
            }
        }
        retour += ((tempo >= 0) ? tempo : 0);
        if(((this.getPopularite() - popularite) > -5 ||
            (this.getPopularite() - popularite) > 5)) retour += 0.1;
        return retour;
    }
}
