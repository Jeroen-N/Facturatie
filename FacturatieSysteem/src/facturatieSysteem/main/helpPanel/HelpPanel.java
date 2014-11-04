package facturatieSysteem.main.helpPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

public class HelpPanel {
	private JTextArea help;
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	
	public JPanel HelpPanel(){
		JPanel paneel = new JPanel();
		paneel.setName("HELP");
		return initComponents();
	}
	
	public JPanel initComponents(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(30,
				144, 255)));
		mainPanel.setBackground(new Color(255, 255, 255));
		
		help = new JTextArea();
		help.setEditable(false);
		
		help.setText(
				  "Welkom bij het facturatiesysteem. \n"
				+ "Om in te loggen logt u in met uw gebruikersnaam en wachtwoord. \n "
				+ "Als u ingelogd bent, komt u terecht op een overzicht van alle klanten die op dit moment aanwezig zijn in het systeem. \n"
				+ "\n"
				+ "U kunt op een klant klikken om meer informatie te vinden over de klant aan de rechterkant van het scherm. \n"
				+ "Het bovenste venster rechts bevat de NAW-gegevens van de klant, en het onderste venster bevat de gegevens van de polis. \n"
				+ "Rechtsbovenin kunt u op klant zoeken door middel van het opgeven van de geboortedatum. \n"
				+ "De afbeeldingen rechtsboven in verwijzen u door naar het klantenoverzicht, of naar het overzicht voor verzekeringsmaatschappijen. \n"
				+ "\n"
				+ "Als u op het rechtse icoon klikt, wordt u doorverwezeen naar het overzicht van verzekeringsmaatschappijen. \n"
				+ "Hier zit u de maatschappij waar u bij aagnesloten bent en als u op deze klikt, verschijnen de gegevens rechts in beeld. \n"
				+ "Rechtsbovenin kunt u op maatschappij zoeken door middel van het opgeven van een naam. \n"
				+ "De iconen die rechtsonderin staan in het scherm van de verzekeringsmaatschappij staan \n"
				+ ", staan voor het toevoegen, wijzigen en verwijderen van een polis en het toevoegen en wijzigen van een verzekeringstype. \n"
				+ "\n"
				+ "De afbeeldingen rechtsonderin in het klantenoverzicht staan voor het toevoegen en wijzigen van een klant, het toevoegen en wijzigen van een verzekeringspolis. \n"
				+ "De laatste afbeelding in dit rijtje staat voor het openen van het facturenscherm voor de geselecteerde klant. \n"
				+ "\n"
				+ "In dit facturenscherm komen alle facturen te staan die horen bij de zojuist geselecteerde klant. \n"
				+ "U kunt op een factuur klikken en de gegevens van deze factuur worden rechts in beeld getoond samen met de bijbehorende behandelingen. \n"
				+ "Als u op de knop factureren klikt, worden alle niet gefactureerde behandelafspraken in rekening gebracht. Hierna wordt het overzicht ge�pdatet. \n"
				+ "Als je een factuur selecteert en vervolgens klikt op open factuur, maakt het programma een pdf aan en opent deze in uw pd viewer. \n"
				+ "Klikt u op print factuur, dan wordt de geselecteerde factuur opgehaald en vervolgens geprint. \n"
				+ "\n"
				+ "Mocht u nog fouten ondervinden aan ons systeem, dan verzoeken wij u om contact met ons op te nemen. \n"
				+ "Wij hopen dat u nog veel plezier mag hebben van ons systeem!"
				);
		
		scrollPane = new JScrollPane(help);
		
		mainPanel.add(scrollPane);
		
		return mainPanel;
	}
}
