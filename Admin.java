package model;

/**
 * Klasse Admin
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Repräsentiert einen Administrator im Buchungssystem.
 * Admins besitzen:
 * - eine eindeutige Admin-ID
 * - alle Attribute der Oberklasse Person (Name, Email, Passwort)
 *
 * Aufgaben eines Admins laut System:
 * - Räume anlegen
 * - Räume löschen
 * - Buchungen einsehen
 * - Buchungen stornieren
 *
 * Die eigentliche Logik wird im KonsolenMenue ausgeführt.
 */
public class Admin extends Person {

    private int adminid;

    /**
     * Konstruktor für Admin.
     * Ruft den Person-Konstruktor auf und speichert die Admin-ID.
     */
    public Admin(String name, String email, String passwort, int adminid) {
        super(name, email, passwort);
        this.adminid = adminid;
    }

    /**
     * Gibt die eindeutige Admin-ID zurück.
     */
    public int getAdminid() {
        return adminid;
    }

    /**
     * Platzhalter-Methode laut Aufgabenstellung.
     * Die tatsächliche Verwaltungslogik befindet sich im KonsolenMenue.
     */
    public void raumVerwalten() {
        // Logik im KonsolenMenue
    }

    /**
     * Liefert eine kompakte Textdarstellung des Admins.
     */
    @Override
    public String toString() {
        return "Admin: " + name + " | Admin-ID: " + adminid;
    }
}
