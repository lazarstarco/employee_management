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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class JFrameEmployeeTable extends JFrame {

	private JPanel contentPane;

	public JTable table;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	public ArrayList<Employee> list = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmployeeTable frame = new JFrameEmployeeTable();
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
	public JFrameEmployeeTable() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 420,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 300, 840, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 820, 455);
		contentPane.add(scrollPane);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();
			ResultSet resultSet = state.executeQuery("SELECT * FROM Employee");
			while (resultSet.next()) {
				list.add(new Employee(Integer.parseInt(resultSet.getString("EmployeeID")),
						resultSet.getString("FirstName"), resultSet.getString("LastName"),
						resultSet.getString("BirthDate"), resultSet.getString("Adress"), resultSet.getString("EMail"),
						resultSet.getString("Gender"), Integer.parseInt(resultSet.getString("WorkingHours")),
						Double.parseDouble(resultSet.getString("Hourly"))));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error in table creating");
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.getStackTrace();
		}

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Ime", "Prezime", "Pol",
				"Datum rođenja", "Adresa", "E-Mail", "Radni sati", "Satnica" }));
		scrollPane.setViewportView(table);

		table.setModel(new JTableEmployee(list));

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameEmployeeInsert frameIE = new JFrameEmployeeInsert();
				frameIE.setVisible(true);
				dispose();
			}
		});
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDodaj.setBounds(20, 518, 256, 71);
		contentPane.add(btnDodaj);

		JButton btnUredi = new JButton("Uredi");
		btnUredi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameEmployeeChange frameCE = new JFrameEmployeeChange();
				frameCE.setVisible(true);
				dispose();
			}
		});
		btnUredi.setBounds(292, 518, 256, 71);
		contentPane.add(btnUredi);

		JButton btnUkloni = new JButton("Ukloni");
		btnUkloni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameEmployeeRemove frameER = new JFrameEmployeeRemove();
				frameER.setVisible(true);
				dispose();
			}
		});
		btnUkloni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUkloni.setBounds(564, 518, 256, 71);
		contentPane.add(btnUkloni);

		JButton btnClose = new JButton("");
		btnClose.setBorder(null);
		btnClose.setIcon(new ImageIcon("C:\\Users\\lazar\\OneDrive\\Desktop\\DefinitelyNotPhotos\\mfcd_micro_closed_shop_6056861.png"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnClose.setBounds(800, 11, 30, 30);
		contentPane.add(btnClose);

		JLabel lblUpravljanjeZaposlenima = new JLabel("Upravljanje zaposlenima");
		lblUpravljanjeZaposlenima.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpravljanjeZaposlenima.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUpravljanjeZaposlenima.setBounds(10, 11, 820, 30);
		contentPane.add(lblUpravljanjeZaposlenima);

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
	}
}
