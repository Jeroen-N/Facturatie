package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

/**
 * The Interface KlantManagerIFrmi used by the other system to communicate through RMI
 */
public interface KlantManagerIFrmi extends Remote {

	/** The Constant servicename. */
	public static final String servicename = "Facturatiesysteem";
	
	/**
	 * Gets the klanten rmi.
	 *
	 * @return the klanten rmi
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<ArrayList<String>> getKlantenRMI() throws RemoteException;
}
