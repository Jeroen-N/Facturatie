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
		setBounds(100, 100, 579, 463);
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
						JSplitPane splitPaneBSN = new JSplitPane();
						splitPaneBSN.setResizeWeight(1.0);
						addKlant_1.add(splitPaneBSN);
						{
							JLabel lblBsn = new JLabel("BSN:");
							splitPaneBSN.setLeftComponent(lblBsn);
						}
						{
							textFieldBSN = new JTextField();
							splitPaneBSN.setRightComponent(textFieldBSN);
							textFieldBSN.setColumns(10);
						}
					}
					{
						JSplitPane splitPaneNaam = new JSplitPane();
						splitPaneNaam.setResizeWeight(1.0);
						addKlant_1.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam:");
							splitPaneNaam.setLeftComponent(lblNaam);
						}
						{
							textFieldNaam = new JTextField();
							splitPaneNaam.setRightComponent(textFieldNaam);
							textFieldNaam.setColumns(10);
						}
					}
					{
						JSplitPane splitPaneAchterNaam = new JSplitPane();
						splitPaneAchterNaam.setResizeWeight(1.0);
						addKlant_1.add(splitPaneAchterNaam);
						{
							JLabel lblAchternaam = new JLabel("Achternaam:");
							splitPaneAchterNaam.setLeftComponent(lblAchternaam);
						}
						{
							textFieldAchternaam = new JTextField();
							splitPaneAchterNaam.setRightComponent(textFieldAchternaam);
							textFieldAchternaam.setColumns(10);
						}
					}
					{
						JSplitPane splitPaneGebDatum = new JSplitPane();
						splitPaneGebDatum.setResizeWeight(1.0);
						addKlant_1.add(splitPaneGebDatum);
						{
							JLabel lblGeboortedatum = new JLabel("Geboortedatum:");
							splitPaneGebDatum.setLeftComponent(lblGeboortedatum);
						}
						{
							textFieldGebDatum = new JTextField();
							splitPaneGebDatum.setRightComponent(textFieldGebDatum);
							textFieldGebDatum.setColumns(10);
							
						}
					}
					{
						JSplitPane splitPaneAdres = new JSplitPane();
						splitPaneAdres.setResizeWeight(1.0);
						addKlant_1.add(splitPaneAdres);
						{
							JLabel lblAdres = new JLabel("Adres:");
							splitPaneAdres.setLeftComponent(lblAdres);
						}
						{
							textFieldAdres = new JTextField();
							splitPaneAdres.setRightComponent(textFieldAdres);
							textFieldAdres.setColumns(10);
						}
					}
					{
						JSplitPane splitPanePostCode = new JSplitPane();
						splitPanePostCode.setResizeWeight(1.0);
						addKlant_1.add(splitPanePostCode);
						{
							JLabel lblPostcode = new JLabel("Postcode:");
							splitPanePostCode.setLeftComponent(lblPostcode);
						}
						{
							textFieldPostCode = new JTextField();
							splitPanePostCode.setRightComponent(textFieldPostCode);
							textFieldPostCode.setColumns(10);
						}
					}
					{
						JSplitPane splitPanePlaats = new JSplitPane();
						splitPanePlaats.setResizeWeight(1.0);
						addKlant_1.add(splitPanePlaats);
						{
							JLabel lblPlaats = new JLabel("Plaats:");
							splitPanePlaats.setLeftComponent(lblPlaats);
						}
						{
							textFieldPlaats = new JTextField();
							splitPanePlaats.setRightComponent(textFieldPlaats);
							textFieldPlaats.setColumns(10);
						}
					}
					{
						JSplitPane splitPaneTelefoonnummer = new JSplitPane();
						splitPaneTelefoonnummer.setResizeWeight(1.0);
						addKlant_1.add(splitPaneTelefoonnummer);
						{
							JLabel lblTelefoonnummer = new JLabel("Telefoonnummer:");
							splitPaneTelefoonnummer.setLeftComponent(lblTelefoonnummer);
						}
						{
							textFieldTelefoonnummer = new JTextField();
							splitPaneTelefoonnummer.setRightComponent(textFieldTelefoonnummer);
							textFieldTelefoonnummer.setColumns(10);
						}
					}
					{
						JSplitPane splitPaneEmail = new JSplitPane();
						splitPaneEmail.setResizeWeight(1.0);
						addKlant_1.add(splitPaneEmail);
						{
							JLabel lblEmail = new JLabel("Email:");
							splitPaneEmail.setLeftComponent(lblEmail);
						}
						{
							textFieldEmail = new JTextField();
							splitPaneEmail.setRightComponent(textFieldEmail);
							textFieldEmail.setColumns(10);
						}
					}
					{
						JSplitPane splitPaneBetaalwijze = new JSplitPane();
						splitPaneBetaalwijze.setResizeWeight(0.3);
						addKlant_1.add(splitPaneBetaalwijze);
						{
							JLabel lblBetaalwijze = new JLabel("Betaalwijze:");
							splitPaneBetaalwijze.setLeftComponent(lblBetaalwijze);
						}
						{
							JComboBox comboBoxBetaalwijze = new JComboBox();
							splitPaneBetaalwijze.setRightComponent(comboBoxBetaalwijze);
						}
					}
					{
						JSplitPane splitPaneRkNummer = new JSplitPane();
						addKlant_1.add(splitPaneRkNummer);
						{
							JLabel lblRekeningnummer = new JLabel("Rekeningnummer:");
							splitPaneRkNummer.setLeftComponent(lblRekeningnummer);
						}
						{
							textFieldRkNummer = new JTextField();
							splitPaneRkNummer.setRightComponent(textFieldRkNummer);
							textFieldRkNummer.setColumns(10);
						}
					}
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
