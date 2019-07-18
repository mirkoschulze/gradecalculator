package eu.ggnet.gradecalculator.ui.update;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Mark;
import eu.ggnet.gradecalculator.model.Grade.Subject;
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
 * Controller class responsible for the logic at {@link UpdateGradeStage}.
 *
 * @author Mirko Schulze
 */
public class UpdateGradeController implements Initializable {

    @Getter
    private Grade grade;

    @FXML
    private ComboBox<Subject> selectSubjectBox;
    @FXML
    private TextField input;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes this class by filling the {@link ComboBox} with the values
     * from enum {@link Subject}.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectSubjectBox.setItems(FXCollections.observableArrayList(Subject.values()));
    }

    /**
     * Tries to create a new {@link Grade} with the entered or selected values.
     * <p>
     * Catches a {@link RunTimeException} by displaying a new AlertStage
     * with a respective error message.
     */
    @FXML
    private void create() {
        Mark mark = null;
        Subject subject = null;
        try {
            mark = Utilities.createMark(Integer.parseInt(this.input.getText()));
        } catch (NumberFormatException e) {
            Utilities.alertWarn("No valid number entered.\n\nException message:\n" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException ex) {
            Utilities.alertWarn("Only values between 0 and 15 are accepted.\n\nException message:\n" + ex.getMessage());
        }
        try {
            subject = this.selectSubjectBox.getValue();
        } catch (NullPointerException e) {
            Utilities.alertWarn("No subject selected.\n\nException mesage:\n" + e.getMessage());
        }
        if (mark != null && subject != null) {
            this.grade = new Grade(subject, mark);
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
