package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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

// TODO: Auto-generated Javadoc
/**
 * The Class FacturatieManagerImpl.
 */
public class FacturatieManagerImpl implements FacturatieManager {
	
	/** The dao factory behandelcodes. */
	private DAOFactoryFactuur daoFactoryBehandelcodes = new DAOFactoryFactuur(
			"XML/behandelcodes.xml", "XML/behandelcodes.xsd");
	
	/** The dao factory client. */
	private DAOFactoryFactuur daoFactoryClient = new DAOFactoryFactuur(
			"XML/ClientFormat.xml", "XML/ClientFormat.xsd");
	
	/** The dao factory facturatie. */
	private DAOFactoryFactuur daoFactoryFacturatie = new DAOFactoryFactuur(
			"XML/facturatieSysteem.xml", "XML/facturatieSysteem.xsd");
	
	/** The factuur dao. */
	private FactuurDAO factuurDAO;
	
	/** The behandeling dao. */
	private BehandelingDAO behandelingDAO;
	
	/** The facturen. */
	private ArrayList<Factuur> facturen;
	
	/** The verzekering. */
	private Verzekeringstype verzekering;
	
	/** The Behandelingen. */
	private ArrayList<Behandeling> Behandelingen;

	/**
	 * Instantiates a new facturatie manager impl.
	 */
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

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#getBDAO()
	 */
	public BehandelingDAO getBDAO(){
		return behandelingDAO;
	}
	
	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#factureer(facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant, facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager)
	 */
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
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		String vandaag = dateFormat.format(cal.getTime());

		// De vervaldatum berekenen en aanmaken
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
		//TODO behandelingen ophalen uit andere systeem;
		
		Map<String, ArrayList<ArrayList<String>>> behandelingen = null;
		for(String behandeling : behandelingen.keySet()){
			ArrayList<String> afspraakIDs = new ArrayList<String>();
			String code = null;
			for (ArrayList<String> afspraak : behandelingen.get(behandeling)){
				afspraakIDs.add(afspraak.get(1));
				code = afspraak.get(7);
			}
			behandelingenlijst.add(new Behandeling(null,behandeling,code,null,null,klant.getBSN(),afspraakIDs,00,afspraakIDs.size()));
		}
		
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
				
		}
		if (behandelingenlijst.size() != 0){
		Factuur f = new Factuur(factuurNummer, vandaag, vDatum, BSN,
				teVergoedenPrijs, behandelingenlijst, "Niet betaald", totaalPrijsFactuur);
		factuurDAO.maakFactuur(klant, f);
		
		return f;
		}
		return null;
		
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#haalFacturen(java.lang.String)
	 */
	@Override
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		
		 ArrayList<Factuur> facturen = factuurDAO.haalFacturen(invoerBSN);
		 for (Factuur factuur: facturen){
			 for (Behandeling behandeling : factuur.getBehandelingen()){
				 String BSN = behandeling.getBSN();
				 String behandelingId = behandeling.getbehandelingId();
				 ArrayList<String> afspraakIDs = behandeling.getAfspraakIDs();			 
				 
				 //TODO rest van info ophalen uit ander systeem
				 //behandeling.setBehandelCode(behandelCode);
			 }
		 }
		 return facturen;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#toonFactuur(java.lang.String, facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant)
	 */
	@Override
	public String toonFactuur(String factuur_nummer, Klant klant) {
		facturen = haalFacturen(klant.getBSN());
		for (Factuur factuur : facturen) {
			if (factuur.getFactuurNummer().equals(factuur_nummer)) {
				return factuur.toString(factuur, loopBehandelingen(factuur));
			}
		}
		return "niks gevonden";
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#getFactuur(java.lang.String, facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant)
	 */
	public Factuur getFactuur(String factuur_nummer, Klant klant) {
		facturen = haalFacturen(klant.getBSN());
		for (Factuur factuur : facturen) {
			if (factuur.getFactuurNummer().equals(factuur_nummer)) {
				return factuur;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#loopBehandelingen(facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur)
	 */
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
	
	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#getTotaalPrijs(facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur)
	 */
	public double getTotaalPrijs(Factuur factuur){
		double totPrijs = 0.0;
		for(Behandeling behandeling : factuur.getBehandelingen()){
			totPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode())* behandeling.getSessies();
		}
		return totPrijs;
	}
	
	/* (non-Javadoc)
	 * @see facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager#getTotaalinclBTW(facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur)
	 */
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
