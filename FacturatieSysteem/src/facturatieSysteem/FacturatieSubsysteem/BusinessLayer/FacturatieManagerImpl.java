package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import facturatie.client.Client;
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
 * The Class FacturatieManagerImpl which implements the FacturatieManager interface.
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
	private Client client = new Client();


	/*The constructor method to create an object of FacturatieManagerImpl
	 * 
	 */

	private HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> klanten = new HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>>() ;



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

	/**
	 * Returns the object of the type behandelingDAO
	 * 
	 * @return behandelingDAO
	 */
	public BehandelingDAO getBDAO() {
		return behandelingDAO;
	}

	/**
	 * Creates a new factuur by the given customer and the manager of the insurance company.
	 * 
	 * @param klant
	 *            the client
	 * @param verzekeringsmanager
	 *            the insurance manager
	 * @return the new factuur
	 */
	@Override
	public Factuur factureer(Klant klant,
			VerzekeringsmaatschappijManager verzekeringsmanager) {
		String[] args = {};
		client.main(args);
		klanten = client.getGegevens();
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
		//Loop door de verzekeringspolissen heen van de klant en haal het verzekeringstype
		//en zet dit in een variabele genaamd polisnaam.
		for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
			polisNaam = polis.getVerzekeringsType();
		}
		
		//Loop door de verzekeringsmaatschappijen heen
		for (Verzekeringsmaatschappij maatschappij : verzekeringsmanager
				.getVerzekeringsmaatschappijen()) {
			//Loop per verzekeringsmaatschappij door alle types
			for (Verzekeringstype type : maatschappij.getTypes()) {
				//Als de polisnaam van de klant overeen komt met de naam van de verzekeringsmaatschappij
				//vul dan het Verzekeringstype veld verzekering met het type dat opgehaald
				//wordt bij de betreffende manager met de maatschappij en de polisnaam die eerder gevonden zijn.
				if (polisNaam.equals(type.getNaam())) {
					verzekering = verzekeringsmanager
							.getVerzekeringstypeByName(maatschappij, polisNaam);
				}
			}
		}
		//Initialiseer de lijst van behandelingen en maak lokale variabelen z en totaalPrijsFactuur aan.
		ArrayList<Behandeling> behandelingenlijst = new ArrayList<>();
		int z = 0;
		double totaalPrijsFactuur = 0;
		
		
		ArrayList<String> afspraakIDs1 = new ArrayList<String>();
		afspraakIDs1.add("0");
		for (Factuur factuur : haalFacturen(klant.getBSN())){
			for(Behandeling behandeling : factuur.getBehandelingen()){
				for (String afspraakID : behandeling.getAfspraakIDs()){
					afspraakIDs1.add(afspraakID);
				}
			}
		}
		
		for(String bsn : klanten.keySet()){
			System.out.println("BSN: "+ bsn);
			if (bsn.equals(klant.getBSN())){
				HashMap<String, ArrayList<ArrayList<String>>> behandelingen = klanten.get(klant.getBSN());
				System.out.println("behandelingen: "+behandelingen.toString());
				for(String behandeling : behandelingen.keySet()){

					ArrayList<String> afspraakIDs = new ArrayList<String>();
					String code = null;
					for (ArrayList<String> afspraak : behandelingen.get(behandeling)){
						for (String afspraakID : afspraakIDs1){
							if(afspraakID != afspraak.get(1)){
								afspraakIDs.add(afspraak.get(1));
								
							}
						}
						code = afspraak.get(7);
					}
					
					if (afspraakIDs.size() > 0){
						behandelingenlijst.add(new Behandeling(null,behandeling,code,null,null,klant.getBSN(),afspraakIDs,00,afspraakIDs.size()));
					}
				}
			}
		}
		
		if(behandelingenlijst.size() > 0){

		for (Behandeling behandeling : behandelingenlijst) {
			totalePrijs = 00;
			//Zet de int totalePrijs op 00;
			//Voor elke behandelingscodes die horen bij het verzekeringstype
			for (String code : verzekering.getBehandelcodes()) {
				z = 0;
				//Als de code van de behandeling gelijk is aan de code van het verzekeringstype
				if (behandeling.getBehandelCode().equals(code)) {
					// Behandeling wordt standaard vergoed
					//Maak een lokale double aan genaamd p en haal hier de prijs van een behandeling aan
					//vermenigvuldigd met het aantal sessies 
					double p = behandelingDAO.getPrijs(behandeling
							.getBehandelCode()) * behandeling.getSessies();
					//Als het resterend eigen risico van de klant niet 0 is
					if (klant.getResterendEigenRisico() != 0) {
						//Als het resterend eigen risico van de klant groter is dan de waarde van p
						if (klant.getResterendEigenRisico() >= (p)) {
							//Maak het eigen risico als gevolg van het oude resterend eigen risico minus de waarde van p
							klant.setTotaalEigenRisico(klant
									.getResterendEigenRisico() - (p));
							//Verhoog de totale prijs met de waarde van p
							totalePrijs += p;
						} 
						//Het resterend eigen risico is niet groter dan de waarde van p
						else {
							//De tevergoeden prijs wordt opgehoogd met de waarde van p minus het resterend eigen risico
							teVergoedenPrijs += p
									- klant.getResterendEigenRisico();
							//De prijs van de factuur wordt het nu nog resterend eigen risico van de klant.
							totalePrijs = klant.getResterendEigenRisico();
							klant.setTotaalEigenRisico(0);
						}
					} else {
						// Eigenrisico = 0
						teVergoedenPrijs += behandelingDAO.getPrijs(behandeling
								.getBehandelCode()) * behandeling.getSessies();
					}
					z = 1;
					break;
				}
			}
			if (z == 0) {

				// Behandeling wordt niet standaard vergoed
				double tijdelijkRisico = behandelingDAO.getPrijs(behandeling
						.getBehandelCode()) * behandeling.getSessies();
				totalePrijs += tijdelijkRisico;

				if (klant.getResterendEigenRisico() != 0) {

					if (klant.getResterendEigenRisico() >= totalePrijs) {

					} else {
						teVergoedenPrijs += totalePrijs
								- klant.getResterendEigenRisico();
						klant.setTotaalEigenRisico(0);
					}
				} else {
					
				}
			} else {
				z = 0;
			}
			behandeling.setTotaalprijs(totalePrijs);
			totaalPrijsFactuur += totalePrijs;

		}

		Factuur f = new Factuur(factuurNummer, vandaag, vDatum, BSN,
				teVergoedenPrijs, behandelingenlijst, "Niet betaald",
				totaalPrijsFactuur);
		factuurDAO.maakFactuur(klant, f);
		teVergoedenPrijs = 00;
		totaalPrijsFactuur = 00;
		return f;

		}
		return null;

	}

	/**
	 * Gets an arraylist of facturen matched with the BSN from the customer
	 * 
	 * @param invoerBSN
	 *            the bsn of the client
	 * @return arraylist<factuur> the list of facturen linked to the client
	 */
	@Override
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {

		String[] args = {};
		client.main(args);
		klanten = client.getGegevens();
		 ArrayList<Factuur> facturen = factuurDAO.haalFacturen(invoerBSN);
		 for (Factuur factuur: facturen){
			 for (Behandeling behandeling : factuur.getBehandelingen()){
				for(String bsn : klanten.keySet()){
					System.out.println("BSN: "+ bsn);
					if (bsn.equals(invoerBSN)){
						HashMap<String, ArrayList<ArrayList<String>>> behandelingen = klanten.get(invoerBSN);
						System.out.println("behandelingen: "+behandelingen.toString());
						for(String behandelingStr: behandelingen.keySet()){
							for (ArrayList<String> afspraak : behandelingen.get(behandelingStr)){
								behandeling.setBehandelCode(afspraak.get(7));
							}
						}
					}	
				}
			 }
		 }
		 return facturen;

	}

	/**
	 * Shows the factuur on the gui with the given number of the factuur and the customer
	 *
	 * @param factuur_nummer
	 *            number of the factuur
	 * @param klant
	 *            the client we need the factuur from
	 * @return String to show the factuur in the gui
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

	/**
	 * Get the factuur with the given number of factuur and the customer
	 *
	 * @param factuur_nummer
	 *            the number we use to search the specified factuur.
	 * @param klant
	 *            the client we used to lookup the specified factuur.
	 * 
	 * @return factuur the factuur we looked for
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

	/**
	 * Loops through the specified factuur to show all the treatments and returns a String with the name of the treatment
	 * 
	 * @param factuur
	 *            the factuur we loop through
	 * @return string a string containing the treatment
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

	/**
	 * Gets the total price off the factuur
	 * 
	 * @param factuur
	 *            the factuur used to get te total price
	 * @return double returns the price
	 */
	public double getTotaalPrijs(Factuur factuur) {
		double totPrijs = 0.0;
		for (Behandeling behandeling : factuur.getBehandelingen()) {
			totPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode())
					* behandeling.getSessies();
		}
		return totPrijs;
	}

	/**
	 * Gets total price incl. tax of the given object from factuur
	 * 
	 * @param factuur
	 *            the factuur we use to get the price.
	 * @return double the price
	 */
	public double getTotaalinclBTW(Factuur factuur) {
		double totPrijs = 0.0;
		double totPrijsBTW = 0.0;
		double btw = 1.21;
		for (Behandeling behandeling : factuur.getBehandelingen()) {
			totPrijs += behandelingDAO.getPrijs(behandeling.getBehandelCode())
					* behandeling.getSessies();
		}
		totPrijsBTW = totPrijs * btw;
		return totPrijsBTW;
	}
}
