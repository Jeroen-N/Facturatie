package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BehandelingDAO implements BehandelDAOinf {

	private Document document = null;
	private DAOFactoryFactuur daoFactoryBehandelcode;
	private DAOFactoryFactuur daoFactoryClient;
	private DAOFactoryFactuur daoFactoryFacturatie;

	public BehandelingDAO(DAOFactoryFactuur daoFactoryBehandelcode,
			DAOFactoryFactuur daoFactoryClient,
			DAOFactoryFactuur daoFactoryFacturatie) {
		this.daoFactoryBehandelcode = daoFactoryBehandelcode;
		this.daoFactoryClient = daoFactoryClient;
		this.daoFactoryFacturatie = daoFactoryFacturatie;
	}

	public double getPrijs(String invoerbehandelCode) {
		document = daoFactoryBehandelcode.getDocument();
		System.out.println(invoerbehandelCode);
		double tarief = 0;
		try {
			Element codesElement = (Element) document.getElementsByTagName(
					"behandelcodes").item(0);
			NodeList codes = codesElement.getElementsByTagName("behandeling");
			for (int i = 0; i < codes.getLength(); i++) {
				Element behandelingElement = (Element) codes.item(i);
				String behandelcode = behandelingElement
						.getAttribute("behandelcode");
				if (behandelcode.equals(invoerbehandelCode)) {

					String stringtarief = behandelingElement
							.getElementsByTagName("tariefbehandeling").item(0)
							.getTextContent();
					stringtarief = stringtarief.replaceAll(",", ".");
					tarief = Double.parseDouble(stringtarief);
					/*
					 * System.out.println("tarief");
					 * 
					 * 
					 * System.out.println();
					 */

				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return tarief;

	}

}
