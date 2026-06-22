package model;

/**
 * Klasse Buchung
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Repräsentiert eine Buchung eines Studenten für einen bestimmten Raum
 * in einem bestimmten Zeitslot.
 *
 * Eine Buchung verbindet also:
 * - einen Studenten
 * - einen Raum
 * - einen Zeitslot
 *
 * Jede Buchung besitzt außerdem eine eindeutige Buchungs-ID.
 */
public class Buchung {

    private int buchungsid;
    private Student student;
    private Raum raum;
    private Zeitslot zeitslot;

    /**
     * Konstruktor für eine Buchung.
     * Speichert alle relevanten Informationen:
     * - ID
     * - Student
     * - Raum
     * - Zeitslot
     */
    public Buchung(int buchungsid, Student student, Raum raum, Zeitslot zeitslot) {
        this.buchungsid = buchungsid;
        this.student = student;
        this.raum = raum;
        this.zeitslot = zeitslot;
    }

    /**
     * Gibt die eindeutige Buchungs-ID zurück.
     */
    public int getBuchungsid() {
        return buchungsid;
    }

    /**
     * Gibt den Studenten zurück, der diese Buchung erstellt hat.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Gibt den Raum zurück, der gebucht wurde.
     */
    public Raum getRaum() {
        return raum;
    }

    /**
     * Gibt den Zeitslot zurück, in dem die Buchung stattfindet.
     */
    public Zeitslot getZeitslot() {
        return zeitslot;
    }

    /**
     * Storniert die Buchung.
     *
     * Die Buchung wird aus:
     * - der Buchungsliste des Studenten
     * - der Buchungsliste des Raums
     * entfernt.
     *
     * Die globale Liste wird im BuchungsManager aktualisiert.
     */
    public void stornieren() {
        student.removeBuchung(this);
        raum.removeBuchung(this);
    }

    /**
     * Liefert eine gut lesbare Darstellung der Buchung.
     */
    @Override
    public String toString() {
        return "Buchung #" + buchungsid +
               " | Student: " + student.getName() +
               " | Raum: " + raum.getName() +
               " | " + zeitslot;
    }
}
