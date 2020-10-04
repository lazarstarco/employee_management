package project;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class JTableProject extends AbstractTableModel {

	ArrayList<Project> list;

	public JTableProject(ArrayList<Project> list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Project project = list.get(row);
		switch (column) {
		case 0:
			return project.projectID;
		case 1:
			return project.title;
		case 2:
			return project.owner;
		case 3:
			return project.beginingDate;
		case 4:
			return project.location;
		case 5:
			return project.description;
		case 6:
			return project.manager;
		default:
			return "Error";
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Naziv";
		case 2:
			return "Poslodavac";
		case 3:
			return "Datum početka";
		case 4:
			return "Lokacija";
		case 5:
			return "Opis";
		case 6:
			return "Menadžer ID";
		default:
			return "Error";
		}
	}
}
