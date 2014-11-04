/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * -Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 * Neither the name of Oracle nor the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 */
package facturatie.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Client {
	
	// Get a logger instance for the current class
	static Logger logger = Logger.getLogger(Client.class);

    private Client() {
		logger.trace("Constructor");
		logger.trace("Done");    	
    }

    public static void main(String[] args) {

		String hostname = null;
		
		if(args.length != 1) {
			System.out.println("Usage: Client [hostname]");
			System.exit(1);
		} else {
			hostname = args[0];
		}
		
		// Configure logging. 
		PropertyConfigurator.configure("./facturatieclient.logconf");
	     
		logger.info("Starting application ---------------------------------");

		System.setProperty("java.security.policy", "http://" + hostname + "/facturatie/resources/facturatieserver.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
            logger.trace("SecurityManager installed");
		}
        
		// As an example, list all system properties. Only visible when loglevel = INFO.
		Properties p = System.getProperties();
		Enumeration<Object> keys = p.keys();
		logger.trace("Listing all system properties:");
		while (keys.hasMoreElements()) {
		  String key = (String)keys.nextElement();
		  String value = (String)p.get(key);
		  logger.trace("    " + key + ": " + value);
		}
		
        try {
    		logger.debug("Locating registry on " + hostname);
            Registry registry = LocateRegistry.getRegistry(hostname);
            
            logger.info("Contents of registry: " + Arrays.toString(registry.list()));
            
            RapportageIF stub = (RapportageIF) registry.lookup(RapportageIF.servicename);
    		logger.info("Found '" + RapportageIF.servicename + "' in registry");
            
    		logger.trace("Calling getBehandelingen()");
            ArrayList<ArrayList<String>> response = stub.getGegevens();
            logger.info("Response: " + response);
        } 
		catch (java.security.AccessControlException e) {
			logger.error("Could not connect to registry: " + e.getMessage());			
		}
        catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            // e.printStackTrace();
        }
    }
}