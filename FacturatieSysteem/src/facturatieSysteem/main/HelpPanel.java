package facturatieSysteem.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

public class HelpPanel {
	private JTextArea help;
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel mainPanel;
	
	public JPanel HelpPanel(){
		JPanel paneel = new JPanel();
		paneel.setName("HELP");
		paneel.add(scrollPane, BorderLayout.CENTER);
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
		
		
		return mainPanel;
	}
}
