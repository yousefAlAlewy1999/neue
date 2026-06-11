package anzeige;

import java.util.List;
import java.util.Scanner;

import exception.BuchungsException;
import manager.BuchungsManager;
import model.Admin;
import model.Buchung;
import model.Raum;
import model.Student;
import model.Zeitslot;

/**
 * Benutzeroberfläche (Konsole) des Systems. Enthält die main()-Methode.
 */
public class KonsolenMenue {

	private BuchungsManager manager = new BuchungsManager();
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		new KonsolenMenue().start();
	}

	/**
	 * Hauptmenü des Programms.
	 */
	public void start() {
		while (true) {
			System.out.println("\n=== Reservierungssystem ===");
			System.out.println("1) Admin-Menü");
			System.out.println("2) Studenten-Menü");
			System.out.println("0) Beenden");

			int wahl = leseInt("Auswahl: ");

			switch (wahl) {
			case 1 -> adminMenue();
			case 2 -> studentMenue();
			case 0 -> {
				System.out.println("Programm beendet.");
				return;
			}
			default -> System.out.println("Ungültige Eingabe.");
			}
		}
	}

	// ------------------------------------------------------------
	// ADMIN-MENÜ
	// ------------------------------------------------------------

	private void adminMenue() {
		Admin admin = new Admin("Admin", "admin@hft.de", "admin", 1);

		while (true) {
			System.out.println("\n=== Admin-Menü ===");
			System.out.println("1) Raum anlegen");
			System.out.println("2) Raum löschen");
			System.out.println("3) Räume anzeigen");
			System.out.println("4) Buchung stornieren");
			System.out.println("0) Zurück");

			int wahl = leseInt("Auswahl: ");

			try {
				switch (wahl) {
				case 1 -> raumAnlegen();
				case 2 -> raumLoeschen();
				case 3 -> raeumeAnzeigen();
				case 4 -> buchungStornieren();
				case 0 -> {
					return;
				}
				default -> System.out.println("Ungültige Eingabe.");
				}
			} catch (BuchungsException e) {
				System.out.println("Fehler: " + e.getMessage());
			}
		}
	}

	private void raumAnlegen() {
		String name = leseString("Raumname: ");
		String geb = leseString("Gebäude: ");
		int kap = leseInt("Kapazität: ");
		String aus = leseString("Ausstattung: ");

		manager.raumAnlegen(name, geb, kap, aus);
		System.out.println("Raum angelegt.");
	}

	private void raumLoeschen() throws BuchungsException {
		int id = leseInt("Raum-ID: ");
		manager.raumLoeschen(id);
		System.out.println("Raum gelöscht.");
	}

	private void raeumeAnzeigen() {
		List<Raum> raeume = manager.getRaeume();

		if (raeume.isEmpty()) {
			System.out.println("Keine Räume vorhanden.");
			return;
		}

		for (Raum r : raeume) {
			System.out.println(r);
			for (Buchung b : r.getBuchungen()) {
				System.out.println("  -> " + b);
			}
		}
	}

	private void buchungStornieren() throws BuchungsException {
		int id = leseInt("Buchungs-ID: ");
		manager.buchungStornieren(id);
		System.out.println("Buchung storniert.");
	}

	// ------------------------------------------------------------
	// STUDENTEN-MENÜ
	// ------------------------------------------------------------

	private void studentMenue() {
		System.out.println("\n=== Studenten-Menü ===");

		String name = leseString("Name: ");
		String email = leseString("Email: ");
		String pw = leseString("Passwort: ");
		String matr = leseString("Matrikelnummer: ");

		Student student = new Student(name, email, pw, matr);

		while (true) {
			System.out.println("\n=== Studenten-Menü ===");
			System.out.println("1) Verfügbare Räume anzeigen");
			System.out.println("2) Platz buchen");
			System.out.println("3) Eigene Buchung stornieren");
			System.out.println("4) Eigene Buchungen anzeigen");
			System.out.println("0) Zurück");

			int wahl = leseInt("Auswahl: ");

			try {
				switch (wahl) {
				case 1 -> verfuegbareRaeume(student);
				case 2 -> platzBuchen(student);
				case 3 -> eigeneBuchungStornieren(student);
				case 4 -> eigeneBuchungen(student);
				case 0 -> {
					return;
				}
				default -> System.out.println("Ungültige Eingabe.");
				}
			} catch (BuchungsException e) {
				System.out.println("Fehler: " + e.getMessage());
			}
		}
	}

	private Zeitslot zeitslotEingeben() {
		String datum = leseString("Datum (YYYY-MM-DD): ");
		int start = leseInt("Startzeit: ");
		int ende = leseInt("Endzeit: ");
		return new Zeitslot(datum, start, ende);
	}

	private void verfuegbareRaeume(Student s) {
		Zeitslot slot = zeitslotEingeben();
		List<Raum> verf = manager.getVerfuegbareRaeume(slot);

		if (verf.isEmpty()) {
			System.out.println("Keine verfügbaren Räume.");
			return;
		}

		for (Raum r : verf) {
			System.out.println(r + " | freie Plätze: " + r.getFreiePlaetze(slot));
		}
	}

	private void platzBuchen(Student s) throws BuchungsException {
		Zeitslot slot = zeitslotEingeben();
		int raumId = leseInt("Raum-ID: ");

		Buchung b = manager.buchungErstellen(s, raumId, slot);
		System.out.println("Buchung erfolgreich: " + b);
	}

	private void eigeneBuchungStornieren(Student s) throws BuchungsException {
		if (s.getBuchungen().isEmpty()) {
			System.out.println("Keine Buchungen vorhanden.");
			return;
		}

		for (Buchung b : s.getBuchungen()) {
			System.out.println(b);
		}

		int id = leseInt("Buchungs-ID: ");
		manager.buchungStornieren(id);
		System.out.println("Buchung storniert.");
	}

	private void eigeneBuchungen(Student s) {
		if (s.getBuchungen().isEmpty()) {
			System.out.println("Keine Buchungen vorhanden.");
			return;
		}

		for (Buchung b : s.getBuchungen()) {
			System.out.println(b);
		}
	}

	// ------------------------------------------------------------
	// HILFSMETHODEN
	// ------------------------------------------------------------

	private int leseInt(String text) {
		System.out.print(text);
		while (!scanner.hasNextInt()) {
			System.out.print("Bitte Zahl eingeben: ");
			scanner.next();
		}
		int wert = scanner.nextInt();
		scanner.nextLine(); // Rest der Zeile löschen
		return wert;
	}

	private String leseString(String text) {
		System.out.print(text);
		return scanner.nextLine();
	}
}
