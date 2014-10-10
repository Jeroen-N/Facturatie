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
		
			frame.setExtendedState(MAXIMIZED_BOTH);
			frame.getContentPane().setLayout(null);
			
			JPanel Header = new JPanel();
			Header.setBackground(SystemColor.scrollbar);
			Header.setBounds(0, 0, 1920, 90);
			frame.getContentPane().add(Header);
			Header.setLayout(null);
			
			JLabel lblFacturatiesysteem = new JLabel("FacturatieSysteem");
			lblFacturatiesysteem.setBackground(SystemColor.controlHighlight);
			lblFacturatiesysteem.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 26));
			lblFacturatiesysteem.setBounds(10, 11, 265, 66);
			Header.add(lblFacturatiesysteem);
			
			final JPanel KlantenPanel = new JPanel();
			KlantenPanel.setBounds(0, 89, 1919, 928);
			frame.getContentPane().add(KlantenPanel);
	        KlantenPanel.add(KlantGUI.KlantGUI());
	        KlantenPanel.setVisible(false);
	        
	        final JPanel VerzekeringPanel = new JPanel();
			VerzekeringPanel.setBounds(0, 89, 1919, 928);
			frame.getContentPane().add(VerzekeringPanel);
			//VerzekeringPanel.add(VerzekeringstypeGUI.VerzekeringstypeGUI());
			VerzekeringPanel.setVisible(false);
			
			final JPanel VerzekeringsMaatschappijPanel = new JPanel();
			VerzekeringsMaatschappijPanel.setBounds(0, 0, 10, 10);
			frame.getContentPane().add(VerzekeringsMaatschappijPanel);
			//VerzekeringsMaatschappijPanel.add(VerzekeringsmaatschappijGUI.VerzekeringsmaatschappijGUI());
			VerzekeringPanel.setVisible(false);
			
			JButton btnVerzekeringmaatschapij = new JButton("Verzekeringmaatschapij");
			btnVerzekeringmaatschapij.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					KlantenPanel.setVisible(false);
					VerzekeringPanel.setVisible(false);
					VerzekeringsMaatschappijPanel.setVisible(true);
				}
			});
			btnVerzekeringmaatschapij.setBounds(1720, 0, 200, 90);
			Header.add(btnVerzekeringmaatschapij);
			btnVerzekeringmaatschapij.setBackground(SystemColor.inactiveCaption);
			
			JButton btnVerzekeringbeheer = new JButton("VerzekeringBeheer");
			btnVerzekeringbeheer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					KlantenPanel.setVisible(false);
					VerzekeringsMaatschappijPanel.setVisible(false);
					VerzekeringPanel.setVisible(true);
				}
			});
			btnVerzekeringbeheer.setBounds(1520, 0, 200, 90);
			Header.add(btnVerzekeringbeheer);
			btnVerzekeringbeheer.setBackground(SystemColor.inactiveCaption);
			
			JButton btnKlantenbeheer = new JButton("KlantenBeheer");
			btnKlantenbeheer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VerzekeringPanel.setVisible(false);
					VerzekeringsMaatschappijPanel.setVisible(false);
					KlantenPanel.setVisible(true);
				}
			});
			btnKlantenbeheer.setBounds(1320, 0, 200, 90);
			Header.add(btnKlantenbeheer);
			btnKlantenbeheer.setBackground(SystemColor.inactiveCaption);
			
			
			
			frame.setVisible(true);
		
	}
}