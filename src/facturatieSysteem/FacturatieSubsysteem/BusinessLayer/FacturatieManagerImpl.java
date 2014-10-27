package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.DAOFactoryFactuur;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.FactuurDAO;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class FacturatieManagerImpl implements FacturatieManager {
	private DAOFactoryFactuur daoFactoryBehandelcodes = new DAOFactoryFactuur(
			"XML/behandelcodes.xml", "XML/behandelcodes.xsd");
	private DAOFactoryFactuur daoFactoryClient = new DAOFactoryFactuur(
			"XML/ClientFormat.xml", "XML/ClientFormat.xsd");
	private DAOFactoryFactuur daoFactoryFacturatie = new DAOFactoryFactuur(
			"XML/facturatieSysteem.xml", "XML/facturatieSysteem.xsd");
	private FactuurDAO factuurDAO;
	private BehandelingDAO behandelingDAO;
	private ArrayList<Factuur> facturen;
	private Verzekeringstype verzekering;
	private ArrayList<Behandeling> Behandelingen;

	public FacturatieManagerImpl() {
		this.factuurDAO = new FactuurDAO(daoFactoryBehandelcodes,
				daoFactoryClient, daoFactoryFacturatie);
		this.behandelingDAO = new BehandelingDAO(daoFactoryBehandelcodes,
				daoFactoryClient);
		// daoFactoryBehandelcodes.validateXML();
		// daoFactoryClient.validateXML();
		// daoFactoryFacturatie.validateXML();
		facturen = new ArrayList<>();
		Behandelingen = new ArrayList<>();

	}

	public BehandelingDAO getBDAO(){
		return behandelingDAO;
	}
	
	@Override
	public Factuur factureer(Klant klant,
			VerzekeringsmaatschappijManager verzekeringsmanager) {
		// Nieuw factuurnummer aanmaken
		facturen = factuurDAO.haalAlleFacturen();
		int n1 = 0;
		int n2 = 0;
		for (Factuur lijstFactuur : facturen) {
			n1 = Integer.parseInt(lijstFactuur.getFactuurNummer());

			if (n1 >= n2) {
				n2 = n1;
			}
		}
		String factuurNummer = Integer.toString(n2 + 1);

		// De factuurdatum aanmaken
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String vandaag = dateFormat.format(cal.getTime());

		// De vervaldatum berekenen en aanmaken
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 14); // Adding 14 days
		String vDatum = sdf.format(c.getTime());

		// BSN aanmaken en hier de waarde van de meegegeven klant aan toekennen
		String BSN = klant.getBSN();
		double totalePrijs = 00;
		double teVergoedenPrijs = 00;
		String polisNaam = "";
		for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
			polisNaam = polis.getVerzekeringsType();
		}
		for (Verzekeringsmaatschappij maatschappij : verzekeringsmanager.getVerzekeringsmaatschappijen()) {
			for (Verzekeringstype type : maatschappij.getTypes()) {
				if (polisNaam.equals(type.getNaam())) {
					verzekering = verzekeringsmanager.getVerzekeringstypeByName(maatschappij, polisNaam);
					
				}
			}
		}
		ArrayList<Behandeling> behandelingenlijst = new ArrayList<>();
		int z = 0;
		double totaalPrijsFactuur = 0;
		behandelingenlijst = behandelingDAO.getBehandelingen(klant);
		for (Behandeling behandeling : behandelingenlijst) {	
			totalePrijs = 00;	
			for (String code : verzekering.getBehandelcodes()) {
				z = 0;
				if (behandeling.getBehandelCode().equals(code)) {	
					// Behandeling wordt standaard vergoed
					double p = behandelingDAO.getPrijs(behandeling.getBehandelCode()) * behandeling.getSessies();
					//teVergoedenPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode()) * behandeling.getSessies() - klant.getResterendEigenRisico();
					if (klant.getResterendEigenRisico() != 0) {
						if (klant.getResterendEigenRisico() >= (p)) {
							klant.setTotaalEigenRisico(klant.getResterendEigenRisico()- (p));
							totalePrijs += p;
						}else{
							teVergoedenPrijs += p - klant.getResterendEigenRisico();
							totalePrijs = klant.getResterendEigenRisico();
							klant.setTotaalEigenRisico(0);
						}
					}else{
						//Eigenrisico = 0
						teVergoedenPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode()) * behandeling.getSessies();
					}
					
					
					
					
					z=1;
					break;
				}
			}
			if (z == 0){
				
				// Behandeling wordt niet standaard vergoed
				System.out.println("prijs voor behandeling"+behandelingDAO.getPrijs(behandeling.getBehandelCode()));
				double tijdelijkRisico = behandelingDAO.getPrijs(behandeling.getBehandelCode())* behandeling.getSessies();
				totalePrijs += tijdelijkRisico;
				
				if (klant.getResterendEigenRisico() != 0) {
					
					if (klant.getResterendEigenRisico() >= totalePrijs) {
						

					} else {
						teVergoedenPrijs += totalePrijs- klant.getResterendEigenRisico();
						klant.setTotaalEigenRisico(0);	
					}
				}else{
					
				}
			}else{
				z= 0;
			}
			
			behandeling.setTotaalprijs(totalePrijs);
			totaalPrijsFactuur += totalePrijs;
			System.out.println("Totaalprijs behandelingen: "+ behandeling.getTotaalprijs());
				
		}
		Factuur f = new Factuur(factuurNummer, vandaag, vDatum, BSN,
				teVergoedenPrijs, behandelingenlijst, "Niet betaald", totaalPrijsFactuur);
		factuurDAO.maakFactuur(klant, f);
		return f;
	}

	@Override
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		return factuurDAO.haalFacturen(invoerBSN);
	}

	@Override
	public String toonFactuur(String factuur_nummer, Klant klant) {
		facturen = haalFacturen(klant.getBSN());
		for (Factuur factuur : facturen) {
			if (factuur.getFactuurNummer().equals(factuur_nummer)) {
				return factuur.toString(factuur, loopBehandelingen(factuur));
			}
		}
		System.out.println("leeg");
		return "niks gevonden";
	}

	public Factuur getFactuur(String factuur_nummer, Klant klant) {
		facturen = haalFacturen(klant.getBSN());
		for (Factuur factuur : facturen) {
			if (factuur.getFactuurNummer().equals(factuur_nummer)) {
				return factuur;
			}
		}
		System.out.println("leeg");
		return null;
	}
	
	public String loopBehandelingen(Factuur factuur) {
		String naam = "";
		Behandelingen = factuur.getBehandelingen();
		for (Behandeling behandeling : Behandelingen) {
			String code = behandeling.getBehandelCode();
			// for(int i = 0; i < Behandelingen.size(); i++){

			naam += "Behandelingen: \t" + behandelingDAO.getNaam(code) + "\n";
			// }

		}

		return naam;
	}
	
	public double getTotaalPrijs(Factuur factuur){
		double totPrijs = 0.0;
		for(Behandeling behandeling : factuur.getBehandelingen()){
			totPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode())* behandeling.getSessies();
		}
		return totPrijs;
	}
	
	public double getTotaalinclBTW(Factuur factuur){
		double totPrijs = 0.0;
		double totPrijsBTW = 0.0;
		double btw = 1.21;
		for(Behandeling behandeling : factuur.getBehandelingen()){
			totPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode())* behandeling.getSessies();
		}
		totPrijsBTW = totPrijs * btw;
		return totPrijsBTW;
	}
}
