package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public interface VerzekeringPolisDAO {

	public boolean addVerzekeringPolisXML(String BSN, VerzekeringPolis polis);

	public boolean updateVerzekeringPolisXML(VerzekeringPolis polis);

	boolean verwijderPolisXML(String Polisnummer);
	
}
