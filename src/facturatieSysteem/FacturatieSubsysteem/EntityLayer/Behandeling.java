package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public class Behandeling implements ImmutableBehandeling {

	private String fysioPraktijkNummer;
	private String behandelCode;
	private String behandelStartDatum;
	private String behandelEindDatum;
	private String BSN;
	private double totaalprijs;
	private int sessies;

	public Behandeling(String fysioPraktijkNummer, String behandelCode,
			String behandelStartDatum, String behandelEindDatum, String BSN, double totaalprijs, int sessies) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
		this.behandelCode = behandelCode;
		this.behandelStartDatum = behandelStartDatum;
		this.behandelEindDatum = behandelEindDatum;
		this.BSN = BSN;
		this.totaalprijs = totaalprijs;
		this.sessies = sessies;

	}

	public double getTotaalprijs() {
		return totaalprijs;
	}

	public void setTotaalprijs(double totaalprijs) {
		this.totaalprijs = totaalprijs;
	}

	public int getSessies() {
		return sessies;
	}

	public void setSessies(int sessies) {
		this.sessies = sessies;
	}

	@Override
	public Behandeling getBehandelingen() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFysioPraktijkNummer() {
		return fysioPraktijkNummer;
	}

	public void setFysioPraktijkNummer(String fysioPraktijkNummer) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
	}

	public String getBehandelCode() {
		return behandelCode;
	}

	public void setBehandelCode(String behandelCode) {
		this.behandelCode = behandelCode;
	}

	public String getBehandelStartDatum() {
		return behandelStartDatum;
	}

	public void setBehandelStartDatum(String behandelStartDatum) {
		this.behandelStartDatum = behandelStartDatum;
	}

	public String getBehandelEindDatum() {
		return behandelEindDatum;
	}

	public void setBehandelEindDatum(String behandelEindDatum) {
		this.behandelEindDatum = behandelEindDatum;
	}

	public String getBSN() {
		return BSN;
	}

	public void setBSN(String bSN) {
		BSN = bSN;
	}

}
