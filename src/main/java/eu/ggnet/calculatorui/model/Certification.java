/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a certification with grades.
 * <p>
 * Contains a String to hold the name of a {@link Pupil} and a list with
 * instances of {@link Grade}.
 * <p>
 * Contains a method to present data in a human-readable format.
 *
 * @author mirko.schulze
 */
public class Certification {

    private final String pupilsName;
    private final List<Grade> grades;

    public Certification(Pupil pupil, Grade... grades) {
        this.pupilsName = pupil.getForename() + " " + pupil.getSurname();
        this.grades = new ArrayList<>();
        this.grades.addAll(Arrays.asList(grades));
    }

    public Certification(Pupil pupil, List<Grade> listOfGrades) {
        this.pupilsName = pupil.getSurname();
        this.grades = listOfGrades;
    }

    public String getPupilsName() {
        return pupilsName;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Certification{" + "pupilsName=" + pupilsName + ", grades=" + grades + '}';
    }

    /**
     * Joins and returns a human-readable String with all data in this instance.
     *
     * @return
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        if (this.pupilsName != null) {
            sb.append("Schüler: ").append(this.pupilsName).append("\n");
        }
        if (this.grades != null) {
            this.grades.forEach(g -> sb.append(g.getSubject()).append(": ")
                    .append(g.getNote().getMiddleschoolNote()).append(" (")
                    .append(g.getNote().getHighschoolNote()).append(")")
                    .append("\n"));
        }
        return sb.toString();
    }

}
