package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class JFrameEmployeeInsert extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldUlica;
	private JTextField textFieldGrad;
	private JTextField textFieldMail1;
	private JTextField textFieldMail2;
	private JTextField textFieldSatnica;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	ArrayList<String> list = new ArrayList<String>();
	ArrayList<Integer> ID = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmployeeInsert frame = new JFrameEmployeeInsert();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JFrameEmployeeInsert() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3,
				Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

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
		JButton btnPonisti = new JButton("Poni\u0161ti");
		btnPonisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPonisti.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPonisti.setBounds(290, 341, 155, 32);
		contentPane.add(btnPonisti);

		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(10, 20, 104, 19);
		contentPane.add(lblIme);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(10, 59, 104, 19);
		contentPane.add(lblPrezime);

		JLabel lblPol = new JLabel("Pol:");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(10, 98, 104, 19);
		contentPane.add(lblPol);

		JLabel lblDatumRoenja = new JLabel("Datum ro\u0111enja:");
		lblDatumRoenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatumRoenja.setBounds(10, 137, 104, 19);
		contentPane.add(lblDatumRoenja);

		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setBounds(10, 176, 104, 19);
		contentPane.add(lblAdresa);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 215, 104, 19);
		contentPane.add(lblEmail);

		JLabel lblSatnica = new JLabel("Satnica:");
		lblSatnica.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSatnica.setBounds(10, 254, 104, 19);
		contentPane.add(lblSatnica);

		JLabel lblIdMenadera = new JLabel("ID menad\u017Eera:");
		lblIdMenadera.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdMenadera.setBounds(10, 293, 104, 19);
		contentPane.add(lblIdMenadera);

		textFieldIme = new JTextField();
		textFieldIme.setToolTipText("Ime");
		textFieldIme.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldIme.setBounds(124, 19, 321, 20);
		contentPane.add(textFieldIme);
		textFieldIme.setColumns(10);

		textFieldPrezime = new JTextField();
		textFieldPrezime.setToolTipText("Prezime");
		textFieldPrezime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldPrezime.setBounds(124, 58, 321, 20);
		contentPane.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);

		JComboBox comboBoxPol = new JComboBox();
		comboBoxPol.setToolTipText("Pol");
		comboBoxPol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxPol.setModel(new DefaultComboBoxModel(new String[] { "Mu\u0161ki   ( M )", "\u017Denski   ( Z )" }));
		comboBoxPol.setBounds(124, 97, 321, 20);
		contentPane.add(comboBoxPol);

		JComboBox comboBoxGodina = new JComboBox();
		comboBoxGodina.setToolTipText("Godina");
		comboBoxGodina.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxGodina.setBounds(124, 136, 104, 20);
		contentPane.add(comboBoxGodina);

		DefaultComboBoxModel modelY = (DefaultComboBoxModel) comboBoxGodina.getModel();
		modelY.removeAllElements();

		for (int i = 1900; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
			modelY.addElement(i + "");
		}

		comboBoxGodina.setModel(modelY);
		getContentPane().add(comboBoxGodina);

		JComboBox comboBoxMesec = new JComboBox();

		comboBoxMesec.setToolTipText("Mesec");
		comboBoxMesec.setModel(new DefaultComboBoxModel(new String[] { "Januar", "Februar", "Mart", "April", "Maj",
				"Jun", "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar" }));
		comboBoxMesec.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxMesec.setBounds(238, 136, 117, 20);
		contentPane.add(comboBoxMesec);

		JComboBox comboBoxDan = new JComboBox();
		comboBoxDan.setToolTipText("Dan");
		comboBoxDan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxDan.setBounds(365, 136, 80, 20);
		contentPane.add(comboBoxDan);

		comboBoxGodina.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int n = YearMonth.of(Integer.parseInt(comboBoxGodina.getSelectedItem().toString()),
						comboBoxMesec.getSelectedIndex() + 1).lengthOfMonth();
				int[] itemsDay = new int[n];
				for (int i = 0; i < n; i++) {
					itemsDay[i] = i + 1;
				}

				DefaultComboBoxModel modelD = (DefaultComboBoxModel) comboBoxDan.getModel();
				modelD.removeAllElements();

				for (int item : itemsDay) {
					modelD.addElement(item + "");
				}
				comboBoxDan.setModel(modelD);
			}
		});
		comboBoxMesec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int n = YearMonth.of(Integer.parseInt(comboBoxGodina.getSelectedItem().toString()),
						comboBoxMesec.getSelectedIndex() + 1).lengthOfMonth();
				int[] itemsDay = new int[n];
				for (int i = 0; i < n; i++) {
					itemsDay[i] = i + 1;
				}

				DefaultComboBoxModel modelD = (DefaultComboBoxModel) comboBoxDan.getModel();
				modelD.removeAllElements();

				for (int item : itemsDay) {
					modelD.addElement(item + "");
				}
				comboBoxDan.setModel(modelD);
			}
		});
		getContentPane().add(comboBoxDan);

		textFieldUlica = new JTextField();
		textFieldUlica.setToolTipText("Ulica i broj");
		textFieldUlica.setBounds(124, 175, 155, 20);
		contentPane.add(textFieldUlica);
		textFieldUlica.setColumns(10);

		textFieldGrad = new JTextField();
		textFieldGrad.setToolTipText("Grad");
		textFieldGrad.setBounds(290, 175, 155, 20);
		contentPane.add(textFieldGrad);
		textFieldGrad.setColumns(10);

		textFieldMail1 = new JTextField();
		textFieldMail1.setBounds(124, 214, 170, 20);
		contentPane.add(textFieldMail1);
		textFieldMail1.setColumns(10);

		JLabel label = new JLabel("@");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(304, 215, 19, 19);
		contentPane.add(label);

		textFieldMail2 = new JTextField();
		textFieldMail2.setBounds(329, 214, 116, 20);
		contentPane.add(textFieldMail2);
		textFieldMail2.setColumns(10);

		textFieldSatnica = new JTextField();
		textFieldSatnica.setBounds(124, 253, 321, 20);
		contentPane.add(textFieldSatnica);
		textFieldSatnica.setColumns(10);

		JComboBox comboBoxMenadzer = new JComboBox();
		comboBoxMenadzer.setBounds(124, 292, 321, 20);
		contentPane.add(comboBoxMenadzer);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();
			ResultSet resultSet = state.executeQuery("SELECT ManagerID, FirstName, LastName FROM Manager");

			while (resultSet.next()) {
				list.add(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
				ID.add(resultSet.getInt(1));
			}

			DefaultComboBoxModel modelM = (DefaultComboBoxModel) comboBoxMenadzer.getModel();
			modelM.removeAllElements();

			for (String item : list) {
				modelM.addElement(item + "");
			}
			comboBoxMenadzer.setModel(modelM);
			getContentPane().add(comboBoxMenadzer);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in Manager input");
		}

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					Statement state = connection.createStatement();
					state.executeQuery("SELECT ManagerID, FirstName, LastName FROM Manager");
					connection.close();

					boolean valid = true;
					String ime = textFieldIme.getText();
					if (ime.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Ime\" je prazno");
						valid = false;
					}
					String prezime = textFieldPrezime.getText();
					if (prezime.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Prezime\" je prazno");
						valid = false;
					}
					String pol = comboBoxPol.getSelectedIndex() == 0 ? "M" : "Z";
					String godina = comboBoxGodina.getSelectedItem().toString();
					String mesec = comboBoxMesec.getSelectedIndex() + 1 + "";
					if (mesec.length() == 1) {
						mesec = "0" + mesec;
					}

					String dan = comboBoxDan.getSelectedIndex() + 1 + "";
					if (dan.length() == 1) {
						dan = "0" + dan;
					}
					String datum = dan + "/" + mesec + "/" + godina;
					String adresa = textFieldUlica.getText() + ", " + textFieldGrad.getText();
					if (adresa.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Adresa\" je prazno");
						valid = false;
					}
					String mail = textFieldMail1.getText() + "@" + textFieldMail2.getText();
					if (mail.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"E-Mail\" je prazno");
						valid = false;
					}
					String satnica = textFieldSatnica.getText();
					if (satnica.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Satnica\" je prazno");
						valid = false;
					}
					int manager = ID.get(comboBoxMenadzer.getSelectedIndex());

					if (valid == false) {
						throw new IOException("Neophodno je popuniti sva polja");
					}

					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					state = connection.createStatement();
					state.executeUpdate(
							"INSERT INTO Employee (FirstName, LastName, Gender, BirthDate, Adress, EMail, WorkingHours, Hourly, ManagerID) VALUES (\""
									+ ime + "\", \"" + prezime + "\", \"" + pol + "\", \"" + datum + "\", \"" + adresa
									+ "\", \"" + mail + "\", 0, \"" + Integer.parseInt(satnica) + "\", \"" + manager
									+ "\");");

					JFrameEmployeePassword frameEP = new JFrameEmployeePassword();
					frameEP.setVisible(true);
					
					dispose();
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Inserting failed");
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDodaj.setBounds(10, 341, 155, 32);
		contentPane.add(btnDodaj);

	}
}
