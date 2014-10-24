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
				daoFactoryClient, daoFactoryFacturatie);
		// daoFactoryBehandelcodes.validateXML();
		// daoFactoryClient.validateXML();
		// daoFactoryFacturatie.validateXML();
		facturen = new ArrayList<>();
		Behandelingen = new ArrayList<>();

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

		double oudRisico = klant.getResterendEigenRisico();
		double totalePrijs = 00;
		String type = "";
		double teVergoedenPrijs = 00;
		for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
			type = polis.getVerzekeringsType();

		}
		//Verzekeringsmaatschappij m1 = new Verzekeringsmaatschappij("maatschappij 1", "adres", "postcode", "plaats", 999, 486);
		//for (Verzekeringsmaatschappij maatschappij : verzekeringsmanager
			//	.getVerzekeringsmaatschappijen()) {
			//verzekering = verzekeringsmanager.getVerzekeringstype(maatschappij,
				//	type);
			ArrayList<String> behandelcodes;
			behandelcodes = new ArrayList<>();
			behandelcodes.add("001");
			verzekering = new Verzekeringstype(666, 500, "Verzekering 1", behandelcodes);
			System.out.println(verzekering.getBehandelcodes());
		//}
		ArrayList<Behandeling> behandelingenlijst = new ArrayList<>();

		behandelingenlijst = behandelingDAO.getBehandelingen(klant);

		for (Behandeling behandeling : behandelingenlijst) {

			for (String code : verzekering.getBehandelcodes()) {

				if (behandeling.getBehandelCode().equals(code)) {
					// Behandeling wordt vergoed

				} else {
					// Behandeling wordt niet vergoed
					double tijdelijkRisico = behandelingDAO
							.getPrijs(behandeling.getBehandelCode())
							* behandeling.getSessies();
					totalePrijs += tijdelijkRisico;
				}
			}
			if (klant.getResterendEigenRisico() != 0) {
				if (klant.getResterendEigenRisico() >= totalePrijs) {
					klant.setTotaalEigenRisico(klant.getResterendEigenRisico() - totalePrijs);
					System.out.println(teVergoedenPrijs);
				}
				else{
					teVergoedenPrijs += totalePrijs-klant.getResterendEigenRisico();
					klant.setTotaalEigenRisico(0);
					
				}
			}
			else{
				teVergoedenPrijs += totalePrijs;
				System.out.println("Vergoede prijs: " + teVergoedenPrijs);
			}
			behandeling.setTotaalprijs(totalePrijs);
			System.out.println("Totaalprijs behandelingen: " + behandeling.getTotaalprijs());
			totalePrijs = 00;
		}
		Factuur f = new Factuur(factuurNummer, vandaag, vDatum, BSN, teVergoedenPrijs, behandelingenlijst, "Niet betaald");
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
	
	
	public String loopBehandelingen(Factuur factuur){
		String naam = "";
		Behandelingen = factuur.getBehandelingen();
		for(Behandeling behandeling : Behandelingen){
			String code = behandeling.getBehandelCode();
				for(int i = 0; i < Behandelingen.size(); i++){
			
			naam += behandelingDAO.getNaam(code) + "\n";
		}
		}
		
	
		return "Behandelingen: \t"	 			+ 	naam;
		
        
		
	}
}
