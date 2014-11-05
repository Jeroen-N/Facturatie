package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RapportageIF extends Remote{
	
	public static final String servicename = "Fysiosysteem";
	
	public ArrayList<ArrayList<String>> getGegevens() throws RemoteException;

}
