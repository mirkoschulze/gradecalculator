/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.model;

/**
 * Represents a pupil at school.
 * <p>
 * Contains {@link Sex} sex, int age, String name and a {@link Certification}.
 * <p>
 * Contains a method to present data in a human-readable format.
 *
 * @author Administrator
 */
public class Pupil implements Comparable<Pupil> {

    private Sex sex;
    private final int age;
    private String forename;
    private String surname;
    private Certification certification;

    public Pupil(Sex sex, int age, String forename, String surename) {
        this.sex = sex;
        this.age = age;
        this.forename = forename;
        this.surname = surename;
    }

    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    @Override
    public String toString() {
        return "Pupil{" + "sex=" + sex + ", age=" + age + ", forename=" + forename + ", surname=" + surname + ", certification=" + certification + '}';
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
