package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class JFrameLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameLogin() {

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Database connection error");
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250, 551, 550);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUnosID = new JLabel("Unesite ID");
		lblUnosID.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnosID.setBounds(159, 53, 233, 30);
		panel.add(lblUnosID);
		lblUnosID.setFont(new Font("Tahoma", Font.BOLD, 20));

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldID.setBounds(159, 136, 233, 30);
		panel.add(textFieldID);
		textFieldID.setToolTipText("UnesiteID");
		textFieldID.setColumns(10);

		JLabel lblUnosLozinka = new JLabel("Unesite lozinku");
		lblUnosLozinka.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnosLozinka.setBounds(159, 219, 233, 30);
		panel.add(lblUnosLozinka);
		lblUnosLozinka.setFont(new Font("Tahoma", Font.BOLD, 20));

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Unesite lozinku");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(159, 302, 233, 30);
		panel.add(passwordField);

		JLabel lblPodaciKojiSu = new JLabel("Podaci koji su uneti nisu validni!");
		lblPodaciKojiSu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodaciKojiSu.setForeground(new Color(204, 0, 0));
		lblPodaciKojiSu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPodaciKojiSu.setBounds(125, 468, 300, 23);
		panel.add(lblPodaciKojiSu);
		lblPodaciKojiSu.setVisible(false);

		JButton btnLogin = new JButton("Potvrdi");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean valid = false;

					Connection connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					Statement state = connection.createStatement();

					ResultSet resultSet = state.executeQuery("SELECT * FROM Manager");
					while (resultSet.next()) {
						int ID = Integer.parseInt(textFieldID.getText());
						if (resultSet.getInt(1) == ID) {
							String password = String.valueOf(passwordField.getPassword());
							if (resultSet.getString(2).equals(password)) {
								Manager manager = new Manager(resultSet.getInt("ManagerID"),
										resultSet.getString("FirstName"), resultSet.getString("LastName"),
										resultSet.getString("BirthDate"), resultSet.getString("Adress"),
										resultSet.getString("EMail"), resultSet.getString("Gender"),
										Integer.parseInt(resultSet.getString("WorkingHours")),
										resultSet.getDouble("Hourly"));

								JFrameManager frameM = new JFrameManager(manager);
								frameM.setVisible(true);
								valid = true;
							}
						}
					}
					resultSet = state.executeQuery("SELECT * FROM Employee");
					while (resultSet.next()) {
						int ID = Integer.parseInt(textFieldID.getText());
						if (resultSet.getInt(1) == ID) {
							String password = String.valueOf(passwordField.getPassword());
							if (resultSet.getString(2).equals(password)) {
								Employee employee = new Employee(resultSet.getInt("EmployeeID"),
										resultSet.getString("FirstName"), resultSet.getString("LastName"),
										resultSet.getString("BirthDate"), resultSet.getString("Adress"),
										resultSet.getString("EMail"), resultSet.getString("Gender"),
										Integer.parseInt(resultSet.getString("WorkingHours")),
										resultSet.getDouble("Hourly"));

								JFrameEmployee frameE = new JFrameEmployee(employee);
								frameE.setVisible(true);
								valid = true;
							}
						}
					}

					textFieldID.setText("");
					passwordField.setText("");
					if (valid == false) {
						throw new Exception();
					} else {
						lblPodaciKojiSu.setVisible(false);
					}

					connection.close();

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					lblPodaciKojiSu.setVisible(true);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(159, 385, 233, 30);
		panel.add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\lazar\\OneDrive\\Desktop\\DefinitelyNotPhotos\\canva-photo-editor.png"));
		label.setBounds(0, 0, 551, 550);
		panel.add(label);
	}
}
