package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class JFrameEmployeePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordFieldTry;
	private JPasswordField passwordFieldFinal;
	private JTextField textFieldID;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	Employee employee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmployeePassword frame = new JFrameEmployeePassword();
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
	public JFrameEmployeePassword() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4,
				Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();

			ResultSet resultSet = state.executeQuery("SELECT TOP 1 * FROM Employee ORDER BY EmployeeID DESC;");
			if (!resultSet.next()) {

			}
			employee = new Employee(Integer.parseInt(resultSet.getString("EmployeeID")),
					resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("BirthDate"),
					resultSet.getString("Adress"), resultSet.getString("EMail"), resultSet.getString("Gender"),
					Integer.parseInt(resultSet.getString("WorkingHours")),
					Double.parseDouble(resultSet.getString("Hourly")));

			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in password window");
			System.out.println(e.getMessage());
		}

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseDownScreenCoords = null;
				mouseDownCompCoords = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseDownScreenCoords = e.getLocationOnScreen();
				mouseDownCompCoords = e.getPoint();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(mouseDownScreenCoords.x + (currCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
						mouseDownScreenCoords.y + (currCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y);
			}
		});
		JLabel lblKreiranjeLozinke = new JLabel("Kreiranje lozinke");
		lblKreiranjeLozinke.setBounds(10, 19, 435, 30);
		lblKreiranjeLozinke.setHorizontalAlignment(SwingConstants.CENTER);
		lblKreiranjeLozinke.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblKreiranjeLozinke);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(20, 68, 170, 20);
		contentPane.add(lblId);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(20, 107, 170, 20);
		contentPane.add(lblIme);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(20, 146, 170, 20);
		contentPane.add(lblPrezime);

		JLabel lblUnesiteLozinku = new JLabel("Unesite lozinku:");
		lblUnesiteLozinku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUnesiteLozinku.setBounds(20, 185, 170, 20);
		contentPane.add(lblUnesiteLozinku);

		JLabel lblPonoviteLozinku = new JLabel("Ponovite lozinku:");
		lblPonoviteLozinku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPonoviteLozinku.setBounds(20, 263, 170, 20);
		contentPane.add(lblPonoviteLozinku);

		passwordFieldTry = new JPasswordField();
		passwordFieldTry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldTry.setBounds(20, 224, 365, 20);
		contentPane.add(passwordFieldTry);

		passwordFieldFinal = new JPasswordField();
		passwordFieldFinal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldFinal.setBounds(20, 302, 365, 20);
		contentPane.add(passwordFieldFinal);

		textFieldID = new JTextField(employee.personID + "");
		textFieldID.setEditable(false);
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldID.setBounds(200, 68, 235, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldIme = new JTextField(employee.firstName);
		textFieldIme.setEditable(false);
		textFieldIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldIme.setBounds(200, 107, 235, 20);
		contentPane.add(textFieldIme);
		textFieldIme.setColumns(10);

		textFieldPrezime = new JTextField(employee.lastName);
		textFieldPrezime.setEditable(false);
		textFieldPrezime.setBounds(200, 146, 235, 20);
		contentPane.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);

		JToggleButton tglbtnSeeTry = new JToggleButton("");
		tglbtnSeeTry.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglbtnSeeTry.isSelected()) {
					passwordFieldTry.setEchoChar((char) 0);
				} else {
					passwordFieldTry.setEchoChar('\u2022');
				}
			}
		});
		tglbtnSeeTry.setBounds(395, 214, 40, 40);
		contentPane.add(tglbtnSeeTry);

		JToggleButton tglbtnSeeFinal = new JToggleButton("");
		tglbtnSeeFinal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tglbtnSeeFinal.isSelected()) {
					passwordFieldFinal.setEchoChar((char) 0);
				} else {
					passwordFieldFinal.setEchoChar('\u2022');
				}
			}
		});

		tglbtnSeeFinal.setBounds(395, 292, 40, 40);
		contentPane.add(tglbtnSeeFinal);

		JLabel lblLozinkeSeNe = new JLabel("Lozinke se ne poklapaju!");
		lblLozinkeSeNe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinkeSeNe.setForeground(Color.RED);
		lblLozinkeSeNe.setBounds(216, 263, 219, 20);
		contentPane.add(lblLozinkeSeNe);
		lblLozinkeSeNe.setVisible(false);

		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (!Arrays.equals(passwordFieldTry.getPassword(), passwordFieldFinal.getPassword())) {
					lblLozinkeSeNe.setVisible(true);
				} else {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();
						state.executeUpdate("UPDATE Employee SET Password = \'" + passwordFieldFinal.getText()
								+ "\' WHERE EmployeeID = " + employee.personID + ";");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error in planting a password");
					}
					JOptionPane.showMessageDialog(null, "Zaposleni uspešno kreiran");
					dispose();
				}
			}
		});
		btnPotvrdi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPotvrdi.setBounds(168, 341, 119, 23);
		contentPane.add(btnPotvrdi);
	}
}
