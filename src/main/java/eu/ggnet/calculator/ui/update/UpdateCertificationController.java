package eu.ggnet.calculator.ui.update;

import eu.ggnet.calculator.Utilities;
import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Grade.Subject;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Controller class responsible for the logic at {@link GradePupilStage}.
 *
 * @author Mirko Schulze
 */
public class UpdateCertificationController implements Initializable {

    @Setter
    private Pupil pupil;
    private List<Grade> grades;
    @Getter
    private Certification certification;
    private boolean justNowDisplayed;

    @FXML
    private VBox parentContainer;
    @FXML
    private TextField engInput;
    @FXML
    private TextField itsInput;
    @FXML
    private TextField itwInput;
    @FXML
    private TextField socInput;
    @FXML
    private TextField devInput;
    @FXML
    private TextField orgaInput;
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
        this.grades = new ArrayList<>();
        this.justNowDisplayed = true;
        this.engInput.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue && this.justNowDisplayed) {
                this.parentContainer.requestFocus();
                this.justNowDisplayed = false;
            }
        });
    }

    /**
     * Tries to create a new {@link Certification} and close the root
     * {@link Stage}.
     * <p>
     * A {@link NullPointerException} is caught by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    @FXML
    private void create() {
        try {
            //TODO - reconsider validation
            this.validateEngInput();
            this.validateItsInput();
            this.validateItwInput();
            this.validateSocInput();
            this.validateDevInput();
            this.validateOrgaInput();
            this.certification = new Certification(this.pupil, this.grades);
            Stage stage = (Stage) this.createButton.getScene().getWindow();
            stage.close();
        } catch (NullPointerException e) {
            new AlertStage("No pupil selected.\nError:\n" + e.getMessage()).warn();
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

    /**
     * Tries to add a new {@link Grade} with the user entered value from a
     * {@link TextField}.
     * <p>
     * Catches a {@link NumberFormatException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    private void validateEngInput() {
        try {
            this.grades.add(new Grade(Subject.ENG, Utilities.createMark(Integer.parseInt(this.engInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\n\nException message:\n" + e.getMessage()).warn();
        }
    }

    /**
     * Tries to add a new {@link Grade} with the user entered value from a
     * {@link TextField}.
     * <p>
     * Catches a {@link NumberFormatException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    private void validateItsInput() {
        try {
            this.grades.add(new Grade(Subject.IT_S, Utilities.createMark(Integer.parseInt(this.itsInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\n\nException message:\n" + e.getMessage()).warn();
        }
    }

    /**
     * Tries to add a new {@link Grade} with the user entered value from a
     * {@link TextField}.
     * <p>
     * Catches a {@link NumberFormatException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    private void validateItwInput() {
        try {
            this.grades.add(new Grade(Subject.IT_W, Utilities.createMark(Integer.parseInt(this.itwInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\n\nException message:\n" + e.getMessage()).warn();
        }
    }

    /**
     * Tries to add a new {@link Grade} with the user entered value from a
     * {@link TextField}.
     * <p>
     * Catches a {@link NumberFormatException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    private void validateSocInput() {
        try {
            this.grades.add(new Grade(Subject.SOC, Utilities.createMark(Integer.parseInt(this.socInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\n\nException message:\n" + e.getMessage()).warn();
        }
    }

    /**
     * Tries to add a new {@link Grade} with the user entered value from a
     * {@link TextField}.
     * <p>
     * Catches a {@link NumberFormatException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    private void validateDevInput() {
        try {
            this.grades.add(new Grade(Subject.DEV, Utilities.createMark(Integer.parseInt(this.devInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\n\nException message:\n" + e.getMessage()).warn();
        }
    }

    /**
     * Tries to add a new {@link Grade} with the user entered value from a
     * {@link TextField}.
     * <p>
     * Catches a {@link NumberFormatException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    private void validateOrgaInput() {
        try {
            this.grades.add(new Grade(Subject.ORGA, Utilities.createMark(Integer.parseInt(this.orgaInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\n\nException message:\n" + e.getMessage()).warn();
        }
    }

}
