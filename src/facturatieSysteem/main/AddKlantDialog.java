package facturatieSysteem.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class AddKlantDialog extends JDialog {
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddKlantDialog dialog = new AddKlantDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddKlantDialog() {
		setBounds(100, 100, 579, 482);
		getContentPane().setLayout(new BorderLayout());
		{
			JTabbedPane klantManager = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(klantManager, BorderLayout.CENTER);
			{
				JPanel addKlant = new JPanel();
				klantManager.addTab("Klant toevoegen", null, addKlant, null);
				addKlant.setLayout(new BorderLayout(0, 0));
				{
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					addKlant.add(separator, BorderLayout.CENTER);
				}
				{
					JPanel addKlant_1 = new JPanel();
					addKlant.add(addKlant_1, BorderLayout.WEST);
					addKlant_1.setLayout(new BoxLayout(addKlant_1, BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						addKlant_1.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblKlant = new JLabel("Klant");
							panel.add(lblKlant, BorderLayout.WEST);
						}
					}
					{
						JSplitPane splitPaneBSN = new JSplitPane();
						splitPaneBSN.setBorder(null);
						splitPaneBSN.setDividerSize(0);
						splitPaneBSN.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneBSN);
						{
							JLabel lblBsn = new JLabel("BSN:");
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
						splitPaneNaam.setBorder(null);
						splitPaneNaam.setDividerSize(0);
						splitPaneNaam.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam:");
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
						JSplitPane splitPaneAchterNaam = new JSplitPane();
						splitPaneAchterNaam.setBorder(null);
						splitPaneAchterNaam.setDividerSize(0);
						splitPaneAchterNaam.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneAchterNaam);
						{
							JLabel lblAchternaam = new JLabel("Achternaam:");
							lblAchternaam.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblAchternaam.setHorizontalAlignment(SwingConstants.RIGHT);
							lblAchternaam.setPreferredSize(new Dimension(120, 16));
							lblAchternaam.setMinimumSize(new Dimension(120, 16));
							lblAchternaam.setMaximumSize(new Dimension(120, 16));
							splitPaneAchterNaam.setLeftComponent(lblAchternaam);
						}
						{
							textFieldAchternaam = new JTextField();
							splitPaneAchterNaam.setRightComponent(textFieldAchternaam);
							textFieldAchternaam.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneGebDatum = new JSplitPane();
						splitPaneGebDatum.setBorder(null);
						splitPaneGebDatum.setDividerSize(0);
						splitPaneGebDatum.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneGebDatum);
						{
							JLabel lblGeboortedatum = new JLabel("Geboortedatum:");
							lblGeboortedatum.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblGeboortedatum.setHorizontalAlignment(SwingConstants.RIGHT);
							lblGeboortedatum.setPreferredSize(new Dimension(120, 16));
							lblGeboortedatum.setMinimumSize(new Dimension(120, 16));
							lblGeboortedatum.setMaximumSize(new Dimension(120, 16));
							splitPaneGebDatum.setLeftComponent(lblGeboortedatum);
						}
						{
							textFieldGebDatum = new JTextField();
							splitPaneGebDatum.setRightComponent(textFieldGebDatum);
							textFieldGebDatum.setColumns(15);
							
						}
					}
					{
						JSplitPane splitPaneAdres = new JSplitPane();
						splitPaneAdres.setBorder(null);
						splitPaneAdres.setDividerSize(0);
						splitPaneAdres.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneAdres);
						{
							JLabel lblAdres = new JLabel("Adres:");
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
						splitPanePostCode.setBorder(null);
						splitPanePostCode.setDividerSize(0);
						splitPanePostCode.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPanePostCode);
						{
							JLabel lblPostcode = new JLabel("Postcode:");
							lblPostcode.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPostcode.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPostcode.setPreferredSize(new Dimension(120, 16));
							lblPostcode.setMinimumSize(new Dimension(120, 16));
							lblPostcode.setMaximumSize(new Dimension(120, 16));
							splitPanePostCode.setLeftComponent(lblPostcode);
						}
						{
							textFieldPostCode = new JTextField();
							splitPanePostCode.setRightComponent(textFieldPostCode);
							textFieldPostCode.setColumns(15);
						}
					}
					{
						JSplitPane splitPanePlaats = new JSplitPane();
						splitPanePlaats.setBorder(null);
						splitPanePlaats.setDividerSize(0);
						splitPanePlaats.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPanePlaats);
						{
							JLabel lblPlaats = new JLabel("Plaats:");
							lblPlaats.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPlaats.setHorizontalAlignment(SwingConstants.RIGHT);
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
						splitPaneTelefoonnummer.setBorder(null);
						splitPaneTelefoonnummer.setDividerSize(0);
						splitPaneTelefoonnummer.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneTelefoonnummer);
						{
							JLabel lblTelefoonnummer = new JLabel("Telefoonnummer:");
							lblTelefoonnummer.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblTelefoonnummer.setHorizontalAlignment(SwingConstants.RIGHT);
							lblTelefoonnummer.setMaximumSize(new Dimension(120, 16));
							lblTelefoonnummer.setMinimumSize(new Dimension(120, 16));
							lblTelefoonnummer.setPreferredSize(new Dimension(120, 16));
							splitPaneTelefoonnummer.setLeftComponent(lblTelefoonnummer);
						}
						{
							textFieldTelefoonnummer = new JTextField();
							splitPaneTelefoonnummer.setRightComponent(textFieldTelefoonnummer);
							textFieldTelefoonnummer.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneEmail = new JSplitPane();
						splitPaneEmail.setBorder(null);
						splitPaneEmail.setDividerSize(0);
						splitPaneEmail.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneEmail);
						{
							JLabel lblEmail = new JLabel("Email:");
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
						splitPaneBetaalwijze.setBorder(null);
						splitPaneBetaalwijze.setDividerSize(0);
						splitPaneBetaalwijze.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneBetaalwijze);
						{
							JLabel lblBetaalwijze = new JLabel("Betaalwijze:");
							lblBetaalwijze.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblBetaalwijze.setHorizontalAlignment(SwingConstants.RIGHT);
							lblBetaalwijze.setPreferredSize(new Dimension(120, 16));
							lblBetaalwijze.setMinimumSize(new Dimension(120, 16));
							lblBetaalwijze.setMaximumSize(new Dimension(120, 16));
							splitPaneBetaalwijze.setLeftComponent(lblBetaalwijze);
						}
						{
							JComboBox comboBoxBetaalwijze = new JComboBox();
							splitPaneBetaalwijze.setRightComponent(comboBoxBetaalwijze);
						}
					}
					{
						JSplitPane splitPaneRkNummer = new JSplitPane();
						splitPaneRkNummer.setBorder(null);
						splitPaneRkNummer.setDividerSize(0);
						splitPaneRkNummer.setPreferredSize(new Dimension(300, 30));
						addKlant_1.add(splitPaneRkNummer);
						{
							JLabel lblRekeningnummer = new JLabel("Rekeningnummer:");
							lblRekeningnummer.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblRekeningnummer.setHorizontalAlignment(SwingConstants.RIGHT);
							lblRekeningnummer.setPreferredSize(new Dimension(120, 16));
							lblRekeningnummer.setMinimumSize(new Dimension(120, 16));
							lblRekeningnummer.setMaximumSize(new Dimension(120, 16));
							splitPaneRkNummer.setLeftComponent(lblRekeningnummer);
						}
						{
							textFieldRkNummer = new JTextField();
							splitPaneRkNummer.setRightComponent(textFieldRkNummer);
							textFieldRkNummer.setColumns(15);
						}
					}
				}
				{
					JPanel addKlant_2 = new JPanel();
					addKlant.add(addKlant_2, BorderLayout.EAST);
					addKlant_2.setLayout(new BoxLayout(addKlant_2, BoxLayout.Y_AXIS));
				}
			}
			{
				JPanel editKlant = new JPanel();
				klantManager.addTab("Klant wijzigen", null, editKlant, null);
			}
			{
				JPanel deleteKlant = new JPanel();
				klantManager.addTab("Klant verwijderen", null, deleteKlant, null);
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
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
