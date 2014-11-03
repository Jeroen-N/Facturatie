package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.util.ArrayList;

public interface ImmutableFactuur {

	public String getStatus();

	public void setStatus(String status);

	public ArrayList<Behandeling> getBehandelingen();

	public void setBehandelingen(ArrayList<Behandeling> behandelingen);

	public double getVergoedeBedrag();

	public void setVergoedeBedrag(double vergoedeBedrag);

	public void berekenBTW();

	public void berekenEigenRisico();

	public String getFactuurNummer();

	public void setFactuurNummer(String factuurNummer);

	public String getFactuurDatum();

	public void setFactuurDatum(String factuurDatum);

	public String getVervalDatum();

	public void setVervalDatum(String vervalDatum);

	public String getBSN();

	public void setBSN(String bSN);

	public String toString(Factuur factuur, String behandelingen);
	
	public double getTotaalPrijs();


	public void setTotaalPrijs(double totaalPrijs);

}
