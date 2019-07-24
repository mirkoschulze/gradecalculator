package eu.ggnet.gradecalculator.ui;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.calculation.CalculatedGrade;
import eu.ggnet.gradecalculator.calculation.Calculator;
import eu.ggnet.gradecalculator.model.Certification;
import eu.ggnet.gradecalculator.model.Classbook;
import eu.ggnet.gradecalculator.model.Generator;
import eu.ggnet.gradecalculator.model.Grade;
import eu.ggnet.gradecalculator.model.Grade.Subject;
import eu.ggnet.gradecalculator.model.Pupil;
import eu.ggnet.gradecalculator.ui.confirmation.ConfirmationStage;
import eu.ggnet.gradecalculator.ui.create.CreateClassbookStage;
import eu.ggnet.gradecalculator.ui.create.CreatePupilStage;
import eu.ggnet.gradecalculator.ui.update.UpdateCertificationStage;
import eu.ggnet.gradecalculator.ui.update.UpdateGradeStage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller class, responsible for the logic at {@link MainStage}.
 * <p>
 * Defines methods to interact with the different components.
 *
 * @author Mirko Schulze
 */
public class MainController implements Initializable {

    private List<Classbook> classbooks;
    private Classbook selectedClassbook;
    private Subject selectedSubject;
    private String selectedCalculation;
    private CalculatedGrade calculatedGrade;

    @FXML
    private ComboBox<Classbook> classbookSelectionBox;
    @FXML
    private ComboBox<Subject> subjectSelectionBox;
    @FXML
    private ComboBox<String> calculationSelectionBox;
    @FXML
    private TextArea presentation;
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

