package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Interface RapportageIF used by this system to communicate with the other system through RMI.
 */
public interface RapportageIF extends Remote{
 
 /** The Constant servicename. */
 public static final String servicename = "Fysiosysteem";
 
 /**
  * Gets the gegevens.
  *
  * @return the gegevens
  * @throws RemoteException the remote exception
  */
 public HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> getGegevens() throws RemoteException;
 

}
