package eu.ggnet.gradecalculator.ui.update;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Certification;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil;
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
 * Controller class responsible for the logic at {@link UpdateGradeStage}.
 *
 * @author Mirko Schulze
 */
public class UpdateCertificationController implements Initializable {

    @Setter
    private Pupil pupil;
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
     * Initializes this controller by instantiating an {@link ArrayList} and
     * adding a listener to the first {@link TextField} to prevent an
     * auto-selection.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.justNowDisplayed = true;
        this.engInput.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue && this.justNowDisplayed) {
                this.parentContainer.requestFocus();
                this.justNowDisplayed = false;
            }
        });
    }

    /**
     * Creates a new {@link Certification} in the following steps:
     * <ul><li>checks if all input areas have a value</li>
     * <li>instantiates a new {@link ArrayList}</li>
     * <li>tries to get the value from each input area and adds a new
     * {@link Grade} to the list</li>
     * <li>invalid values are catched by a new AlertStage that displays
     * a respective error message</li></ul>
     * <p>
     * If all input areas can be resolved:
     * <ul><li>instantiates a new Certification with the created list</li>
     * <li>closes the root {@link Stage}</li></ul>
     */
    @FXML
    private void create() {
        if (!(this.engInput.getText().isEmpty() && this.itsInput.getText().isEmpty()
                && this.itwInput.getText().isEmpty() && this.engInput.getText().isEmpty()
                && this.socInput.getText().isEmpty() && this.orgaInput.getText().isEmpty())) {
            List<Grade> grades = new ArrayList<>();
            try {
                grades.add(new Grade(Subject.ENG, Utilities.createMark(Integer.parseInt(this.engInput.getText()))));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
               Utilities.alertWarn("Could not properly resolve input.\n\nException message:\n" + e.getMessage());
            }

            try {
                grades.add(new Grade(Subject.IT_S, Utilities.createMark(Integer.parseInt(this.itsInput.getText()))));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Utilities.alertWarn("Could not properly resolve input.\n\nException message:\n" + e.getMessage());
            }

            try {
                grades.add(new Grade(Subject.IT_W, Utilities.createMark(Integer.parseInt(this.itwInput.getText()))));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Utilities.alertWarn("Could not properly resolve input.\n\nException message:\n" + e.getMessage());
            }

            try {
                grades.add(new Grade(Subject.SOC, Utilities.createMark(Integer.parseInt(this.socInput.getText()))));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Utilities.alertWarn("Could not properly resolve input.\n\nException message:\n" + e.getMessage());
            }

            try {
                grades.add(new Grade(Subject.DEV, Utilities.createMark(Integer.parseInt(this.devInput.getText()))));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Utilities.alertWarn("Could not properly resolve input.\n\nException message:\n" + e.getMessage());
            }

            try {
                grades.add(new Grade(Subject.ORGA, Utilities.createMark(Integer.parseInt(this.orgaInput.getText()))));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
               Utilities.alertWarn("Could not properly resolve input.\n\nException message:\n" + e.getMessage());
            }
            if (grades.size() == 6) {
                this.certification = new Certification(this.pupil, grades);
                Stage stage = (Stage) this.createButton.getScene().getWindow();
                stage.close();
            }
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
