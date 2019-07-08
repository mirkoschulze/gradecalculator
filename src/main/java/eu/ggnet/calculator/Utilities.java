package eu.ggnet.calculator;

import eu.ggnet.calculator.model.Grade.Mark;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
     * Displays a new {@link Stage} with {@link Label} and {@link Button}
     * components to indicate failures.
     */
    public static void alertWarn(String message) {
        new Alert(Alert.AlertType.WARNING, "Something went wrong.\n"
                + "But don't worry. It's your fault, probably. :)\n\n" + message, ButtonType.CLOSE).show();
    }

}
