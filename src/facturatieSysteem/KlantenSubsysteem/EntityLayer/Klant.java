package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.util.Date;

public class Klant {

	private String BSN;
	private String Naam;
	private String Adres;
	private String Postcode;
	private String Woonplaats;
	private String Geboortedatum;
	private String TelefoonNr;
	private String Email;
	private double ResterendEigenRisico;
	private String RekeningNr;
	private VerzekeringPolis Verzekering;
	private String Betaalwijze;

	/*
	 * Constructor voor de Klant klasse. Hiermee kan een klant aangemaakt worden
	 * en de basisgegevens van deze klant neergezet worden
	 */
	public Klant(String BSN, String Naam, String Adres, String Postcode,
			String Woonplaats, String Geboortedatum, String TelefoonNr,
			String Email, String RekeningNr, double ResterendEigenRisico,
			VerzekeringPolis Verzekering, String Betaalwijze) {
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
		this.Verzekering = Verzekering;
		this.Betaalwijze = Betaalwijze;
	}

	/*
	 * In deze methode wordt het totaal eigen risico ingesteld.
	 */
	public void setTotaalEigenRisico(double eigenRisico) {
		ResterendEigenRisico = eigenRisico;
	}

	/*
	 * In deze methode word het totaal eigen risico berekent, door gebruik te
	 * maken het te declareren bedrag, wordt het totaal eigen risico verminderd.
	 */
	public double BerekenTotaalEigenRisico(double Bedrag) {
		double eindBedrag;
		eindBedrag = ResterendEigenRisico - Bedrag;
		return eindBedrag;
	}

	/*
	 * Hierbij wordt de betaalwijze aangepast.
	 */
	public void SetIncassoOfFactuur(String Betaalwijze) {
		this.Betaalwijze = Betaalwijze;
	}

	/*
	 * In deze methode wordt het verzekeringstype van de client opgevraagd. Dit
	 * wordt gedaan door middel van de getType methode uit verzekeringsPolis
	 */
	public VerzekeringPolis getVerzekering() {
		return Verzekering;
	}

	public String getBSN() {
		return BSN;
	}

	public String getNaam() {
		return Naam;
	}

	public String getAdres() {
		return Adres;
	}

	public String getPostcode() {
		return Postcode;
	}

	public String getWoonplaats() {
		return Woonplaats;
	}

	public String getGeboortedatum() {
		return Geboortedatum;
	}

	public String getTelefoonnummer() {
		return TelefoonNr;
	}

	public String getEmail() {
		return Email;
	}

	public String getBetaalMethode() {
		return Betaalwijze;
	}

	public Double getResterendEigenRisico() {
		return ResterendEigenRisico;
	}

	public String getRekeningnummer() {
		return RekeningNr;
	}

	public String toString(){
		return "BSN: " 						+ 	BSN 			+ "\n\n" +
				"Naam: " 					+ 	Naam 			+ "\n\n" +
				"GeboorteDatum: "			+	Geboortedatum	+ "\n\n" +
				"Adres: "					+ 	Adres			+ "\n\n" +
				"Postcode en woonplaats: " 	+ 	Postcode + " "	+ Woonplaats		+ "\n\n" +
				"Telefoonnummer: "	 		+ 	TelefoonNr		+ "\n\n" +
				"Email: " 					+ 	Email			+ "\n\n" +
				"Betaalwijze: "				+	Betaalwijze		+ "\n\n" +
				"Resterend eigen risico: "	+ "€" +	ResterendEigenRisico + "\n\n" +
				"Rekeningnummer: "			+	RekeningNr		+ "\n\n" ;
	}
}
