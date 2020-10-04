package project;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class JFrameManager extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldPol;
	private JTextField textFieldDatumRodjenja;
	private JTextField textFieldAdresa;
	private JTextField textFieldEMail;
	ArrayList<Employee> listE = new ArrayList<Employee>();
	ArrayList<Project> listP = new ArrayList<Project>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameManager frame = new JFrameManager(new Manager());
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
	public JFrameManager(Manager manager) {
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

		JPanel panelMain = new JPanel();
		panelMain.setBorder(null);
		panelMain.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 400,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 300, 800, 600);
		contentPane.add(panelMain);
		panelMain.setLayout(null);

		JLabel lblMenadzer = new JLabel("M E N A DŽ E R");
		lblMenadzer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenadzer.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMenadzer.setBounds(5, 5, 785, 41);
		panelMain.add(lblMenadzer);

		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(10, 37, 280, 300);
		panelMain.add(panelInfo);
		panelInfo.setBorder(new TitledBorder(null, "Lični podaci", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelInfo.setLayout(null);
		((javax.swing.border.TitledBorder) panelInfo.getBorder()).setTitleFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(10, 25, 125, 20);
		panelInfo.add(lblIme);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(10, 70, 125, 20);
		panelInfo.add(lblPrezime);

		JLabel lblDatumRodjenja = new JLabel("Datum rođenja:");
		lblDatumRodjenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatumRodjenja.setBounds(10, 160, 125, 20);
		panelInfo.add(lblDatumRodjenja);

		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setBounds(10, 205, 125, 20);
		panelInfo.add(lblAdresa);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 250, 125, 20);
		panelInfo.add(lblEmail);

		JLabel lblPol = new JLabel("Pol:");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(10, 115, 125, 20);
		panelInfo.add(lblPol);

		textFieldIme = new JTextField(manager.firstName);
		textFieldIme.setEditable(false);
		textFieldIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldIme.setBounds(116, 23, 154, 25);
		panelInfo.add(textFieldIme);
		textFieldIme.setColumns(10);

		textFieldPrezime = new JTextField(manager.lastName);
		textFieldPrezime.setEditable(false);
		textFieldPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPrezime.setBounds(116, 68, 154, 25);
		panelInfo.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);

		textFieldPol = new JTextField(manager.gender);
		textFieldPol.setEditable(false);
		textFieldPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPol.setBounds(116, 113, 154, 25);
		panelInfo.add(textFieldPol);
		textFieldPol.setColumns(10);

		textFieldDatumRodjenja = new JTextField(manager.birthDate);
		textFieldDatumRodjenja.setEditable(false);
		textFieldDatumRodjenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDatumRodjenja.setBounds(116, 158, 154, 25);
		panelInfo.add(textFieldDatumRodjenja);
		textFieldDatumRodjenja.setColumns(10);

		textFieldAdresa = new JTextField(manager.adress);
		textFieldAdresa.setEditable(false);
		textFieldAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldAdresa.setBounds(65, 203, 205, 25);
		panelInfo.add(textFieldAdresa);
		textFieldAdresa.setColumns(10);

		textFieldEMail = new JTextField(manager.eMail);
		textFieldEMail.setEditable(false);
		textFieldEMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldEMail.setBounds(65, 248, 205, 25);
		panelInfo.add(textFieldEMail);
		textFieldEMail.setColumns(10);

		JPanel panelNadleznosti = new JPanel();
		panelNadleznosti.setBorder(
				new TitledBorder(null, "Nadle\u017Enosti", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelNadleznosti.setBounds(300, 37, 490, 500);
		panelMain.add(panelNadleznosti);
		panelNadleznosti.setLayout(null);
		((javax.swing.border.TitledBorder) panelNadleznosti.getBorder())
				.setTitleFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblZaposleni = new JLabel("Zaposleni");
		lblZaposleni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZaposleni.setBounds(10, 11, 59, 20);
		panelNadleznosti.add(lblZaposleni);

		JScrollPane scrollPaneZaposleni = new JScrollPane();
		scrollPaneZaposleni.setBounds(10, 42, 470, 200);
		panelNadleznosti.add(scrollPaneZaposleni);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();

			ResultSet resultSet = state.executeQuery(
					"SELECT DISTINCT Employee.EmployeeID, Employee.FirstName, Employee.LastName, Employee.BirthDate, Employee.Adress, Employee.EMail, Employee.Gender, "
							+ "Employee.WorkingHours, Employee.Hourly, Manager.ManagerID FROM Manager INNER JOIN Employee ON "
							+ "Manager.ManagerID = Employee.ManagerID WHERE (((Manager.ManagerID)=" + manager.personID
							+ "));");

			while (resultSet.next()) {
				listE.add(new Employee(resultSet.getInt("EmployeeID"), resultSet.getString("FirstName"),
						resultSet.getString("LastName"), resultSet.getString("BirthDate"),
						resultSet.getString("Adress"), resultSet.getString("EMail"), resultSet.getString("Gender"),
						resultSet.getInt("WorkingHours"), resultSet.getDouble("Hourly")));
			}

			connection.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Employees can not be shown");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		DefaultListModel<String> modelZ = new DefaultListModel<String>();

		for (Employee employee : listE) {
			modelZ.addElement(employee.toString());
		}

		JList<String> listZaposleni = new JList<String>(modelZ);
		listZaposleni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listZaposleni.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});
		scrollPaneZaposleni.setViewportView(listZaposleni);
		scrollPaneZaposleni.setViewportView(listZaposleni);

		JLabel lblProjekti = new JLabel("Projekti");
		lblProjekti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProjekti.setBounds(10, 258, 59, 20);
		panelNadleznosti.add(lblProjekti);

		JScrollPane scrollPaneProjekti = new JScrollPane();
		scrollPaneProjekti.setBounds(10, 289, 470, 200);
		panelNadleznosti.add(scrollPaneProjekti);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();

			ResultSet resultSet = state.executeQuery(
					"SELECT DISTINCT Project.ProjectID, Project.Title, Project.Owner, Project.BeginingDate, Project.Location, Project.Description, Work.ManagerID "
							+ "FROM Project INNER JOIN [Work] ON Project.ProjectID = Work.ProjectID "
							+ "WHERE (((Work.ManagerID)=" + manager.personID + "));");

			while (resultSet.next()) {
				listP.add(new Project(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getInt(7)));
			}

			connection.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Projects can not be shown");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		DefaultListModel<String> modelP = new DefaultListModel<String>();

		for (Project project : listP) {
			modelP.addElement(project.toString());
		}

		JList<String> listProjekti = new JList<String>(modelP);
		listProjekti.setValueIsAdjusting(true);
		listProjekti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listProjekti.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});
		scrollPaneProjekti.setViewportView(listProjekti);

		JButton btnPrijava = new JButton("Prijava");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();

			ResultSet resultSet = state.executeQuery("SELECT Manager.CheckIn FROM Manager "
					+ "WHERE (((Manager.ManagerID)= " + manager.personID + "));");

			if (!resultSet.next()) {
				throw new Exception("Non existing");
			}

			if (!resultSet.getString("CheckIn").equals("")) {
				btnPrijava.setText("Odjava");
			}

			connection.close();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (btnPrijava.getText().equals("Prijava")) {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();

						state.executeUpdate("UPDATE Manager SET Manager.CheckIn = \""
								+ (new SimpleDateFormat("HH:mm:ss").format(new Date()))
								+ "\"WHERE (((Manager.ManagerID)= " + manager.personID + "));");

						btnPrijava.setText("Odjava");
						connection.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error in Checking In");
					}

				} else {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();

						ResultSet resultSet = state.executeQuery(
								"SELECT Manager.CheckIn, Manager.WorkingHours FROM Manager WHERE (((Manager.ManagerID)= "
										+ manager.personID + "));");

						if (!resultSet.next()) {
							throw new Exception("Error in getting time");
						}

						String in = resultSet.getString(1);
						in = in.substring(0, 2);
						String out = new SimpleDateFormat("HH:mm:ss").format(new Date());
						out = out.substring(0, 2);
						int difference = Integer.parseInt(out) - Integer.parseInt(in);
						if (difference >= 2) {
							int count = Integer.parseInt(resultSet.getString(2)) + difference;

							state.executeUpdate("UPDATE Manager SET Manager.WorkingHours = \"" + count
									+ "\", Manager.CheckIn = \"\"" + "WHERE (((Manager.ManagerID)= " + manager.personID
									+ "));");
						}

						btnPrijava.setText("Prijava");

						connection.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				JFrameManagerPayment frameM = new JFrameManagerPayment(manager);
				frameM.setVisible(true);
			}
		});

		btnPrijava.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrijava.setBounds(5, 348, 285, 41);
		panelMain.add(btnPrijava);

		JButton btnUpravljanjeRadnicima = new JButton("Upravljanje radnicima");
		btnUpravljanjeRadnicima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameEmployeeTable frameET = new JFrameEmployeeTable();
				frameET.setVisible(true);
			}
		});
		btnUpravljanjeRadnicima.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpravljanjeRadnicima.setBounds(5, 400, 285, 41);
		panelMain.add(btnUpravljanjeRadnicima);

		JButton btnUpravaljanjeProjektima = new JButton("Upravaljanje projektima");
		btnUpravaljanjeProjektima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameProjectTable framePT = new JFrameProjectTable();
				framePT.setVisible(true);
			}
		});
		btnUpravaljanjeProjektima.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpravaljanjeProjektima.setBounds(5, 452, 285, 41);
		panelMain.add(btnUpravaljanjeProjektima);

		JButton btnStampaj = new JButton("Menica");
		btnStampaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String text = manager.firstName + " " + manager.lastName + " \n\r\n\r\n\r\n\rID:\t\t"
							+ manager.personID + "\n\r\n\rPol:\t\t" + manager.gender + "\n\r\n\rDatum rođenja:\t"
							+ manager.birthDate + "\n\r\n\rAdresa:\t\t" + manager.adress + "\n\r\n\rE-Mail:\t\t"
							+ manager.eMail + "\n\r\n\rRadnih sati:\t" + manager.workingHours
							+ "\n\r\n\rSatnica:\t" + manager.hourly + "\n\r\n\r\n\r\n\rZa isplatu:\t"
							+ (manager.workingHours * manager.hourly) + "\n\r\n\r\n\r\n\r________________________\n\r\n\r\t(Izdao)";					
					String path = "C:\\Users\\lazar\\OneDrive\\Desktop\\Menice\\" + manager.personID + "_"
							+ manager.firstName + "_" + manager.lastName + ".txt";
					BufferedWriter output = null;
					File file = new File(path); // id_ime_prezime.txt
					file.createNewFile();
					output = new BufferedWriter(new FileWriter(file));
					output.write(text);
					if (output != null) {
						output.close();
						JOptionPane.showMessageDialog(null, "Vaš račun možete naći u folderu \"Menice\"");
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error in printing");
				}
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					Statement state = connection.createStatement();

					state.executeUpdate("UPDATE Manager SET Manager.WorkingHours = \"0\""
							+ "WHERE (((Manager.ManagerID)= " + manager.personID + "));");
					
					connection.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error in database handling");
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

		});

		JButton btnZatvoriProgram = new JButton("Zatvori program");
		btnZatvoriProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Svi trenutni podaci biće izgubljeni!", "UPOZORENJE",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();

						state.executeUpdate(
								"UPDATE Employee SET Employee.HadLunch = False, Employee.CheckIn = \"\", Employee.LunchTime = \"\";"
										+ "");
						state.executeUpdate("UPDATE Manager SET Manager.CheckIn = \"\"");

						connection.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error in closing");
						;
					}
					System.exit(0);
				}
			}
		});
		btnZatvoriProgram.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnZatvoriProgram.setBounds(5, 504, 285, 41);
		panelMain.add(btnZatvoriProgram);

		JLabel lblDateTime = new JLabel("Loading...");
		lblDateTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateTime.setBounds(5, 556, 285, 41);
		panelMain.add(lblDateTime);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				String date = new SimpleDateFormat("dd. MM. yyyy. HH:mm:ss").format(new Date());
				lblDateTime.setText(date);
			}
		}, 1000, 1000);

		btnStampaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStampaj.setBounds(412, 548, 170, 41);
		panelMain.add(btnStampaj);

		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnZatvori.setBounds(606, 548, 170, 41);
		panelMain.add(btnZatvori);
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
