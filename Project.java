package project;

public class Project {

	public int projectID;
	public String title;
	public String owner;
	public String beginingDate;
	public String location;
	public String description;
	public int manager;

	public Project(int projectID, String title, String owner, String beginingDate, String location, String description,
			int manager) {
		super();
		this.projectID = projectID;
		this.title = title;
		this.owner = owner;
//		String pattern = "dd-MM-yyyy";
//		SimpleDateFormat date = new SimpleDateFormat(pattern);
//		this.beginingDate = date.format(new Date());
		this.beginingDate = beginingDate;
		this.location = location;
		this.description = description;
		this.manager = manager;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBeginingDate() {
		return beginingDate;
	}

	public void setBeginingDate(String beginingDate) {
		this.beginingDate = beginingDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Naziv: " + title + "    Datum pocetka: " + beginingDate + "    Lokacija: " + location + "    Opis: "
				+ description;
	}
}
