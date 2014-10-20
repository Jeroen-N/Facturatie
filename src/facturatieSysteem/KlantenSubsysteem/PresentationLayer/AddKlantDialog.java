package facturatieSysteem.KlantenSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class AddKlantDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JTextField textFieldPolisNummer;
	private JTextField textFieldStartDatum;
	private JTextField textFieldEindDatum;
	private JTextField textFieldEigenRisico;
	private JComboBox<String> comboBoxBetaalwijze;
	private JComboBox<String> comboBoxMaatschappij;
	private JComboBox<String> comboBoxVerzekeringsType;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddKlantDialog(final KlantManager manager,
			final VerzekeringsmaatschappijManager vermaatschappijManager) {

		setTitle("Klant en verzekering beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 480);
		getContentPane().setLayout(new BorderLayout());
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
				JPanel addKlant = new JPanel();
				klantManager.addTab("Klant toevoegen", null, addKlant, null);
				addKlant.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de verzekering en de klant te kunnen scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					addKlant.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de klant gegevens in te kunnen
					 * vullen.
					 */
					JPanel addKlant_1 = new JPanel();
					addKlant.add(addKlant_1, BorderLayout.WEST);
					addKlant_1.setLayout(new BoxLayout(addKlant_1,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						addKlant_1.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblKlant = new JLabel("Klant");
							lblKlant.setPreferredSize(new Dimension(32, 20));
							lblKlant.setMinimumSize(new Dimension(32, 20));
							lblKlant.setMaximumSize(new Dimension(32, 20));
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
						addKlant_1.add(splitPaneBSN);
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
						addKlant_1.add(splitPaneNaam);
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
						addKlant_1.add(splitPaneGebDatum);
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
							splitPaneGebDatum
									.setRightComponent(textFieldGebDatum);
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
						addKlant_1.add(splitPaneAdres);
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
						addKlant_1.add(splitPanePostCode);
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
							splitPanePostCode
									.setRightComponent(textFieldPostCode);
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
						addKlant_1.add(splitPanePlaats);
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
						addKlant_1.add(splitPaneTelefoonnummer);
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
							splitPaneTelefoonnummer
									.setRightComponent(textFieldTelefoonnummer);
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
						addKlant_1.add(splitPaneEmail);
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
						addKlant_1.add(splitPaneBetaalwijze);
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
							comboBoxBetaalwijze = new JComboBox();
							comboBoxBetaalwijze.addItem("");
							comboBoxBetaalwijze.addItem("Incasso");
							comboBoxBetaalwijze.addItem("Acceptgiro");
							splitPaneBetaalwijze
									.setRightComponent(comboBoxBetaalwijze);
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
						addKlant_1.add(splitPaneRkNummer);
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
							splitPaneRkNummer
									.setRightComponent(textFieldRkNummer);
							textFieldRkNummer.setColumns(15);
						}
					}
				}
				{
					JPanel addKlant_2 = new JPanel();
					addKlant.add(addKlant_2, BorderLayout.EAST);
					addKlant_2.setLayout(new BoxLayout(addKlant_2,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setAlignmentY(0.0f);
						panel.setAlignmentX(0.0f);
						addKlant_2.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel("Verzekering");
							lblVerzekering.setPreferredSize(new Dimension(74,
									20));
							lblVerzekering
									.setMinimumSize(new Dimension(74, 20));
							lblVerzekering
									.setMaximumSize(new Dimension(74, 20));
							panel.add(lblVerzekering, BorderLayout.NORTH);
						}
					}
					{
						JSplitPane splitPanePolisNummer = new JSplitPane();
						splitPanePolisNummer.setMaximumSize(new Dimension(300,
								30));
						splitPanePolisNummer.setMinimumSize(new Dimension(300,
								30));
						splitPanePolisNummer.setPreferredSize(new Dimension(
								300, 30));
						splitPanePolisNummer.setDividerSize(0);
						splitPanePolisNummer.setBorder(null);
						addKlant_2.add(splitPanePolisNummer);
						{
							JLabel lblPolisnummer = new JLabel("Polisnummer: ");
							lblPolisnummer.setPreferredSize(new Dimension(120,
									16));
							lblPolisnummer
									.setMinimumSize(new Dimension(120, 16));
							lblPolisnummer
									.setMaximumSize(new Dimension(120, 16));
							lblPolisnummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPolisnummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPanePolisNummer
									.setLeftComponent(lblPolisnummer);
						}
						{
							textFieldPolisNummer = new JTextField();
							textFieldPolisNummer.setColumns(15);
							splitPanePolisNummer
									.setRightComponent(textFieldPolisNummer);
							textFieldPolisNummer.setText(manager
									.createPolisnummer());
							textFieldPolisNummer.setEditable(false);
						}
					}
					{
						JSplitPane splitPaneVerzekeringMaatschappij = new JSplitPane();
						splitPaneVerzekeringMaatschappij
								.setPreferredSize(new Dimension(300, 30));
						splitPaneVerzekeringMaatschappij
								.setMinimumSize(new Dimension(300, 30));
						splitPaneVerzekeringMaatschappij
								.setMaximumSize(new Dimension(300, 30));
						splitPaneVerzekeringMaatschappij.setDividerSize(0);
						splitPaneVerzekeringMaatschappij.setBorder(null);
						addKlant_2.add(splitPaneVerzekeringMaatschappij);
						{
							JLabel lblVerzekeringsmaatschappij = new JLabel(
									"Maatschappij: ");
							lblVerzekeringsmaatschappij
									.setPreferredSize(new Dimension(120, 16));
							lblVerzekeringsmaatschappij
									.setMinimumSize(new Dimension(120, 16));
							lblVerzekeringsmaatschappij
									.setMaximumSize(new Dimension(120, 16));
							lblVerzekeringsmaatschappij
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblVerzekeringsmaatschappij
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneVerzekeringMaatschappij
									.setLeftComponent(lblVerzekeringsmaatschappij);
						}
						{
							comboBoxMaatschappij = new JComboBox();
							splitPaneVerzekeringMaatschappij
									.setRightComponent(comboBoxMaatschappij);
							comboBoxMaatschappij.addItem("");
							for (Verzekeringsmaatschappij maatschappij : vermaatschappijManager
									.getVerzekeringsmaatschappijen()) {
								comboBoxMaatschappij.addItem(maatschappij
										.getNaam());
							}
							comboBoxMaatschappij.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									comboBoxVerzekeringsType.removeAllItems();;
									textFieldEigenRisico.setText("");
									comboBoxVerzekeringsType.addItem("");
									if(comboBoxMaatschappij.getSelectedItem() != ""){
										System.out.println("types combobox vullen");
										for (Verzekeringstype type : vermaatschappijManager.getTypes(vermaatschappijManager.getVerzekeringsmaatschappij(comboBoxMaatschappij.getSelectedItem().toString()))) {
											comboBoxVerzekeringsType.addItem(type.getNaam());
										}
									}
								}
							});

						}
					}
					{
						JSplitPane splitPaneVerzekeringsType = new JSplitPane();
						splitPaneVerzekeringsType.setMaximumSize(new Dimension(
								300, 30));
						splitPaneVerzekeringsType.setMinimumSize(new Dimension(
								300, 30));
						splitPaneVerzekeringsType
								.setPreferredSize(new Dimension(300, 30));
						splitPaneVerzekeringsType.setDividerSize(0);
						splitPaneVerzekeringsType.setBorder(null);
						addKlant_2.add(splitPaneVerzekeringsType);
						{
							JLabel lblVerzekeringstype = new JLabel(
									"Verzekeringstype: ");
							lblVerzekeringstype.setPreferredSize(new Dimension(
									120, 16));
							lblVerzekeringstype.setMinimumSize(new Dimension(
									120, 16));
							lblVerzekeringstype.setMaximumSize(new Dimension(
									120, 16));
							lblVerzekeringstype
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblVerzekeringstype
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneVerzekeringsType
									.setLeftComponent(lblVerzekeringstype);
						}
						{
							comboBoxVerzekeringsType = new JComboBox();
							splitPaneVerzekeringsType.setRightComponent(comboBoxVerzekeringsType);
							comboBoxVerzekeringsType.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if(comboBoxVerzekeringsType.getSelectedItem() != ""){
										textFieldEigenRisico.setText(Integer.toString(vermaatschappijManager.getVerzekeringstype(vermaatschappijManager.getVerzekeringsmaatschappij(comboBoxMaatschappij.getSelectedItem().toString()),comboBoxVerzekeringsType.getSelectedItem().toString()).getEigenRisico()));
									}
								}
							});
						}
					}
					{
						JSplitPane splitPaneEigenRisico = new JSplitPane();
						splitPaneEigenRisico.setPreferredSize(new Dimension(
								300, 30));
						splitPaneEigenRisico.setMinimumSize(new Dimension(300,
								30));
						splitPaneEigenRisico.setMaximumSize(new Dimension(300,
								30));
						splitPaneEigenRisico.setDividerSize(0);
						splitPaneEigenRisico.setBorder(null);
						addKlant_2.add(splitPaneEigenRisico);
						{
							JLabel lblEigenRisico = new JLabel("Eigen Risico: ");
							lblEigenRisico.setPreferredSize(new Dimension(120,
									16));
							lblEigenRisico
									.setMinimumSize(new Dimension(120, 16));
							lblEigenRisico
									.setMaximumSize(new Dimension(120, 16));
							lblEigenRisico
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblEigenRisico
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneEigenRisico
									.setLeftComponent(lblEigenRisico);
						}
						{
							textFieldEigenRisico = new JTextField();
							textFieldEigenRisico.setColumns(15);
							splitPaneEigenRisico
									.setRightComponent(textFieldEigenRisico);
							textFieldEigenRisico.setEditable(false);
						}
					}
					{
						JSplitPane splitPaneStartDatum = new JSplitPane();
						splitPaneStartDatum.setPreferredSize(new Dimension(300,
								30));
						splitPaneStartDatum.setMinimumSize(new Dimension(300,
								30));
						splitPaneStartDatum.setMaximumSize(new Dimension(300,
								30));
						splitPaneStartDatum.setDividerSize(0);
						splitPaneStartDatum.setBorder(null);
						addKlant_2.add(splitPaneStartDatum);
						{
							JLabel lblStartDatum = new JLabel("Start datum: ");
							lblStartDatum.setPreferredSize(new Dimension(120,
									16));
							lblStartDatum
									.setMinimumSize(new Dimension(120, 16));
							lblStartDatum
									.setMaximumSize(new Dimension(120, 16));
							lblStartDatum
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblStartDatum
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneStartDatum.setLeftComponent(lblStartDatum);
						}
						{
							textFieldStartDatum = new JTextField();
							textFieldStartDatum.setColumns(15);
							splitPaneStartDatum
									.setRightComponent(textFieldStartDatum);
						}
					}
					{
						JSplitPane splitPaneEindDatum = new JSplitPane();
						splitPaneEindDatum.setPreferredSize(new Dimension(300,
								30));
						splitPaneEindDatum
								.setMinimumSize(new Dimension(300, 30));
						splitPaneEindDatum
								.setMaximumSize(new Dimension(300, 30));
						splitPaneEindDatum.setDividerSize(0);
						splitPaneEindDatum.setBorder(null);
						addKlant_2.add(splitPaneEindDatum);
						{
							JLabel lblEindDatum = new JLabel("Eind datum: ");
							lblEindDatum
									.setPreferredSize(new Dimension(120, 16));
							lblEindDatum.setMinimumSize(new Dimension(120, 16));
							lblEindDatum.setMaximumSize(new Dimension(120, 16));
							lblEindDatum
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblEindDatum
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneEindDatum.setLeftComponent(lblEindDatum);
						}
						{
							textFieldEindDatum = new JTextField();
							textFieldEindDatum.setColumns(15);
							splitPaneEindDatum
									.setRightComponent(textFieldEindDatum);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ArrayList<VerzekeringPolis>verzekeringPolissen = new ArrayList<>();
						verzekeringPolissen.add(manager.createPolis(textFieldPolisNummer.getText(), comboBoxVerzekeringsType.getSelectedItem().toString(), Double.parseDouble(textFieldEigenRisico.getText()), textFieldStartDatum.getText(), textFieldEindDatum.getText()));
						manager.createKlant(
								textFieldBSN.getText(), 
								textFieldNaam.getText(), 
								textFieldAdres.getText(), 
								textFieldPostCode.getText(), 
								textFieldPlaats.getText(), 
								textFieldGebDatum.getText(), 
								textFieldTelefoonnummer.getText(), 
								textFieldEmail.getText(), 
								textFieldRkNummer.getText(), 
								Double.parseDouble(textFieldEigenRisico.getText()), 
								verzekeringPolissen, 
								comboBoxBetaalwijze.getSelectedItem().toString());
					}
				});
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