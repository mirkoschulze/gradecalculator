package eu.ggnet.gradecalculator.model;

import javafx.scene.control.ComboBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a pupil at school.
 * <p>
 * Contains {@link Sex} sex, int age, Strings forename and surname, and a
 * {@link Certification}. Pupil can be sorted by comparing the first letter of
 * their surnames.
 * <p>
 * Contains methods to present data in a human-readable format.
 * <p>
 * Overrides {@link Object#toString()} for gui presentation purposes.
 *
 * @author Mirko Schulze
 */
@Getter
@AllArgsConstructor
public class Pupil implements Comparable<Pupil> {

    private final Sex sex;
    private final int age;
    private final String forename;
    private final String surname;
    @Setter
    private Certification certification;

    /**
     * Constructor to instantiate a new {@link Pupil} with the submitted values
     * and no {@link Certification}.
     *
     * @param sex Sex - {@link Sex} of the Pupil
     * @param age int - age of the Pupil
     * @param forename - String - forename of the Pupil
     * @param surname - String - surname of the Pupil
     */
    public Pupil(Sex sex, int age, String forename, String surname) {
        this.sex = sex;
        this.age = age;
        this.forename = forename;
        this.surname = surname;
    }

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return String - the full name of this {@link Pupil}
     * @see #toSimpleLine()
     */
    @Override
    public String toString() {
        return this.toSimpleLine();
    }

    /**
     * Implements {@link Comparable} by comparing the first letter of surename.
     * Returns -1 if the first letter of the compared {@link Pupil} has a bigger
     * alphanumerical value, 1 if otherwise.
     *
     * @param p Pupil - the {@link Pupil} this Pupil shall be compared to
     * @return int - the result of the alphanumerical comparison between two
     * instances of {@link Pupil}
     */
    @Override
    public int compareTo(Pupil p) {
        return (this.surname.charAt(0) <= p.surname.charAt(0)) ? -1 : 1;
    }

    /**
     * Joins and returns a human-readable String with the full name of this
     * {@link Pupil}.
     *
     * @return String - the full name of this {@link Pupil}
     */
    public String toSimpleLine() {
        return this.forename + " " + this.surname;
    }

    /**
     * Joins and returns a human-readable String with the data of this
     * {@link Pupil}.
     *
     * @return String - human-readable representation of this {@link Pupil}
     */
    public String toEnhancedLine() {
        return this.forename + " " + this.surname
                + " (" + this.age + " Jahre, " + this.sex.description
                + ") [" + this.certification.toEnhancedLine()
                + "]";
    }

    /**
     * Enumeration of biological genders.
     */
    public enum Sex {

        MALE("Männlich"), FEMALE("Weiblich");

        private final String description;

        private Sex(String sex) {
            this.description = sex;
        }

        public String getDescription() {
            return this.description;
        }

    }

}
