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
 * Contains methods to present data in a human-readable format.
 * <p>
 * Overrides {@link Object#toString()} for gui presentation purposes.
 *
 * @author Mirko Schulze
 */
@Getter
@AllArgsConstructor
public class Classbook {

    /**
     * Title of a {@link Classbook}, e.g. "IT 8i".
     */
    private final String classbookTitle;
    private final List<Pupil> pupils;

    /**
     * {@link ComboBox} uses {@link Object#toString()} to display data.
     *
     * @return String - {@link #classbookTitle} of this {@link Classbook}
     * @see #toSimpleLine()
     */
    @Override
    public String toString() {
        return this.toSimpleLine();
    }

    /**
     * Returns the {@link #classbookTitle} of this {@link Classbook}.
     *
     * @return String - {@link #classbookTitle} of this {@link Classbook}
     */
    public String toSimpleLine() {
        return this.classbookTitle;
    }

    /**
     * Joins and returns a human-readable String with the data of this
     * {@link Classbook}.
     *
     * @return String - human-readable representation of thi s {@link Classbook}
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
