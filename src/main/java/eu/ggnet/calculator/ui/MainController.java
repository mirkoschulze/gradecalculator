package eu.ggnet.calculator.ui;

import eu.ggnet.calculator.model.Generator;
import eu.ggnet.calculator.calculation.CalculatedGrade;
import eu.ggnet.calculator.calculation.Calculator;
import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Grade.Subject;
import eu.ggnet.calculator.model.Pupil;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class MainController {

    private List<Classbook> classbooks;
    private CalculatedGrade calculatedGrade;

    private final Stage mainStage;
    private Label instructions;
    private ComboBox<Classbook> classbookSelectionBox;
    private ComboBox<Subject> subjectSelectionBox;
    private ComboBox<String> calculationSelectionBox;
    private Label presentationLabel;
    private TextArea presentation;
    private Label pupilsListLabel;
    private ListView<Pupil> pupilsListView;
    private Label gradesListLabel;
    private ListView<Grade> gradesListView;
    private Button calculateButton;
    private Button calculateForEachClassbookButton;
    private Button calculateForEachSubjectButton;
    private Button clearButton;
    private Button closeButton;

    public MainController(Stage mainStage) {
        this.mainStage = mainStage;
    }

    /**
     * Initializes the controller in the following steps:
     * <ul><li>instantiation of all fields and components</li>
     * <li>auto-generation of 5 instances of {@link Classbook} to simulate, with
     * already set {@link Certification}</li>
     * <li>adding {@link ObservableList} to {@link ComboBox} components</li>
     * <li>adding {@link ChangeListener} to lists</li>
     * <li>setting methods to {@link Button} components</li></ul>
     *
     */
    public void initialize() {
        this.instructions = new Label("Select a class, a subject and a calculation, then click on 'Calculate'."
                + " Click on 'Clear' to reset the text area.");
        this.classbooks = new ArrayList<>();
        this.classbookSelectionBox = new ComboBox<>();
        this.subjectSelectionBox = new ComboBox<>();
        this.calculationSelectionBox = new ComboBox<>();
        this.presentationLabel = new Label("Calculation view:");
        this.presentation = new TextArea();
        this.pupilsListLabel = new Label("Pupils in the selected class:");
        this.pupilsListView = new ListView<>();
        this.gradesListLabel = new Label("Grades of the selected pupil:");
        this.gradesListView = new ListView<>();
        this.calculateButton = new Button("Calculate");
        this.calculateForEachClassbookButton = new Button("Calculate for each classbook");
        this.calculateForEachSubjectButton = new Button("Calculate for each subject");
        this.clearButton = new Button("Clear");
        this.closeButton = new Button("Close");

        //auto-generation of classbooks to simulate the application
        for (int i = 0; i < 5; i++) {
            this.classbooks.add(Generator.generateClassbook());
        }

        for (Classbook classbook : this.classbooks) {
            for (Pupil pupil : classbook.getPupils()) {
                pupil.setCertification(Generator.generateCertification(pupil));
            }
        }

        this.classbookSelectionBox.setPromptText("Select a schoolclass");
        this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        this.subjectSelectionBox.setPromptText("Select a subject");
        this.subjectSelectionBox.setItems(FXCollections.observableArrayList(Subject.values()));
        this.calculationSelectionBox.setItems(FXCollections.observableArrayList("Average", "Accumulation"));
        this.calculationSelectionBox.setPromptText("Select a calculation");

        this.classbookSelectionBox.getSelectionModel().selectedItemProperty()
                .addListener((change, oldValue, newValue) -> {
                    ObservableList<Pupil> pupils = FXCollections
                            .observableArrayList(this.classbookSelectionBox
                                    .getSelectionModel().getSelectedItem()
                                    .getPupils());
                    this.pupilsListView.setItems(pupils);
                });

        this.pupilsListView.getSelectionModel().selectedItemProperty().addListener((change, oldValue, newValue) -> {
            try {
                this.gradesListView.setItems(FXCollections
                        .observableArrayList(this.pupilsListView
                                .getSelectionModel().getSelectedItem()
                                .getCertification().getGrades()));
            } catch (NullPointerException e) {
                this.gradesListView.setItems(FXCollections.emptyObservableList());
            }
        });

        this.presentation.setEditable(false);
        this.presentation.setWrapText(true);

        this.calculateButton.setOnAction(e -> this.calculateAndPresent());
        this.calculateForEachClassbookButton.setOnAction(e -> this.calculateAndPresentForEachClassbook());
        this.calculateForEachSubjectButton.setOnAction(e -> this.calculateAndPresentForEachSubject());
        this.clearButton.setOnAction(e -> this.clear());
        this.closeButton.setOnAction(e -> this.close(this.mainStage));
    }

    /**
     * If all {@link ComboBox} are selected:
     * <ul><li>generates a {@link CalculatedGrade} to set calculatedGrade</li>
     * <li>calculatedGrade is added to {@link Text}</li></ul>
     */
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
        sb.append(this.calculatedGrade.toEnhancedLine()).append("\n");
        this.presentation.setText(new String(sb).concat("\n"));
    }

    /**
     * Resets {@link Text} presentation.
     */
    private void clear() {
        this.presentation.setText("");
    }

    /**
     * Opens a new {@link ConfirmationStage} to confirm the closing of a
     * {@link Stage}.
     *
     * @param stage Stage
     */
    private void close(Stage stage) {
        if (new ConfirmationStage("Do you really want to close the programm?").display()) {
            stage.close();
        }
    }

}
