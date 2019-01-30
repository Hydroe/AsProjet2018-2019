import java.util.*;
import java.lang.*;

public class Profil {

    private int id;
    private int tranche;
    private static final String[] trancheAge = {"1 - 10","11 - 20","21 - 30","31 - 40","41 - 50","51 - 60","61 - 70","71 - 80"};
    private static Profil[] tabProfil = new Profil[1000];
    private static int nbProfil = 0;

    public Profil (int age) {
	
	this.id=nbProfil;
	this.tranche=(age-1)/10;
	tabProfil[nbProfil]=this;
	nbProfil++;
	
    }

    public int getId() {
	return this.id;
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

    public String toString() {

	return this.trancheAge[this.tranche];
    }
}
