package eu.ggnet.calculator.calculation;

import eu.ggnet.calculator.model.Grade;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Represents a {@link Grade} that has been calculated by {@link Calculator}.
 * <p>
 * Contains Strings to hold informations about the calculation and the Grade.
 * <p>
 * Contains methods to present data in a human-readable format.
 *
 * @author Mirko Schulze
 */
@AllArgsConstructor
@ToString
public class CalculatedGrade {

    private final String calculation;
    private final Grade grade;
    private final String suffix;

    public CalculatedGrade(String calculation, Grade grade) {
        this.calculation = calculation;
        this.grade = grade;
        this.suffix = "";
    }

    /**
     * Joins and returns a human-readable String with most data of this
     * instance.
     *
     * @return String
     */
    public String toSimpleLine() {
        return this.calculation + this.grade.toSimpleLine();
    }

    /**
     * Joins and returns a human-readable String with the data of this instance.
     *
     * @return String
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.calculation)
                .append(this.grade.toSimpleLine());
        if (this.suffix != null) {
            sb.append(" ").append(this.suffix);
        }
        sb.append(" [").append(this.grade.getLocalDate()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .append("]");
        return sb.toString();
    }

}
