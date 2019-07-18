package eu.ggnet.gradecalculator.model;

import java.util.List;
import javafx.scene.control.ComboBox;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the classbook of a schoolclass.
 * <p>
 * Contains a String to hold the name of a schoolclass and a list with instances
 * of {@link Pupil}.
 * <p>
 * Contains methods to present data in a human-readable format. Overrides
 * {@link Object#toString()} for presentation purposes.
 *
 * @author Mirko Schulze
 */
@Getter
@AllArgsConstructor
public class Classbook {

    private final String classbookTitle;
    private final List<Pupil> pupils;

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.toSimpleLine();
    }

    /**
     * Returns the classbookTitle of this instance.
     *
     * @return String
     */
    public String toSimpleLine() {
        return this.classbookTitle;
    }

    /**
     * Joins and returns a human-readable String with all data in this instance.
     *
     * @return String
     */
    public String toEnhancedLine() {
        StringBuilder sb = new StringBuilder();
        if (this.classbookTitle != null) {
            sb.append("Klasse: ").append(this.classbookTitle).append("\n");
            sb.append("\n");
        }
        if (this.pupils != null) {
            this.pupils.forEach(p -> {
                sb.append(p.getForename()).append(" ").append(p.getSurname()).append(": ").append("\n");
                if (p.getCertification() != null) {
                    p.getCertification().getGrades().forEach(g -> sb.append(g.toEnhancedLine()).append("\n"));
                }
                sb.append("\n");
            });
        }
        return sb.toString();
    }

}
