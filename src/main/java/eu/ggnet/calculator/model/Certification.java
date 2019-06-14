package eu.ggnet.calculator.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a school certification with grades.
 * <p>
 * Contains a {@link Pupil} and a list with instances of {@link Grade}.
 * <p>
 * Contains a method to present data in a human-readable format.
 *
 * @author Mirko Schulze
 */
@Getter
@AllArgsConstructor
@ToString
public class Certification {

    private final Pupil pupil;
    private final List<Grade> grades;

    /**
     * Joins and returns a human-readable String with all data in this instance.
     *
     * @return String
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        if (this.pupil != null) {
            sb.append("Pupil: ").append(this.pupil.toString()).append("\n");
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
