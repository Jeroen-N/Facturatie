package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;

public class Bon {

	private String file;
	private FacturatieManager factManager;
	private KlantManager klantManager;
	private VerzekeringsmaatschappijManager verzekeringManager;
	
	public Bon(FacturatieManager factManager, KlantManager klantManager, VerzekeringsmaatschappijManager verzekeringManager){
		this.factManager = factManager;
		this.klantManager = klantManager;
		this.verzekeringManager = verzekeringManager;
		
		create();
	}
	
	private void create(){
		
	}
	
	private Paragraph Header(){
		Paragraph header = new Paragraph("Factuur", FontFactory.getFont("Times-Roman", 20, Font.BOLD));

        header.setAlignment(Element.ALIGN_CENTER);

        return header;
	}
	
	private Paragraph Client(Klant klant){
		Paragraph client = new Paragraph();
		
		
		
		return client;
	}
	
	private Paragraph facturatieInformatie(){
		Paragraph facInfo = new Paragraph();
		
		return facInfo;
	}
	
}
