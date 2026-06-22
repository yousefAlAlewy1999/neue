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
 * Klasse KonsolenMenue
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Stellt die Benutzeroberfläche des Systems dar (Kommandozeile).
 * Enthält das Hauptmenü, das Admin-Menü und das Studenten-Menü.
 * Alle Eingaben werden über Scanner eingelesen.
 */
public class KonsolenMenue {

    private BuchungsManager manager = new BuchungsManager();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Einstiegspunkt des Programms.
     * Erstellt ein KonsolenMenue-Objekt und startet das Hauptmenü.
     */
    public static void main(String[] args) {
        new KonsolenMenue().start();
    }

    /**
     * Hauptmenü des Programms.
     * Der Benutzer kann wählen zwischen:
     * - Admin-Menü
     * - Studenten-Menü
     * - Beenden
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

    /**
     * Menü für Administratoren.
     * Ein Admin kann:
     * - Räume anlegen
     * - Räume löschen
     * - Räume anzeigen
     * - Buchungen stornieren
     */
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
                    case 0 -> { return; }
                    default -> System.out.println("Ungültige Eingabe.");
                }
            } catch (BuchungsException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    /**
     * Liest Raumdaten ein und legt einen neuen Raum über den Manager an.
     */
    private void raumAnlegen() {
        String name = leseString("Raumname: ");
        String geb = leseString("Gebäude: ");
        int kap = leseInt("Kapazität: ");
        String aus = leseString("Ausstattung: ");

        manager.raumAnlegen(name, geb, kap, aus);
        System.out.println("Raum angelegt.");
    }

    /**
     * Löscht einen Raum anhand seiner ID.
     */
    private void raumLoeschen() throws BuchungsException {
        int id = leseInt("Raum-ID: ");
        manager.raumLoeschen(id);
        System.out.println("Raum gelöscht.");
    }

    /**
     * Zeigt alle Räume und deren Buchungen an.
     */
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

    /**
     * Storniert eine Buchung anhand ihrer ID.
     */
    private void buchungStornieren() throws BuchungsException {
        int id = leseInt("Buchungs-ID: ");
        manager.buchungStornieren(id);
        System.out.println("Buchung storniert.");
    }

    // ------------------------------------------------------------
    // STUDENTEN-MENÜ
    // ------------------------------------------------------------

    /**
     * Menü für Studenten.
     * Ein Student kann:
     * - verfügbare Räume anzeigen
     * - Plätze buchen
     * - eigene Buchungen stornieren
     * - eigene Buchungen anzeigen
     */
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
                    case 0 -> { return; }
                    default -> System.out.println("Ungültige Eingabe.");
                }
            } catch (BuchungsException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    /**
     * Liest Datum, Startzeit und Endzeit ein und erstellt einen Zeitslot.
     */
    private Zeitslot zeitslotEingeben() {
        String datum = leseString("Datum (YYYY-MM-DD): ");
        int start = leseInt("Startzeit: ");
        int ende = leseInt("Endzeit: ");
        return new Zeitslot(datum, start, ende);
    }

    /**
     * Zeigt alle Räume an, die im angegebenen Zeitslot verfügbar sind.
     */
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

    /**
     * Erstellt eine Buchung für den Studenten.
     */
    private void platzBuchen(Student s) throws BuchungsException {
        Zeitslot slot = zeitslotEingeben();
        int raumId = leseInt("Raum-ID: ");

        Buchung b = manager.buchungErstellen(s, raumId, slot);
        System.out.println("Buchung erfolgreich: " + b);
    }

    /**
     * Storniert eine Buchung des Studenten.
     */
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

    /**
     * Zeigt alle Buchungen des Studenten an.
     */
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

    /**
     * Liest eine ganze Zahl ein.
     * Prüft, ob die Eingabe gültig ist.
     */
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

    /**
     * Liest einen String ein.
     */
    private String leseString(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }
}
