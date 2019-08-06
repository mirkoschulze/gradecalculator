package eu.ggnet.gradecalculator;

import eu.ggnet.gradecalculator.model.Grade.Mark;
import javafx.scene.control.Alert;

/**
 * Class with static methods, useful for multiple situations.
 *
 * @author Mirko Schulze
 */
public class Utilities {

    /**
     * Private constructor to prevent an instantiation.
     */
    private Utilities() {

    }
    
    /**
     * Compares the submitted String to the values of {@link Mark} and returns
     * the matching Mark.
     * <p>
     * Returns null if no matching Mark can be found.
     *
     * @param middleschoolMark the value for which the matching Mark is looked
     * for
     * @return Mark - the matching Mark or null
     */
    public static Mark findByMiddleschoolMark(String middleschoolMark) {
        for (Mark value : Mark.values()) {
            if (value.getMiddleschoolMark().equalsIgnoreCase(middleschoolMark)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Compares the submitted integer to the values of {@link Mark} and returns
     * the matching Mark.
     * <p>
     * Returns null if no matching Mark can be found.
     *
     * @param highschoolMark the value for which the matching Mark is looked for
     * @return Mark - the matching Mark or null
     */
    public static Mark findByHighschoolMark(int highschoolMark) {
        for (Mark value : Mark.values()) {
            if (value.getHighschoolMark() == highschoolMark) {
                return value;
            }
        }
        return null;
    }

    /**
     * Displays a new {@link Alert} pane with a message to indicate a failure.
     * <p>
     * Probably wrong user input.
     *
     * @param message String - additional information about the failure
     */
    public static void alertWarn(String message) {
        new Alert(Alert.AlertType.WARNING, message).show();
    }

    /**
     * Displays a new {@link Alert} pane with a message to indicate an error.
     *
     * @param message String - additional information about the error
     */
    public static void alertError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

}
