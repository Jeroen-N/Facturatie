/** @author Jeroen Nuijten
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
	private VerzekeringstypeDAO VerzekeringtypeDAO = new VerzekeringstypeDAOImpl();
	private String errorMessage;

	public VerzekeringsmaatschappijManagerImpl(){
		ArrayList<Verzekeringsmaatschappij> lijst = VerzekeringDAO.getMaatschappijenXML();
		this.importData(lijst);
	}
	
	/** Gets the insurance company with the given number
	 *@param nr the number of the company which is used to search te company
	 */
	@Override
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String nr) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNr().equals(nr)){
				return maatschappij;
			}
		}
		return null;
	}

	/** Adds an insurance company with the given company object
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which represents the object to be added
	 * @return boolean true if succesfull
	 */
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

	/** Update an insurance company with the given company object
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which represents the object to be the new version
	 * @return boolean true if succesfull
	 *
	 */
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

	/** Deletes an insurance company with the given company object
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which represents the object to be deleted
	 * @return boolean true if succesfull
	 * 
     */
	@Override
	public boolean deleteVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) != null && maatschappij.getTypes().size() < 1){
			verzekeringsMaatschappijen.remove(maatschappij);
			VerzekeringDAO.deleteMaatschappijXML(maatschappij.getNr());
			return true;
		}
		return false;
	}

	/** Gets all the insurance companies in an list
	 * @return arraylist of the type Verzekeringsmaatschappij
	 */
	@Override
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		return verzekeringsMaatschappijen;
	}

	/** Gets the insurance type with the given company object and the number
	 * @param maatschappij the insurance company object
	 * @param nr the number of the type which needs to be searched for
	 * @return the wanted insurance type
	 */
	@Override
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String nr) {
		for(Verzekeringstype type : maatschappij.getTypes()){
			if(type.getNr().equals(nr)){
				return type;
			}
		}
		return null;
	}

	/** Gets the insurance type with the given company object and the name
	 * @param maatschappij the insurance company object
	 * @param naam the name to be searched for
	 * @return the wanted insurance type
	 */
	public Verzekeringstype getVerzekeringstypeByName(Verzekeringsmaatschappij maatschappij, String naam) {
		for(Verzekeringstype type : maatschappij.getTypes()){
			if(type.getNaam().equals(naam)){
				return type;
			}
		}
		return null;
	}

	/** Adds an insurance type with the given company object and the type object
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which needs to be used for a number
	 * @param type the type object to be added
	 * @return boolean true if succesfull
	 */
	@Override
	public boolean addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) == null){
			maatschappij.addType(type);
			return VerzekeringtypeDAO.addVerzekeringstypeXML(maatschappij.getNr(), type);
		}
		return false;
	}
	/** Updates an insurance type with the given company object and the type object
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which needs to be used for a number
	 * @param type the type object to be the new version
	 * @return boolean true if succesfull
	 */
	@Override
	public boolean updateVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			Verzekeringstype type2 = getVerzekeringstype(maatschappij, type.getNr());
			type2.setEigenRisicio(type.getEigenRisico());
			type2.setNaam(type.getNaam());
			return VerzekeringtypeDAO.updateVerzekeringstypeXML(maatschappij.getNr(), type);
		}
		return false;
	}

	/** Deletes an insurance type with the given company object and the type object
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which needs to be used for a number
	 * @param type the type object to be deleted
	 * @return boolean true if succesfull
	 */
	@Override
	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			maatschappij.deleteType(type);
			return VerzekeringtypeDAO.deleteVerzekeringstypeXML(maatschappij.getNr(), type);
		}
		return false;
	}

	/** Adds an treatment code with the given company object, type object and the code itself
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which needs to be used for a number
	 * @param type the object of the type Verzekeringstype which needs to be used for an number
	 * @param behandelcode the code that will be linked to the company and the type
	 * @return boolean true if succesfull
	 */
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

	/** Updates an treatment code with the given company object, type object and the code itself
	 * @param maatschappij the object of the type Verzekeringsmaatschappij which needs to be used for a number
	 * @param type the object of the type Verzekeringstype which needs to be used for an number
	 * @param behandelcode the code that will be linked to the company and the type that will be deleted
	 * @return boolean true if succesfull
	 */
	@Override
	public boolean deleteBehandelcode(Verzekeringsmaatschappij maatschappij, Verzekeringstype type, String behandelcode){
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			Verzekeringstype type2 = getVerzekeringstype(maatschappij, type.getNr());
			type2.deleteCode(behandelcode);
			return VerzekeringtypeDAO.removeBehandelCode(maatschappij.getNr(), type.getNr(), behandelcode);
		}
		return false;
	}

	/** Imports a list of companies to be added to the list of companies in this class
	 * @param lijst the list of insurance companies
	 * 
	 */
	@Override
	public void importData(ArrayList<Verzekeringsmaatschappij> lijst){
		for(Verzekeringsmaatschappij maatschappij : lijst){
			verzekeringsMaatschappijen.add(maatschappij);
		}
	}
	
	/** Gets the information of an insurance company
	 *	@param maatschappij the maatschappij from which the information needs to be extracted
	 *	@return string of the information from the company
	 */
	@Override
	public String maatschappijInfo(Verzekeringsmaatschappij maatschappij) {
		return "Verzekeringsmaatschappij \n\nNaam: "+ maatschappij.getNaam() + "\nAdres: " + maatschappij.getAdres() + "\nPlaats: " + maatschappij.getPlaats() + "\nPostcode: " + maatschappij.getPostcode() + "\nKVKnummer: " + maatschappij.getKVK() + "\nRekeningnummer: " + maatschappij.getRekeningNR();
	}
	
	/** Checks if the parameters of the insurance are correct
	 * @param maatschappijnr the number of the company
	 * @param Naam the name of the company
	 * @param Adres the adress of the company
	 * @param Postcode the zip adress of the company
	 * @param Plaats the location of the company
	 * @param KVK the KVK number of the company
	 * @param RekeningNr the bank account number of the company
	 * 
	 * @return String returns a error message if one of the parameters in incorrect
	 * 
	 */
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
	
	/** Checks the insurance type with the given number, risk value and the name
	 * @param typenr the type number of the insurance type
	 * @param EigenRisico the risk of the type
	 * @param Naam the name of the type
	 * 
	 * @return returns an error message if one of the checks gives an error
	 */
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

	/**Checks if the KVK matches with the KVK of any of the companies
	 * @param maatschappij the object of the company to be checked with the others
	 * 
	 * @return boolean true if the KVK of the company matches the KVK of any of the other companies
	 */
	public boolean checkKvk(Verzekeringsmaatschappij maatschappij){
		boolean state = false;
		for (Verzekeringsmaatschappij mts: verzekeringsMaatschappijen){
			if(mts.getKVK() == (maatschappij.getKVK())){
				state = false;
				break;
			}else {
				state = true;
			}
		}
		return state;
	}
}
