package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.FactuurDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FacturatieManagerImpl implements FacturatieManager {

	private FactuurDAO factuurDAO;
	private ArrayList<Factuur> facturen;

	public FacturatieManagerImpl() {
		this.factuurDAO = new FactuurDAO();

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
		
		String BSN = klant.getBSN();
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
