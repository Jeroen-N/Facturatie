package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Bon;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;


public class testMain {
private static ArrayList<Factuur> facturen = new ArrayList<>();
	public static void main(String[] args) throws ParseException {
		FacturatieManagerImpl m1 = new FacturatieManagerImpl();
		String polisNummer = "123456";
		  String BSN = "125651201";
		  ArrayList<VerzekeringPolis> VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		  VerzekeringPolis polis = new VerzekeringPolis(polisNummer, "007", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011")); 
		  VerzekeringPolissen.add(polis);
		Klant klant = new Klant(BSN, 
				"Sander Blijlevens", 
				"Schijfstraat 26B", 
				"4847SM", 
				"Teteringen", 
				new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1995"),"0625235100",
				"sjmblijl@avans.nl",
				"NL47RABO0136052185",
				25.25,
				VerzekeringPolissen,
				"incoasso");
		VerzekeringsmaatschappijManagerImpl v1 = new VerzekeringsmaatschappijManagerImpl(); 
		Factuur factuur = m1.factureer(klant, v1);
		//System.out.println(factuur.getFactuurNummer());
		//System.out.println(factuur.getFactuurDatum());
		//System.out.println(factuur.getVervalDatum());
		//System.out.println(factuur.getBSN());
		//System.out.println(factuur.getVergoedeBedrag());
		//System.out.println(factuur.getStatus());
		//System.out.println();
		
		Bon bon = new Bon(m1, factuur, v1.getVerzekeringsmaatschappij("Kaas Verzekeringen"),klant, v1);
	}
	
	}

