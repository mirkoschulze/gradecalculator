/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.calculation;

import eu.ggnet.calculatorui.model.Grade;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
@Getter
@AllArgsConstructor
public class CalculatedGrade {

    private final String calculation;
    private final Grade grade;
    private String suffix;

    public CalculatedGrade(String calculation, Grade grade) {
        this.calculation = calculation;
        this.grade = grade;
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
        sb.append(this.calculation)
                .append(this.grade.getNote().getMiddleschoolNote()).append(" / ")
                .append(this.grade.getNote().getHighschoolNote());
        if (this.suffix != null) {
            sb.append(" ").append(this.suffix);
        }
        sb.append(" [").append(this.grade.getLocalDate()
                .format(DateTimeFormatter.ofPattern("EEEE, dd.MM.yyyy")))
                .append("]");
        return sb.toString();
    }

}
