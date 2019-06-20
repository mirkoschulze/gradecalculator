package eu.ggnet.calculator.ui;

import eu.ggnet.calculator.calculation.*;
import eu.ggnet.calculator.model.*;
import eu.ggnet.calculator.model.Grade.Mark;
import eu.ggnet.calculator.model.Grade.Subject;
import eu.ggnet.calculator.ui.confirmation.ConfirmationStage;
import eu.ggnet.calculator.ui.modification.SetCertificationAtSelectedPupilStage;
import eu.ggnet.calculator.ui.insertion.InsertClassbookStage;
import eu.ggnet.calculator.ui.insertion.InsertPupilStage;
import eu.ggnet.calculator.ui.modification.EnterMarkStage;
import eu.ggnet.calculator.ui.modification.SelectSubjectStage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.ToString;

/**
 * Controller class, responsible for the logic at {@link MainStage}.
 * <p>
 * Defines methods to interact with the different components.
 *
 * @author Mirko Schulze
 */
@Getter
@ToString
public class MainController implements Initializable {

    private List<Classbook> classbooks;
    private Classbook selectedClassbook;
    private Subject selectedSubject;
    private String selectedCalculation;
    private CalculatedGrade calculatedGrade;
    private boolean selectedPupil;

    @FXML
    private ComboBox<Classbook> classbookSelectionBox;
    @FXML
    private ComboBox<Subject> subjectSelectionBox;
    @FXML
    private ComboBox<String> calculationSelectionBox;
    @FXML
    private TextArea presentation;
    @FXML
    private ProgressIndicator indicator;
    @FXML
    private ListView<Pupil> pupilsListView;
    @FXML
    private ListView<Grade> gradesListView;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller in the following steps:
     * <ul><li>auto-generation of 5 instances of {@link Classbook} to simulate,
     * with already set {@link Certification}</li>
     * <li>adding {@link ObservableList} to {@link ComboBox} components</li>
     * <li>adding {@link ChangeListener} to lists</li></ul>
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.classbooks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.classbooks.add(Generator.generateClassbook());
        }
        for (Classbook classbook : this.classbooks) {
            for (Pupil pupil : classbook.getPupils()) {
                pupil.setCertification(Generator.generateCertification(pupil));
            }
        }

        this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        this.classbookSelectionBox.getSelectionModel().selectedItemProperty()
                .addListener((value, oldValue, newValue) -> {
                    this.selectedClassbook = newValue;
                    try {
                        this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
                    } catch (NullPointerException e) {
                        this.gradesListView.setItems(FXCollections.emptyObservableList());
                    }
                    this.indicator.setProgress(this.indicator.getProgress() + 0.33);
                });

        this.subjectSelectionBox.setItems(FXCollections.observableArrayList(Subject.values()));
        this.subjectSelectionBox.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            this.selectedSubject = newValue;
            this.indicator.setProgress(this.indicator.getProgress() + 0.33);
        });

        this.calculationSelectionBox.setItems(FXCollections.observableArrayList("Average", "Accumulation"));
        this.calculationSelectionBox.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            this.selectedCalculation = newValue;
            this.indicator.setProgress(this.indicator.getProgress() + 0.34);
        });

        this.pupilsListView.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            try {
                this.gradesListView.setItems(FXCollections.observableArrayList(this.pupilsListView
                        .getSelectionModel()
                        .getSelectedItem()
                        .getCertification()
                        .getGrades()));
            } catch (NullPointerException e) {
                this.gradesListView.setItems(FXCollections.emptyObservableList());
            }
        });
        //TODO listener new pupil new classbook new certification docu
    }

    /**
     * If all {@link ComboBox} are selected:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link Text}</li></ul>
     */
    @FXML
    private void calculateAndPresent() {
        if (this.selectedCalculation != null
                && this.selectedSubject != null
                && this.selectedClassbook != null) {
            this.calculate(this.selectedClassbook, this.selectedSubject);
            this.present();
        }
    }

    /**
     * If {@link ComboBox} calculationSelection and subjectSelection are
     * selected:
     * <p>
     * For each {@link Classbook} in classbooks:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link Text} presentation</li></ul>
     */
    @FXML
    private void calculateAndPresentForEachClassbook() {
        if (this.selectedCalculation != null
                && this.selectedSubject != null) {
            for (Classbook classbook : classbooks) {
                this.calculate(classbook, this.selectedSubject);
                this.present();
            }
        }
    }

    /**
     * If {@link ComboBox} calculationSelection and classbookSelection are
     * selected:
     * <p>
     * For each {@link Subject}:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link Text} </li></ul>
     */
    @FXML
    private void calculateAndPresentForEachSubject() {
        if (this.selectedCalculation != null
                && this.selectedClassbook != null) {
            for (Subject value : Subject.values()) {
                this.calculate(this.selectedClassbook, value);
                this.present();
            }
        }
    }

    /**
     * Resets {@link Text} presentation.
     */
    @FXML
    private void clear() {
        this.presentation.setText("");
    }

    /**
     * Closes the root stage after confirming the decision.
     */
    @FXML
    private void close() {
        if (new ConfirmationStage().confirm()) {
            Stage stage = (Stage) this.closeButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Displays a new {@link InsertPupilStage} to create a new {@link Pupil}.
     * That Pupil is added to the {@link Classbook}, selected via
     * {@link ComboBox}.
     */
    @FXML
    private void addPupil() {
        Pupil pupil = new InsertPupilStage().createPupil();
        this.selectedClassbook.getPupils().add(pupil);
        this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
        //TODO listener
    }

    /**
     * Removes the selected {@link Pupil} from the selected {@link Classbook}.
     */
    @FXML
    private void removePupil() {
        this.selectedClassbook.getPupils().remove(this.pupilsListView.getSelectionModel().getSelectedItem());
        this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
        //TODO listener
    }

    /**
     * Displays a new {@link InsertClassbookStage} to create a new
     * {@link Classbook}. That Classbook is added to the list of available
     * Classbooks, selected via {@link ComboBox}.
     */
    @FXML
    private void addClassbook() {
        Classbook classbook = new InsertClassbookStage().createClassbook();
        this.classbooks.add(classbook);
        this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        //TODO listener
    }

    /**
     * Removes the selected {@link Classbook} from the list of available
     * Classbooks.
     */
    @FXML
    private void removeClassbook() {
        this.classbooks.remove(this.selectedClassbook);
        this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        //TODO listener
    }

    @FXML
    private void setCertificationAtSelectedPupil() {
        this.pupilsListView.getSelectionModel().getSelectedItem()
                .setCertification(new SetCertificationAtSelectedPupilStage().createCertification());
        this.gradesListView.setItems(FXCollections.observableArrayList(this
                .pupilsListView.getSelectionModel().getSelectedItem().getCertification().getGrades()));
        //TODO listener
    }

    @FXML
    private void setGradeAtSelectedPupil() {
        Pupil pupil = this.pupilsListView.getSelectionModel().getSelectedItem();
        List<Grade> grades = pupil.getCertification().getGrades();
        Grade[] gradeArray = new Grade[6];
        for (int i = 0; i < gradeArray.length; i++) {
            gradeArray[i] = grades.get(i);
        }
        //TODO ListOfGrades class

        Subject subject = new SelectSubjectStage().selectSubject();
//        Mark mark = new EnterMarkStage().enterMark();
//        gradeArray[subject.ordinal()] = new Grade(subject, mark);
//
//        grades = Arrays.asList(gradeArray);
//        pupil.setCertification(new Certification(pupil, grades));
//        this.gradesListView.setItems(FXCollections.observableArrayList(this
//                .pupilsListView.getSelectionModel().getSelectedItem().getCertification().getGrades()));
        //TODO listener
    }

    /**
     * Checks {@link ComboBox} calculationSelection for the selected calculation
     * and calls the respective method from {@link Calculator} to generate a
     * {@link CalculatedGrade} for the submitted {@link Classbook} and
     * {@link Subject}.
     *
     * @param classbook Classbook
     * @param subject Subject
     */
    private void calculate(Classbook classbook, Subject subject) {
        switch (this.selectedCalculation.toLowerCase()) {
            case "average":
                this.calculatedGrade = Calculator.calculateAverageGrade(classbook, subject);
                break;
            case "accumulation":
                this.calculatedGrade = Calculator.calculateAccumulatedGrade(classbook, subject);
                break;
        }
    }

    /**
     * Adds a String representation of a {@link CalculatedGrade} to {@link Text}
     * presentation.
     */
    private void present() {
        StringBuilder sb = new StringBuilder(this.presentation.getText());
        sb.append(this.calculatedGrade.toSimpleLine());
        this.presentation.setText(new String(sb).concat("\n"));
    }

}
