package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Pupil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link InsertClassbookStage}.
 * <p>
 * Defines methods to create a {@link Classbook} via {@link Button} components.
 *
 * @author Mirko Schulze
 */
public class InsertClassbookController implements Initializable {

    private List<Pupil> pupils;
    @Getter
    private Classbook classbook;

    @FXML
    private TextField nameInput;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes this controller by instantiating an {@link ArrayList}.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.pupils = new ArrayList<>();
    }

    /**
     * If a name is entered into the {@link TextField}, a new {@link Classbook}
     * will be created with that name and an empty list for {@link Pupil}.
     * <p>
     * The root {@link Stage} will be closed afterwards.
     */
    @FXML
    private void create() {
        if (!this.nameInput.getText().isEmpty()) {
            this.classbook = new Classbook(this.nameInput.getText(), this.pupils);
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
