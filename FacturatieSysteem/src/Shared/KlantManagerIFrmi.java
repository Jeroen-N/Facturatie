package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface KlantManagerIFrmi extends Remote {

	/** The Constant servicename. */
	public static final String servicename = "Facturatiesysteem";
	
	public ArrayList<ArrayList<String>> getKlantenRMI() throws RemoteException;
}
