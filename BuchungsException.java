package exception;

/**
 * Eigene Exception für Fehler im Buchungssystem.
 */
public class BuchungsException extends Exception {

	public BuchungsException(String message) {
		super(message);
	}

	@Override
	public String toString() {
		return "BuchungsException: " + getMessage();
	}
}
