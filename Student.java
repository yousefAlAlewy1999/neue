package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

	private String matrikelnummer;
	private List<Buchung> buchungen;

	public Student(String name, String email, String passwort, String matrikelnummer) {
		super(name, email, passwort);
		this.matrikelnummer = matrikelnummer;
		this.buchungen = new ArrayList<>();
	}

	public String getMatrikelnummer() {
		return matrikelnummer;
	}

	public List<Buchung> getBuchungen() {
		return buchungen;
	}

	public void addBuchung(Buchung b) {
		if (!buchungen.contains(b)) {
			buchungen.add(b);
		}
	}

	public void removeBuchung(Buchung b) {
		buchungen.remove(b);
	}

	@Override
	public String toString() {
		return String.format("Student: %s | Matrikel: %s | Buchungen: %d", name, matrikelnummer, buchungen.size());
	}
}