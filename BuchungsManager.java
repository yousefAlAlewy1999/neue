package manager;

import java.util.ArrayList;
import java.util.List;

import exception.BuchungsException;
import model.Buchung;
import model.Raum;
import model.Student;
import model.Zeitslot;

/**
 * Klasse BuchungsManager
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Zentrale Geschäftslogik des Reservierungssystems.
 * Verantwortlich für:
 * - Verwaltung aller Räume
 * - Verwaltung aller Buchungen
 * - Erstellen und Stornieren von Buchungen
 * - Prüfen von Verfügbarkeit
 *
 * Diese Klasse bildet das "Herz" des Systems.
 */
public class BuchungsManager {

    private List<Raum> raeume;
    private List<Buchung> buchungen;

    private int nextRaumId = 1;
    private int nextBuchungId = 1;

    /**
     * Konstruktor: legt leere Listen für Räume und Buchungen an.
     */
    public BuchungsManager() {
        raeume = new ArrayList<>();
        buchungen = new ArrayList<>();
    }

    // ------------------------------------------------------------
    // RÄUME
    // ------------------------------------------------------------

    /**
     * Legt einen neuen Raum an und vergibt automatisch eine ID.
     */
    public void raumAnlegen(String name, String gebaeude, int kapazitaet, String ausstattung) {
        raeume.add(new Raum(nextRaumId++, name, gebaeude, kapazitaet, ausstattung));
    }

    /**
     * Löscht einen Raum anhand seiner ID.
     * Bedingungen:
     * - Raum muss existieren
     * - Raum darf keine Buchungen mehr haben
     */
    public void raumLoeschen(int id) throws BuchungsException {
        Raum r = findeRaum(id);
        if (r == null)
            throw new BuchungsException("Raum existiert nicht.");
        if (!r.getBuchungen().isEmpty())
            throw new BuchungsException("Raum hat noch Buchungen.");
        raeume.remove(r);
    }

    /**
     * Gibt alle Räume zurück.
     */
    public List<Raum> getRaeume() {
        return raeume;
    }

    /**
     * Gibt alle Räume zurück, die im angegebenen Zeitslot noch freie Plätze haben.
     */
    public List<Raum> getVerfuegbareRaeume(Zeitslot s) {
        List<Raum> erg = new ArrayList<>();
        for (Raum r : raeume) {
            if (r.istVerfuegbar(s))
                erg.add(r);
        }
        return erg;
    }

    // ------------------------------------------------------------
    // BUCHUNGEN
    // ------------------------------------------------------------

    /**
     * Erstellt eine neue Buchung.
     *
     * Ablauf:
     * 1. Raum anhand ID suchen
     * 2. Prüfen, ob Student den Raum im gleichen Zeitslot schon gebucht hat
     * 3. Prüfen, ob Raum noch freie Plätze hat
     * 4. Buchung erzeugen und in allen Listen eintragen:
     *      - globale Buchungsliste
     *      - Buchungsliste des Studenten
     *      - Buchungsliste des Raums
     */
    public Buchung buchungErstellen(Student student, int raumId, Zeitslot s) throws BuchungsException {
        Raum raum = findeRaum(raumId);
        if (raum == null)
            throw new BuchungsException("Raum existiert nicht.");

        // Prüfen, ob Student diesen Raum im gleichen Zeitslot schon gebucht hat
        for (Buchung b : student.getBuchungen()) {
            if (b.getRaum().getRaumid() == raumId && b.getZeitslot().ueberschneidetSich(s)) {
                throw new BuchungsException("Student hat diesen Raum bereits gebucht.");
            }
        }

        // Prüfen, ob Raum noch freie Plätze hat
        if (!raum.istVerfuegbar(s))
            throw new BuchungsException("Raum ist voll.");

        // Buchung erstellen
        Buchung b = new Buchung(nextBuchungId++, student, raum, s);

        // In allen relevanten Listen eintragen
        buchungen.add(b);
        student.addBuchung(b);
        raum.addBuchung(b);

        return b;
    }

    /**
     * Storniert eine Buchung anhand ihrer ID.
     * Die Buchung wird:
     * - aus der globalen Liste entfernt
     * - aus dem Raum entfernt
     * - aus dem Studenten entfernt
     * (dies übernimmt b.stornieren())
     */
    public void buchungStornieren(int id) throws BuchungsException {
        Buchung b = findeBuchung(id);
        if (b == null)
            throw new BuchungsException("Buchung existiert nicht.");

        b.stornieren();
        buchungen.remove(b);
    }

    /**
     * Gibt alle Buchungen zurück.
     */
    public List<Buchung> getBuchungen() {
        return buchungen;
    }

    /**
     * Gibt alle Buchungen in der Konsole aus.
     */
    public void uebersichtsansicht() {
        if (buchungen.isEmpty()) {
            System.out.println("Keine Buchungen vorhanden.");
            return;
        }
        for (Buchung b : buchungen)
            System.out.println(b);
    }

    // ------------------------------------------------------------
    // HILFSMETHODEN
    // ------------------------------------------------------------

    /**
     * Sucht einen Raum anhand seiner ID.
     * Gibt null zurück, wenn kein Raum gefunden wird.
     */
    private Raum findeRaum(int id) {
        return raeume.stream().filter(r -> r.getRaumid() == id).findFirst().orElse(null);
    }

    /**
     * Sucht eine Buchung anhand ihrer ID.
     * Gibt null zurück, wenn keine Buchung gefunden wird.
     */
    private Buchung findeBuchung(int id) {
        return buchungen.stream().filter(b -> b.getBuchungsid() == id).findFirst().orElse(null);
    }
}
