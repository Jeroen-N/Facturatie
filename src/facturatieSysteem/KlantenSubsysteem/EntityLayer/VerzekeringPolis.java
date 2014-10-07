package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import java.util.Date;

public class VerzekeringPolis {
// jkhasdkjf
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
	
	public void NewVerzekering(){
		
	}
	
	public void WijzigVerzekering(){
		
	}
	
	public void RemoveVerzekering(){
		
	}
}
