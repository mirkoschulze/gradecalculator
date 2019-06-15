package eu.ggnet.calculator.ui.confirmation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;

/**
 *
 * @author Administrator
 */
public class ConfirmationController {

    @Getter
    public boolean answer;

    @FXML
    private Button confirmButton;
    @FXML
    private Button denyButton;

    /**
     * Closes the root stage with a true result.
     */
    @FXML
    private void confirm() {
        this.answer = true;
        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the root stage with a false result.
     */
    @FXML
    private void deny() {
        this.answer = false;
        Stage stage = (Stage) this.denyButton.getScene().getWindow();
        stage.close();
    }

}
