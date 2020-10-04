package project;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class JTableEmployee extends AbstractTableModel {

	ArrayList<Employee> list;

	public JTableEmployee(ArrayList<Employee> list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Employee employee = list.get(row);
		switch (column) {
		case 0:
			return employee.personID;
		case 1:
			return employee.firstName;
		case 2:
			return employee.lastName;
		case 3:
			return employee.gender;
		case 4:
			return employee.birthDate;
		case 5:
			return employee.adress;
		case 6:
			return employee.eMail;
		case 7:
			return employee.workingHours;
		case 8:
			return employee.hourly;
		default:
			return "Error";
		}
	}

	@Override
	public String getColumnName (int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Ime";
		case 2:
			return "Prezime";
		case 3:
			return "Pol";
		case 4:
			return "Datum rođenja";
		case 5:
			return "Adresa";
		case 6:
			return "E-Mail";
		case 7:
			return "Radni sati";
		case 8:
			return "Satnica";
		default:
			return "Error";
		}
	}
}
