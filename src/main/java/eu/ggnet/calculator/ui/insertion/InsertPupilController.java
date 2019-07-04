package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.model.Pupil.Sex;
import eu.ggnet.calculator.ui.AlertStage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link InsertPupilStage}.
 * <p>
 * Defines methods to validate user input in {@link TextField} and
 * {@link ComboBox} components and to create a new {@link Pupil} via
 * {@link Button} components.
 *
 * @author Mirko Schulze
 */
public class InsertPupilController implements Initializable {

    private String forename;
    private String surname;
    private int age;
    private Sex sex;
    @Getter
    private Pupil pupil;

    @FXML
    private TextField forenameInput;
    @FXML
    private TextField surnameInput;
    @FXML
    private TextField ageInput;
    @FXML
    private ComboBox<Sex> selectSexBox;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class by setting values to
     * {@link #selectSexBox}.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectSexBox.setItems(FXCollections.observableArrayList(Sex.values()));
    }

    /**
     * Tries to create a new {@link Pupil} and close the root {@link Stage}.
     * <p>
     * Catches {@link Exception} by displaying a new {@link AlertStage} with a
     * respective error message.
     */
    @FXML
    private void create() {
        if (this.inputsAreEntered()) {
            this.forename = this.forenameInput.getText().replaceAll("\\d", "").trim();
            this.surname = this.surnameInput.getText().replaceAll("\\d", "").trim();
            try {
                this.age = Integer.parseInt(this.ageInput.getText());
            } catch (NumberFormatException e) {
                new AlertStage("Could not set age.").warn();
            }
            this.sex = this.selectSexBox.getSelectionModel().getSelectedItem();
            if (this.valuesAreSet()) {
                this.pupil = new Pupil(this.sex, this.age, this.forename, this.surname);
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();
            }
        } else {
            new AlertStage("Plese check if all values are entered.").warn();
        }
    }

    private boolean inputsAreEntered() {
        return this.forenameInput.getText() != null
                && this.surnameInput.getText() != null
                && this.ageInput.getText() != null
                && this.selectSexBox.getSelectionModel().getSelectedItem() != null;
    }

    private boolean valuesAreSet() {
        return this.forename != null 
                && this.surname != null 
                && this.age != 0 
                && this.sex != null;
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
