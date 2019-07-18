package eu.ggnet.gradecalculator.ui.create;

import eu.ggnet.gradecalculator.model.Classbook;
import eu.ggnet.gradecalculator.model.Pupil;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link CreateClassbookStage}.
 *
 * @author Mirko Schulze
 */
public class CreateClassbookController {

    @Getter
    private Classbook classbook;

    @FXML
    private TextField titleInput;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    /**
     * If a title is entered into the {@link TextField}, a new {@link Classbook}
     * will be created with that title and a new, empty {@link ArrayList} for
     * instances of {@link Pupil}.
     * <p>
     * The root {@link Stage} will be closed afterwards.
     */
    @FXML
    private void create() {
        if (!this.titleInput.getText().isEmpty()) {
            List<Pupil> pupils = new ArrayList<>();
            this.classbook = new Classbook(this.titleInput.getText(), pupils);
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
