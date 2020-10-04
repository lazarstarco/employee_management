package project;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class JFrameManagerPayment extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRadnihSati;
	private JTextField textFieldZarada;
	private JTextField textFieldUkupno;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameManagerPayment frame = new JFrameManagerPayment(new Manager());
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
	public JFrameManagerPayment(Manager manager) {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3,
				Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setAlwaysOnTop(true);
		
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

		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnZatvori.setBounds(325, 205, 120, 40);
		btnZatvori.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnZatvori);

		JLabel labelRadnihSati = new JLabel("Radnih sati:");
		labelRadnihSati.setBounds(10, 45, 75, 20);
		labelRadnihSati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(labelRadnihSati);

		JLabel labelZarada = new JLabel("Zarada po satu:");
		labelZarada.setBounds(10, 76, 104, 20);
		labelZarada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(labelZarada);

		textFieldRadnihSati = new JTextField();
		textFieldRadnihSati.setBounds(223, 45, 222, 20);
		textFieldRadnihSati.setText("0");
		textFieldRadnihSati.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldRadnihSati.setEditable(false);
		textFieldRadnihSati.setColumns(10);
		contentPane.add(textFieldRadnihSati);

		textFieldZarada = new JTextField();
		textFieldZarada.setBounds(223, 76, 222, 20);
		textFieldZarada.setText("0.00");
		textFieldZarada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldZarada.setEditable(false);
		textFieldZarada.setColumns(10);
		contentPane.add(textFieldZarada);

		JLabel labelIme = new JLabel("");
		labelIme.setBounds(0, 11, 445, 23);
		labelIme.setHorizontalAlignment(SwingConstants.CENTER);
		labelIme.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(labelIme);

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection connection = DriverManager.getConnection(
					"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
			
			Statement state = connection.createStatement();
			ResultSet resultSet = state.executeQuery("SELECT Manager.FirstName, Manager.LastName, Manager.WorkingHours, Manager.Hourly FROM Manager "
					+ "WHERE (((Manager.ManagerID)=" + manager.personID + "));");
			
			if (!resultSet.next()) {
				throw new Exception("Error in reading from database");
			}
			
			labelIme.setText(resultSet.getString(1) + " " + resultSet.getString(2));
			textFieldRadnihSati.setText(resultSet.getInt(3) + "");
			textFieldZarada.setText(resultSet.getDouble(4) + "");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		

		JLabel lblUkupno = new JLabel("Ukupno:");
		lblUkupno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUkupno.setBounds(10, 107, 66, 20);
		contentPane.add(lblUkupno);

		textFieldUkupno = new JTextField();
		textFieldUkupno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldUkupno.setText("0.00");
		textFieldUkupno.setEditable(false);
		textFieldUkupno.setBounds(223, 107, 222, 20);
		contentPane.add(textFieldUkupno);
		textFieldUkupno.setColumns(10);

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
		btnUkupno.setBounds(10, 205, 120, 40);
		contentPane.add(btnUkupno);
	}
}
