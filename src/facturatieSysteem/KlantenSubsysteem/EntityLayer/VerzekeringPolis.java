package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class VerzekeringPolis {

	private String PolisNummer;
	private String VerzekeringsType;
	private double ExtraEigenRisico;
	private Date StartDatum;
	private Date EindDatum;
	
	public VerzekeringPolis(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum){
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
	
	public Date getStartDatum(){
		return StartDatum;
	}
	
	public Date getEindDatum(){
		return EindDatum;
	}
	
	public String toString(){
		NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        String ExtraEigenRisic = getallenOpmaker.format(ExtraEigenRisico);
		
		return "Polisnummer: \t\t" 				+ 	PolisNummer 			+ "\n" +
				"Verzekeringstype: \t" 		+ 	VerzekeringsType 			+ "\n" +
				"Totaal eigen risico: \t"		+	"\u20ac"+ExtraEigenRisic	+ "\n" +
				"Start datum: \t\t"				+ 	StartDatum				+ "\n" +
				"Eind datum: \t\t"				+	EindDatum				+ "\n" ;
	}

}
