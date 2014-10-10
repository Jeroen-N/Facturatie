package facturatieSysteem.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;
import facturatieSysteem.VerzekeringSubsysteem.PresentationLayer.VerzekeringstypeGUI;
import facturatieSysteem.VerzekeringSubsysteem.PresentationLayer.VerzekeringsmaatschappijGUI;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.KlantGUI;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainGUI {
	private JFrame frame;
	private KlantGUI KlantGUI;
	private VerzekeringstypeGUI VerzekeringstypeGUI;
	private VerzekeringsmaatschappijGUI VerzekeringsmaatschappijGUI;

	public MainGUI() {

		makeFrame();
	}

	public void makeFrame() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel VerzekeringPanel = new JPanel();
		frame.getContentPane().add(VerzekeringPanel, "name_30497855602569");
		// VerzekeringPanel.add(VerzekeringstypeGUI.VerzekeringstypeGUI());
		VerzekeringPanel.setVisible(false);

		final JPanel VerzekeringsMaatschappijPanel = new JPanel();
		frame.getContentPane().add(VerzekeringsMaatschappijPanel,
				"name_30497881246570");

		JPanel Klanten = new JPanel();
		frame.getContentPane().add(Klanten, "name_31629163661906");
		Klanten.setLayout(new BorderLayout(0, 0));

		final JPanel KlantenPanel = new JPanel();
		Klanten.add(KlantenPanel, BorderLayout.CENTER);
		KlantenPanel.add(KlantGUI.KlantGUI());
		KlantenPanel.setLayout(new BorderLayout(0, 0));

		JPanel Header = new JPanel();
		Klanten.add(Header, BorderLayout.NORTH);
		Header.setBackground(Color.ORANGE);

		JLabel lblFacturatiesysteem = new JLabel("FacturatieSysteem");
		lblFacturatiesysteem.setBackground(SystemColor.controlHighlight);
		lblFacturatiesysteem.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 26));
		Header.setLayout(new BorderLayout(0, 0));
		Header.add(lblFacturatiesysteem, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		Header.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
				JButton btnVerzekeringmaatschapij = new JButton(
						"Verzekeringmaatschapij");
				panel.add(btnVerzekeringmaatschapij, BorderLayout.EAST);
				btnVerzekeringmaatschapij.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						KlantenPanel.setVisible(false);
						// VerzekeringPanel.setVisible(false);
						// VerzekeringsMaatschappijPanel.setVisible(true);
					}
				});
				btnVerzekeringmaatschapij.setBackground(SystemColor.inactiveCaption);
				
						JButton btnVerzekeringbeheer = new JButton("VerzekeringBeheer");
						panel.add(btnVerzekeringbeheer, BorderLayout.WEST);
						btnVerzekeringbeheer.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								KlantenPanel.setVisible(false);
								// VerzekeringsMaatschappijPanel.setVisible(false);
								// VerzekeringPanel.setVisible(true);
							}
						});
						btnVerzekeringbeheer.setBackground(SystemColor.inactiveCaption);
						
								JButton btnKlantenbeheer = new JButton("KlantenBeheer");
								panel.add(btnKlantenbeheer, BorderLayout.CENTER);
								btnKlantenbeheer.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										// VerzekeringPanel.setVisible(false);
										// VerzekeringsMaatschappijPanel.setVisible(false);
										KlantenPanel.setVisible(true);
									}
								});
								btnKlantenbeheer.setBackground(SystemColor.inactiveCaption);
		KlantenPanel.setVisible(false);
		// VerzekeringsMaatschappijPanel.add(VerzekeringsmaatschappijGUI.VerzekeringsmaatschappijGUI());
		VerzekeringPanel.setVisible(false);

		frame.setVisible(true);

	}
}