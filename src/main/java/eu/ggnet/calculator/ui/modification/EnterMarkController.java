package eu.ggnet.calculator.ui.modification;

import eu.ggnet.calculator.Utilities;
import eu.ggnet.calculator.model.Grade.Mark;
import eu.ggnet.calculator.ui.AlertStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
//TODO all
/**
 *
 * @author Mirko Schulze
 */
public class EnterMarkController {

    @Getter
    private Mark mark;
    @FXML
    private TextField input;

    @FXML
    private Button enterButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void enterMark() {
        try {
            this.mark = Utilities.createMark(Integer.parseInt(this.input.getText()));
            Stage stage = (Stage) this.enterButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            new AlertStage("No valid number entered.\nError:\n" + e.getMessage()).warn();
        } catch (NullPointerException e) {
            new AlertStage("No number entered.\nError:\n" + e.getMessage()).warn();
        }
    }

    /**
     * Closes the root {@link Stage}.
     */
    @FXML
    private void cancel() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }
}
