/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.model;

import java.util.List;
import javafx.scene.control.ComboBox;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a school certification with grades.
 * <p>
 * Contains a {@link Pupil} and a list with instances of {@link Grade}.
 * <p>
 * Contains a method to present data in a human-readable format. Overrides
 * {@link Object#toString()} for presentation purposes.
 *
 * @author mirko.schulze
 */
@Getter
@AllArgsConstructor
public class Certification {

    private final Pupil pupil;
    private final List<Grade> grades;

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return Returns the pupil, for whom the certification was made.
     */
    @Override
    public String toString() {
        return "For " + this.pupil.toString();
    }

    /**
     * Joins and returns a human-readable String with all data in this instance.
     *
     * @return Returns the created String.
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
