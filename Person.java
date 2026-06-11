package model;

/**
 * Abstrakte Oberklasse für alle Personen im System. Gemeinsame Attribute: -
 * name - email - passwort
 */
public abstract class Person {

	protected String name;
	protected String email;
	protected String passwort;

	public Person(String name, String email, String passwort) {
		this.name = name;
		this.email = email;
		this.passwort = passwort;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswort() {
		return passwort;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Email: " + email;
	}
}
