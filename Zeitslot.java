package model;

/**
 * Klasse Zeitslot
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Repräsentiert ein Zeitfenster für eine Buchung.
 * Ein Zeitslot besteht aus:
 * - einem Datum (z. B. "2026-06-15")
 * - einer Startzeit (Stunde als int, z. B. 10)
 * - einer Endzeit (Stunde als int, z. B. 12)
 *
 * Zeitslots werden verwendet, um zu prüfen,
 * ob Buchungen sich zeitlich überschneiden.
 */
public class Zeitslot {

    private String datum;
    private int startzeit;
    private int endzeit;

    /**
     * Konstruktor für einen Zeitslot.
     * Speichert Datum, Startzeit und Endzeit.
     */
    public Zeitslot(String datum, int startzeit, int endzeit) {
        this.datum = datum;
        this.startzeit = startzeit;
        this.endzeit = endzeit;
    }

    /**
     * Gibt das Datum des Zeitslots zurück.
     */
    public String getDatum() {
        return datum;
    }

    /**
     * Gibt die Startzeit (Stunde) zurück.
     */
    public int getStartzeit() {
        return startzeit;
    }

    /**
     * Gibt die Endzeit (Stunde) zurück.
     */
    public int getEndzeit() {
        return endzeit;
    }

    /**
     * Prüft, ob sich zwei Zeitslots überschneiden.
     *
     * Logik:
     * - Wenn die Daten unterschiedlich sind → keine Überschneidung.
     * - Zeitliche Überschneidung liegt vor, wenn:
     *      this.startzeit < s.endzeit
     *   UND s.startzeit < this.endzeit
     *
     * Beispiel:
     *   Slot A: 10–12
     *   Slot B: 11–13
     *   → überschneiden sich
     */
    public boolean ueberschneidetSich(Zeitslot s) {
        if (!this.datum.equals(s.datum))
            return false;

        return this.startzeit < s.endzeit && s.startzeit < this.endzeit;
    }

    /**
     * Liefert eine gut lesbare Darstellung des Zeitslots.
     */
    @Override
    public String toString() {
        return datum + " " + startzeit + ":00-" + endzeit + ":00";
    }
}
