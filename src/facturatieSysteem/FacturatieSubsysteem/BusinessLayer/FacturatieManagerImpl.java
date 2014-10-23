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

public class FacturatieManagerImpl implements FacturatieManager {
	private DAOFactoryFactuur daoFactoryBehandelcodes = new DAOFactoryFactuur("XML/behandelcodes.xml", "XML/behandelcodes.xsd");
	private DAOFactoryFactuur daoFactoryClient = new DAOFactoryFactuur("XML/ClientFormat.xml", "XML/ClientFormat.xsd");
	private DAOFactoryFactuur daoFactoryFacturatie = new DAOFactoryFactuur("XML/facturatieSysteem.xml", "XML/facturatieSysteem.xsd");
	private FactuurDAO factuurDAO;
	private BehandelingDAO behandelingDAO;
	private ArrayList<Factuur> facturen;

	public FacturatieManagerImpl() {
		this.factuurDAO = new FactuurDAO(daoFactoryBehandelcodes, daoFactoryClient, daoFactoryFacturatie);
		this.behandelingDAO = new BehandelingDAO(daoFactoryBehandelcodes, daoFactoryClient, daoFactoryFacturatie);
		daoFactoryBehandelcodes.validateXML();
		daoFactoryClient.validateXML();
		daoFactoryFacturatie.validateXML();
		facturen = new ArrayList<>();
	
	}

	@Override
	public boolean factureer(Klant klant, ArrayList<Behandeling> behandelingen) {
		//Nieuw factuurnummer aanmaken
		facturen = factuurDAO.haalFacturen(klant.getBSN());
		int n1 = 0;
		int n2 = 0;
		for (Factuur lijstFactuur : facturen) {
			n1 = Integer.parseInt(lijstFactuur.getFactuurNummer());

			if (n1 >= n2) {
				n2 = n1;
			}
		}
		String factuurNummer = Integer.toString(n2);
		
		//De factuurdatum aanmaken
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String vandaag = dateFormat.format(cal.getTime());
		
		//De vervaldatum berekenen en aanmaken
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 14); // Adding 14 days
		String vDatum = sdf.format(c.getTime());
		
		//BSN aanmaken en hier de waarde van de meegegeven klant aan toekennen
		String BSN = klant.getBSN();
		
		double oudRisico = klant.getResterendEigenRisico();
		double totalePrijs = 00;
		
		for(Behandeling behandeling : behandelingen){
			
			double tijdelijkRisico = behandelingDAO.getPrijs(behandeling.getBehandelCode());
			totalePrijs += tijdelijkRisico;
		
		}
		return true;
	}

	@Override
	public void controleerBehandelingen(ArrayList<Behandeling> behandelingen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		// TODO Auto-generated method stub
		return factuurDAO.haalFacturen(invoerBSN);
	}

}
