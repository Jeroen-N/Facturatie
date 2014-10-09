package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;

public class testMain {

	public static void main(String[] args) {

		KlantDAOImpl klantdao = new KlantDAOImpl();
		
		klantdao.getKlantenXML();

	}

}
