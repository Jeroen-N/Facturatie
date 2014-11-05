package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Behandeling.
 */
public class Behandeling implements ImmutableBehandeling {

	/** The fysio praktijk nummer. */
	private String fysioPraktijkNummer;
	
	/** The behandeling id. */
	private String behandelingId;
	
	/** The behandel code. */
	private String behandelCode;
	
	/** The behandel start datum. */
	private String behandelStartDatum;
	
	/** The behandel eind datum. */
	private String behandelEindDatum;
	
	/** The bsn. */
	private String BSN;
	
	/** The afspraak i ds. */
	private ArrayList<String> afspraakIDs;
	
	/** The totaalprijs. */
	private double totaalprijs;
	
	/** The sessies. */
	private int sessies;

	/**
	 * Instantiates a new behandeling.
	 *
	 * @param fysioPraktijkNummer the fysio praktijk nummer
	 * @param behandelingId the behandeling id
	 * @param behandelCode the behandel code
	 * @param behandelStartDatum the behandel start datum
	 * @param behandelEindDatum the behandel eind datum
	 * @param BSN the bsn
	 * @param afspraakIDs the afspraak i ds
	 * @param totaalprijs the totaalprijs
	 * @param sessies the sessies
	 */
	public Behandeling(String fysioPraktijkNummer, String behandelingId, String behandelCode,
			String behandelStartDatum, String behandelEindDatum, String BSN,  ArrayList<String> afspraakIDs, double totaalprijs, int sessies) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
		this.behandelingId = behandelingId;
		this.behandelCode = behandelCode;
		this.behandelStartDatum = behandelStartDatum;
		this.behandelEindDatum = behandelEindDatum;
		this.BSN = BSN;
		this.afspraakIDs = afspraakIDs;
		this.totaalprijs = totaalprijs;
		this.sessies = sessies;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getTotaalprijs()
	 */
	public double getTotaalprijs() {
		return totaalprijs;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setTotaalprijs(double)
	 */
	public void setTotaalprijs(double totaalprijs) {
		this.totaalprijs = totaalprijs;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getSessies()
	 */
	public int getSessies() {
		return sessies;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setSessies(int)
	 */
	public void setSessies(int sessies) {
		this.sessies = sessies;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getBehandelingen()
	 */
	@Override
	public Behandeling getBehandelingen() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getFysioPraktijkNummer()
	 */
	public String getFysioPraktijkNummer() {
		return fysioPraktijkNummer;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setFysioPraktijkNummer(java.lang.String)
	 */
	public void setFysioPraktijkNummer(String fysioPraktijkNummer) {
		this.fysioPraktijkNummer = fysioPraktijkNummer;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getBehandelCode()
	 */
	public String getBehandelCode() {
		return behandelCode;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setBehandelCode(java.lang.String)
	 */
	public void setBehandelCode(String behandelCode) {
		this.behandelCode = behandelCode;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getBehandelStartDatum()
	 */
	public String getBehandelStartDatum() {
		return behandelStartDatum;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setBehandelStartDatum(java.lang.String)
	 */
	public void setBehandelStartDatum(String behandelStartDatum) {
		this.behandelStartDatum = behandelStartDatum;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getBehandelEindDatum()
	 */
	public String getBehandelEindDatum() {
		return behandelEindDatum;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setBehandelEindDatum(java.lang.String)
	 */
	public void setBehandelEindDatum(String behandelEindDatum) {
		this.behandelEindDatum = behandelEindDatum;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#getBSN()
	 */
	public String getBSN() {
		return BSN;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableBehandeling#setBSN(java.lang.String)
	 */
	public void setBSN(String bSN) {
		BSN = bSN;
	}
	
	/**
	 * Gets the afspraak i ds.
	 *
	 * @return the afspraak i ds
	 */
	public ArrayList<String> getAfspraakIDs(){
		return afspraakIDs;
	}
	
	/**
	 * Gets the behandeling id.
	 *
	 * @return the behandeling id
	 */
	public String getbehandelingId(){
		return behandelingId;
	}

}
