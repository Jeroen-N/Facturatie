package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface RapportageIF extends Remote{
	
	public static final String servicename = "Fysiosysteem";
	
	public HashMap<String, ArrayList<ArrayList<String>>> getGegevens(String BSN) throws RemoteException;

}
