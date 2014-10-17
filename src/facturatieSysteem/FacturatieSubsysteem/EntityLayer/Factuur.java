package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public class Factuur implements ImmutableFactuur {

	private int factuurNummer;
	private String factuurDatum;
	private String vervalDatum;
	private String BSN;

	public Factuur(int factuurNummer, String factuurDatum, String vervalDatum, String BSN) {
		this.factuurNummer = factuurNummer;
		this.factuurDatum = factuurDatum;
		this.vervalDatum = vervalDatum;
		this.BSN = BSN;
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
