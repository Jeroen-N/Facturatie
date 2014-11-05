package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface RapportageIF extends Remote{
 
 public static final String servicename = "Fysiosysteem";
 
 public HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> getGegevens() throws RemoteException;
 

}
