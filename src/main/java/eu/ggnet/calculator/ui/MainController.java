package eu.ggnet.calculator.ui;

import eu.ggnet.calculator.ui.confirmation.ConfirmationStage;
import eu.ggnet.calculator.model.Generator;
import eu.ggnet.calculator.calculation.CalculatedGrade;
import eu.ggnet.calculator.calculation.Calculator;
import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Grade.Subject;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.insertion.InsertPupilStage;
import java.net.URL;
import java.util.ArrayList;
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
 * Controller class, responsible for the logic of MainApp.
 *
 * @author Mirko Schulze
 */
@Getter
@ToString
public class MainController implements Initializable {

    private List<Classbook> classbooks;
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
    private ProgressIndicator indicator;
    @FXML
    private ListView<Pupil> pupilsListView;
    @FXML
    private ListView<Grade> gradesListView;
    @FXML
    private Button closeButton;
    @FXML
    private Button addPupilButton;

    /**
     * Initializes the controller in the following steps:
     * <ul><li>instantiation of all fields and components</li>
     * <li>auto-generation of 5 instances of {@link Classbook} to simulate, with
     * already set {@link Certification}</li>
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
                    ObservableList<Pupil> pupils = FXCollections
                            .observableArrayList(this.classbookSelectionBox
                                    .getSelectionModel().getSelectedItem()
                                    .getPupils());
                    this.pupilsListView.setItems(pupils);
                    this.indicator.setProgress(this.indicator.getProgress() + 0.33);
                });

        this.subjectSelectionBox.setItems(FXCollections.observableArrayList(Subject.values()));
        this.subjectSelectionBox.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            this.indicator.setProgress(this.indicator.getProgress() + 0.33);
        });

        this.calculationSelectionBox.setItems(FXCollections.observableArrayList("Average", "Accumulation"));
        this.calculationSelectionBox.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            this.indicator.setProgress(this.indicator.getProgress() + 0.34);
        });

        this.pupilsListView.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
            try {
                this.gradesListView.setItems(FXCollections
                        .observableArrayList(this.pupilsListView
                                .getSelectionModel().getSelectedItem()
                                .getCertification().getGrades()));
            } catch (NullPointerException e) {
                this.gradesListView.setItems(FXCollections.emptyObservableList());
            }
        });
    }

    /**
     * If all {@link ComboBox} are selected:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link Text}</li></ul>
     */
    @FXML
    private void calculateAndPresent() {
        if (this.calculationSelectionBox.getSelectionModel().getSelectedItem() != null
                && this.subjectSelectionBox.getSelectionModel().getSelectedItem() != null
                && this.classbookSelectionBox.getSelectionModel().getSelectedItem() != null) {
            this.calculate(this.classbookSelectionBox.getSelectionModel().getSelectedItem(),
                    this.subjectSelectionBox.getSelectionModel().getSelectedItem());
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
        if (this.calculationSelectionBox.getSelectionModel().getSelectedItem() != null
                && this.subjectSelectionBox.getSelectionModel().getSelectedItem() != null) {
            for (Classbook classbook : classbooks) {
                this.calculate(classbook,
                        this.subjectSelectionBox.getSelectionModel().getSelectedItem());
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
        if (this.calculationSelectionBox.getSelectionModel().getSelectedItem() != null
                && this.classbookSelectionBox.getSelectionModel().getSelectedItem() != null) {
            for (Subject value : Subject.values()) {
                this.calculate(this.classbookSelectionBox.getSelectionModel().getSelectedItem(),
                        value);
                this.present();
            }
        }
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
        switch (this.calculationSelectionBox.getSelectionModel().getSelectedItem().toLowerCase()) {
            case "average":
                this.calculatedGrade = Calculator
                        .calculateAverageGrade(classbook, subject);
                break;
            case "accumulation":
                this.calculatedGrade = Calculator
                        .calculateAccumulatedGrade(classbook, subject);
                break;
        }
    }

    /**
     * Adds a String representation of a {@link CalculatedGrade} to {@link Text}
     * presentation.
     */
    private void present() {
        StringBuilder sb = new StringBuilder(this.presentation.getText());
        sb.append(this.calculatedGrade.toSimpleLine()).append("\n");
        this.presentation.setText(new String(sb).concat("\n"));
    }

    /**
     * Resets {@link Text} presentation.
     */
    @FXML
    private void clear() {
        this.presentation.setText("");
    }

    /**
     * Closes the root stage.
     */
    @FXML
    private void close() {
        if (new ConfirmationStage().display()) {
            Stage stage = (Stage) this.closeButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void addPupil() throws Exception {
        new InsertPupilStage().display();
    }

}
