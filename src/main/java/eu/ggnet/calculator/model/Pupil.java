/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculator.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a pupil at school.
 * <p>
 * Contains {@link Sex} sex, int age, Strings forename and surname, and a
 * {@link Certification}.
 * <p>
 * Contains a method to present data in a human-readable format. Overrides
 * {@link Object#toString()} for presentation purposes.
 * <p>
 * Implements {@link Comparable} by comparing the first letter of surename.
 *
 * @author Administrator
 */
@Getter
public class Pupil implements Comparable<Pupil> {

    private Sex sex;
    private final int age;
    private String forename;
    private String surname;
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
     * @return Returns the pupils name.
     */
    @Override
    public String toString() {
        return this.forename + " " + this.surname;
    }

    @Override
    public int compareTo(Pupil p) {
        return (this.surname.charAt(0) <= p.surname.charAt(0)) ? -1 : 1;
    }

    /**
     * Joins and returns a human-readable String with data about this instance.
     *
     * @return Returns the created String.
     */
    public String toSimpleLine() {
        return this.forename + " " + this.surname + " (" + this.age + " Jahre, " + this.sex.description + ")";
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
            return description;
        }

    }

}
