package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

// TODO: Auto-generated Javadoc
/**
 * The Class Factuur.
 */
public class Factuur implements ImmutableFactuur {

	/** The factuur nummer. */
	private String factuurNummer;
	
	/** The factuur datum. */
	private String factuurDatum;
	
	/** The verval datum. */
	private String vervalDatum;
	
	/** The bsn. */
	private String BSN;
	
	/** The behandelingen. */
	private ArrayList<Behandeling> behandelingen;
	
	/** The vergoede bedrag. */
	private double vergoedeBedrag;
	
	/** The status. */
	private String status;
	
	/** The totaal prijs. */
	private double totaalPrijs;

	/**
	 * Instantiates a new factuur.
	 *
	 * @param factuurNummer the factuur nummer
	 * @param factuurDatum the factuur datum
	 * @param vervalDatum the verval datum
	 * @param BSN the bsn
	 * @param vergoedeBedrag the vergoede bedrag
	 * @param behandelingen the behandelingen
	 * @param status the status
	 * @param totaalPrijs the totaal prijs
	 */
	public Factuur(String factuurNummer, String factuurDatum, String vervalDatum, String BSN, double vergoedeBedrag, ArrayList<Behandeling> behandelingen, String status, double totaalPrijs) {
		this.factuurNummer = factuurNummer;
		this.factuurDatum = factuurDatum;
		this.vervalDatum = vervalDatum;
		this.BSN = BSN;
		this.vergoedeBedrag = vergoedeBedrag;
		this.behandelingen = behandelingen;
		this.status = status;
		this.totaalPrijs = totaalPrijs;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getTotaalPrijs()
	 */
	public double getTotaalPrijs() {
		return totaalPrijs;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setTotaalPrijs(double)
	 */
	public void setTotaalPrijs(double totaalPrijs) {
		this.totaalPrijs = totaalPrijs;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getStatus()
	 */
	public String getStatus() {
		return status;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setStatus(java.lang.String)
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getBehandelingen()
	 */
	public ArrayList<Behandeling> getBehandelingen() {
		return behandelingen;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setBehandelingen(java.util.ArrayList)
	 */
	public void setBehandelingen(ArrayList<Behandeling> behandelingen) {
		this.behandelingen = behandelingen;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getVergoedeBedrag()
	 */
	public double getVergoedeBedrag() {
		return vergoedeBedrag;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setVergoedeBedrag(double)
	 */
	public void setVergoedeBedrag(double vergoedeBedrag) {
		this.vergoedeBedrag = vergoedeBedrag;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#berekenBTW()
	 */
	@Override
	public void berekenBTW() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#berekenEigenRisico()
	 */
	@Override
	public void berekenEigenRisico() {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getFactuurNummer()
	 */
	public String getFactuurNummer() {
		return factuurNummer;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setFactuurNummer(java.lang.String)
	 */
	public void setFactuurNummer(String factuurNummer) {
		this.factuurNummer = factuurNummer;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getFactuurDatum()
	 */
	public String getFactuurDatum() {
		return factuurDatum;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setFactuurDatum(java.lang.String)
	 */
	public void setFactuurDatum(String factuurDatum) {
		this.factuurDatum = factuurDatum;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getVervalDatum()
	 */
	public String getVervalDatum() {
		return vervalDatum;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setVervalDatum(java.lang.String)
	 */
	public void setVervalDatum(String vervalDatum) {
		this.vervalDatum = vervalDatum;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#getBSN()
	 */
	public String getBSN() {
		return BSN;
	}


	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#setBSN(java.lang.String)
	 */
	public void setBSN(String bSN) {
		BSN = bSN;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.EntityLayer.ImmutableFactuur#toString(facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur, java.lang.String)
	 */
	public String toString(Factuur factuur, String behandelingen){
		NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        String totaalPrijs1	 = getallenOpmaker.format(totaalPrijs);
        String vergoedeBedrag1	 = getallenOpmaker.format(vergoedeBedrag);
		return "Factuurnummer: \t" 				+ 	factuurNummer 				+ "\n" +
				"Factuurdatum: \t\t" 			+ 	factuurDatum 				+ "\n" +
				"Vervaldatum: \t\t"				+	vervalDatum					+ "\n" +
				"Tebetalen: \t\t"					+ "\u20ac" +	totaalPrijs1			+ "\n" +
				"Vergoede bedrag: \t"			+ "\u20ac" +	vergoedeBedrag1		+ "\n" +
				"Status: \t\t"					+ 	status						+ "\n" +
				behandelingen;
	}
	
	/**
	 * To string bon.
	 *
	 * @param klant the klant
	 * @return the string
	 */
	public String toStringBon(Klant klant){
		NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        String risk = getallenOpmaker.format(klant.getResterendEigenRisico());
		return  "Burger service nummer: \t" 		+ klant.getBSN() 			+ "\n" +
				"Factuurnummer: \t\t"			+ factuurNummer 			+ "\n" +
				"Factuurdatum: \t\t"			+ factuurDatum				+ "\n" +
				"Vervaldatum: \t\t"				+ vervalDatum				+ "\n" +
				"Resterend eigen risico: \t"	+ "\u20ac" + risk;
	}
	
	
}
