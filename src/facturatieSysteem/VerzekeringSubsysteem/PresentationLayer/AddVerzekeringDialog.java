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

public class AddVerzekeringDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNaam;
	private JTextField textFieldAdres;
	private JTextField textFieldPostcode;
	private JTextField textFieldPlaats;
	private JTextField textFieldKVK;
	private JTextField textFieldRekeningNr;
	private JTextField textFieldNr;
	private String errorMessage;

	/**
	 * Create the dialog.
	 */
	public AddVerzekeringDialog(final VerzekeringsmaatschappijManager manager) {

		setTitle("Verzekeringen beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 480);
		getContentPane().setLayout(new BorderLayout());
		{
			/*
			 * JTabbedPane wordt aangemaakt
			 */
			JTabbedPane VerzekeringsmaatschappijManager = new JTabbedPane(
					JTabbedPane.TOP);
			getContentPane().add(VerzekeringsmaatschappijManager,
					BorderLayout.CENTER);
			{
				/*
				 * JPanel, de basispaneel, wordt aangemaakt
				 */
				JPanel addVerzekering = new JPanel();
				VerzekeringsmaatschappijManager.addTab("Verzekering toevoegen",
						null, addVerzekering, null);
				addVerzekering.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de verzekering en de klant te kunnen scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					addVerzekering.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de klant gegevens in te kunnen
					 * vullen.
					 */
					JPanel addVerzekering_1 = new JPanel();
					JPanel addVerzekering_2 = new JPanel();
					addVerzekering.add(addVerzekering_1, BorderLayout.WEST);
					addVerzekering.add(addVerzekering_2, BorderLayout.EAST);
					addVerzekering_1.setLayout(new BoxLayout(addVerzekering_1,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						addVerzekering_1.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel("Verzekering");
							lblVerzekering.setPreferredSize(new Dimension(100,
									20));
							lblVerzekering
									.setMinimumSize(new Dimension(100, 20));
							lblVerzekering
									.setMaximumSize(new Dimension(100, 20));
							panel.add(lblVerzekering, BorderLayout.WEST);
						}
					}
					{
						JSplitPane splitPaneNr = new JSplitPane();
						splitPaneNr.setMinimumSize(new Dimension(300, 30));
						splitPaneNr.setMaximumSize(new Dimension(300, 30));
						splitPaneNr.setBorder(null);
						splitPaneNr.setDividerSize(0);
						splitPaneNr.setPreferredSize(new Dimension(300, 30));
						addVerzekering_1.add(splitPaneNr);
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
						}

						{
							JSplitPane splitPaneNaam = new JSplitPane();
							splitPaneNaam
									.setMinimumSize(new Dimension(300, 30));
							splitPaneNaam
									.setMaximumSize(new Dimension(300, 30));
							splitPaneNaam.setBorder(null);
							splitPaneNaam.setDividerSize(0);
							splitPaneNaam.setPreferredSize(new Dimension(300,
									30));
							addVerzekering_1.add(splitPaneNaam);
							{

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
									textFieldNaam = new JTextField();
									splitPaneNaam
											.setRightComponent(textFieldNaam);
									textFieldNaam.setColumns(15);
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
								addVerzekering_1.add(splitPaneAdres);
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
									textFieldAdres = new JTextField();
									splitPaneAdres
											.setRightComponent(textFieldAdres);
									textFieldAdres.setColumns(15);
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
								addVerzekering_1.add(splitPanePostcode);
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
									textFieldPostcode = new JTextField();
									splitPanePostcode
											.setRightComponent(textFieldPostcode);
									textFieldPostcode.setColumns(15);

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
								addVerzekering_1.add(splitPanePlaats);
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
									textFieldPlaats = new JTextField();
									splitPanePlaats
											.setRightComponent(textFieldPlaats);
									textFieldPlaats.setColumns(15);
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
								addVerzekering_1.add(splitPaneKVK);
								{
									JLabel lblPostcode = new JLabel(
											"KVK Nummer: ");
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
									splitPaneKVK.setLeftComponent(lblPostcode);
								}
								{
									textFieldKVK = new JTextField();
									splitPaneKVK
											.setRightComponent(textFieldKVK);
									textFieldKVK.setColumns(15);
								}
							}
							{
								JSplitPane splitPaneRekeningNr = new JSplitPane();
								splitPaneRekeningNr
										.setMinimumSize(new Dimension(300, 30));
								splitPaneRekeningNr
										.setMaximumSize(new Dimension(300, 30));
								splitPaneRekeningNr.setBorder(null);
								splitPaneRekeningNr.setDividerSize(0);
								splitPaneRekeningNr
										.setPreferredSize(new Dimension(300, 30));
								addVerzekering_1.add(splitPaneRekeningNr);
								{
									JLabel lblRekeningNr = new JLabel(
											"Rekening NR: ");
									lblRekeningNr
											.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblRekeningNr
											.setHorizontalAlignment(SwingConstants.RIGHT);
									lblRekeningNr.setMinimumSize(new Dimension(
											120, 16));
									lblRekeningNr.setMaximumSize(new Dimension(
											120, 16));
									lblRekeningNr
											.setPreferredSize(new Dimension(
													120, 16));
									splitPaneRekeningNr
											.setLeftComponent(lblRekeningNr);
								}
								{
									textFieldRekeningNr = new JTextField();
									splitPaneRekeningNr
											.setRightComponent(textFieldRekeningNr);
									textFieldRekeningNr.setColumns(15);
								}
							}
						}

						JPanel buttonPane = new JPanel();
						buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
						getContentPane().add(buttonPane, BorderLayout.SOUTH);

						JButton okButton = new JButton("OK");
						okButton.setActionCommand("OK");
						buttonPane.add(okButton);
						getRootPane().setDefaultButton(okButton);

						okButton.addMouseListener(new MouseAdapter() {

							public void mouseClicked(MouseEvent e) {
								// TODO extra attribuut maatschappijnr
								String errorMessage = manager.checkVerzekering(
										textFieldNr.getText(),
										textFieldNaam.getText(),
										textFieldAdres.getText(),
										textFieldPostcode.getText(),
										textFieldPlaats.getText(),
										textFieldKVK.getText(),
										textFieldRekeningNr.getText());
								if (!errorMessage.equals("")) {
									showConfirmationWindow(errorMessage);
								} else {

									Verzekeringsmaatschappij maatschappij = new Verzekeringsmaatschappij(
											textFieldNr.getText(),
											textFieldNaam.getText(),
											textFieldAdres.getText(),
											textFieldPostcode.getText(),
											textFieldPlaats.getText(),
											Integer.parseInt(textFieldKVK
													.getText()),
											Integer.parseInt(textFieldRekeningNr
													.getText()));
									manager.addVerzekeringsmaatschappij(maatschappij);
									dispose();
								}
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
						buttonPane.add(cancelButton);

					}

				}

			}

		}

	}

	public void showConfirmationWindow(String message) {
		Component frame = null;
		JOptionPane.showMessageDialog(frame, message);

	}
}
