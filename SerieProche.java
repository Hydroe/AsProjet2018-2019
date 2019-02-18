import java.util.*;

public class SerieProche {
	private int id;
	private String nomSerie1;
	private String nomSerie2;
	private double noteCorrelation;
	private static int nbCorrelation = 0;

	public SerieProche(String nomSerie1, String nomSerie2, double noteCorrelation){
		this.id = nbCorrelation;
		this.nomSerie1 = nomSerie1;
		this.nomSerie2 = nomSerie2;
		this.noteCorrelation = noteCorrelation;
		nbCorrelation++;
	}

	public String getNomSerie1(){
		return this.nomSerie1;
	}

	public String getNomSerie2(){
		return this.nomSerie2;
	}

	public double getNoteCorrelation(){
		return this.noteCorrelation;
	}

	public int getId(){
		return this.id;
	}

	public static int getNbCorrelation(){
		return nbCorrelation;
	}
}