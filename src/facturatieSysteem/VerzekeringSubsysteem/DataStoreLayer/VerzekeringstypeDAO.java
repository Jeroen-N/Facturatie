package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringstypeDAO {
	
	public boolean updateVerzekeringstypeXML(Verzekeringstype type);

	boolean addVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);

	boolean deleteVerzekeringstypeXML(String typenr, Verzekeringstype type);
}
