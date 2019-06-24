package eu.ggnet.calculator.ui.confirmation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link ConfirmationStage}.
 * <p>
 * Defines methods to set a boolean.
 *
 * @author Mirko Schulze
 */
public class ConfirmationController {

    @Getter
    private boolean answer;

    @FXML
    private Button confirmButton;
    @FXML
    private Button denyButton;

    /**
     * Closes the root {@link Stage} with a true result.
     */
    @FXML
    private void confirm() {
        this.answer = true;
        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the root {@link Stage} with a false result.
     */
    @FXML
    private void deny() {
        this.answer = false;
        Stage stage = (Stage) this.denyButton.getScene().getWindow();
        stage.close();
    }

}
