package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.util.ArrayList;

public class Factuur implements ImmutableFactuur {

	private int factuurNummer;
	private String factuurDatum;
	private String vervalDatum;
	private String BSN;
	private ArrayList<Behandeling> behandelingen;
	private double eigenRisico;

	public Factuur(int factuurNummer, String factuurDatum, String vervalDatum, String BSN, double eigenRisico) {
		this.factuurNummer = factuurNummer;
		this.factuurDatum = factuurDatum;
		this.vervalDatum = vervalDatum;
		this.BSN = BSN;
		this.eigenRisico = eigenRisico;
		behandelingen = new ArrayList<>();
	}


	public ArrayList<Behandeling> getBehandelingen() {
		return behandelingen;
	}


	public void setBehandelingen(ArrayList<Behandeling> behandelingen) {
		this.behandelingen = behandelingen;
	}


	public double getEigenRisico() {
		return eigenRisico;
	}


	public void setEigenRisico(double eigenRisico) {
		this.eigenRisico = eigenRisico;
	}


	@Override
	public void berekenBTW() {
		// TODO Auto-generated method stub

	}

	@Override
	public void berekenEigenRisico() {
		// TODO Auto-generated method stub

	}


	public int getFactuurNummer() {
		return factuurNummer;
	}


	public void setFactuurNummer(int factuurNummer) {
		this.factuurNummer = factuurNummer;
	}


	public String getFactuurDatum() {
		return factuurDatum;
	}


	public void setFactuurDatum(String factuurDatum) {
		this.factuurDatum = factuurDatum;
	}


	public String getVervalDatum() {
		return vervalDatum;
	}


	public void setVervalDatum(String vervalDatum) {
		this.vervalDatum = vervalDatum;
	}


	public String getBSN() {
		return BSN;
	}


	public void setBSN(String bSN) {
		BSN = bSN;
	}

}
