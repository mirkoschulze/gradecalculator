package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Grade.Mark;
import eu.ggnet.calculator.model.Grade.Subject;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Controller class responsible for the logic at {@link GradePupilStage}.
 * <p>
 * Defines methods to validate user input in {@link TextField} components and to
 * create a {@link Certification} for a {@link Pupil} via {@link Button}
 * components.
 *
 * @author Mirko Schulze
 */
public class GradePupilController {

    @Setter
    private Pupil pupil;
    private List<Grade> grades;
    @Getter
    private Certification certification;

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
    private Button createCertificationButton;
    @FXML
    private Button cancelButton;

    /**
     * Validates each {@link TextField} before trying to create a new
     * {@link Certification}.
     * <p>
     * A {@link NullPointerException} is caught by displaying a new
     * {@link AlertStage} with a respective error message.
     * <p>
     * Closes the root {link Stage}.
     */
    @FXML
    private void confirm() {
        this.grades = new ArrayList<>();
        this.validateEngInput();
        this.validateItsInput();
        this.validateItwInput();
        this.validateSocInput();
        this.validateDevInput();
        this.validateOrgaInput();
        try {
            this.certification = new Certification(this.pupil, this.grades);
        } catch (NullPointerException e) {
            new AlertStage("No pupil selected.\nError:\n" + e.getMessage()).display();
        }
        Stage stage = (Stage) this.createCertificationButton.getScene().getWindow();
        stage.close();
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
     * Converts an integer value into a respective {@link Mark}.
     *
     * @param value integer
     * @return Mark
     */
    private Mark createMark(int value) {
        Mark mark = Mark.ZERO;
        for (Mark m : Mark.values()) {
            if (m.getHighschoolMark() == value) {
                mark = m;
            }
        }
        return mark;
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
            this.grades.add(new Grade(Subject.ENG, this.createMark(Integer.parseInt(this.engInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\nError:\n" + e.getMessage()).display();
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
            this.grades.add(new Grade(Subject.IT_S, this.createMark(Integer.parseInt(this.itsInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\nError:\n" + e.getMessage()).display();
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
            this.grades.add(new Grade(Subject.IT_W, this.createMark(Integer.parseInt(this.itwInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\nError:\n" + e.getMessage()).display();
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
            this.grades.add(new Grade(Subject.SOC, this.createMark(Integer.parseInt(this.socInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\nError:\n" + e.getMessage()).display();
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
            this.grades.add(new Grade(Subject.DEV, this.createMark(Integer.parseInt(this.devInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\nError:\n" + e.getMessage()).display();
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
            this.grades.add(new Grade(Subject.ORGA, this.createMark(Integer.parseInt(this.orgaInput.getText()))));
        } catch (NumberFormatException e) {
            new AlertStage("Could not properly resolve input.\nError:\n" + e.getMessage()).display();
        }
    }

}
