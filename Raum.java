package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse Raum
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Repräsentiert einen Raum, der von Studenten gebucht werden kann.
 * Ein Raum besitzt:
 * - eine eindeutige ID
 * - einen Namen
 * - ein Gebäude
 * - eine Kapazität (max. Anzahl gleichzeitiger Buchungen)
 * - eine Ausstattung
 * - eine Liste aller Buchungen, die diesem Raum zugeordnet sind
 */
public class Raum {

    private int raumid;
    private String name;
    private String gebaeude;
    private int kapazitaet;
    private String ausstattung;

    private List<Buchung> buchungen;

    /**
     * Konstruktor für Raum.
     * Initialisiert alle Attribute und legt eine leere Buchungsliste an.
     */
    public Raum(int raumid, String name, String gebaeude, int kapazitaet, String ausstattung) {
        this.raumid = raumid;
        this.name = name;
        this.gebaeude = gebaeude;
        this.kapazitaet = kapazitaet;
        this.ausstattung = ausstattung;
        this.buchungen = new ArrayList<>();
    }

    /**
     * Gibt die eindeutige Raum-ID zurück.
     */
    public int getRaumid() {
        return raumid;
    }

    /**
     * Gibt den Namen des Raums zurück.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt alle Buchungen zurück, die diesem Raum zugeordnet sind.
     */
    public List<Buchung> getBuchungen() {
        return buchungen;
    }

    /**
     * Fügt eine Buchung zur Liste hinzu.
     * Es wird nicht geprüft, ob die Buchung bereits existiert.
     * (Diese Logik übernimmt normalerweise der BuchungsManager.)
     */
    public void addBuchung(Buchung b) {
        buchungen.add(b);
    }

    /**
     * Entfernt eine Buchung aus der Liste.
     */
    public void removeBuchung(Buchung b) {
        buchungen.remove(b);
    }

    /**
     * Berechnet, wie viele Plätze im angegebenen Zeitslot noch frei sind.
     *
     * Ablauf:
     * - Jede Buchung wird geprüft.
     * - Wenn sich der Zeitslot der Buchung mit dem angefragten Zeitslot überschneidet,
     *   wird der Zähler "belegte" erhöht.
     * - Am Ende wird Kapazität - belegte zurückgegeben.
     */
    public int getFreiePlaetze(Zeitslot s) {
        int belegte = 0;
        for (Buchung b : buchungen) {
            if (b.getZeitslot().ueberschneidetSich(s))
                belegte++;
        }
        return kapazitaet - belegte;
    }

    /**
     * Prüft, ob der Raum im angegebenen Zeitslot noch mindestens einen freien Platz hat.
     */
    public boolean istVerfuegbar(Zeitslot s) {
        return getFreiePlaetze(s) > 0;
    }

    /**
     * Liefert eine kompakte Textdarstellung des Raums.
     */
    @Override
    public String toString() {
        return "Raum #" + raumid +
               " | " + name +
               " | Kapazität: " + kapazitaet +
               " | Buchungen: " + buchungen.size();
    }
}
