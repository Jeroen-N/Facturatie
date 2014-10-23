package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public interface ImmutableBehandeling {

	public Behandeling getBehandelingen();

	public int getFysioPraktijkNummer();

	public void setFysioPraktijkNummer(int fysioPraktijkNummer);

	public String getBehandelCode();

	public void setBehandelCode(String behandelCode);

	public long getBehandelStartDatum();

	public void setBehandelStartDatum(long behandelStartDatum);

	public long getBehandelEindDatum();

	public void setBehandelEindDatum(long behandelEindDatum);

	public String getBSN();

	public void setBSN(String BSN);

}
