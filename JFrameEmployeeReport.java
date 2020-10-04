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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class JFrameEmployeeReport extends JFrame {

	private JPanel contentPane;

	static Point mouseDownScreenCoords;
	static Point mouseDownCompCoords;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmployeeReport frame = new JFrameEmployeeReport(new Employee());
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
	public JFrameEmployeeReport(Employee employee) {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 4,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4,
				Toolkit.getDefaultToolkit().getScreenSize().width / 2,
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

		JLabel labelIme = new JLabel(employee.firstName);
		labelIme.setBounds(10, 11, 663, 23);
		labelIme.setHorizontalAlignment(SwingConstants.CENTER);
		labelIme.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(labelIme);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 663, 246);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnZatvori.setBounds(553, 333, 120, 40);
		btnZatvori.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnZatvori);

		JButton btnPodnesi = new JButton("Podnesi");
		btnPodnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text = textArea.getText();
					String date = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date());
					String path = "C:\\Users\\lazar\\OneDrive\\Desktop\\Izvestaji\\" + date + employee.personID + "_"
							+ employee.firstName + "_" + employee.lastName + ".txt";
					BufferedWriter output = null;
					File file = new File(path); // datum_vreme_id_ime_prezime.txt
					file.createNewFile();
					output = new BufferedWriter(new FileWriter(file));
					output.write(text);
					if (output != null) {
						output.close();
						JOptionPane.showMessageDialog(null, "Vaš izveštaj možete naći u folderu \"Izvesaji\"");
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error in printing");
				}
			}
		});
		btnPodnesi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPodnesi.setBounds(10, 333, 120, 40);
		contentPane.add(btnPodnesi);

		JLabel lblIZV = new JLabel("I Z V E \u0160 T A J   O   P R O J E K T U");
		lblIZV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIZV.setBounds(219, 45, 244, 20);
		contentPane.add(lblIZV);

		JButton btnObriši = new JButton("Obr\u0161i");
		btnObriši.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		btnObriši.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnObriši.setBounds(281, 335, 120, 40);
		contentPane.add(btnObriši);
	}
}
