package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;


public class testMain {
private static ArrayList<Factuur> facturen = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Fix deze test?
		int i = 0;
		FacturatieManagerImpl m1 = new FacturatieManagerImpl();
		facturen = m1.haalFacturen("123456789");
		for(Factuur factuur : facturen){
		System.out.println(factuur.getBSN());
		System.out.println(factuur.getFactuurNummer());
		System.out.println(factuur.getFactuurDatum());
		System.out.println(factuur.getVervalDatum());
		System.out.println();
	}
	}}


