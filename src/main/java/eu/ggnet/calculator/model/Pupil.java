package eu.ggnet.calculator.model;

import javafx.scene.control.ComboBox;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a pupil at school.
 * <p>
 * Contains {@link Sex} sex, int age, Strings forename and surname, and a
 * {@link Certification}.
 * <p>
 * Contains methods to present data in a human-readable format. Overrides
 * {@link Object#toString()} for presentation purposes.
 * <p>
 * Implements {@link Comparable} by comparing the first letter of surename.
 *
 * @author Mirko Schulze
 */
@Getter
public class Pupil implements Comparable<Pupil> {

    private final Sex sex;
    private final int age;
    private final String forename;
    private final String surname;
    @Setter
    private Certification certification;

    public Pupil(Sex sex, int age, String forename, String surename) {
        this.sex = sex;
        this.age = age;
        this.forename = forename;
        this.surname = surename;
    }

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.toSimpleLine();
    }

    @Override
    public int compareTo(Pupil p) {
        return (this.surname.charAt(0) <= p.surname.charAt(0)) ? -1 : 1;
    }

    /**
     * Joins and returns a human-readable String with most data in this
     * instance.
     *
     * @return String
     */
    public String toSimpleLine() {
        return this.forename + " " + this.surname;
    }

    /**
     * Joins and returns a human-readable String with the data in this instance.
     *
     * @return String
     */
    public String toEnhancedLine() {
        return this.forename + " " + this.surname
                + " (" + this.age + " Jahre, " + this.sex.description
                + ") [" + this.getCertification().toEnhancedLine()
                + "]";
    }

    /**
     * Enumeration of biological genders.
     */
    public enum Sex {

        MALE("männlich"), FEMALE("weiblich");

        private final String description;

        private Sex(String sex) {
            this.description = sex;
        }

        public String getDescription() {
            return this.description;
        }

    }

}
