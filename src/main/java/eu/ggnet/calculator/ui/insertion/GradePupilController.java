package eu.ggnet.calculator.ui.insertion;

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
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GradePupilController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.grades = new ArrayList<>();
    }

    @FXML
    private void createCertification() {
        this.grades.add(new Grade(Subject.ENG, this.generateMark(Integer.parseInt(this.engInput.getText()))));
        this.grades.add(new Grade(Subject.IT_S, this.generateMark(Integer.parseInt(this.itsInput.getText()))));
        this.grades.add(new Grade(Subject.IT_W, this.generateMark(Integer.parseInt(this.itwInput.getText()))));
        this.grades.add(new Grade(Subject.SOC, this.generateMark(Integer.parseInt(this.socInput.getText()))));
        this.grades.add(new Grade(Subject.DEV, this.generateMark(Integer.parseInt(this.devInput.getText()))));
        this.grades.add(new Grade(Subject.ORGA, this.generateMark(Integer.parseInt(this.orgaInput.getText()))));
        try {
            this.certification = new Certification(this.pupil, this.grades);
        } catch (NullPointerException e) {
            new AlertStage("No pupil selected.").display();
        }
        Stage stage = (Stage) this.createCertificationButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    private Grade.Mark generateMark(int value) {
        Grade.Mark returnMark = Grade.Mark.ZERO;
        for (Grade.Mark note : Grade.Mark.values()) {
            if (note.getHighschoolMark() == value) {
                returnMark = note;
            }
        }
        return returnMark;
    }

}
