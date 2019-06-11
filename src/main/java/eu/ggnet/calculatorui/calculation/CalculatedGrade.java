/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.calculation;

import eu.ggnet.calculatorui.model.Grade;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@link Grade} that has been calculated by {@link Calculator}.
 * <p>
 * Contains Strings to hold informations about the calculatoin and a
 * {@link Grade}.
 * <p>
 * Contains a method to present data in a human-readable format.
 *
 * @author Administrator
 */
public class CalculatedGrade {

    private final String calculation;
    private final Grade grade;
    private String suffix;

    public CalculatedGrade(String calculation, Grade grade) {
        this.calculation = calculation;
        this.grade = grade;
    }

    public CalculatedGrade(String calculation, Grade grade, String suffix) {
        this.calculation = calculation;
        this.grade = grade;
        this.suffix = suffix;
    }

    public String getCalculation() {
        return calculation;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return "CalculatedGrade{" + "calculation=" + calculation + ", grade=" + grade + ", suffix=" + suffix + '}';
    }

    /**
     * Joins and returns a human-readable String with the data of this instance.
     *
     * @return Returns the created String.
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.calculation).append(" (")
                .append(this.grade.getSubject().getTheme()).append("): ")
                .append(this.grade.getNote().getMiddleschoolNote()).append(" (")
                .append(this.grade.getNote().getHighschoolNote()).append(")");
        if (this.suffix != null) {
            sb.append(" ").append(this.suffix);
        }
        sb.append(" [Datum: ").append(this.grade.getLocalDate()
                .format(DateTimeFormatter.ofPattern("EEEE, dd.MM.yyyy")))
                .append("]");
        return sb.toString();
    }

}
