package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen Raum, der gebucht werden kann.
 */
public class Raum {

	private int raumid;
	private String name;
	private String gebaeude;
	private int kapazitaet;
	private String ausstattung;

	private List<Buchung> buchungen;

	public Raum(int raumid, String name, String gebaeude, int kapazitaet, String ausstattung) {
		this.raumid = raumid;
		this.name = name;
		this.gebaeude = gebaeude;
		this.kapazitaet = kapazitaet;
		this.ausstattung = ausstattung;
		this.buchungen = new ArrayList<>();
	}

	public int getRaumid() {
		return raumid;
	}

	public String getName() {
		return name;
	}

	public List<Buchung> getBuchungen() {
		return buchungen;
	}

	public void addBuchung(Buchung b) {
		buchungen.add(b);
	}

	public void removeBuchung(Buchung b) {
		buchungen.remove(b);
	}

	public int getFreiePlaetze(Zeitslot s) {
		int belegte = 0;
		for (Buchung b : buchungen) {
			if (b.getZeitslot().ueberschneidetSich(s))
				belegte++;
		}
		return kapazitaet - belegte;
	}

	public boolean istVerfuegbar(Zeitslot s) {
		return getFreiePlaetze(s) > 0;
	}

	@Override
	public String toString() {
		return "Raum #" + raumid + " | " + name + " | Kapazität: " + kapazitaet + " | Buchungen: " + buchungen.size();
	}
}
