package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public interface ImmutableBehandeling {

	public Behandeling getBehandelingen();

	public String getFysioPraktijkNummer();

	public void setFysioPraktijkNummer(String fysioPraktijkNummer);

	public String getBehandelCode();

	public void setBehandelCode(String behandelCode);

	public String getBehandelStartDatum();

	public void setBehandelStartDatum(String behandelStartDatum);

	public String getBehandelEindDatum();

	public void setBehandelEindDatum(String behandelEindDatum);

	public String getBSN();

	public void setBSN(String BSN);

}
