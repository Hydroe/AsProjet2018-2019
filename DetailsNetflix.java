import java.util.*;

public class DetailsNetflix{

	private char longueur;
    
    private List<String> type = new ArrayList<String>();
    
    private int popularite;

    public DetailsNetflix(int temps, String type, int popularite){
    	if(temps > 0 && temps <= 25) this.longueur = 's';
    	if(temps > 25 && temps <= 45) this.longueur = 'm';
    	if(temps > 45) this.longueur = 'l';
    	String[] strtab = type.split("/");
    	for(int i = 0; i < strtab.length; i++){
    		this.type.add(strtab[i]);
    	}
    	this.popularite = popularite;
    }

    public double compare(DetailsNetflix dN){
    	double retour = 0.1;
    	if(this.getLongueur() == 'm' ^ dN.getLongueur() == 'm') retour += 0.1;
    	else if(!(this.getLongueur() == dN.getLongueur())) retour += 0.2;
    	double tempo = 0.4;
    	for(int i = 0; i < this.type.size(); i++){
    		for(int j = i; j < dN.type.size(); j++){
    			if((this.getType().get(i)).equals(dN.getType().get(j))) tempo -= 0.2;
    		}
    	}
    	retour += ((tempo >= 0) ? tempo : 0);
    	if(((this.getPopularite() - dN.getPopularite()) > -5 ||
    		(this.getPopularite() - dN.getPopularite()) > 5)) retour += 0.1;
    	return retour;
    }

	public char getLongueur(){
		return this.longueur;
	}

	public List<String> getType(){
		return this.type;
	}

	public int getPopularite(){
		return this.popularite;
	}

}