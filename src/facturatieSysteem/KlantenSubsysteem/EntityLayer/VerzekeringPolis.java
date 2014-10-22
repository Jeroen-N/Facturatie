package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class VerzekeringPolis {

	private String PolisNummer;
	private String VerzekeringsType;
	private double ExtraEigenRisico;
	private String StartDatum;
	private String EindDatum;
	
	public VerzekeringPolis(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, String StartDatum, String EindDatum){
		this.PolisNummer = PolisNummer;
		this.VerzekeringsType = VerzekeringsType;
		this.ExtraEigenRisico = ExtraEigenRisico;
		this.StartDatum = StartDatum;
		this.EindDatum = EindDatum;
	}
	
	public String getPolisNummer(){
		return PolisNummer;
	}
	
	public String getVerzekeringsType(){
		return VerzekeringsType;
	}
	
	public double getExtraEigenRisico(){
		return ExtraEigenRisico;
	}
	
	public String getStartDatum(){
		return StartDatum;
	}
	
	public String getEindDatum(){
		return EindDatum;
	}
	
	public String toString(){
		NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        String ExtraEigenRisic = getallenOpmaker.format(ExtraEigenRisico);
		
		return "Polisnummer: \t\t" 				+ 	PolisNummer 			+ "\n\n" +
				"Verzekeringstype: \t" 		+ 	VerzekeringsType 			+ "\n\n" +
				"Totaal eigen risico: \t"		+	"€"+ExtraEigenRisic	+ "\n\n" +
				"Start datum: \t\t"				+ 	StartDatum				+ "\n\n" +
				"Eind datum: \t\t"				+	EindDatum				+ "\n\n" ;
	}

}
