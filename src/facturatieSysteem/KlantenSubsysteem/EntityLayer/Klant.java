//test voor git; Xander Gerreman
//test

package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.util.Date;

public class Klant {

	private String BSN;
	private String Naam;
	private String Adres;
	private String Postcode;
	private String Woonplaats;
	private Date Geboortedatum;
	private String TelefoonNr;
	private String Email;
	private String ResterendEigenRisico;
	private String RekeningNr;
	private VerzekeringPolis Verzekering;
	private String Betaalwijze;

	public Klant(String BSN, String Naam, String Adres, String Postcode,
			String Woonplaats, Date Geboortedatum, String TelefoonNr,
			String Email, String ResterendEigenRisico, String RekeningNr,
			VerzekeringPolis Verzekering, String Betaalwijze) {
		this.BSN = BSN;
		this.Naam = Naam;
		this.Adres = Adres;
		this.Postcode = Postcode;
		this.Woonplaats = Woonplaats;
		this.Geboortedatum = Geboortedatum;
		this.TelefoonNr = TelefoonNr;
		this.Email = Email;
		this.ResterendEigenRisico = ResterendEigenRisico;
		this.RekeningNr = RekeningNr;
		this.Verzekering = Verzekering;
		this.Betaalwijze = Betaalwijze;
	}

	public double BerekenTotaalEigenRisico(){
		return 0;
		
	}
	
	public String SetIncassoOfFactuur(){
		return ;
		
	}
	
}
