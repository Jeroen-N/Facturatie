package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.util.Date;

public class VerzekeringPolis {

	private int PolisNummer;
	private String VerzekeringsType;
	private double ExtraEigenRisico;
	private Date StartDatum;
	private Date EindDatum;
	
	public VerzekeringPolis(int PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum){
		this.PolisNummer = PolisNummer;
		this.VerzekeringsType = VerzekeringsType;
		this.ExtraEigenRisico = ExtraEigenRisico;
		this.StartDatum = StartDatum;
		this.EindDatum = EindDatum;
	}
	
	
	public boolean WijzigVerzekeringPolis(int PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum){
		//zoek de bestaande polis door middel van het PolisNummer
		
		//voer de neuwe gegevens in, in de plaats van de oude gegevens.
		
		return true;
	}
	
	public boolean RemoveVerzekeringPolis(int PolisNummer){
		//zoek de polis door middel van het polisnummer
		
		//verwijder de polis
		
		return false;
		
	}
}
