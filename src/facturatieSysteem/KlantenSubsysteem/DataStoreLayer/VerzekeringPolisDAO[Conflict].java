package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public interface VerzekeringPolisDAO {

	public ArrayList<VerzekeringPolis> getVerzekeringPolis();

	public boolean addVerzekeringPolisXML(VerzekeringPolis polis);

	public boolean updateVerzekeringPolisXML(VerzekeringPolis polis);

	public boolean verwijderVerzekeringPolisXML(VerzekeringPolis polis);
}