        //auto-generate classbooks to simulate
        this.classbooks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.classbooks.add(Generator.generateClassbook());
        }
        for (Classbook classbook : this.classbooks) {
            classbook.getPupils().forEach((pupil) -> {
                pupil.setCertification(Generator.generateCertification(pupil));
            });
        }

        //listener to show pupils when a classbook is selected
        this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        this.classbookSelectionBox.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                    this.selectedClassbook = newValue;
                    try {
                        this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
                    } catch (NullPointerException e) {
                        this.gradesListView.setItems(FXCollections.emptyObservableList());
                    }
                });

        this.subjectSelectionBox.setItems(FXCollections.observableArrayList(Subject.values()));
        this.subjectSelectionBox.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            this.selectedSubject = newValue;
        });

        this.calculationSelectionBox.setItems(FXCollections.observableArrayList("Average", "Accumulation"));
        this.calculationSelectionBox.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            this.selectedCalculation = newValue;
        });

        //listener to show grades when a pupil is selected
        this.pupilsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
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
     * Adds a String representation of a {@link CalculatedGrade} to
     * {@link TextArea} presentation.
     */
    private void present() {
        StringBuilder sb = new StringBuilder(this.presentation.getText());
        sb.append(this.calculatedGrade.toSimpleLine());
        this.presentation.setText(new String(sb).concat("\n"));
    }

    /**
     * If all {@link ComboBox} are selected:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link TextArea}</li></ul>
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
     * <li>calculatedGrade is added to {@link TextArea} presentation</li></ul>
     */
    @FXML
    private void calculateAndPresentForEachClassbook() {
        if (this.selectedCalculation != null
                && this.selectedSubject != null) {
            this.classbooks.forEach(c -> {
                this.calculate(c, this.selectedSubject);
                this.present();
            });
        }
    }

    /**
     * If {@link ComboBox} calculationSelection and classbookSelection are
     * selected:
     * <p>
     * For each {@link Subject}:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link TextArea} </li></ul>
     */
    @FXML
    private void calculateAndPresentForEachSubject() {
        if (this.selectedCalculation != null
                && this.selectedClassbook != null) {
            Arrays.asList(Subject.values()).forEach(s -> {
                this.calculate(this.selectedClassbook, s);
                this.present();
            });
        }
    }

    /**
     * Resets {@link TextArea} presentation.
     */
    @FXML
    private void clear() {
        this.presentation.setText("");
    }

    /**
     * Displays a new {@link CreateClassbookStage} to create a new
     * {@link Classbook}.
     * <p>
     * If a Classbook is returned, that Classbook is added to the available
     * Classbooks.
     */
    @FXML
    private void addClassbook() {
        Optional<Classbook> classbook = new CreateClassbookStage().createClassbook();
        if (classbook.isPresent()) {
            this.classbooks.add(classbook.get());
            this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        }
    }

    /**
     * Tries to remove the selected {@link Classbook} from the list of available
     * Classbooks.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    @FXML
    private void removeClassbook() {
        try {
            this.classbooks.remove(this.selectedClassbook);
            this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
            this.pupilsListView.setItems(FXCollections.emptyObservableList());
        } catch (NullPointerException e) {
            Utilities.alertWarn("No classbook selected.\n\nException message:\n" + e.getMessage());
        }
    }

    /**
     * Tries to displays a new {@link CreatePupilStage} to create a new
     * {@link Pupil} at a {@link Classbook}.
     * <p>
     * If a Pupil is returned, that Pupil is addded to the selected Classbook.
     * <p>
     * Catches a {@link NullPointerException} by dispülaying a new
     * {@link AlertStage} with a respective error message.
     */
    @FXML
    private void addPupil() {
        Optional<Pupil> pupil = new CreatePupilStage().showAndWait();
        System.out.println(pupil.orElse(null));
        try {

            if (pupil.isPresent()) {
                this.selectedClassbook.getPupils().add(pupil.get());
                this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
            }
        } catch (NullPointerException e) {
            Utilities.alertWarn("No classbook selected.\n\nException message:\n" + e.getMessage());
        }

    }

    /**
     * Tries to remove the selected {@link Pupil} from the selected
     * {@link Classbook}.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    @FXML
    private void removePupil() {
        try {
            this.selectedClassbook.getPupils().remove(this.pupilsListView.getSelectionModel().getSelectedItem());
            this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
        } catch (NullPointerException e) {
            Utilities.alertWarn("No pupil selected.\n\nException message:\n" + e.getMessage());
        }

    }

    /**
     * Tries to display a new {@link UpdateCertificationStage} to create a new
     * {@link Certification} at a {@link Pupil}.
     * <p>
     * If a Certification is returned, the Certification is set at the selected
     * Pupil.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new
     * {@link AlertStage} with a respective error message.
     */
    @FXML
    private void setCertificationAtSelectedPupil() {
        try {
            Optional<Certification> certification = new UpdateCertificationStage().createCertification();
            if (certification.isPresent()) {
                this.pupilsListView.getSelectionModel().getSelectedItem().setCertification(certification.get());
                this.gradesListView.setItems(FXCollections.observableArrayList(this.pupilsListView.getSelectionModel().getSelectedItem().getCertification().getGrades()));
            }
        } catch (NullPointerException e) {
            Utilities.alertWarn("No pupil selected.\n\nException message:\n" + e.getMessage());
        }

    }

    /**
     * Tries to display a new {@link UpdateGradeStage} to create a new
     * {@link Grade} for a {@link Certification} at a {@link Pupil}.
     * <p>
     * If a Grade is returned, a new Certification with the old values and the
     * newly created Grade is set at the selected Pupil.
     */
    @FXML
    private void setGradeAtSelectedPupil() {
        try {
            Pupil pupil = this.pupilsListView.getSelectionModel().getSelectedItem();
            List<Grade> grades = pupil.getCertification().getGrades();
            Optional<Grade> grade = new UpdateGradeStage().updateGrade();
            if (grade.isPresent()) {
                grades.set(grade.get().getSubject().ordinal(), grade.get());
                pupil.setCertification(new Certification(pupil, grades));
                this.gradesListView.setItems(FXCollections.observableArrayList(this.pupilsListView.getSelectionModel().getSelectedItem().getCertification().getGrades()));
            }
        } catch (NullPointerException e) {
            Utilities.alertWarn("No pupil selected.\n\nException message:\n" + e.getMessage());
        }

    }

    /**
     * Displays a new {@link ConfirmationStage} to confirm the close request.
     * Closes the root {@link Stage} when the event is confirmed.
     */
    @FXML
    private void close() {
        if (new ConfirmationStage().confirm()) {
            Stage stage = (Stage) this.closeButton.getScene().getWindow();
            stage.close();
        }
    }

}