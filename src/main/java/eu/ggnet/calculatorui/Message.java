/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui;

/**
 * A few messages to indicate events.
 *
 * @author Administrator
 */
public enum Message {

    CLASSTIME("Klassenbildung"), CERTIFICATIONTIME("Zeugnisvergabe"),
    AVERAGETIME("Durchschnittliche Noten"), ACCUMULATIONTIME("Häufung der Noten");

    private final String message;

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
