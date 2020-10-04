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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class JFrameEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldPol;
	private JTextField textFieldDatumRodjenja;
	private JTextField textFieldAdresa;
	private JTextField textFieldEMail;
	private JTextField textFieldMenadzer;
	private JTextField textFieldPrijava;
	private JTextField textFieldVremeRucka;
	private JTextField textFieldRadnihSati;
	private JTextField textFieldZarada;
	private JTextField textFieldUkupno;
	ArrayList<Project> listP = new ArrayList<Project>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmployee frame = new JFrameEmployee(new Employee());
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
	public JFrameEmployee(Employee employee) {
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

		textFieldIme = new JTextField(employee.firstName);
		textFieldIme.setEditable(false);
		textFieldIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldIme.setBounds(116, 23, 154, 25);
		panelInfo.add(textFieldIme);
		textFieldIme.setColumns(10);

		textFieldPrezime = new JTextField(employee.lastName);
		textFieldPrezime.setEditable(false);
		textFieldPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPrezime.setBounds(116, 68, 154, 25);
		panelInfo.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);

		textFieldPol = new JTextField(employee.gender);
		textFieldPol.setEditable(false);
		textFieldPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPol.setBounds(116, 113, 154, 25);
		panelInfo.add(textFieldPol);
		textFieldPol.setColumns(10);

		textFieldDatumRodjenja = new JTextField(employee.birthDate);
		textFieldDatumRodjenja.setEditable(false);
		textFieldDatumRodjenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDatumRodjenja.setBounds(116, 158, 154, 25);
		panelInfo.add(textFieldDatumRodjenja);
		textFieldDatumRodjenja.setColumns(10);

		textFieldAdresa = new JTextField(employee.adress);
		textFieldAdresa.setEditable(false);
		textFieldAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldAdresa.setBounds(65, 203, 205, 25);
		panelInfo.add(textFieldAdresa);
		textFieldAdresa.setColumns(10);

		textFieldEMail = new JTextField(employee.eMail);
		textFieldEMail.setEditable(false);
		textFieldEMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldEMail.setBounds(65, 248, 205, 25);
		panelInfo.add(textFieldEMail);
		textFieldEMail.setColumns(10);

		JLabel lblZaposleni = new JLabel("Z A P O S L E N I");
		lblZaposleni.setHorizontalAlignment(SwingConstants.CENTER);
		lblZaposleni.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblZaposleni.setBounds(5, 5, 785, 31);
		panelMain.add(lblZaposleni);

		JLabel lblNadredjeni = new JLabel("Nadređeni:");
		lblNadredjeni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNadredjeni.setBounds(300, 61, 125, 20);
		panelMain.add(lblNadredjeni);

		textFieldMenadzer = new JTextField();
		textFieldMenadzer.setEditable(false);
		textFieldMenadzer.setBounds(426, 61, 364, 20);
		panelMain.add(textFieldMenadzer);
		textFieldMenadzer.setColumns(10);

		JLabel lblPrijavljenU = new JLabel("Prijavljen u:");
		lblPrijavljenU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrijavljenU.setBounds(300, 94, 75, 20);
		panelMain.add(lblPrijavljenU);

		textFieldPrijava = new JTextField();
		textFieldPrijava.setEditable(false);
		textFieldPrijava.setBounds(426, 94, 364, 20);
		panelMain.add(textFieldPrijava);
		textFieldPrijava.setColumns(10);

		JLabel lblPauzaZaRucak = new JLabel("Pauza za rucak iskorišćena:");
		lblPauzaZaRucak.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPauzaZaRucak.setBounds(300, 125, 178, 20);
		panelMain.add(lblPauzaZaRucak);

		JCheckBox checkBox = new JCheckBox("");
		checkBox.setEnabled(false);
		checkBox.setBounds(484, 125, 21, 20);
		panelMain.add(checkBox);

		JLabel lblU = new JLabel("u:");
		lblU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblU.setBounds(511, 125, 21, 20);
		panelMain.add(lblU);

		textFieldVremeRucka = new JTextField();
		textFieldVremeRucka.setEditable(false);
		textFieldVremeRucka.setBounds(542, 125, 234, 20);
		panelMain.add(textFieldVremeRucka);
		textFieldVremeRucka.setColumns(10);

		JLabel lblRadnihSati = new JLabel("Radnih sati:");
		lblRadnihSati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRadnihSati.setBounds(300, 238, 75, 20);
		panelMain.add(lblRadnihSati);

		textFieldRadnihSati = new JTextField();
		textFieldRadnihSati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldRadnihSati.setEditable(false);
		textFieldRadnihSati.setBounds(300, 269, 178, 20);
		panelMain.add(textFieldRadnihSati);
		textFieldRadnihSati.setColumns(10);

		JLabel lblZaradaPoSatu = new JLabel("Zarada po satu:");
		lblZaradaPoSatu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZaradaPoSatu.setBounds(484, 238, 104, 20);
		panelMain.add(lblZaradaPoSatu);

		textFieldZarada = new JTextField();
		textFieldZarada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldZarada.setEditable(false);
		textFieldZarada.setBounds(488, 269, 288, 20);
		panelMain.add(textFieldZarada);
		textFieldZarada.setColumns(10);

		JButton btnUkupno = new JButton("Ukupno");
		btnUkupno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					textFieldUkupno.setText((Integer.parseInt(textFieldRadnihSati.getText())
							* Double.parseDouble(textFieldZarada.getText())) + "");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error in multiplication");
				}
			}
		});
		btnUkupno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUkupno.setBounds(300, 314, 179, 23);
		panelMain.add(btnUkupno);

		JButton btnPrijava = new JButton("Prijava");

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();

			ResultSet resultSet = state.executeQuery("SELECT Manager.FirstName, Manager.LastName, Employee.EmployeeID "
					+ "FROM Manager INNER JOIN Employee ON Manager.ManagerID = Employee.ManagerID "
					+ "WHERE (((Employee.EmployeeID)=" + employee.personID + "))");

			if (!resultSet.next()) {
				throw new Exception("Error in finding Manager");
			}

			textFieldMenadzer.setText(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));

			resultSet = state.executeQuery("SELECT Employee.HadLunch, LunchTime FROM Employee "
					+ "WHERE (((Employee.EmployeeID)=" + employee.personID + "));");

			if (!resultSet.next()) {
				throw new Exception("Error in setting checkbox");
			}

			if (resultSet.getBoolean(1)) {
				checkBox.setSelected(true);
				textFieldVremeRucka.setText(resultSet.getString(2));
			}

			resultSet = state.executeQuery("SELECT Employee.WorkingHours, Employee.Hourly FROM Employee "
					+ "WHERE (((Employee.EmployeeID)=" + employee.personID + "));");

			if (!resultSet.next()) {
				throw new Exception("Error in finding hours or hourly");
			}

			textFieldRadnihSati.setText(resultSet.getInt(1) + "");
			textFieldZarada.setText(resultSet.getDouble(2) + "");

			resultSet = state.executeQuery("SELECT Employee.CheckIn FROM Employee WHERE (((Employee.EmployeeID)= \""
					+ employee.personID + "\"));");

			if (!resultSet.next()) {
				throw new Exception("Error in check in time");
			}

			if (!resultSet.getString(1).equals("")) {
				textFieldPrijava.setText(resultSet.getString(1));
				btnPrijava.setText("Odjava");
			}

			connection.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		textFieldUkupno = new JTextField();
		textFieldUkupno.setEditable(false);
		textFieldUkupno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldUkupno.setBounds(489, 317, 287, 20);
		panelMain.add(textFieldUkupno);
		textFieldUkupno.setColumns(10);

		JLabel lblTrenutniProjekti = new JLabel("Trenutni projekti:");
		lblTrenutniProjekti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTrenutniProjekti.setBounds(24, 348, 111, 20);
		panelMain.add(lblTrenutniProjekti);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 379, 752, 158);
		panelMain.add(scrollPane);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();

			ResultSet resultSet = state.executeQuery(
					"SELECT DISTINCT Project.ProjectID, Project.Title, Project.Owner, Project.BeginingDate, Project.Location, Project.Description, Work.ManagerID "
							+ "FROM Project INNER JOIN [Work] ON Project.ProjectID = Work.ProjectID "
							+ "WHERE (((Work.EmployeeID)=" + employee.personID + "));");

			while (resultSet.next()) {
				listP.add(new Project(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7)));
			}

			connection.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Projects can not be shown");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		DefaultListModel<String> model = new DefaultListModel<String>();

		for (Project project : listP) {
			model.addElement(project.toString());
		}

		JList<String> list = new JList<String>(model);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					JFrameEmployeeReport frameER = new JFrameEmployeeReport(employee);
					frameER.setVisible(true);
				}
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});
		scrollPane.setViewportView(list);

		JButton btnRucak = new JButton("Ručak");
		btnRucak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (!checkBox.isSelected()) {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();

						String hour = new SimpleDateFormat("HH:mm:ss").format(new Date());
						state.executeUpdate("UPDATE Employee SET Employee.HadLunch = true, Employee.LunchTime = \""
								+ hour + "\"" + "WHERE (((Employee.EmployeeID)= " + employee.personID + "));");

						checkBox.setSelected(true);
						textFieldVremeRucka.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));

						connection.close();
					}

					else {
						JOptionPane.showMessageDialog(null, "Vaš bonus je već iskorišćen");
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error in lunch selection");
				}
			}
		});
		btnRucak.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRucak.setBounds(218, 548, 170, 41);
		panelMain.add(btnRucak);

		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (btnPrijava.getText().equals("Prijava")) {
						String hour = new SimpleDateFormat("HH:mm:ss").format(new Date());
						textFieldPrijava.setText(hour);
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();

						state.executeUpdate("UPDATE Employee SET Employee.CheckIn = \"" + hour
								+ "\"WHERE (((Employee.EmployeeID)= " + employee.personID + "));");

						btnPrijava.setText("Odjava");
						connection.close();
					}

					else {
						Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection connection = DriverManager.getConnection(
								"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
						Statement state = connection.createStatement();

						ResultSet resultSet = state.executeQuery(
								"SELECT Employee.CheckIn, Employee.WorkingHours FROM Employee WHERE (((Employee.EmployeeID)= "
										+ employee.personID + "));");

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

							textFieldRadnihSati.setText(count + "");
							state.executeUpdate("UPDATE Employee SET Employee.WorkingHours = \"" + count
									+ "\", Employee.CheckIn = \"\"" + "WHERE (((Employee.EmployeeID)= "
									+ employee.personID + "));");
						}

						btnPrijava.setText("Prijava");
						textFieldPrijava.setText("");

						connection.close();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnPrijava.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrijava.setBounds(24, 548, 170, 41);
		panelMain.add(btnPrijava);

		JButton btnStampaj = new JButton("Menica");
		btnStampaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String text = employee.firstName + " " + employee.lastName + " \n\r\n\r\n\r\n\rID:\t\t"
							+ employee.personID + "\n\r\n\rPol:\t\t" + employee.gender + "\n\r\n\rDatum rođenja:\t"
							+ employee.birthDate + "\n\r\n\rAdresa:\t\t" + employee.adress + "\n\r\n\rE-Mail:\t\t"
							+ employee.eMail + "\n\r\n\rRadnih sati:\t" + employee.workingHours + "\n\r\n\rSatnica:\t"
							+ employee.hourly + "\n\r\n\r\n\r\n\rZa isplatu:\t"
							+ (employee.workingHours * employee.hourly)
							+ "\n\r\n\r\n\r\n\r________________________\n\r\n\r\t(Izdao)";
					String path = "C:\\Users\\lazar\\OneDrive\\Desktop\\Menice\\" + employee.personID + "_"
							+ employee.firstName + "_" + employee.lastName + ".txt";
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

					state.executeUpdate("UPDATE Employee SET Employee.WorkingHours = \"0\""
							+ "WHERE (((Employee.EmployeeID)= " + employee.personID + "));");

					ResultSet resultSet = state
							.executeQuery("SELECT Employee.WorkingHours FROM Employee WHERE (((Employee.EmployeeID)= "
									+ employee.personID + "));");

					if (!resultSet.next()) {
						throw new Exception("Error in getting int");
					}

					textFieldRadnihSati.setText(resultSet.getInt(1) + "");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error in database handling");
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

		});
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
