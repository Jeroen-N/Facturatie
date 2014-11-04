/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De verzekeringsmaatschappij manager
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer.*;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsmaatschappijManagerImpl implements VerzekeringsmaatschappijManager {
	private ArrayList<Verzekeringsmaatschappij> verzekeringsMaatschappijen = new ArrayList<>();
	private VerzekeringsmaatschappijDAO VerzekeringDAO = new VerzekeringsmaatschappijDAOImpl();
	private VerzekeringstypeDAO VerzekeringtypeDAO = new VerzekeringsDAOImpl();
	private String errorMessage;

	public VerzekeringsmaatschappijManagerImpl(){
		ArrayList<Verzekeringsmaatschappij> lijst = VerzekeringDAO.getMaatschappijenXML();
		this.importData(lijst);
	}
	@Override
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String nr) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNr().equals(nr)){
				return maatschappij;
			}
		}
		return null;
	}

	@Override
	public boolean addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) == null){
			if(checkKvk(maatschappij) == true){
				verzekeringsMaatschappijen.add(maatschappij);
				VerzekeringDAO.addMaatschappijXML(maatschappij);
				return true;
			}}
		return false;
	}

	@Override
	public boolean updateVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) != null){
			if(checkKvk(maatschappij) == true){
				Verzekeringsmaatschappij maat = getVerzekeringsmaatschappij(maatschappij.getNr());
				maat.setNaam(maatschappij.getNaam());
				maat.setAdres(maatschappij.getAdres());
				maat.setPostcode(maatschappij.getPostcode());
				maat.setPlaats(maatschappij.getPlaats());
				maat.setKVK(maatschappij.getKVK());
				maat.setRekeningNR(maatschappij.getRekeningNR());
				VerzekeringDAO.updateMaatschappijXML(maatschappij);
				return true;
			}}
		return false;
	}

	@Override
	public boolean deleteVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) != null){
			verzekeringsMaatschappijen.remove(maatschappij);
			VerzekeringDAO.deleteMaatschappijXML(maatschappij.getNr());
		}
		return false;
	}

	@Override
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		return verzekeringsMaatschappijen;
	}

	@Override
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String nr) {
		for(Verzekeringstype type : maatschappij.getTypes()){
			if(type.getNr().equals(nr)){
				return type;
			}
		}
		return null;
	}

	public Verzekeringstype getVerzekeringstypeByName(Verzekeringsmaatschappij maatschappij, String naam) {
		for(Verzekeringstype type : maatschappij.getTypes()){
			if(type.getNaam().equals(naam)){
				return type;
			}
		}
		return null;
	}

	@Override
	public void addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) == null){
			maatschappij.addType(type);
			VerzekeringtypeDAO.addVerzekeringstypeXML(maatschappij.getNr(), type);
		}
	}

	@Override
	public void updateVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			Verzekeringstype type2 = getVerzekeringstype(maatschappij, type.getNr());
			type2.setEigenRisicio(type.getEigenRisico());
			type2.setNaam(type.getNaam());
			VerzekeringtypeDAO.updateVerzekeringstypeXML(maatschappij.getNr(), type);
		}
	}

	@Override
	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			maatschappij.deleteType(type);
			return VerzekeringtypeDAO.deleteVerzekeringstypeXML(maatschappij.getNr(), type);
		}
		return false;
	}

	@Override
	public boolean addBehandelcode(Verzekeringsmaatschappij maatschappij, Verzekeringstype type, String behandelcode){
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){			
			if(!type.getBehandelcodes().contains(behandelcode)){
				type.addCode(behandelcode);
				return VerzekeringtypeDAO.addBehandelCode(maatschappij.getNr(), type.getNr(), behandelcode);
			}
		}
		return false;
	}

	@Override
	public boolean deleteBehandelcode(Verzekeringsmaatschappij maatschappij, Verzekeringstype type, String behandelcode){
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			Verzekeringstype type2 = getVerzekeringstype(maatschappij, type.getNr());
			type2.deleteCode(behandelcode);
			return VerzekeringtypeDAO.removeBehandelCode(maatschappij.getNr(), type.getNr(), behandelcode);
		}
		return false;
	}

	public void importData(ArrayList<Verzekeringsmaatschappij> lijst){
		for(Verzekeringsmaatschappij maatschappij : lijst){
			verzekeringsMaatschappijen.add(maatschappij);
		}
	}
	@Override
	public String maatschappijInfo(Verzekeringsmaatschappij maatschappij) {
		return "Verzekeringsmaatschappij \n\nNaam: "+ maatschappij.getNaam() + "\nAdres: " + maatschappij.getAdres() + "\nPlaats: " + maatschappij.getPlaats() + "\nPostcode: " + maatschappij.getPostcode() + "\nKVKnummer: " + maatschappij.getKVK() + "\nRekeningnummer: " + maatschappij.getRekeningNR();
	}
	@Override
	public String checkVerzekering(String maatschappijnr, String Naam, String Adres,
			String Postcode, String Plaats, String KVK, String RekeningNr) {
		errorMessage = "";


		// Maatschappij nummer
		if (!maatschappijnr.matches("([0-9]{3})")) {
			if (maatschappijnr.length() < 1) {
				errorMessage = errorMessage + "\nMaatschappij nummer niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nMaatschappij nummer niet correct";
			}
		}

		// Naam
		if(!Naam.matches("([A-Z, a-z]{1,50})")){
			if (Naam.length() < 1) {
				errorMessage = errorMessage + "\nNaam niet ingevuld";
			}else{
				errorMessage = errorMessage + "\nEen naam kan alleen uit letters bestaan!";
			}
		}


		// Adres	
		if(!Adres.matches("([A-Z, a-z]{1,50})([ ]{1})([0-9]{1,9})")){
			if (Adres.length() < 1) {
				errorMessage = errorMessage + "\nAdres niet ingevuld";
			}else{
				errorMessage = errorMessage + "\nHet adres moet bestaan uit een straat gevolgd door een spatie en een nummer!";
			}
		}


		// Postcode
		if (!Postcode.matches("([0-9]{4})([A-Z]{2})")) {
			if (Postcode.length() < 1) {
				errorMessage = errorMessage + "\nPostcode niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nPostcode niet correct \nHoofdletters gebruiken svp!";
			}
		}

		//Plaats
		if(!Plaats.matches("([A-Z, a-z]{1,99})")){
			if (Plaats.length() < 1) {
				errorMessage = errorMessage + "\nWoonplaats niet ingevuld";
			}else{
				errorMessage = errorMessage + "\nEen plaatsnaam kan alleen uit letters bestaan!";
			}
		}


		// KVK nummer
		if(!KVK.matches("([0-9]{8})")){	
			if (KVK.length() < 1) {
				errorMessage = errorMessage + "\nKVK nummer niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nKVK nummer niet correct";
			}
		}

		// Rekening Nummer
		if(!RekeningNr.matches("([0-9]{9})")){	
			if (RekeningNr.length() < 1) {
				errorMessage = errorMessage + "\nRekeningnummer niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nRekeningnummer niet correct";
			}
		}
		return errorMessage;
	}
	@Override
	public String checkType(String typenr, String EigenRisico, String Naam) {
		errorMessage = "";

			
		if (!typenr.matches("([0-9]{3})")) {
			if (typenr.length() < 1) {

				errorMessage = errorMessage + "\nType nummer niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nType nummer niet correct";
			}
		}

		
		if(!EigenRisico.matches("([0-9]{1,10})")){
			if(EigenRisico.length() < 1){
				errorMessage = errorMessage + "\nEigenrisico niet ingevuld";
		}else{
				errorMessage = errorMessage + "\nEigenrisico niet correct";
		}
	}
		
		if(!Naam.matches("[A-Z, a-z]{1,50}")){
			if(Naam.length() < 1){
				errorMessage = errorMessage + "\nNaam niet ingevuld";
			}else{
					errorMessage = errorMessage + "\nNaam niet correct";
				}


		
		}
	
		return errorMessage;
	}

	public boolean checkKvk(Verzekeringsmaatschappij maatschappij){
		boolean state = false;
		for (Verzekeringsmaatschappij mts: verzekeringsMaatschappijen){
			if(mts.getKVK() == (maatschappij.getKVK())){
				state = false;
			}else {
				state = true;
			}
		}
		return state;
	}
}
