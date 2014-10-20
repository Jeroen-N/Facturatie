package facturatieSysteem.KlantenSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;


public class ChangeKlantDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel changeKlant;
	private JTextField textFieldNaam;
	private JTextField textFieldAchternaam;
	private JTextField textFieldGebDatum;
	private JTextField textFieldBSN;
	private JTextField textFieldAdres;
	private JTextField textFieldPostCode;
	private JTextField textFieldPlaats;
	private JTextField textFieldTelefoonnummer;
	private JTextField textFieldEmail;
	private JTextField textFieldRkNummer;
	private JTextField textFieldNaam2;
	private JTextField textFieldAchternaam2;
	private JTextField textFieldGebDatum2;
	private JTextField textFieldBSN2;
	private JTextField textFieldAdres2;
	private JTextField textFieldPostCode2;
	private JTextField textFieldPlaats2;
	private JTextField textFieldTelefoonnummer2;
	private JTextField textFieldEmail2;
	private JTextField textFieldRkNummer2;
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChangeKlantDialog(KlantManager manager,
			final VerzekeringsmaatschappijManager vermaatschappijManager, String BSN) {
		setTitle("Klant en verzekering beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 480);
		getContentPane().setLayout(new BorderLayout());
		/*
		 * Klant wordt opgehaald
		 */
		Klant klant = manager.getKlant(BSN);
		
		{
			/*
			 * JTabbedPane wordt aangemaakt
			 */
			JTabbedPane klantManager = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(klantManager, BorderLayout.CENTER);
			{
				/*
				 * JPanel, de basispaneel, wordt aangemaakt
				 */
				changeKlant = new JPanel();
				JPanel deleteKlant = new JPanel();
				klantManager.addTab("Klant wijzigen", null, changeKlant, null);
				klantManager.addTab("Klant verwijderen",null, deleteKlant, null);
				changeKlant.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de verzekering en de klant te kunnen scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					changeKlant.add(separator, BorderLayout.CENTER);
					deleteKlant.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de klant gegevens in te kunnen
					 * vullen.
					 */
					JPanel changeKlant_1 = new JPanel();
					changeKlant.add(changeKlant_1, BorderLayout.WEST);
					changeKlant_1.setLayout(new BoxLayout(changeKlant_1,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						changeKlant_1.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblKlant = new JLabel("Klant Gegevens");
							lblKlant.setPreferredSize(new Dimension(100, 20));
							lblKlant.setMinimumSize(new Dimension(100, 20));
							lblKlant.setMaximumSize(new Dimension(100, 20));
							panel.add(lblKlant, BorderLayout.WEST);
						}
					}
					{
						JSplitPane splitPaneBSN = new JSplitPane();
						splitPaneBSN.setMinimumSize(new Dimension(300, 30));
						splitPaneBSN.setMaximumSize(new Dimension(300, 30));
						splitPaneBSN.setBorder(null);
						splitPaneBSN.setDividerSize(0);
						splitPaneBSN.setPreferredSize(new Dimension(300, 30));
						changeKlant_1.add(splitPaneBSN);
						{
							JLabel lblBsn = new JLabel("BSN: ");
							lblBsn.setHorizontalAlignment(SwingConstants.RIGHT);
							lblBsn.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblBsn.setPreferredSize(new Dimension(120, 16));
							lblBsn.setMinimumSize(new Dimension(120, 16));
							lblBsn.setMaximumSize(new Dimension(120, 16));
							splitPaneBSN.setLeftComponent(lblBsn);
						}
						{
							textFieldBSN = new JTextField();
							splitPaneBSN.setRightComponent(textFieldBSN);
							textFieldBSN.setText(klant.getBSN());
							textFieldBSN.setEditable(false);
							textFieldBSN.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneNaam = new JSplitPane();
						splitPaneNaam.setMinimumSize(new Dimension(300, 30));
						splitPaneNaam.setMaximumSize(new Dimension(300, 30));
						splitPaneNaam.setBorder(null);
						splitPaneNaam.setDividerSize(0);
						splitPaneNaam.setPreferredSize(new Dimension(300, 30));
						changeKlant_1.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam: ");
							lblNaam.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblNaam.setHorizontalAlignment(SwingConstants.RIGHT);
							lblNaam.setPreferredSize(new Dimension(120, 16));
							lblNaam.setMinimumSize(new Dimension(120, 16));
							lblNaam.setMaximumSize(new Dimension(120, 16));
							splitPaneNaam.setLeftComponent(lblNaam);
						}
						{
							textFieldNaam = new JTextField();
							splitPaneNaam.setRightComponent(textFieldNaam);
							textFieldNaam.setText(klant.getNaam());
							textFieldNaam.setEditable(false);
							textFieldNaam.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneGebDatum = new JSplitPane();
						splitPaneGebDatum
								.setMinimumSize(new Dimension(300, 30));
						splitPaneGebDatum
								.setMaximumSize(new Dimension(300, 30));
						splitPaneGebDatum.setBorder(null);
						splitPaneGebDatum.setDividerSize(0);
						splitPaneGebDatum.setPreferredSize(new Dimension(300,
								30));
						changeKlant_1.add(splitPaneGebDatum);
						{
							JLabel lblGeboortedatum = new JLabel(
									"Geboortedatum: ");
							lblGeboortedatum
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblGeboortedatum
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblGeboortedatum.setPreferredSize(new Dimension(
									120, 16));
							lblGeboortedatum.setMinimumSize(new Dimension(120,
									16));
							lblGeboortedatum.setMaximumSize(new Dimension(120,
									16));
							splitPaneGebDatum
									.setLeftComponent(lblGeboortedatum);
						}
						{
							textFieldGebDatum = new JTextField();
							splitPaneGebDatum.setRightComponent(textFieldGebDatum);
							textFieldGebDatum.setText(klant.getGeboortedatum());
							textFieldGebDatum.setEditable(false);
							textFieldGebDatum.setColumns(15);

						}
					}
					{
						JSplitPane splitPaneAdres = new JSplitPane();
						splitPaneAdres.setMinimumSize(new Dimension(300, 30));
						splitPaneAdres.setMaximumSize(new Dimension(300, 30));
						splitPaneAdres.setBorder(null);
						splitPaneAdres.setDividerSize(0);
						splitPaneAdres.setPreferredSize(new Dimension(300, 30));
						changeKlant_1.add(splitPaneAdres);
						{
							JLabel lblAdres = new JLabel("Adres: ");
							lblAdres.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblAdres.setHorizontalAlignment(SwingConstants.RIGHT);
							lblAdres.setPreferredSize(new Dimension(120, 16));
							lblAdres.setMinimumSize(new Dimension(120, 16));
							lblAdres.setMaximumSize(new Dimension(120, 16));
							splitPaneAdres.setLeftComponent(lblAdres);
						}
						{
							textFieldAdres = new JTextField();
							splitPaneAdres.setRightComponent(textFieldAdres);
							textFieldAdres.setText(klant.getAdres());
							textFieldAdres.setEditable(false);
							textFieldAdres.setColumns(15);
						}
					}
					{
						JSplitPane splitPanePostCode = new JSplitPane();
						splitPanePostCode
								.setMinimumSize(new Dimension(300, 30));
						splitPanePostCode
								.setMaximumSize(new Dimension(300, 30));
						splitPanePostCode.setBorder(null);
						splitPanePostCode.setDividerSize(0);
						splitPanePostCode.setPreferredSize(new Dimension(300,
								30));
						changeKlant_1.add(splitPanePostCode);
						{
							JLabel lblPostcode = new JLabel("Postcode: ");
							lblPostcode
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPostcode
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPostcode
									.setPreferredSize(new Dimension(120, 16));
							lblPostcode.setMinimumSize(new Dimension(120, 16));
							lblPostcode.setMaximumSize(new Dimension(120, 16));
							splitPanePostCode.setLeftComponent(lblPostcode);
						}
						{
							textFieldPostCode = new JTextField();
							splitPanePostCode.setRightComponent(textFieldPostCode);
							textFieldPostCode.setText(klant.getPostcode());
							textFieldPostCode.setEditable(false);
							textFieldPostCode.setColumns(15);
						}
					}
					{
						JSplitPane splitPanePlaats = new JSplitPane();
						splitPanePlaats.setMinimumSize(new Dimension(300, 30));
						splitPanePlaats.setMaximumSize(new Dimension(300, 30));
						splitPanePlaats.setBorder(null);
						splitPanePlaats.setDividerSize(0);
						splitPanePlaats
								.setPreferredSize(new Dimension(300, 30));
						changeKlant_1.add(splitPanePlaats);
						{
							JLabel lblPlaats = new JLabel("Plaats: ");
							lblPlaats
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPlaats
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPlaats.setMinimumSize(new Dimension(120, 16));
							lblPlaats.setMaximumSize(new Dimension(120, 16));
							lblPlaats.setPreferredSize(new Dimension(120, 16));
							splitPanePlaats.setLeftComponent(lblPlaats);
						}
						{
							textFieldPlaats = new JTextField();
							splitPanePlaats.setRightComponent(textFieldPlaats);
							textFieldPlaats.setText(klant.getWoonplaats());
							textFieldPlaats.setEditable(false);
							textFieldPlaats.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneTelefoonnummer = new JSplitPane();
						splitPaneTelefoonnummer.setMinimumSize(new Dimension(
								300, 30));
						splitPaneTelefoonnummer.setMaximumSize(new Dimension(
								300, 30));
						splitPaneTelefoonnummer.setBorder(null);
						splitPaneTelefoonnummer.setDividerSize(0);
						splitPaneTelefoonnummer.setPreferredSize(new Dimension(
								300, 30));
						changeKlant_1.add(splitPaneTelefoonnummer);
						{
							JLabel lblTelefoonnummer = new JLabel(
									"Telefoonnummer: ");
							lblTelefoonnummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblTelefoonnummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblTelefoonnummer.setMaximumSize(new Dimension(120,
									16));
							lblTelefoonnummer.setMinimumSize(new Dimension(120,
									16));
							lblTelefoonnummer.setPreferredSize(new Dimension(
									120, 16));
							splitPaneTelefoonnummer
									.setLeftComponent(lblTelefoonnummer);
						}
						{
							textFieldTelefoonnummer = new JTextField();
							splitPaneTelefoonnummer.setRightComponent(textFieldTelefoonnummer);
							textFieldTelefoonnummer.setText(klant.getTelefoonnummer());
							textFieldTelefoonnummer.setEditable(false);
							textFieldTelefoonnummer.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneEmail = new JSplitPane();
						splitPaneEmail.setMinimumSize(new Dimension(300, 30));
						splitPaneEmail.setMaximumSize(new Dimension(300, 30));
						splitPaneEmail.setBorder(null);
						splitPaneEmail.setDividerSize(0);
						splitPaneEmail.setPreferredSize(new Dimension(300, 30));
						changeKlant_1.add(splitPaneEmail);
						{
							JLabel lblEmail = new JLabel("Email: ");
							lblEmail.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
							lblEmail.setPreferredSize(new Dimension(120, 16));
							lblEmail.setMinimumSize(new Dimension(120, 16));
							lblEmail.setMaximumSize(new Dimension(120, 16));
							splitPaneEmail.setLeftComponent(lblEmail);
						}
						{
							textFieldEmail = new JTextField();
							splitPaneEmail.setRightComponent(textFieldEmail);
							textFieldEmail.setText(klant.getEmail());
							textFieldEmail.setEditable(false);
							textFieldEmail.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneBetaalwijze = new JSplitPane();
						splitPaneBetaalwijze.setMinimumSize(new Dimension(300,
								30));
						splitPaneBetaalwijze.setMaximumSize(new Dimension(300,
								30));
						splitPaneBetaalwijze.setBorder(null);
						splitPaneBetaalwijze.setDividerSize(0);
						splitPaneBetaalwijze.setPreferredSize(new Dimension(
								300, 30));
						changeKlant_1.add(splitPaneBetaalwijze);
						{
							JLabel lblBetaalwijze = new JLabel("Betaalwijze: ");
							lblBetaalwijze
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblBetaalwijze
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblBetaalwijze.setPreferredSize(new Dimension(120,
									16));
							lblBetaalwijze
									.setMinimumSize(new Dimension(120, 16));
							lblBetaalwijze
									.setMaximumSize(new Dimension(120, 16));
							splitPaneBetaalwijze
									.setLeftComponent(lblBetaalwijze);
						}
						{
							JComboBox comboBoxBetaalwijze = new JComboBox();
							comboBoxBetaalwijze.addItem(klant.getBetaalMethode());
							comboBoxBetaalwijze.setEditable(false);
							splitPaneBetaalwijze.setRightComponent(comboBoxBetaalwijze);
						}
					}
					{
						JSplitPane splitPaneRkNummer = new JSplitPane();
						splitPaneRkNummer
								.setMinimumSize(new Dimension(300, 30));
						splitPaneRkNummer
								.setMaximumSize(new Dimension(300, 30));
						splitPaneRkNummer.setBorder(null);
						splitPaneRkNummer.setDividerSize(0);
						splitPaneRkNummer.setPreferredSize(new Dimension(300,
								30));
						changeKlant_1.add(splitPaneRkNummer);
						{
							JLabel lblRekeningnummer = new JLabel(
									"Rekeningnummer: ");
							lblRekeningnummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblRekeningnummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblRekeningnummer.setPreferredSize(new Dimension(
									120, 16));
							lblRekeningnummer.setMinimumSize(new Dimension(120,
									16));
							lblRekeningnummer.setMaximumSize(new Dimension(120,
									16));
							splitPaneRkNummer
									.setLeftComponent(lblRekeningnummer);
						}
						{
							textFieldRkNummer = new JTextField();
							splitPaneRkNummer.setRightComponent(textFieldRkNummer);
							textFieldRkNummer.setText(klant.getRekeningnummer());
							textFieldRkNummer.setEditable(false);
							textFieldRkNummer.setColumns(15);
						}
					}
				}
				{
					JPanel changeKlant_2 = new JPanel();
					changeKlant.add(changeKlant_2, BorderLayout.EAST);
					changeKlant_2.setLayout(new BoxLayout(changeKlant_2,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setAlignmentY(0.0f);
						panel.setAlignmentX(0.0f);
						changeKlant_2.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel("Gegevens Wijzigen");
							lblVerzekering.setPreferredSize(new Dimension(100,
									20));
							lblVerzekering
									.setMinimumSize(new Dimension(100, 20));
							lblVerzekering
									.setMaximumSize(new Dimension(100, 20));
							panel.add(lblVerzekering, BorderLayout.NORTH);
						}
					}
					{
						JSplitPane splitPaneBSN = new JSplitPane();
						splitPaneBSN.setMinimumSize(new Dimension(300, 30));
						splitPaneBSN.setMaximumSize(new Dimension(300, 30));
						splitPaneBSN.setBorder(null);
						splitPaneBSN.setDividerSize(0);
						splitPaneBSN.setPreferredSize(new Dimension(300, 30));
						changeKlant_2.add(splitPaneBSN);
						{
							JLabel lblBsn = new JLabel("BSN: ");
							lblBsn.setHorizontalAlignment(SwingConstants.RIGHT);
							lblBsn.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblBsn.setPreferredSize(new Dimension(120, 16));
							lblBsn.setMinimumSize(new Dimension(120, 16));
							lblBsn.setMaximumSize(new Dimension(120, 16));
							splitPaneBSN.setLeftComponent(lblBsn);
						}
						{
							textFieldBSN2 = new JTextField();
							splitPaneBSN.setRightComponent(textFieldBSN2);
							textFieldBSN2.setText(klant.getBSN());
							textFieldBSN2.setEditable(false);
							textFieldBSN2.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneNaam = new JSplitPane();
						splitPaneNaam.setMinimumSize(new Dimension(300, 30));
						splitPaneNaam.setMaximumSize(new Dimension(300, 30));
						splitPaneNaam.setBorder(null);
						splitPaneNaam.setDividerSize(0);
						splitPaneNaam.setPreferredSize(new Dimension(300, 30));
						changeKlant_2.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam: ");
							lblNaam.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblNaam.setHorizontalAlignment(SwingConstants.RIGHT);
							lblNaam.setPreferredSize(new Dimension(120, 16));
							lblNaam.setMinimumSize(new Dimension(120, 16));
							lblNaam.setMaximumSize(new Dimension(120, 16));
							splitPaneNaam.setLeftComponent(lblNaam);
						}
						{
							textFieldNaam2 = new JTextField();
							splitPaneNaam.setRightComponent(textFieldNaam2);
							textFieldNaam2.setText(klant.getNaam());
							textFieldNaam2.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneGebDatum = new JSplitPane();
						splitPaneGebDatum
								.setMinimumSize(new Dimension(300, 30));
						splitPaneGebDatum
								.setMaximumSize(new Dimension(300, 30));
						splitPaneGebDatum.setBorder(null);
						splitPaneGebDatum.setDividerSize(0);
						splitPaneGebDatum.setPreferredSize(new Dimension(300,
								30));
						changeKlant_2.add(splitPaneGebDatum);
						{
							JLabel lblGeboortedatum = new JLabel(
									"Geboortedatum: ");
							lblGeboortedatum
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblGeboortedatum
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblGeboortedatum.setPreferredSize(new Dimension(
									120, 16));
							lblGeboortedatum.setMinimumSize(new Dimension(120,
									16));
							lblGeboortedatum.setMaximumSize(new Dimension(120,
									16));
							splitPaneGebDatum
									.setLeftComponent(lblGeboortedatum);
						}
						{
							textFieldGebDatum2 = new JTextField();
							splitPaneGebDatum.setRightComponent(textFieldGebDatum2);
							textFieldGebDatum2.setText(klant.getGeboortedatum());
							textFieldGebDatum2.setColumns(15);

						}
					}
					{
						JSplitPane splitPaneAdres = new JSplitPane();
						splitPaneAdres.setMinimumSize(new Dimension(300, 30));
						splitPaneAdres.setMaximumSize(new Dimension(300, 30));
						splitPaneAdres.setBorder(null);
						splitPaneAdres.setDividerSize(0);
						splitPaneAdres.setPreferredSize(new Dimension(300, 30));
						changeKlant_2.add(splitPaneAdres);
						{
							JLabel lblAdres = new JLabel("Adres: ");
							lblAdres.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblAdres.setHorizontalAlignment(SwingConstants.RIGHT);
							lblAdres.setPreferredSize(new Dimension(120, 16));
							lblAdres.setMinimumSize(new Dimension(120, 16));
							lblAdres.setMaximumSize(new Dimension(120, 16));
							splitPaneAdres.setLeftComponent(lblAdres);
						}
						{
							textFieldAdres2 = new JTextField();
							splitPaneAdres.setRightComponent(textFieldAdres2);
							textFieldAdres2.setText(klant.getAdres());
							textFieldAdres2.setColumns(15);
						}
					}
					{
						JSplitPane splitPanePostCode = new JSplitPane();
						splitPanePostCode
								.setMinimumSize(new Dimension(300, 30));
						splitPanePostCode
								.setMaximumSize(new Dimension(300, 30));
						splitPanePostCode.setBorder(null);
						splitPanePostCode.setDividerSize(0);
						splitPanePostCode.setPreferredSize(new Dimension(300,
								30));
						changeKlant_2.add(splitPanePostCode);
						{
							JLabel lblPostcode = new JLabel("Postcode: ");
							lblPostcode
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPostcode
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPostcode
									.setPreferredSize(new Dimension(120, 16));
							lblPostcode.setMinimumSize(new Dimension(120, 16));
							lblPostcode.setMaximumSize(new Dimension(120, 16));
							splitPanePostCode.setLeftComponent(lblPostcode);
						}
						{
							textFieldPostCode2 = new JTextField();
							splitPanePostCode.setRightComponent(textFieldPostCode2);
							textFieldPostCode2.setText(klant.getPostcode());
							textFieldPostCode2.setColumns(15);
						}
					}
					{
						JSplitPane splitPanePlaats = new JSplitPane();
						splitPanePlaats.setMinimumSize(new Dimension(300, 30));
						splitPanePlaats.setMaximumSize(new Dimension(300, 30));
						splitPanePlaats.setBorder(null);
						splitPanePlaats.setDividerSize(0);
						splitPanePlaats
								.setPreferredSize(new Dimension(300, 30));
						changeKlant_2.add(splitPanePlaats);
						{
							JLabel lblPlaats2 = new JLabel("Plaats: ");
							lblPlaats2
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPlaats2
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPlaats2.setMinimumSize(new Dimension(120, 16));
							lblPlaats2.setMaximumSize(new Dimension(120, 16));
							lblPlaats2.setPreferredSize(new Dimension(120, 16));
							splitPanePlaats.setLeftComponent(lblPlaats2);
						}
						{
							textFieldPlaats2 = new JTextField();
							splitPanePlaats.setRightComponent(textFieldPlaats2);
							textFieldPlaats2.setText(klant.getWoonplaats());
							textFieldPlaats2.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneTelefoonnummer = new JSplitPane();
						splitPaneTelefoonnummer.setMinimumSize(new Dimension(
								300, 30));
						splitPaneTelefoonnummer.setMaximumSize(new Dimension(
								300, 30));
						splitPaneTelefoonnummer.setBorder(null);
						splitPaneTelefoonnummer.setDividerSize(0);
						splitPaneTelefoonnummer.setPreferredSize(new Dimension(
								300, 30));
						changeKlant_2.add(splitPaneTelefoonnummer);
						{
							JLabel lblTelefoonnummer = new JLabel(
									"Telefoonnummer: ");
							lblTelefoonnummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblTelefoonnummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblTelefoonnummer.setMaximumSize(new Dimension(120,
									16));
							lblTelefoonnummer.setMinimumSize(new Dimension(120,
									16));
							lblTelefoonnummer.setPreferredSize(new Dimension(
									120, 16));
							splitPaneTelefoonnummer
									.setLeftComponent(lblTelefoonnummer);
						}
						{
							textFieldTelefoonnummer2 = new JTextField();
							splitPaneTelefoonnummer.setRightComponent(textFieldTelefoonnummer2);
							textFieldTelefoonnummer2.setText(klant.getTelefoonnummer());
							textFieldTelefoonnummer2.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneEmail = new JSplitPane();
						splitPaneEmail.setMinimumSize(new Dimension(300, 30));
						splitPaneEmail.setMaximumSize(new Dimension(300, 30));
						splitPaneEmail.setBorder(null);
						splitPaneEmail.setDividerSize(0);
						splitPaneEmail.setPreferredSize(new Dimension(300, 30));
						changeKlant_2.add(splitPaneEmail);
						{
							JLabel lblEmail = new JLabel("Email: ");
							lblEmail.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
							lblEmail.setPreferredSize(new Dimension(120, 16));
							lblEmail.setMinimumSize(new Dimension(120, 16));
							lblEmail.setMaximumSize(new Dimension(120, 16));
							splitPaneEmail.setLeftComponent(lblEmail);
						}
						{
							textFieldEmail2 = new JTextField();
							splitPaneEmail.setRightComponent(textFieldEmail2);
							textFieldEmail2.setText(klant.getEmail());
							textFieldEmail2.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneBetaalwijze = new JSplitPane();
						splitPaneBetaalwijze.setMinimumSize(new Dimension(300,
								30));
						splitPaneBetaalwijze.setMaximumSize(new Dimension(300,
								30));
						splitPaneBetaalwijze.setBorder(null);
						splitPaneBetaalwijze.setDividerSize(0);
						splitPaneBetaalwijze.setPreferredSize(new Dimension(
								300, 30));
						changeKlant_2.add(splitPaneBetaalwijze);
						{
							JLabel lblBetaalwijze = new JLabel("Betaalwijze: ");
							lblBetaalwijze
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblBetaalwijze
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblBetaalwijze.setPreferredSize(new Dimension(120,
									16));
							lblBetaalwijze
									.setMinimumSize(new Dimension(120, 16));
							lblBetaalwijze
									.setMaximumSize(new Dimension(120, 16));
							splitPaneBetaalwijze
									.setLeftComponent(lblBetaalwijze);
						}
						{
							JComboBox comboBoxBetaalwijze2 = new JComboBox();
							comboBoxBetaalwijze2.addItem("");
							comboBoxBetaalwijze2.addItem("Incasso");
							comboBoxBetaalwijze2.addItem("Acceptgiro");
							comboBoxBetaalwijze2.setEditable(false);
							splitPaneBetaalwijze.setRightComponent(comboBoxBetaalwijze2);
						}
					}
					{
						JSplitPane splitPaneRkNummer = new JSplitPane();
						splitPaneRkNummer
								.setMinimumSize(new Dimension(300, 30));
						splitPaneRkNummer
								.setMaximumSize(new Dimension(300, 30));
						splitPaneRkNummer.setBorder(null);
						splitPaneRkNummer.setDividerSize(0);
						splitPaneRkNummer.setPreferredSize(new Dimension(300,
								30));
						changeKlant_2.add(splitPaneRkNummer);
						{
							JLabel lblRekeningnummer = new JLabel(
									"Rekeningnummer: ");
							lblRekeningnummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblRekeningnummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblRekeningnummer.setPreferredSize(new Dimension(
									120, 16));
							lblRekeningnummer.setMinimumSize(new Dimension(120,
									16));
							lblRekeningnummer.setMaximumSize(new Dimension(120,
									16));
							splitPaneRkNummer
									.setLeftComponent(lblRekeningnummer);
						}
						{
							textFieldRkNummer2 = new JTextField();
							splitPaneRkNummer.setRightComponent(textFieldRkNummer2);
							textFieldRkNummer2.setText(klant.getRekeningnummer());
							textFieldRkNummer2.setColumns(15);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			changeKlant.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Wijzigen");
				okButton.setActionCommand("Wijzigen");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}