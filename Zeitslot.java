package model;

/**
 * Repräsentiert ein Zeitfenster für eine Buchung.
 */
public class Zeitslot {

	private String datum;
	private int startzeit;
	private int endzeit;

	public Zeitslot(String datum, int startzeit, int endzeit) {
		this.datum = datum;
		this.startzeit = startzeit;
		this.endzeit = endzeit;
	}

	public String getDatum() {
		return datum;
	}

	public int getStartzeit() {
		return startzeit;
	}

	public int getEndzeit() {
		return endzeit;
	}

	/**
	 * Prüft, ob sich zwei Zeitslots überschneiden.
	 */
	public boolean ueberschneidetSich(Zeitslot s) {
		if (!this.datum.equals(s.datum))
			return false;
		return this.startzeit < s.endzeit && s.startzeit < this.endzeit;
	}

	@Override
	public String toString() {
		return datum + " " + startzeit + ":00-" + endzeit + ":00";
	}
}
