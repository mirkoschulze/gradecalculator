package eu.ggnet.gradecalculator;

import eu.ggnet.gradecalculator.model.Grade.Mark;
import javafx.scene.control.Alert;

/**
 * Static class with useful methods for multiple situations.
 *
 * @author Mirko Schulze
 */
public class Utilities {

    private Utilities() {

    }

    /**
     * Converts an integer value into a {@link Mark}.
     *
     * @param value integer
     * @return Mark
     */
    public static Mark createMark(int value) {
        return Mark.values()[value];
    }

    /**
     * Displays a new {@link Alert} with a message to indicate failures.
     *
     * @param message String to give detailed information about the error.
     */
    public static void alertWarn(String message) {
        new Alert(Alert.AlertType.WARNING, message).show();
    }
    
    public static void alertError(String message){
        new Alert(Alert.AlertType.ERROR, message).show();
    }

}
