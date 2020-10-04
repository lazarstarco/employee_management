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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class JFrameProjectInsert extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNaziv;
	private JTextField textFieldPoslodavac;
	private JTextField textFieldUlica;
	private JTextField textFieldGrad;

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
					JFrameProjectInsert frame = new JFrameProjectInsert();
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
	public JFrameProjectInsert() {
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

		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNaziv.setBounds(10, 20, 104, 19);
		contentPane.add(lblNaziv);

		JLabel lblPoslodavac = new JLabel("Poslodavac:");
		lblPoslodavac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPoslodavac.setBounds(10, 59, 104, 19);
		contentPane.add(lblPoslodavac);

		JLabel lblLokacija = new JLabel("Lokacija:");
		lblLokacija.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLokacija.setBounds(10, 98, 104, 19);
		contentPane.add(lblLokacija);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOpis.setBounds(10, 137, 104, 19);
		contentPane.add(lblOpis);

		textFieldNaziv = new JTextField();
		textFieldNaziv.setToolTipText("Ime");
		textFieldNaziv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNaziv.setBounds(124, 19, 321, 20);
		contentPane.add(textFieldNaziv);
		textFieldNaziv.setColumns(10);

		textFieldPoslodavac = new JTextField();
		textFieldPoslodavac.setToolTipText("Prezime");
		textFieldPoslodavac.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldPoslodavac.setBounds(124, 58, 321, 20);
		contentPane.add(textFieldPoslodavac);
		textFieldPoslodavac.setColumns(10);

		textFieldUlica = new JTextField();
		textFieldUlica.setToolTipText("Ulica i broj");
		textFieldUlica.setBounds(124, 97, 155, 20);
		contentPane.add(textFieldUlica);
		textFieldUlica.setColumns(10);

		textFieldGrad = new JTextField();
		textFieldGrad.setToolTipText("Grad");
		textFieldGrad.setBounds(290, 97, 155, 20);
		contentPane.add(textFieldGrad);
		textFieldGrad.setColumns(10);
		setUndecorated(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 435, 163);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean valid = true;

					String naziv = textFieldNaziv.getText();
					if (naziv.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Ime\" je prazno");
						valid = false;
					}

					String poslodavac = textFieldPoslodavac.getText();
					if (poslodavac.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Prezime\" je prazno");
						valid = false;
					}

					String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

					String lokacija = textFieldUlica.getText() + ", " + textFieldGrad.getText();
					if (lokacija.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Lokacija\" je prazno");
						valid = false;
					}

					String opis = textArea.getText();
					if (opis.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Polje \"Opis\" je prazno");
					}

					if (valid == false) {
						throw new IOException("Neophodno je popuniti sva polja");
					}

					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection connection = DriverManager.getConnection(
							"jdbc:ucanaccess://D:\\FTN\\II godina\\OOP\\Seminarski\\ManagerDB.accdb");
					Statement state = connection.createStatement();
					state.executeUpdate(
							"INSERT INTO Project (Title, Owner, BeginingDate, Location, Description) VALUES (\"" + naziv
									+ "\", \"" + poslodavac + "\", \"" + date + "\", \"" + lokacija + "\", \"" + opis
									+ "\");");

					JOptionPane.showMessageDialog(null, "Projekat je uspešno kreiran");

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
