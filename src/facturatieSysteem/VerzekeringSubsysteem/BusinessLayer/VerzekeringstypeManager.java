/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De verzekeringstype manager interface die wordt geimplementeerd
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringstypeManager {
	public void addVerzekeringstype(Verzekeringstype type);
	
	public Verzekeringstype getVerzekeringstype(int VerzekeringsTypeID);
	
	public Verzekeringstype getVerzekeringstype(String Naam);
	
	public boolean deleteVerzekeringstype(int VerzekeringsTypeID);
	
	public ArrayList<Verzekeringstype> getVerzekeringsTypes();
}
