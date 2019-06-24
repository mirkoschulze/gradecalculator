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
        try {
            //TODO no seperate methods needed
            this.validateAge();
            this.validateSex();
            this.validateForename();
            this.validateSurname();
            //these four
            this.pupil = new Pupil(this.sex, this.age, this.forename, this.surname);
            Stage stage = (Stage) this.createButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            new AlertStage("Not all fields set.\nError:\n" + e.getMessage()).warn();
        }//multi catch
    }

    /**
     * Closes the root {@link Stage}.
     */
    @FXML
    private void cancel() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    private void validateAge() {
        //TODO
        try {
            this.age = Integer.parseInt(this.ageInput.getText());
        } catch (NumberFormatException e) {
            new AlertStage("Could not set age.").warn();
        }
    }

    private void validateForename() {
        //TODO
        try {
            this.forename = this.forenameInput.getText();
        } catch (Exception e) {
            new AlertStage("Could not set forename").warn();
        }
    }

    private void validateSurname() {
        //TODO
        try {
            this.surname = this.surnameInput.getText();
        } catch (Exception e) {
            new AlertStage("Could not set surname").warn();
        }
    }

    private void validateSex() {
        //TODO
        try {
            this.sex = this.selectSexBox.getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            new AlertStage("Could not set sex").warn();
        }
    }

}
