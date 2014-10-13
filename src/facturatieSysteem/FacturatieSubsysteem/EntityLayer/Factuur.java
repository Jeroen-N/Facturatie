package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public class Factuur implements ImmutableFactuur {

	private int factuurNummer;
	private long factuurDatum;
	private long vervalDatum;
	private String BSN;

	public Factuur(int factuurNummer, long factuurDatum, long vervalDatum, String BSN) {
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


	public long getFactuurDatum() {
		return factuurDatum;
	}


	public void setFactuurDatum(long factuurDatum) {
		this.factuurDatum = factuurDatum;
	}


	public long getVervalDatum() {
		return vervalDatum;
	}


	public void setVervalDatum(long vervalDatum) {
		this.vervalDatum = vervalDatum;
	}


	public String getBSN() {
		return BSN;
	}


	public void setBSN(String bSN) {
		BSN = bSN;
	}

}
