package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelDAOinf;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;

public class Bon {

	private String file;
	private FacturatieManager factManager;
	private KlantManager klantManager;
	private VerzekeringsmaatschappijManager verzekeringManager;
	private BehandelingDAO behandelDAO;
	
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
	
	private Paragraph facturatieInformatie(Factuur factuur){
		Paragraph facInfo = new Paragraph();
		
		PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        PdfPCell cell;

        Font fontbold = FontFactory.getFont("Times-Roman", 13, Font.BOLD);
        Font normal = FontFactory.getFont("Times-Roman", 13);

        table.addCell(new Phrase("Aantal:", fontbold));
        table.addCell(new Phrase("Behandeling:", fontbold));
        table.addCell(new Phrase("Prijs:", fontbold));
        table.addCell(new Phrase("Totaalprijs:", fontbold));
        table.addCell(new Phrase("BTW:", fontbold));

        for (Behandeling behandeling : factuur.getBehandelingen()) {
	        	table.addCell(new Phrase(behandeling.getSessies()));
	            table.addCell(new Phrase(behandelDAO.getNaam(behandeling.getBehandelCode())));
	            table.addCell(new Phrase((((int)behandeling.getTotaalprijs())/behandeling.getSessies())));
	            table.addCell(new Phrase(Double.toString(behandeling.getTotaalprijs())));
	            table.addCell(new Phrase("21%"));
	
	            cell = new PdfPCell(new Phrase());
	            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	            cell.setBorder(0);
	            cell.setPaddingBottom(5);
	            table.addCell(cell);
        }
       

        cell = new PdfPCell(new Phrase("Excl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        //TODO totaalprijs methode in de manager aanmaken
        cell = new PdfPCell(new Phrase(Double.toString(factManager.getTotaalPrijs(factuur)), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("BTW 21%", fontbold));
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);

        //TODO methode aanmaken om 21% te berekenen van de totaalprijs
        cell = new PdfPCell(new Phrase(Double.toString((factManager.getTotaalPrijs(factuur)*0.21)), fontbold));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Incl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        //TODO methode om de BTW + de totaalprijs te doen
        cell = new PdfPCell(new Phrase(Double.toString(factManager.getTotaalinclBTW(factuur)), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);
        facInfo.add(table);
		
		return facInfo;
	}
	
}
