package model;

/**
 * Repräsentiert eine Buchung eines Studenten für einen Raum.
 */
public class Buchung {

	private int buchungsid;
	private Student student;
	private Raum raum;
	private Zeitslot zeitslot;

	public Buchung(int buchungsid, Student student, Raum raum, Zeitslot zeitslot) {
		this.buchungsid = buchungsid;
		this.student = student;
		this.raum = raum;
		this.zeitslot = zeitslot;
	}

	public int getBuchungsid() {
		return buchungsid;
	}

	public Student getStudent() {
		return student;
	}

	public Raum getRaum() {
		return raum;
	}

	public Zeitslot getZeitslot() {
		return zeitslot;
	}

	/**
	 * Entfernt die Buchung aus Student und Raum.
	 */
	public void stornieren() {
		student.removeBuchung(this);
		raum.removeBuchung(this);
	}

	@Override
	public String toString() {
		return "Buchung #" + buchungsid + " | Student: " + student.getName() + " | Raum: " + raum.getName() + " | "
				+ zeitslot;
	}
}
