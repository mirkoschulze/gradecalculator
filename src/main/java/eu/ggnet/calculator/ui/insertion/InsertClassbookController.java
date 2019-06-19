package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
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
     * Tries to create a new {@link Classbook} and close the root {@link Stage}.
     * <p>
     * Catches an {@link Exception} by displaying a new {@link AlertStage} with
     * a respective error message.
     */
    @FXML
    private void create() {
        try {
            this.classbook = new Classbook(this.nameInput.getText(), this.pupils);
            Stage stage = (Stage) this.createButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            new AlertStage("No valid name entered.\nError:\n" + e.getMessage()).display();
        }
        //TODO validation
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
