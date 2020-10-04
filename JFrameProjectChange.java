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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class JFrameProjectChange extends JFrame {

	private JPanel contentPane;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	ArrayList<String> list = new ArrayList<String>();
	ArrayList<Integer> ID = new ArrayList<Integer>();
	ArrayList<Project> listP = new ArrayList<Project>();

	int selected;
	private JTextField textFieldNaziv;
	private JTextField textFieldPoslodavac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameProjectChange frame = new JFrameProjectChange();
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
	public JFrameProjectChange() {
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

		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNaziv.setBounds(10, 143, 104, 19);
		contentPane.add(lblNaziv);

		textFieldNaziv = new JTextField();
		textFieldNaziv.setToolTipText("Ime");
		textFieldNaziv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNaziv.setColumns(10);
		textFieldNaziv.setBounds(124, 143, 321, 20);
		contentPane.add(textFieldNaziv);

		JLabel lblPoslodavac = new JLabel("Poslodavac:");
		lblPoslodavac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPoslodavac.setBounds(10, 173, 104, 19);
		contentPane.add(lblPoslodavac);

		textFieldPoslodavac = new JTextField();
		textFieldPoslodavac.setToolTipText("Prezime");
		textFieldPoslodavac.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldPoslodavac.setColumns(10);
		textFieldPoslodavac.setBounds(124, 174, 321, 20);
		contentPane.add(textFieldPoslodavac);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOpis.setBounds(10, 203, 104, 19);
		contentPane.add(lblOpis);

		JScrollPane scrollPaneOpis = new JScrollPane();
		scrollPaneOpis.setBounds(10, 233, 435, 97);
		contentPane.add(scrollPaneOpis);

		JTextArea textAreaOpis = new JTextArea();
		scrollPaneOpis.setViewportView(textAreaOpis);

		JButton btnPonisti = new JButton("Poni\u0161ti");
		btnPonisti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPonisti.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPonisti.setBounds(290, 341, 155, 32);
		contentPane.add(btnPonisti);
		JScrollPane scrollPaneProjekti = new JScrollPane();
		scrollPaneProjekti.setBounds(10, 11, 435, 121);
		contentPane.add(scrollPaneProjekti);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			Statement state = connection.createStatement();
			ResultSet resultSet = state.executeQuery(
					"SELECT DISTINCT Project.ProjectID, Project.Title, Project.Owner, Project.BeginingDate, Project.Location, Project.Description, Work.ManagerID\r\n"
							+ "FROM Project INNER JOIN [Work] ON Project.ProjectID = Work.ProjectID;\r\n" + "");

			while (resultSet.next()) {
				listP.add(new Project(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7)));
			}

			connection.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		DefaultListModel<String> modelP = new DefaultListModel<String>();

		for (int i = 0; i < listP.size()-1; i++) {
			if (listP.get(i).projectID != listP.get(i+1).projectID || listP.get(i+1) == listP.get(listP.size()-1)) {
				String model = listP.get(i).title + " " + listP.get(i).owner + ", " + listP.get(i).beginingDate + ", "
						+ listP.get(i).location;
				modelP.addElement(model);
			}
		}

		JList<String> listEmployee = new JList<String>(modelP);
		listEmployee.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					selected = listEmployee.getSelectedIndex();
					textFieldNaziv.setText(listP.get(selected).title);
					textFieldPoslodavac.setText(listP.get(selected).owner);
					textAreaOpis.setText(listP.get(selected).description);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		scrollPaneProjekti.setViewportView(listEmployee);

		JButton btnUredi = new JButton("Uredi");
		btnUredi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					PreparedStatement state = connection.prepareStatement(
							"UPDATE Project SET Title = ?, Owner = ?, Description = ? WHERE ProjectID = ?");
					state.setString(1, textFieldNaziv.getText());
					state.setString(2, textFieldPoslodavac.getText());
					state.setString(3, textAreaOpis.getText());
					state.setInt(4, listP.get(selected).projectID);

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
