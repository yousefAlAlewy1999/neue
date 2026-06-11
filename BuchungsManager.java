package manager;

import java.util.ArrayList;
import java.util.List;

import exception.BuchungsException;
import model.Buchung;
import model.Raum;
import model.Student;
import model.Zeitslot;

/**
 * Zentrale Geschäftslogik des Systems.
 */
public class BuchungsManager {

	private List<Raum> raeume;
	private List<Buchung> buchungen;

	private int nextRaumId = 1;
	private int nextBuchungId = 1;

	public BuchungsManager() {
		raeume = new ArrayList<>();
		buchungen = new ArrayList<>();
	}

	// ------------------ RÄUME ------------------

	public void raumAnlegen(String name, String gebaeude, int kapazitaet, String ausstattung) {
		raeume.add(new Raum(nextRaumId++, name, gebaeude, kapazitaet, ausstattung));
	}

	public void raumLoeschen(int id) throws BuchungsException {
		Raum r = findeRaum(id);
		if (r == null)
			throw new BuchungsException("Raum existiert nicht.");
		if (!r.getBuchungen().isEmpty())
			throw new BuchungsException("Raum hat noch Buchungen.");
		raeume.remove(r);
	}

	public List<Raum> getRaeume() {
		return raeume;
	}

	public List<Raum> getVerfuegbareRaeume(Zeitslot s) {
		List<Raum> erg = new ArrayList<>();
		for (Raum r : raeume) {
			if (r.istVerfuegbar(s))
				erg.add(r);
		}
		return erg;
	}

	// ------------------ BUCHUNGEN ------------------

	public Buchung buchungErstellen(Student student, int raumId, Zeitslot s) throws BuchungsException {
		Raum raum = findeRaum(raumId);
		if (raum == null)
			throw new BuchungsException("Raum existiert nicht.");

		for (Buchung b : student.getBuchungen()) {
			if (b.getRaum().getRaumid() == raumId && b.getZeitslot().ueberschneidetSich(s)) {
				throw new BuchungsException("Student hat diesen Raum bereits gebucht.");
			}
		}

		if (!raum.istVerfuegbar(s))
			throw new BuchungsException("Raum ist voll.");

		Buchung b = new Buchung(nextBuchungId++, student, raum, s);
		buchungen.add(b);
		student.addBuchung(b);
		raum.addBuchung(b);

		return b;
	}

	public void buchungStornieren(int id) throws BuchungsException {
		Buchung b = findeBuchung(id);
		if (b == null)
			throw new BuchungsException("Buchung existiert nicht.");

		b.stornieren();
		buchungen.remove(b);
	}

	public List<Buchung> getBuchungen() {
		return buchungen;
	}

	public void uebersichtsansicht() {
		if (buchungen.isEmpty()) {
			System.out.println("Keine Buchungen vorhanden.");
			return;
		}
		for (Buchung b : buchungen)
			System.out.println(b);
	}

	// ------------------ HILFSMETHODEN ------------------

	private Raum findeRaum(int id) {
		return raeume.stream().filter(r -> r.getRaumid() == id).findFirst().orElse(null);
	}

	private Buchung findeBuchung(int id) {
		return buchungen.stream().filter(b -> b.getBuchungsid() == id).findFirst().orElse(null);
	}
}
