package model;

/**
 * Klasse Person
 * ----------------------------
 * Autor: Yousef Al Alewy
 *
 * Abstrakte Oberklasse für alle Personen im System.
 * Sie definiert gemeinsame Attribute und Methoden, die
 * sowohl Studenten als auch Administratoren besitzen.
 *
 * Gemeinsame Attribute:
 * - name       → Anzeigename der Person
 * - email      → eindeutige Kontaktadresse / Login
 * - passwort   → Passwort für das System
 *
 * Da Person abstrakt ist, kann sie nicht direkt erzeugt werden.
 * Nur Unterklassen wie Student oder Admin können instanziiert werden.
 */
public abstract class Person {

    protected String name;
    protected String email;
    protected String passwort;

    /**
     * Konstruktor für Person.
     * Wird von Unterklassen (Student, Admin) über super(...) aufgerufen.
     */
    public Person(String name, String email, String passwort) {
        this.name = name;
        this.email = email;
        this.passwort = passwort;
    }

    /**
     * Gibt den Namen der Person zurück.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die E-Mail-Adresse der Person zurück.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gibt das Passwort der Person zurück.
     * (In echten Systemen würde man Passwörter niemals im Klartext speichern.)
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Liefert eine einfache Textdarstellung der Person.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email;
    }
}
