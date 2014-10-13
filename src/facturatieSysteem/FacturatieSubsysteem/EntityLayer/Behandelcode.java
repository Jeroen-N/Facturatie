package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public class Behandelcode {

	private int behandelCode;
	private String behandelingNaam;
	private int aantalSessies;
	private double sessieDuur;
	private double tariefBehandeling;

	public Behandelcode(int behandelCode, String behandelingNaam,
			int aantalSessies, double sessieDuur, double tariefBehandeling) {
		this.behandelCode = behandelCode;
		this.behandelingNaam = behandelingNaam;
		this.aantalSessies = aantalSessies;
		this.sessieDuur = sessieDuur;
		this.tariefBehandeling = tariefBehandeling;
	}

	public int getBehandelCode() {
		return behandelCode;
	}

	public void setBehandelCode(int behandelCode) {
		this.behandelCode = behandelCode;
	}

	public String getBehandelingNaam() {
		return behandelingNaam;
	}

	public void setBehandelingNaam(String behandelingNaam) {
		this.behandelingNaam = behandelingNaam;
	}

	public int getAantalSessies() {
		return aantalSessies;
	}

	public void setAantalSessies(int aantalSessies) {
		this.aantalSessies = aantalSessies;
	}

	public double getSessieDuur() {
		return sessieDuur;
	}

	public void setSessieDuur(double sessieDuur) {
		this.sessieDuur = sessieDuur;
	}

	public double getTariefBehandeling() {
		return tariefBehandeling;
	}

	public void setTariefBehandeling(double tariefBehandeling) {
		this.tariefBehandeling = tariefBehandeling;
	}

}
