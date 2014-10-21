package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.FactuurDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;


public class testMain {
private static ArrayList<Factuur> facturen = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Fix deze test?
		FactuurDAO m1 = new FactuurDAO();
		facturen = m1.haalAlleFacturen();
		for(Factuur factuur : facturen){
		System.out.println(factuur.getBSN());
		System.out.println(factuur.getFactuurNummer());
		System.out.println(factuur.getFactuurDatum());
		System.out.println(factuur.getVervalDatum());
		System.out.println();
		int n1 = 0;
		int n2 = 0;
		for (Factuur lijstFactuur : facturen) {
			n1 = lijstFactuur.getFactuurNummer();

			if (n1 >= n2) {
				n2 = n1;
			}
		}
		System.out.println(n2  + 1);
	}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	}
	
}


