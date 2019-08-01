package eu.ggnet.gradecalculator.calculation;

import eu.ggnet.gradecalculator.model.Grade;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Represents a {@link Grade} that has been calculated by {@link Calculator}.
 * <p>
 * Contains the wrapped Grade and Strings to hold informations about the
 * calculation.
 * <p>
 * Contains methods to present data in a human-readable format.
 *
 * @author Mirko Schulze
 */
@AllArgsConstructor
@ToString
public class CalculatedGrade {

    /**
     * Field for information about the method, the {@link CalculatedGrade} is
     * calculated.
     */
    private final String calculation;
    private final Grade grade;
    /**
     * Field for additional information about the {@link CalculatedGrade}.
     */
    private String suffix;

    /**
     * Constructor for a {@link CalculatedGrade} with information about the
     * calculation and a null {@link #suffix}.
     *
     * @param calculation String - information about the method to calculate
     * this {@link CalculatedGrade}
     * @param grade Grade - the wrapped {@link Grade}
     */
    public CalculatedGrade(String calculation, Grade grade) {
        this.calculation = calculation;
        this.grade = grade;
    }

    /**
     * Joins and returns a human-readable String with most data of this
     * {@link CalculatedGrade}.
     *
     * @return String - human-readable representation of this
     * {@link CalculatedGrade}
     */
    public String toSimpleLine() {
        return this.calculation + this.grade.toSimpleLine();
    }

    /**
     * Joins and returns a human-readable String with the data of this
     * {@link CaluclatedGrade}.
     *
     * @return String - human-readable representation of this
     * {@link CalculatedGrade}
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
