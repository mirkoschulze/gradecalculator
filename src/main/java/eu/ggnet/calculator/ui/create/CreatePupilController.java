package eu.ggnet.calculator.ui.create;

import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.model.Pupil.Sex;
import eu.ggnet.calculator.ui.AlertStage;
import java.net.URL;
import java.util.Locale;
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
 *
 * @author Mirko Schulze
 */
public class CreatePupilController implements Initializable {

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
     * Initializes the controller class by setting values to {@link ComboBox}.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectSexBox.setItems(FXCollections.observableArrayList(Sex.values()));
    }

    /**
     * Ceates a new {@link Pupil} in the following steps:
     * <ul><li>Checks if all input areas have a value</li>
     * <li>Validates the entered values</li>
     * <li>Instantiates a new Pupil with the entered values</li>
     * <li>Closes the root {@link Stage}</li></ul>
     * <p>
     * If not all values have been entered correctly, a new {@link AlertStage}
     * with a respective error message is displayed.
     */
    @FXML
    private void create() {
        //TODO - first char uppercase, rest lowercase
        if (!this.forenameInput.getText().isEmpty() && !this.surnameInput.getText().isEmpty()
                && !this.ageInput.getText().isEmpty() && this.selectSexBox.getSelectionModel().getSelectedItem() != null) {
            String forename = this.forenameInput.getText().replaceAll("\\d", "").replaceAll("\\s+\\s", "").toLowerCase(Locale.GERMANY);
            String surname = this.surnameInput.getText().replaceAll("\\d", "").replaceAll("\\s+\\s", "").toLowerCase(Locale.GERMANY);
            int age = 0;
            try {
                age = Integer.parseInt(this.ageInput.getText());
            } catch (NumberFormatException e) {
                new AlertStage("Could not set age.").warn();
            }
            Sex sex = this.selectSexBox.getSelectionModel().getSelectedItem();
            if (forename != null && surname != null && age != 0 && sex != null) {
                this.pupil = new Pupil(sex, age, forename, surname);
                Stage stage = (Stage) this.createButton.getScene().getWindow();
                stage.close();
            }
        } else {
            new AlertStage("Please check if all values are entered.").warn();
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