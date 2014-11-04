package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

public class ChangeVerzekeringDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel changeVerzekering, changeVerzekering_1;
	private Verzekeringsmaatschappij verzekering;
	private JTextField textFieldNr;
	private JTextField textFieldNaam;
	private JTextField textFieldAdres;
	private JTextField textFieldPostcode;
	private JTextField textFieldPlaats;
	private JTextField textFieldKVK;
	private JTextField textFieldRekeningNr;
	private JTextField textFieldNr2;
	private JTextField textFieldNaam2;
	private JTextField textFieldAdres2;
	private JTextField textFieldPostcode2;
	private JTextField textFieldPlaats2;
	private JTextField textFieldKVK2;
	private JTextField textFieldRekeningNr2;

	/**
	 * Create the dialog.
	 */
	public ChangeVerzekeringDialog(VerzekeringsmaatschappijManager manager,
			String maatschappijnr) {
		setTitle("Verzekeringsmaatschappij beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 480);
		getContentPane().setLayout(new BorderLayout());
		/*
		 * Klant wordt opgehaald
		 */

		verzekering = manager.getVerzekeringsmaatschappij(maatschappijnr);
		/*
		 * JTabbedPane wordt aangemaakt
		 */
		JTabbedPane verzekeringsManager = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(verzekeringsManager, BorderLayout.CENTER);
		{
			/*
			 * JPanel, de basispaneel, wordt aangemaakt
			 */
			changeVerzekering = new JPanel();
			verzekeringsManager.addTab("Maatschappij wijzigen", null,
					changeVerzekering, null);
			changeVerzekering.setLayout(new BorderLayout(0, 0));
			{
				/*
				 * Om de oude en nieuwe klant gegevens te scheiden is er gebruik
				 * gemaakt van een seperator
				 */
				JSeparator separator = new JSeparator();
				separator.setOrientation(SwingConstants.VERTICAL);
				changeVerzekering.add(separator, BorderLayout.CENTER);
			}
			{
				/*
				 * Panel wordt aangemaakt om de oude klant gegevens weer te
				 * geven
				 */
				changeVerzekering_1 = new JPanel();
				changeVerzekering.add(changeVerzekering_1, BorderLayout.WEST);
				changeVerzekering_1.setLayout(new BoxLayout(
						changeVerzekering_1, BoxLayout.Y_AXIS));
				{
					JPanel panel = new JPanel();
					panel.setMinimumSize(new Dimension(300, 20));
					panel.setMaximumSize(new Dimension(300, 20));
					panel.setPreferredSize(new Dimension(300, 20));
					panel.setAlignmentX(Component.LEFT_ALIGNMENT);
					panel.setAlignmentY(Component.TOP_ALIGNMENT);
					changeVerzekering_1.add(panel);
					panel.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblKlant = new JLabel("Gegevens");
						lblKlant.setPreferredSize(new Dimension(100, 20));
						lblKlant.setMinimumSize(new Dimension(100, 20));
						lblKlant.setMaximumSize(new Dimension(100, 20));
						panel.add(lblKlant, BorderLayout.WEST);
					}
				}
				{
					JSplitPane splitPaneNr = new JSplitPane();
					splitPaneNr.setMinimumSize(new Dimension(300, 30));
					splitPaneNr.setMaximumSize(new Dimension(300, 30));
					splitPaneNr.setBorder(null);
					splitPaneNr.setDividerSize(0);
					splitPaneNr.setPreferredSize(new Dimension(300, 30));
					changeVerzekering_1.add(splitPaneNr);
					{
						JLabel lblNr = new JLabel("Nr: ");
						lblNr.setHorizontalAlignment(SwingConstants.RIGHT);
						lblNr.setHorizontalTextPosition(SwingConstants.RIGHT);
						lblNr.setPreferredSize(new Dimension(120, 16));
						lblNr.setMinimumSize(new Dimension(120, 16));
						lblNr.setMaximumSize(new Dimension(120, 16));
						splitPaneNr.setLeftComponent(lblNr);
					}
					{
						textFieldNr = new JTextField();
						splitPaneNr.setRightComponent(textFieldNr);
						textFieldNr.setColumns(15);
						textFieldNr.setEditable(false);
						textFieldNr.setText(verzekering.getNr());
					}

					{
						JSplitPane splitPaneNaam = new JSplitPane();
						splitPaneNaam.setMinimumSize(new Dimension(300, 30));
						splitPaneNaam.setMaximumSize(new Dimension(300, 30));
						splitPaneNaam.setBorder(null);
						splitPaneNaam.setDividerSize(0);
						splitPaneNaam.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam: ");
							lblNaam.setHorizontalAlignment(SwingConstants.RIGHT);
							lblNaam.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblNaam.setPreferredSize(new Dimension(120, 16));
							lblNaam.setMinimumSize(new Dimension(120, 16));
							lblNaam.setMaximumSize(new Dimension(120, 16));
							splitPaneNaam.setLeftComponent(lblNaam);
						}
						{
							textFieldNaam = new JTextField();
							splitPaneNaam.setRightComponent(textFieldNaam);
							textFieldNaam.setColumns(15);
							textFieldNaam.setEditable(false);
							textFieldNaam.setText(verzekering.getNaam());
						}
					}
					{
						JSplitPane splitPaneAdres = new JSplitPane();
						splitPaneAdres.setMinimumSize(new Dimension(300, 30));
						splitPaneAdres.setMaximumSize(new Dimension(300, 30));
						splitPaneAdres.setBorder(null);
						splitPaneAdres.setDividerSize(0);
						splitPaneAdres.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPaneAdres);
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
							textFieldAdres.setEditable(false);
							textFieldAdres.setText(verzekering.getAdres());
						}
					}
					{
						JSplitPane splitPanePostcode = new JSplitPane();
						splitPanePostcode
								.setMinimumSize(new Dimension(300, 30));
						splitPanePostcode
								.setMaximumSize(new Dimension(300, 30));
						splitPanePostcode.setBorder(null);
						splitPanePostcode.setDividerSize(0);
						splitPanePostcode.setPreferredSize(new Dimension(300,
								30));
						changeVerzekering_1.add(splitPanePostcode);
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
							splitPanePostcode.setLeftComponent(lblPostcode);
						}
						{
							textFieldPostcode = new JTextField();
							splitPanePostcode
									.setRightComponent(textFieldPostcode);
							textFieldPostcode.setColumns(15);
							textFieldPostcode.setEditable(false);
							textFieldPostcode
									.setText(verzekering.getPostcode());

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
						changeVerzekering_1.add(splitPanePlaats);
						{
							JLabel lblPlaats = new JLabel("Plaats: ");
							lblPlaats
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPlaats
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPlaats.setPreferredSize(new Dimension(120, 16));
							lblPlaats.setMinimumSize(new Dimension(120, 16));
							lblPlaats.setMaximumSize(new Dimension(120, 16));
							splitPanePlaats.setLeftComponent(lblPlaats);
						}
						{
							textFieldPlaats = new JTextField();
							splitPanePlaats.setRightComponent(textFieldPlaats);
							textFieldPlaats.setColumns(15);
							textFieldPlaats.setEditable(false);
							textFieldPlaats.setText(verzekering.getPlaats());
						}
					}
					{
						JSplitPane splitPaneKVK = new JSplitPane();
						splitPaneKVK.setMinimumSize(new Dimension(300, 30));
						splitPaneKVK.setMaximumSize(new Dimension(300, 30));
						splitPaneKVK.setBorder(null);
						splitPaneKVK.setDividerSize(0);
						splitPaneKVK.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPaneKVK);
						{
							JLabel lblPostcode = new JLabel("KVK Nummer: ");
							lblPostcode
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPostcode
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPostcode
									.setPreferredSize(new Dimension(120, 16));
							lblPostcode.setMinimumSize(new Dimension(120, 16));
							lblPostcode.setMaximumSize(new Dimension(120, 16));
							splitPaneKVK.setLeftComponent(lblPostcode);
						}
						{
							textFieldKVK = new JTextField();
							splitPaneKVK.setRightComponent(textFieldKVK);
							textFieldKVK.setColumns(15);
							textFieldKVK.setEditable(false);
							textFieldKVK.setText(Integer.toString(verzekering
									.getKVK()));
						}
					}
					{
						JSplitPane splitPaneRekeningNr = new JSplitPane();
						splitPaneRekeningNr.setMinimumSize(new Dimension(300,
								30));
						splitPaneRekeningNr.setMaximumSize(new Dimension(300,
								30));
						splitPaneRekeningNr.setBorder(null);
						splitPaneRekeningNr.setDividerSize(0);
						splitPaneRekeningNr.setPreferredSize(new Dimension(300,
								30));
						changeVerzekering_1.add(splitPaneRekeningNr);
						{
							JLabel lblRekeningNr = new JLabel("Rekening NR: ");
							lblRekeningNr
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblRekeningNr
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblRekeningNr
									.setMinimumSize(new Dimension(120, 16));
							lblRekeningNr
									.setMaximumSize(new Dimension(120, 16));
							lblRekeningNr.setPreferredSize(new Dimension(120,
									16));
							splitPaneRekeningNr.setLeftComponent(lblRekeningNr);
						}
						{
							textFieldRekeningNr = new JTextField();
							splitPaneRekeningNr
									.setRightComponent(textFieldRekeningNr);
							textFieldRekeningNr.setColumns(15);
							textFieldRekeningNr.setEditable(false);
							textFieldRekeningNr.setText(Integer
									.toString(verzekering.getRekeningNR()));

						}

						JPanel changeVerzekering_2 = new JPanel();
						changeVerzekering.add(changeVerzekering_2,
								BorderLayout.EAST);
						changeVerzekering_2.setLayout(new BoxLayout(
								changeVerzekering_2, BoxLayout.Y_AXIS));

						{
							JLabel lblVerzekering = new JLabel(
									"Gegevens Wijzigen");
							lblVerzekering.setPreferredSize(new Dimension(200,
									20));
							lblVerzekering
									.setMinimumSize(new Dimension(200, 20));
							lblVerzekering
									.setMaximumSize(new Dimension(200, 20));
							changeVerzekering_2.add(lblVerzekering,
									BorderLayout.NORTH);
						}

						{
							JSplitPane splitPaneNr1 = new JSplitPane();
							splitPaneNr1.setMinimumSize(new Dimension(300, 30));
							splitPaneNr1.setMaximumSize(new Dimension(300, 30));
							splitPaneNr1.setBorder(null);
							splitPaneNr1.setDividerSize(0);
							splitPaneNr1
									.setPreferredSize(new Dimension(300, 30));
							changeVerzekering_2.add(splitPaneNr1);
							{
								JLabel lblNr = new JLabel("Nr: ");
								lblNr.setHorizontalAlignment(SwingConstants.RIGHT);
								lblNr.setHorizontalTextPosition(SwingConstants.RIGHT);
								lblNr.setPreferredSize(new Dimension(120, 16));
								lblNr.setMinimumSize(new Dimension(120, 16));
								lblNr.setMaximumSize(new Dimension(120, 16));
								splitPaneNr1.setLeftComponent(lblNr);
							}
							{
								textFieldNr2 = new JTextField();
								splitPaneNr1.setRightComponent(textFieldNr2);
								textFieldNr2.setColumns(15);
								textFieldNr2.setText(verzekering.getNr());
								textFieldNr2.setEditable(false);
							}

							{
								JSplitPane splitPaneNaam = new JSplitPane();
								splitPaneNaam.setMinimumSize(new Dimension(300,
										30));
								splitPaneNaam.setMaximumSize(new Dimension(300,
										30));
								splitPaneNaam.setBorder(null);
								splitPaneNaam.setDividerSize(0);
								splitPaneNaam.setPreferredSize(new Dimension(
										300, 30));
								changeVerzekering_2.add(splitPaneNaam);
								{
									JLabel lblNaam = new JLabel("Naam: ");
									lblNaam.setHorizontalAlignment(SwingConstants.RIGHT);
									lblNaam.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblNaam.setPreferredSize(new Dimension(120,
											16));
									lblNaam.setMinimumSize(new Dimension(120,
											16));
									lblNaam.setMaximumSize(new Dimension(120,
											16));
									splitPaneNaam.setLeftComponent(lblNaam);
								}
								{
									textFieldNaam2 = new JTextField();
									splitPaneNaam
											.setRightComponent(textFieldNaam2);
									textFieldNaam2.setColumns(15);
									textFieldNaam2.setText(verzekering
											.getNaam());
								}
							}
							{
								JSplitPane splitPaneAdres = new JSplitPane();
								splitPaneAdres.setMinimumSize(new Dimension(
										300, 30));
								splitPaneAdres.setMaximumSize(new Dimension(
										300, 30));
								splitPaneAdres.setBorder(null);
								splitPaneAdres.setDividerSize(0);
								splitPaneAdres.setPreferredSize(new Dimension(
										300, 30));
								changeVerzekering_2.add(splitPaneAdres);
								{
									JLabel lblAdres = new JLabel("Adres: ");
									lblAdres.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblAdres.setHorizontalAlignment(SwingConstants.RIGHT);
									lblAdres.setPreferredSize(new Dimension(
											120, 16));
									lblAdres.setMinimumSize(new Dimension(120,
											16));
									lblAdres.setMaximumSize(new Dimension(120,
											16));
									splitPaneAdres.setLeftComponent(lblAdres);
								}
								{
									textFieldAdres2 = new JTextField();
									splitPaneAdres
											.setRightComponent(textFieldAdres2);
									textFieldAdres2.setColumns(15);
									textFieldAdres2.setText(verzekering
											.getAdres());
								}
							}
							{
								JSplitPane splitPanePostcode = new JSplitPane();
								splitPanePostcode.setMinimumSize(new Dimension(
										300, 30));
								splitPanePostcode.setMaximumSize(new Dimension(
										300, 30));
								splitPanePostcode.setBorder(null);
								splitPanePostcode.setDividerSize(0);
								splitPanePostcode
										.setPreferredSize(new Dimension(300, 30));
								changeVerzekering_2.add(splitPanePostcode);
								{
									JLabel lblPostcode = new JLabel(
											"Postcode: ");
									lblPostcode
											.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblPostcode
											.setHorizontalAlignment(SwingConstants.RIGHT);
									lblPostcode.setPreferredSize(new Dimension(
											120, 16));
									lblPostcode.setMinimumSize(new Dimension(
											120, 16));
									lblPostcode.setMaximumSize(new Dimension(
											120, 16));
									splitPanePostcode
											.setLeftComponent(lblPostcode);
								}
								{
									textFieldPostcode2 = new JTextField();
									splitPanePostcode
											.setRightComponent(textFieldPostcode2);
									textFieldPostcode2.setColumns(15);
									textFieldPostcode2.setText(verzekering
											.getPostcode());

								}
							}
							{
								JSplitPane splitPanePlaats = new JSplitPane();
								splitPanePlaats.setMinimumSize(new Dimension(
										300, 30));
								splitPanePlaats.setMaximumSize(new Dimension(
										300, 30));
								splitPanePlaats.setBorder(null);
								splitPanePlaats.setDividerSize(0);
								splitPanePlaats.setPreferredSize(new Dimension(
										300, 30));
								changeVerzekering_2.add(splitPanePlaats);
								{
									JLabel lblPlaats = new JLabel("Plaats: ");
									lblPlaats
											.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblPlaats
											.setHorizontalAlignment(SwingConstants.RIGHT);
									lblPlaats.setPreferredSize(new Dimension(
											120, 16));
									lblPlaats.setMinimumSize(new Dimension(120,
											16));
									lblPlaats.setMaximumSize(new Dimension(120,
											16));
									splitPanePlaats.setLeftComponent(lblPlaats);
								}
								{
									textFieldPlaats2 = new JTextField();
									splitPanePlaats
											.setRightComponent(textFieldPlaats2);
									textFieldPlaats2.setColumns(15);
									textFieldPlaats2.setText(verzekering
											.getPlaats());
								}
							}
							{
								JSplitPane splitPaneKVK = new JSplitPane();
								splitPaneKVK.setMinimumSize(new Dimension(300,
										30));
								splitPaneKVK.setMaximumSize(new Dimension(300,
										30));
								splitPaneKVK.setBorder(null);
								splitPaneKVK.setDividerSize(0);
								splitPaneKVK.setPreferredSize(new Dimension(
										300, 30));
								changeVerzekering_2.add(splitPaneKVK);
								{
									JLabel lblKVK = new JLabel("KVK Nummer: ");
									lblKVK.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblKVK.setHorizontalAlignment(SwingConstants.RIGHT);
									lblKVK.setPreferredSize(new Dimension(120,
											16));
									lblKVK.setMinimumSize(new Dimension(120, 16));
									lblKVK.setMaximumSize(new Dimension(120, 16));
									splitPaneKVK.setLeftComponent(lblKVK);
								}
								{
									textFieldKVK2 = new JTextField();
									splitPaneKVK
											.setRightComponent(textFieldKVK2);
									textFieldKVK2.setColumns(15);
									textFieldKVK2.setText(Integer
											.toString(verzekering.getKVK()));
								}
							}
							JSplitPane splitPaneRekeningNr1 = new JSplitPane();
							splitPaneRekeningNr1.setMinimumSize(new Dimension(
									300, 30));
							splitPaneRekeningNr1.setMaximumSize(new Dimension(
									300, 30));
							splitPaneRekeningNr1.setBorder(null);
							splitPaneRekeningNr1.setDividerSize(0);
							splitPaneRekeningNr1
									.setPreferredSize(new Dimension(300, 30));
							changeVerzekering_2.add(splitPaneRekeningNr1);
							{
								JLabel lblRekeningNr = new JLabel(
										"Rekening NR: ");
								lblRekeningNr
										.setHorizontalTextPosition(SwingConstants.RIGHT);
								lblRekeningNr
										.setHorizontalAlignment(SwingConstants.RIGHT);
								lblRekeningNr.setMinimumSize(new Dimension(120,
										16));
								lblRekeningNr.setMaximumSize(new Dimension(120,
										16));
								lblRekeningNr.setPreferredSize(new Dimension(
										120, 16));
								splitPaneRekeningNr1
										.setLeftComponent(lblRekeningNr);
							}
							{
								textFieldRekeningNr2 = new JTextField();
								splitPaneRekeningNr1
										.setRightComponent(textFieldRekeningNr2);
								textFieldRekeningNr2.setColumns(15);
								textFieldRekeningNr2.setText(Integer
										.toString(verzekering.getRekeningNR()));

							}
						}
					}
				}

				{
					JPanel buttonChangePane = new JPanel();
					buttonChangePane
							.setLayout(new FlowLayout(FlowLayout.RIGHT));
					changeVerzekering.add(buttonChangePane, BorderLayout.SOUTH);
					{
						JButton wijzigButton = new JButton("Wijzigen");
						wijzigButton.setActionCommand("Wijzigen");
						buttonChangePane.add(wijzigButton);
						getRootPane().setDefaultButton(wijzigButton);

						wijzigButton.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								String errorMessage = manager.checkVerzekering(
										textFieldNr2.getText(),
										textFieldNaam2.getText(),
										textFieldAdres2.getText(),
										textFieldPostcode2.getText(),
										textFieldPlaats2.getText(),
										textFieldKVK2.getText(),
										textFieldRekeningNr2.getText());

								if (!errorMessage.equals("")) {
									showConfirmationWindow(errorMessage);
								} else {

									Verzekeringsmaatschappij maatschappij = new Verzekeringsmaatschappij(
											textFieldNr2.getText(),
											textFieldNaam2.getText(),
											textFieldAdres2.getText(),
											textFieldPostcode2.getText(),
											textFieldPlaats2.getText(),
											Integer.parseInt(textFieldKVK2
													.getText()),
											Integer.parseInt(textFieldRekeningNr2
													.getText()));

									if (manager.checkKvk(maatschappij) == true) {
										manager.updateVerzekeringsmaatschappij(maatschappij);
										dispose();
									} else {
										showConfirmationWindow("KVK nummer bestaat al!");

									}

								}
							}

							private void showConfirmationWindow(String message) {
								Component frame = null;
								JOptionPane.showMessageDialog(frame, message);

							}

						});
						JButton cancelButton = new JButton("Cancel");
						cancelButton.setActionCommand("Cancel");
						cancelButton.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								dispose();
							}
						});
						buttonChangePane.add(cancelButton);

						{

						}
					}
				}
			}
		}
	}
}
