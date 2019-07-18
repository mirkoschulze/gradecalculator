package eu.ggnet.gradecalculator.ui.confirmation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link ConfirmationStage}.
 *
 * @author Mirko Schulze
 */
public class ConfirmationController {

    @Getter
    private boolean confirmed;

    @FXML
    private Button confirmButton;
    @FXML
    private Button denyButton;

    /**
     * Closes the root {@link Stage} with a true result.
     */
    @FXML
    private void confirm() {
        this.confirmed = true;
        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the root {@link Stage} with a false result.
     */
    @FXML
    private void deny() {
        this.confirmed = false;
        Stage stage = (Stage) this.denyButton.getScene().getWindow();
        stage.close();
    }

}
