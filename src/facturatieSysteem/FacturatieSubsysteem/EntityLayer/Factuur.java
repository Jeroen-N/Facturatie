package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Factuur implements ImmutableFactuur {

	private String factuurNummer;
	private String factuurDatum;
	private String vervalDatum;
	private String BSN;
	private ArrayList<Behandeling> behandelingen;
	private double vergoedeBedrag;
	private String status;
	private String s;

	public Factuur(String factuurNummer, String factuurDatum, String vervalDatum, String BSN, double vergoedeBedrag, ArrayList<Behandeling> behandelingen, String status) {
		this.factuurNummer = factuurNummer;
		this.factuurDatum = factuurDatum;
		this.vervalDatum = vervalDatum;
		this.BSN = BSN;
		this.vergoedeBedrag = vergoedeBedrag;
		this.behandelingen = behandelingen;
		this.status = status;
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

	public String toString(Factuur factuur){
		NumberFormat getallenOpmaker = new DecimalFormat("###,##0.00");
        String risk = getallenOpmaker.format(vergoedeBedrag);
		return "Factuurnummer: \t" 				+ 	factuurNummer 				+ "\n" +
				"Factuurdatum: \t\t" 			+ 	factuurDatum 				+ "\n" +
				"Vervaldatum: \t\t"				+	vervalDatum					+ "\n" +
				"Resterend eigen risico: \t"	+ "\u20ac" +	risk			+ "\n" +
				"Status: \t\t"					+ 	status						+ "\n" +
				loopBehandelingen(factuur);
	}
	
	public String loopBehandelingen(Factuur factuur){
		System.out.println(factuur.getBehandelingen().size());
		behandelingen = factuur.getBehandelingen();
		for(Behandeling b : behandelingen){
			 s = b.getBehandelingNaam();
		}
		return "Behandelingen: \t"	 			+ 	s	+ "\n" ;
		
        
		
	}
}
