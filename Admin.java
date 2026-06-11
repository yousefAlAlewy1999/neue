package model;

/**
 * Repräsentiert einen Administrator. Admins verwalten Räume und Buchungen.
 */
public class Admin extends Person {

	private int adminid;

	public Admin(String name, String email, String passwort, int adminid) {
		super(name, email, passwort);
		this.adminid = adminid;
	}

	public int getAdminid() {
		return adminid;
	}

	/**
	 * Platzhalter laut Aufgabenstellung.
	 */
	public void raumVerwalten() {
		// Logik im KonsolenMenue
	}

	@Override
	public String toString() {
		return "Admin: " + name + " | Admin-ID: " + adminid;
	}
}
