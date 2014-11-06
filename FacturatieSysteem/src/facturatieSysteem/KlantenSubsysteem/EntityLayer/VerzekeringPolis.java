package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * The Class VerzekeringPolis.
 */
public class VerzekeringPolis {

	/** The Polis nummer. */
	private String PolisNummer;
	
	/** The Verzekerings type. */
	private String VerzekeringsType;
	
	/** The Extra eigen risico. */
	private double ExtraEigenRisico;
	
	/** The Start datum. */
	private String StartDatum;
	
	/** The Eind datum. */
	private String EindDatum;
	
	/**
	 * Instantiates a new verzekering polis.
	 *
	 * @param PolisNummer the polis nummer
	 * @param VerzekeringsType the verzekerings type
	 * @param ExtraEigenRisico the extra eigen risico
	 * @param StartDatum the start datum
	 * @param EindDatum the eind datum
	 */
	public VerzekeringPolis(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, String StartDatum, String EindDatum){
		this.PolisNummer = PolisNummer;
		this.VerzekeringsType = VerzekeringsType;
		this.ExtraEigenRisico = ExtraEigenRisico;
		this.StartDatum = StartDatum;
		this.EindDatum = EindDatum;
	}
	
	/**
	 * Gets the polis nummer.
	 *
	 * @return the polis nummer
	 */
	public String getPolisNummer(){
		return PolisNummer;
	}
	
	/**
	 * Gets the verzekerings type.
	 *
	 * @return the verzekerings type
	 */
	public String getVerzekeringsType(){
		return VerzekeringsType;
	}
	
	/**
	 * Gets the extra eigen risico.
	 *
	 * @return the extra eigen risico
	 */
	public double getExtraEigenRisico(){
		return ExtraEigenRisico;
	}
	
	/**
	 * Gets the start datum.
	 *
	 * @return the start datum
	 */
	public String getStartDatum(){
		return StartDatum;
	}
	
	/**
	 * Gets the eind datum.
	 *
	 * @return the eind datum
	 */
	public String getEindDatum(){
		return EindDatum;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
