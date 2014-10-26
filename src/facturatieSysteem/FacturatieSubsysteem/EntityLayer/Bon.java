package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

public class Bon {

	private String file;
	private FacturatieManager factManager;
	private BehandelingDAO behandelDAO;
	private Factuur factuur;
	private Verzekeringsmaatschappij maatschappij;
	private Klant klant;
	
	public Bon(FacturatieManager factManager, Factuur factuur, Verzekeringsmaatschappij maatschappij, Klant klant){
		this.factManager = factManager;
		this.factuur = factuur;
		this.maatschappij = maatschappij;
		this.klant = klant;
		
		file = "/xandergerreman/Documents/" + factuur.getFactuurDatum() + "-" + factuur.getFactuurNummer() + ".pdf";
		
		create();
	}
	
	private void create(){
		try {

            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            document.add(Header());

            document.add(Chunk.NEWLINE);

            document.add(Client());
            
            document.add(Chunk.NEWLINE);
            
            document.add(facturatieInformatie());

            document.setMargins(0, 0, 0, 100);

            document.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	private Paragraph Header(){
		Chunk maatschappijChunk = new Chunk(maatschappij.toString());
		
		Paragraph header = new Paragraph("Bedrijfsgegevens: \n", FontFactory.getFont("Times-Roman", 20, Font.BOLD));
		
		header.add(maatschappijChunk);
		
        header.setAlignment(Element.ALIGN_RIGHT);

        return header;
	}
	
	private Paragraph Client(){
		Chunk client = new Chunk(klant.toStringFactuur());
		
		Paragraph clientParagraph = new Paragraph("Klantgegevens: \n", FontFactory.getFont("Times-Roman", 20, Font.BOLD));
		
		clientParagraph.add(client);
		
		clientParagraph.setAlignment(Element.ALIGN_LEFT);
		
		return clientParagraph;
	}
	
	private Paragraph facturatieInformatie(){
		Paragraph facInfo = new Paragraph();
		
		PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);
        PdfPCell cell;

        Font fontbold = FontFactory.getFont("Times-Roman", 13, Font.BOLD);
        FontFactory.getFont("Times-Roman", 13);

        table.addCell(new Phrase("Aantal", fontbold));
        table.addCell(new Phrase("Behandeling", fontbold));
        table.addCell(new Phrase("Prijs", fontbold));
        table.addCell(new Phrase("Totaalprijs", fontbold));
        table.addCell(new Phrase("BTW", fontbold));

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

        cell = new PdfPCell(new Phrase(Double.toString(factManager.getTotaalPrijs(factuur)), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("BTW 21%", fontbold));
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Double.toString((factManager.getTotaalPrijs(factuur)*0.21)), fontbold));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Incl. BTW", fontbold));
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(Double.toString(factManager.getTotaalinclBTW(factuur)), fontbold));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.TOP);
        table.addCell(cell);
        facInfo.add(table);
		
        //TODO wat vergoed verzekering
        
		return facInfo;
	}
	
}
