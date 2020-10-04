package project;

public class Employee extends Person {

	public boolean lunchBreak;

	public Employee(int personID, String firstName, String lastName, String birthDate, String adress, String eMail,
			String gender, int workingHours, double hourly) {
		super(personID, firstName, lastName, birthDate, adress, eMail, gender, workingHours, hourly);
		this.lunchBreak = false;
	}

	public Employee() {
		super(0, "", "", "", "", "", "", 0, 0);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public double getHourly() {
		return hourly;
	}

	public void setHourly(double hourly) {
		this.hourly = hourly;
	}

	public boolean isLunchBreak() {
		return lunchBreak;
	}

	public void setLunchBreak(boolean lunchBreak) {
		this.lunchBreak = lunchBreak;
	}

	@Override
	public String toString() {
		return "Ime: " + firstName + "   Prezime: " + lastName + "   Pol: " + gender + "   E-Mail: " + eMail
				+ "   Satnica: " + hourly;
	}

}
