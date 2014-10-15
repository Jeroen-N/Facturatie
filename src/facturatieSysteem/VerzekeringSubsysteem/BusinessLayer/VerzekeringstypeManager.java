package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringstypeManager {
	public void addVerzekeringstype(Verzekeringstype type);
	
	public Verzekeringstype getVerzekeringstype(int VerzekeringsTypeID);
	
	public boolean deleteVerzekeringstype(int VerzekeringsTypeID);
	
	public ArrayList<Verzekeringstype> getVerzekeringsTypes();
}
