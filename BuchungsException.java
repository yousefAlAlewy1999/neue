package exception;

/**
 * Klasse BuchungsException
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Eigene Exception-Klasse für das Buchungssystem.
 * Sie wird verwendet, um verständliche Fehlermeldungen auszugeben,
 * z. B. wenn:
 * - ein Raum nicht existiert
 * - ein Raum voll ist
 * - eine Buchung nicht existiert
 * - ein Raum nicht gelöscht werden kann
 *
 * Durch eine eigene Exception wird die Fehlerbehandlung klarer
 * und das System bleibt übersichtlich.
 */
public class BuchungsException extends Exception {

    /**
     * Konstruktor, der die Fehlermeldung an die Basisklasse Exception weitergibt.
     */
    public BuchungsException(String message) {
        super(message);
    }

    /**
     * Liefert eine lesbare Darstellung der Exception.
     */
    @Override
    public String toString() {
        return "BuchungsException: " + getMessage();
    }
}
