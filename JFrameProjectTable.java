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
public class JFrameProjectTable extends JFrame {

	private JPanel contentPane;

	public JTable table;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	public ArrayList<Project> list = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameProjectTable frame = new JFrameProjectTable();
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
	public JFrameProjectTable() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 420,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 300, 840, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
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
			ResultSet resultSet = state.executeQuery(
					"SELECT DISTINCT Project.ProjectID, Project.Title, Project.Owner, Project.BeginingDate, Project.Location, Project.Description, Work.ManagerID\r\n"
							+ "FROM Project INNER JOIN [Work] ON Project.ProjectID = Work.ProjectID;\r\n" + "");
			while (resultSet.next()) {
				list.add(new Project(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7)));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Error in table creating");
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.getStackTrace();
		}

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Naziv", "Poslodavac", "Datum početka", "Lokacija", "Opis", "Menadžer ID" }));
		scrollPane.setViewportView(table);

		table.setModel(new JTableProject(list));

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameProjectInsert framePI = new JFrameProjectInsert();
				framePI.setVisible(true);
				dispose();;
			}
		});
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDodaj.setBounds(20, 518, 256, 71);
		contentPane.add(btnDodaj);

		JButton btnUredi = new JButton("Uredi");
		btnUredi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameProjectChange framePC = new JFrameProjectChange();
				framePC.setVisible(true);
				dispose();
			}
		});
		btnUredi.setBounds(292, 518, 256, 71);
		contentPane.add(btnUredi);

		JButton btnUkloni = new JButton("Ukloni");
		btnUkloni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameProjectRemove framePR = new JFrameProjectRemove();
				framePR.setVisible(true);
				dispose();
			}
		});
		btnUkloni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUkloni.setBounds(564, 518, 256, 71);
		contentPane.add(btnUkloni);

		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon(
				"C:\\Users\\lazar\\OneDrive\\Desktop\\DefinitelyNotPhotos\\mfcd_micro_closed_shop_6056861.png"));
		btnClose.setBorder(null);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnClose.setBounds(800, 11, 30, 30);
		contentPane.add(btnClose);

		JLabel lblUpravljanjeZaposlenima = new JLabel("Upravljanje projektima");
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
