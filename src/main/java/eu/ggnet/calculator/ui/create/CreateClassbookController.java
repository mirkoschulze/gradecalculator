package eu.ggnet.calculator.ui.create;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Pupil;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link InsertClassbookStage}.
 *
 * @author Mirko Schulze
 */
public class CreateClassbookController {

    @Getter
    private Classbook classbook;

    @FXML
    private TextField nameInput;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    /**
     * If a name is entered into the {@link TextField}, a new {@link Classbook}
     * will be created with that name and a new {@link ArrayList} for
     * {@link Pupil}.
     * <p>
     * The root {@link Stage} will be closed afterwards.
     */
    @FXML
    private void create() {
        if (!this.nameInput.getText().isEmpty()) {
            this.classbook = new Classbook(this.nameInput.getText(), new ArrayList<>());
            Stage stage = (Stage) this.createButton.getScene().getWindow();
            stage.close();
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
