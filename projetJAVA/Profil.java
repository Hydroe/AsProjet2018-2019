import java.util.*;
import java.lang.*;

public class Profil {

    private int id;
    private String nom;
    private String prenom;
    private int tranche;
    private static final String[] trancheAge = {"1 - 10","11 - 20","21 - 30","31 - 40","41 - 50","51 - 60","61 - 70","71 - 80"};
    private static Profil[] tabProfil = new Profil[1000];
    private static int nbProfil = 0;

    public Profil (int age) {
	
	this.id=nbProfil;
	this.nom=definirNom();
	this.prenom=definirPrenom();
	this.tranche=(age-1)/10;
	tabProfil[nbProfil]=this;
	nbProfil++;	
    }

    public int getId() {
	return this.id;
    }

    public static String getTrancheAgeByTranche(int tranche) {
	return trancheAge[tranche];
    }

    public static Profil getProfilById(int identifiant) {
	return tabProfil[identifiant];
    }

    public String[] getTrancheAge() {
	return trancheAge;
    }

    public int getTranche() {
	return this.tranche;
    }

    public String getTrancheV2() {
	return String.valueOf(this.tranche);
    }

    public String definirPrenom() {

	String[] tabPrenom = {"Jean","Guillaume","Vincent","Julien","Denis","Francis","Thibault","Robin","Théo","Francesco","Victoria","Madalina","Evi","Jade","Emma","Juliette","Océane","Anna","Marie","Lucie"};

	return tabPrenom[Ut.randomMinMax(0,tabPrenom.length-1)];
    }

    public String definirNom() {

	String[] tabNom = {"LETOURMY","GARCIA","BECLE","DUPONT","JOHNSON","ALAUZET","DUFOUR","MATTHEWS","VICHET","CROITORU","RAOUL"};

	return tabNom[Ut.randomMinMax(0,tabNom.length-1)];
    }

    public String toString() {

	String ch="";

	if (nom.length()<9)
	    ch+=nom+"\t\t| ";
	else
	    ch+=nom+"\t| ";

	if (prenom.length()>=6)
	    ch+=prenom+"\t | ";
	else
	    ch+=prenom+"\t\t | ";

	if (trancheAge[tranche].equals("1 - 10"))
	    ch+=" "+trancheAge[tranche]+"\t";
	else
	    ch+=trancheAge[tranche]+"\t";

	return ch;
    }
}
