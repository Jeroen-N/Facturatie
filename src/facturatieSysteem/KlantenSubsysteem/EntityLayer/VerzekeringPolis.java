package facturatieSysteem.KlantenSubsysteem.EntityLayer;

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
	
	

}
