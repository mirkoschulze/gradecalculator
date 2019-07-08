package eu.ggnet.calculator.ui.create;

import eu.ggnet.calculator.Utilities;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.model.Pupil.Sex;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class responsible for the logic at {@link CreatePupilStage}.
 *
 * @author Mirko Schulze
 */
public class CreatePupilController implements Initializable {

    @Getter
    private Pupil pupil;
    private boolean justNowDisplayed;

    @FXML
    private VBox parentContainer;
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
     * Initializes the controller class by setting values to {@link ComboBox}
     * and adding a listener to the first {@link TextField} to prevent an
     * auto-selection.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectSexBox.setItems(FXCollections.observableArrayList(Sex.values()));
        this.justNowDisplayed = true;
        this.forenameInput.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue && this.justNowDisplayed) {
                this.parentContainer.requestFocus();
                this.justNowDisplayed = false;
            }
        });

        this.ageInput.tooltipProperty().setValue(new Tooltip("Only digits are allowed for age validation."));
        this.ageInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // matcher for decimal input: newValue.matches("\\d{0,7}([\\.]\\d{0,4})?
                if (!newValue.matches("\\d{0,3}")) {
                    ageInput.setText(oldValue);
                }
            }
        });
    }

    /**
     * Ceates a new {@link Pupil} in the following steps:
     * <ul><li>checks if all input areas have a value</li>
     * <li>validates the entered values</li>
     * <li>instantiates a new Pupil with the entered values</li>
     * <li>closes the root {@link Stage}</li></ul>
     * <p>
     * If not all values have been entered correctly, a new AlertStage
     * with a respective error message is displayed.
     */
    @FXML
    private void create() {
        if (!this.forenameInput.getText().isEmpty() && !this.surnameInput.getText().isEmpty()
                && !this.ageInput.getText().isEmpty() && this.selectSexBox.getSelectionModel().getSelectedItem() != null) {
            Sex sex = this.selectSexBox.getSelectionModel().getSelectedItem();
            int age = 0;

            try {
                age = Integer.parseInt(this.ageInput.getText());
            } catch (NumberFormatException e) {
                Utilities.alertWarn("Could not set age.\n\nException message:\n" + e.getMessage());
            }
            String validForename = this.forenameInput.getText().replaceAll("\\d", "").replaceAll("\\s", "").toLowerCase();
            String forename = Character.toUpperCase(validForename.charAt(0)) + validForename.substring(1);
            String validSurname = this.surnameInput.getText().replaceAll("\\d", "").replaceAll("\\s", "").toLowerCase();
            String surname = Character.toUpperCase(validSurname.charAt(0)) + validSurname.substring(1);

            if (forename != null && surname != null && age > 0 && age < 125 && sex != null) {
                this.pupil = new Pupil(sex, age, forename, surname);
                Stage stage = (Stage) this.createButton.getScene().getWindow();
                stage.close();
            }
        } else {
            Utilities.alertWarn("Please check if all values are entered properly.");
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
