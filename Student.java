package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse Student
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Repräsentiert einen Studenten im Buchungssystem.
 * Ein Student erbt von der Oberklasse Person und besitzt zusätzlich:
 * - eine Matrikelnummer
 * - eine Liste seiner Buchungen
 */
public class Student extends Person {

    private String matrikelnummer;
    private List<Buchung> buchungen;

    /**
     * Konstruktor für Student.
     * Ruft den Konstruktor der Oberklasse Person auf (super),
     * um Name, Email und Passwort zu setzen.
     * Zusätzlich wird die Matrikelnummer gespeichert und
     * eine leere Liste für Buchungen angelegt.
     */
    public Student(String name, String email, String passwort, String matrikelnummer) {
        super(name, email, passwort);
        this.matrikelnummer = matrikelnummer;
        this.buchungen = new ArrayList<>();
    }

    /**
     * Gibt die Matrikelnummer des Studenten zurück.
     */
    public String getMatrikelnummer() {
        return matrikelnummer;
    }

    /**
     * Gibt alle Buchungen des Studenten zurück.
     */
    public List<Buchung> getBuchungen() {
        return buchungen;
    }

    /**
     * Fügt eine Buchung zur Liste hinzu,
     * aber nur wenn sie noch nicht existiert.
     * Dadurch werden doppelte Einträge verhindert.
     */
    public void addBuchung(Buchung b) {
        if (!buchungen.contains(b)) {
            buchungen.add(b);
        }
    }

    /**
     * Entfernt eine Buchung aus der Liste des Studenten.
     */
    public void removeBuchung(Buchung b) {
        buchungen.remove(b);
    }

    /**
     * Liefert eine kompakte Textdarstellung des Studenten.
     * Zeigt Name, Matrikelnummer und Anzahl der Buchungen.
     */
    @Override
    public String toString() {
        return String.format("Student: %s | Matrikel: %s | Buchungen: %d",
                name, matrikelnummer, buchungen.size());
    }
}
