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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
@SuppressWarnings("serial")
public class JFrameEmployeeChange extends JFrame {

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
	ArrayList<Employee> listE = new ArrayList<Employee>();

	int selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmployeeChange frame = new JFrameEmployeeChange();
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
	public JFrameEmployeeChange() {
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
		lblIme.setBounds(10, 143, 104, 19);
		contentPane.add(lblIme);

		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(10, 173, 104, 19);
		contentPane.add(lblPrezime);

		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setBounds(10, 203, 104, 19);
		contentPane.add(lblAdresa);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 233, 104, 19);
		contentPane.add(lblEmail);

		JLabel lblSatnica = new JLabel("Satnica:");
		lblSatnica.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSatnica.setBounds(10, 263, 104, 19);
		contentPane.add(lblSatnica);

		JLabel lblIdMenadera = new JLabel("ID menad\u017Eera:");
		lblIdMenadera.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdMenadera.setBounds(10, 293, 104, 19);
		contentPane.add(lblIdMenadera);

		textFieldIme = new JTextField();
		textFieldIme.setToolTipText("Ime");
		textFieldIme.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldIme.setBounds(124, 142, 321, 20);
		contentPane.add(textFieldIme);
		textFieldIme.setColumns(10);

		textFieldPrezime = new JTextField();
		textFieldPrezime.setToolTipText("Prezime");
		textFieldPrezime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldPrezime.setBounds(124, 172, 321, 20);
		contentPane.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);

		textFieldUlica = new JTextField();
		textFieldUlica.setToolTipText("Ulica i broj");
		textFieldUlica.setBounds(124, 202, 155, 20);
		contentPane.add(textFieldUlica);
		textFieldUlica.setColumns(10);

		textFieldGrad = new JTextField();
		textFieldGrad.setToolTipText("Grad");
		textFieldGrad.setBounds(290, 202, 155, 20);
		contentPane.add(textFieldGrad);
		textFieldGrad.setColumns(10);

		textFieldMail1 = new JTextField();
		textFieldMail1.setBounds(124, 232, 170, 20);
		contentPane.add(textFieldMail1);
		textFieldMail1.setColumns(10);

		JLabel label = new JLabel("@");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(304, 233, 19, 19);
		contentPane.add(label);

		textFieldMail2 = new JTextField();
		textFieldMail2.setBounds(329, 232, 116, 20);
		contentPane.add(textFieldMail2);
		textFieldMail2.setColumns(10);

		textFieldSatnica = new JTextField();
		textFieldSatnica.setBounds(124, 262, 321, 20);
		contentPane.add(textFieldSatnica);
		textFieldSatnica.setColumns(10);

		JComboBox comboBoxMenadzer = new JComboBox();
		comboBoxMenadzer.setBounds(124, 292, 321, 20);
		contentPane.add(comboBoxMenadzer);
		setUndecorated(true);

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 435, 121);
		contentPane.add(scrollPane);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();
			ResultSet resultSet = state.executeQuery(
					"SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName, Employee.BirthDate, Employee.Adress, Employee.EMail, Employee.Gender, "
							+ "Employee.WorkingHours, Employee.Hourly, Employee.ManagerID FROM Employee;");

			while (resultSet.next()) {
				listE.add(new Employee(resultSet.getInt("EmployeeID"), resultSet.getString("FirstName"),
						resultSet.getString("LastName"), resultSet.getString("BirthDate"),
						resultSet.getString("Adress"), resultSet.getString("EMail"), resultSet.getString("Gender"),
						resultSet.getInt("WorkingHours"), resultSet.getDouble("Hourly")));
				ID.add(resultSet.getInt("ManagerID"));
			}

			connection.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		DefaultListModel<String> modelE = new DefaultListModel<String>();

		for (Employee employee : listE) {
			String model = employee.firstName + " " + employee.lastName + ", " + employee.adress + ", " + employee.eMail
					+ ", " + employee.hourly + "/h";
			modelE.addElement(model);
		}

		JList<String> listEmployee = new JList<String>(modelE);
		listEmployee.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					selected = listEmployee.getSelectedIndex();
					String toSplit = listE.get(selected).adress;
					String[] splitted = toSplit.split(",", 2);
					textFieldIme.setText(listE.get(selected).firstName);
					textFieldPrezime.setText(listE.get(selected).lastName);
					textFieldUlica.setText(splitted[0]);
					textFieldGrad.setText(splitted[1]);
					toSplit = listE.get(selected).eMail;
					splitted = toSplit.split("@", 2);
					textFieldMail1.setText(splitted[0]);
					textFieldMail2.setText(splitted[1]);
					textFieldSatnica.setText(listE.get(selected).hourly + "");
					comboBoxMenadzer.setSelectedIndex(ID.get(selected) - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(listEmployee);

		JButton btnUredi = new JButton("Uredi");
		btnUredi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					PreparedStatement state = connection.prepareStatement(
							"UPDATE Employee SET FirstName = ?, LastName = ?, Adress = ?, EMail = ?, Hourly = ?, ManagerID = ? WHERE EmployeeID = ?");
					state.setString(1, textFieldIme.getText());
					state.setString(2, textFieldPrezime.getText());
					state.setString(3, textFieldUlica.getText() + ", " + textFieldGrad.getText());
					state.setString(4, textFieldMail1.getText() + "@" + textFieldMail2.getText());
					state.setString(5, textFieldSatnica.getText());
					state.setInt(6, ID.get(selected));
					state.setInt(7, listE.get(selected).personID);
					state.executeUpdate();
					JOptionPane.showMessageDialog(null, "Izmene su unete");
					state.close();
					
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Inserting failed");
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnUredi.setBounds(10, 341, 155, 32);
		contentPane.add(btnUredi);
	}
}
