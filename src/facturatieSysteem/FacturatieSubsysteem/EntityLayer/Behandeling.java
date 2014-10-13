package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public class Behandeling implements ImmutableBehandeling {

	private int fysioPraktijkNummer;
	private int behandelCode;
	private long behandelStartDatum;
	private long behandelEindDatum;
	private String BSN;

	public Behandeling(int fysioPraktijkNummer, int behandelCode,
			long behandelStartDatum, long behandelEindDatum, String BSN) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
		this.behandelCode = behandelCode;
		this.behandelStartDatum = behandelStartDatum;
		this.behandelEindDatum = behandelEindDatum;
		this.BSN = BSN;

	}

	@Override
	public Behandeling getBehandelingen() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getFysioPraktijkNummer() {
		return fysioPraktijkNummer;
	}

	public void setFysioPraktijkNummer(int fysioPraktijkNummer) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
	}

	public int getBehandelCode() {
		return behandelCode;
	}

	public void setBehandelCode(int behandelCode) {
		this.behandelCode = behandelCode;
	}

	public long getBehandelStartDatum() {
		return behandelStartDatum;
	}

	public void setBehandelStartDatum(long behandelStartDatum) {
		this.behandelStartDatum = behandelStartDatum;
	}

	public long getBehandelEindDatum() {
		return behandelEindDatum;
	}

	public void setBehandelEindDatum(long behandelEindDatum) {
		this.behandelEindDatum = behandelEindDatum;
	}

	public String getBSN() {
		return BSN;
	}

	public void setBSN(String bSN) {
		BSN = bSN;
	}

}
