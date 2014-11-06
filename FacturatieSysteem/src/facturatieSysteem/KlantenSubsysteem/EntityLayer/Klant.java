package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * The Class Klant.
 */
public class Klant {

	/** The bsn. */
	private String BSN;
	
	/** The Naam. */
	private String Naam;
	
	/** The Adres. */
	private String Adres;
	
	/** The Postcode. */
	private String Postcode;
	
	/** The Woonplaats. */
	private String Woonplaats;
	
	/** The Geboortedatum. */
	private String Geboortedatum;
	
	/** The Telefoon nr. */
	private String TelefoonNr;
	
	/** The Email. */
	private String Email;
	
	/** The Resterend eigen risico. */
	private double ResterendEigenRisico;
	
	/** The Rekening nr. */
	private String RekeningNr;
	
	/** The Verzekering polissen. */
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	
	/** The Betaalwijze. */
	private String Betaalwijze;

	/*
	 * Constructor voor de Klant klasse. Hiermee kan een klant aangemaakt worden
	 * en de basisgegevens van deze klant neergezet worden
	 */
	/**
	 * Instantiates a new klant.
	 *
	 * @param BSN the bsn
	 * @param Naam the naam
	 * @param Adres the adres
	 * @param Postcode the postcode
	 * @param Woonplaats the woonplaats
	 * @param Geboortedatum the geboortedatum
	 * @param TelefoonNr the telefoon nr
	 * @param Email the email
	 * @param RekeningNr the rekening nr
	 * @param ResterendEigenRisico the resterend eigen risico
	 * @param VerzekeringPolissen the verzekering polissen
	 * @param Betaalwijze the betaalwijze
	 */
	public Klant(String BSN, String Naam, String Adres, String Postcode,
			String Woonplaats, String Geboortedatum, String TelefoonNr,
			String Email, String RekeningNr, double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze) {
		this.BSN = BSN;
		this.Naam = Naam;
		this.Adres = Adres;
		this.Postcode = Postcode;
		this.Woonplaats = Woonplaats;
		this.Geboortedatum = Geboortedatum;
		this.TelefoonNr = TelefoonNr;
		this.Email = Email;
		this.RekeningNr = RekeningNr;
		this.ResterendEigenRisico = ResterendEigenRisico;
		this.VerzekeringPolissen = VerzekeringPolissen;
		this.Betaalwijze = Betaalwijze;
	}

	/*
	 * In deze methode wordt het totaal eigen risico ingesteld.
	 */
	/**
	 * Sets the totaal eigen risico.
	 *
	 * @param eigenRisico the new totaal eigen risico
	 */
	public void setTotaalEigenRisico(double eigenRisico) {
		ResterendEigenRisico = eigenRisico;
	}

	/*
	 * In deze methode word het totaal eigen risico berekent, door gebruik te
	 * maken het te declareren bedrag, wordt het totaal eigen risico verminderd.
	 */
	/**
	 * Bereken totaal eigen risico.
	 *
	 * @param Bedrag the bedrag
	 * @return the double
	 */
	public double BerekenTotaalEigenRisico(double Bedrag) {
		double eindBedrag;
		eindBedrag = ResterendEigenRisico - Bedrag;
		return eindBedrag;
	}

	/*
	 * In deze methode wordt het verzekeringstype van de client opgevraagd. Dit
	 * wordt gedaan door middel van de getType methode uit verzekeringsPolis
	 */
	/**
	 * Gets the verzekering polissen.
	 *
	 * @return the verzekering polissen
	 */
	public ArrayList<VerzekeringPolis> getVerzekeringPolissen() {
		return VerzekeringPolissen;
	}

	/**
	 * Gets the bsn.
	 *
	 * @return the bsn
	 */
	public String getBSN() {
		return BSN;
	}

	/**
	 * Gets the naam.
	 *
	 * @return the naam
	 */
	public String getNaam() {
		return Naam;
	}

	/**
	 * Gets the adres.
	 *
	 * @return the adres
	 */
	public String getAdres() {
		return Adres;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return Postcode;
	}

	/**
	 * Gets the woonplaats.
	 *
	 * @return the woonplaats
	 */
	public String getWoonplaats() {
		return Woonplaats;
	}

	/**
	 * Gets the geboortedatum.
	 *
	 * @return the geboortedatum
	 */
	public String getGeboortedatum() {
		return Geboortedatum;
	}

	/**
	 * Gets the telefoonnummer.
	 *
	 * @return the telefoonnummer
	 */
	public String getTelefoonnummer() {
		return TelefoonNr;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Gets the betaal methode.
	 *
	 * @return the betaal methode
	 */
	public String getBetaalMethode() {
		return Betaalwijze;
	}

	/**
	 * Gets the resterend eigen risico.
	 *
	 * @return the resterend eigen risico
	 */
	public Double getResterendEigenRisico() {
		return ResterendEigenRisico;
	}

	/**
	 * Gets the rekeningnummer.
	 *
	 * @return the rekeningnummer
	 */
	public String getRekeningnummer() {
		return RekeningNr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        String RestRisico = getallenOpmaker.format(ResterendEigenRisico);
		
		return "BSN: \t\t" 						+ 	BSN 			+ "\n" +
				"Naam: \t\t" 					+ 	Naam 			+ "\n" +
				"GeboorteDatum: \t"				+	Geboortedatum	+ "\n" +
				"Adres: \t\t"					+ 	Adres			+ "\n" +
				"Postcode en woonplaats: \t" 	+ 	Postcode + " "	+ Woonplaats		+ "\n" +
				"Telefoonnummer: \t"	 		+ 	TelefoonNr		+ "\n" +
				"Email: \t\t" 					+ 	Email			+ "\n" +
				"Betaalwijze: \t\t"				+	Betaalwijze		+ "\n" +
				"Resterend eigen risico: \t"	+ "\u20ac" +	RestRisico+ "\n" +
				"Rekeningnummer: \t"			+	RekeningNr		+ "\n" ;
	}
	
	/**
	 * To string factuur.
	 *
	 * @return the string
	 */
	public String toStringFactuur(){
		
		return Naam 			+ "\n" +
				Adres			+ "\n" +
				Postcode + " "	+ Woonplaats		+ "\n";
	}
}
