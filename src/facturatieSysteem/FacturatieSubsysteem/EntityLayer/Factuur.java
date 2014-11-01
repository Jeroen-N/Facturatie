package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class Factuur implements ImmutableFactuur {

	private String factuurNummer;
	private String factuurDatum;
	private String vervalDatum;
	private String BSN;
	private ArrayList<Behandeling> behandelingen;
	private double vergoedeBedrag;
	private String status;
	private double totaalPrijs;

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


	public double getTotaalPrijs() {
		return totaalPrijs;
	}


	public void setTotaalPrijs(double totaalPrijs) {
		this.totaalPrijs = totaalPrijs;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ArrayList<Behandeling> getBehandelingen() {
		return behandelingen;
	}


	public void setBehandelingen(ArrayList<Behandeling> behandelingen) {
		this.behandelingen = behandelingen;
	}


	public double getVergoedeBedrag() {
		return vergoedeBedrag;
	}


	public void setVergoedeBedrag(double vergoedeBedrag) {
		this.vergoedeBedrag = vergoedeBedrag;
	}


	@Override
	public void berekenBTW() {
		// TODO Auto-generated method stub

	}

	@Override
	public void berekenEigenRisico() {
		// TODO Auto-generated method stub

	}


	public String getFactuurNummer() {
		return factuurNummer;
	}


	public void setFactuurNummer(String factuurNummer) {
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
