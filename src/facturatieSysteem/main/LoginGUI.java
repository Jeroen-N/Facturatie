package facturatieSysteem.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LoginGUI {
	private LoginManager userManager;
	private JPanel loginPanel = new JPanel(new BorderLayout());

	private JPanel loginPanel2 = new JPanel(new GridLayout(3, 2));
	private JTextField inputFieldUser = new JTextField(10);
	private JPasswordField inputFieldPass = new JPasswordField(10);
	private JButton loginButton = new JButton("Login");
	private JPanel cardPanel;
	private int finalDelay = 10;
	private JScrollPane scrollPane = new JScrollPane();

	public JPanel LoginGUI() {
		JPanel panel = new JPanel();
		panel.setName("LOGIN");
		panel.add(scrollPane, BorderLayout.CENTER);

		return initComponents();
	}

	public JPanel initComponents() {

		loginPanel2.add(new JLabel("Gebruikersnaam: "));
		loginPanel2.add(inputFieldUser);
		loginPanel2.add(new JLabel("Wachtwoord: "));
		loginPanel2.add(inputFieldPass);
		loginPanel2.add(new JLabel());
		loginPanel2.add(loginButton);
		loginPanel2.setPreferredSize(new Dimension(300, 100));
		loginPanel2.setMaximumSize(loginPanel.getPreferredSize());
		loginPanel2.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

		loginButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});
		loginPanel.add(loginPanel2);

		return loginPanel;
	}

	private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String username = inputFieldUser.getText();
		String password = inputFieldPass.getText();

		if (username.length() > 0 && password.length() > 0) {
			if (userManager.check(username, password)) {
				inputFieldUser.setText("");
				inputFieldPass.setText("");
			} else {
				JOptionPane.showMessageDialog(null,
						"Gebruikersnaam, wachtwoord combinatie niet gevonden.");
			}
		} else {
			JOptionPane
					.showMessageDialog(null,
							"Zowel de gebruikersnaam als wachtwoord dient ingevuld te zijn.");
		}

	}
}