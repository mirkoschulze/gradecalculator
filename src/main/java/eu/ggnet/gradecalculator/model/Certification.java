package eu.ggnet.gradecalculator.model;

import eu.ggnet.gradecalculator.model.Grade.Subject;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a school certification for a {@link Pupil} with a {@link Grade}
 * for each {@link Subject}.
 * <p>
 * Contains a Pupil and a list with instances of Grade.
 * <p>
 * Contains a method to present data in a human-readable format.
 *
 * @author Mirko Schulze
 */
@AllArgsConstructor
@ToString
public class Certification {

    private final Pupil pupil;
    @Getter
    private final List<Grade> grades;

    /**
     * Joins and returns a human-readable String with the data in this
     * {@link Certification}.
     *
     * @return String - human-redable representation of this
     * {@link Certification}
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        if (this.pupil != null) {
            sb.append("Pupil: ").append(this.pupil.toString()).append("\n");
        }
        if (this.grades != null) {
            this.grades.forEach(g -> sb.append(g.getSubject()).append(": ")
                    .append(g.getMark().getMiddleschoolMark()).append(" (")
                    .append(g.getMark().getHighschoolMark()).append(")")
                    .append("\n"));
        }
        return sb.toString();
    }

}
