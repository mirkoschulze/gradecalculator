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
 * FXML Controller class
 *
 * @author Administrator
 */
@Getter
public class InsertPupilController implements Initializable {

    private String forename;
    private String surname;
    private int age;
    private Sex sex;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectSexBox.setItems(FXCollections.observableArrayList(Sex.values()));
        this.selectSexBox.setPromptText("Select a sex...");
    }

    @FXML
    private void cancel() {
        this.clear();
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void create() {
        if (isReady()) {
            validateAge();
            validateSex();
            validateForename();
            validateSurname();
            this.pupil = new Pupil(this.sex, this.age, this.forename, this.surname);
            Stage stage = (Stage) this.cancelButton.getScene().getWindow();
            stage.close();
        } else {
            new AlertStage("Not all fields set.").display();
        }
    }

    private boolean isReady() {
        return (this.forenameInput.getText() != null
                && !this.forenameInput.getText().isEmpty()
                && this.surnameInput.getText() != null
                && !this.surnameInput.getText().isEmpty()
                && this.ageInput.getText() != null
                && !(this.ageInput.getText().isEmpty())
                && this.selectSexBox.getSelectionModel().getSelectedItem() != null);

    }

    private void validateAge() {
        try {
            this.age = Integer.parseInt(this.ageInput.getText());
        } catch (NumberFormatException e) {
            new AlertStage("Could not set age.").display();
        }
    }

    private void validateForename() {
        try {
            this.forename = this.forenameInput.getText();
        } catch (Exception e) {
            new AlertStage("Could not setr forename").display();
        }
    }

    private void validateSurname() {
        try {
            this.surname = this.surnameInput.getText();
        } catch (Exception e) {
            new AlertStage("Could not setr forename").display();
        }
    }

    private void validateSex() {
        try {
            this.sex = this.selectSexBox.getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            new AlertStage("Could not set sex").display();
        }
    }

    private void clear() {
        this.forenameInput.clear();
        this.surnameInput.clear();
        this.ageInput.clear();
        this.selectSexBox.getSelectionModel().clearSelection();
    }

}
