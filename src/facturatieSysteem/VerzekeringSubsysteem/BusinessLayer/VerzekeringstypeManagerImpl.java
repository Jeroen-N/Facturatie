/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De verzekeringstype manager
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringstypeManagerImpl implements VerzekeringstypeManager {
	private ArrayList<Verzekeringstype> verzekeringsTypes = new ArrayList<>();
	
	@Override
	public void addVerzekeringstype(Verzekeringstype type) {
		verzekeringsTypes.add(type);
	}

	@Override
	public Verzekeringstype getVerzekeringstype(int VerzekeringsTypeID) {
		for(Verzekeringstype type : verzekeringsTypes){
			if(type.getID() == VerzekeringsTypeID){
				return type;
			}
		}
		return null;
	}
	
	@Override
	public Verzekeringstype getVerzekeringstype(String Naam) {
		for(Verzekeringstype type : verzekeringsTypes){
			if(type.getNaam().equals(Naam)){
				return type;
			}
		}
		return null;
	}

	@Override
	public boolean deleteVerzekeringstype(int VerzekeringsTypeID) {
		for(Verzekeringstype type : verzekeringsTypes){
			if(type.getID() == VerzekeringsTypeID){
				return verzekeringsTypes.remove(type);
			}
		}
		return false;
	}

	@Override
	public ArrayList<Verzekeringstype> getVerzekeringsTypes() {
		return verzekeringsTypes;
	}

}
