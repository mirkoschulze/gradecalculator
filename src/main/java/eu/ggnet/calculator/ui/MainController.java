/*
*
* by Mirko Schulze
 */
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
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Controller class, responsible for the logic of MainApp.
 *
 * @author Administrator
 */
@Getter
public class MainController {

    //root
    private Stage mainStage;

    //fields
    private List<Classbook> classbooks;
    private CalculatedGrade calculatedGrade;

    //components
    private Label instructions;
    private ComboBox<Classbook> classbookSelectionBox;
    private ComboBox<Subject> subjectSelectionBox;
    private ComboBox<String> calculationSelectionBox;
    private Label presentationLabel;
    private Text presentation;
    private Label pupilsListLabel;
    private ListView<Pupil> pupilsList;
    private Label gradesListLabel;
    private ListView<Grade> gradesList;
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
        //instantiation
        this.instructions = new Label("Select a class, a subject and a calculation, then click on 'Calculate'."
                + " Click on 'Clear' to reset the text area.");
        this.classbooks = new ArrayList<>();
        this.classbookSelectionBox = new ComboBox<>();
        this.subjectSelectionBox = new ComboBox<>();
        this.calculationSelectionBox = new ComboBox<>();
        this.presentationLabel = new Label("Calculation view:");
        this.presentation = new Text();
        this.pupilsListLabel = new Label("Pupils in the selected class:");
        this.pupilsList = new ListView<>();
        this.gradesListLabel = new Label("Grades of the selected pupil:");
        this.gradesList = new ListView<>();
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

        //filling comboboxes
        this.classbookSelectionBox.setPromptText("Select a schoolclass");
        this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        this.subjectSelectionBox.setPromptText("Select a subject");
        this.subjectSelectionBox.setItems(FXCollections.observableArrayList(Subject.values()));
        this.calculationSelectionBox.setItems(FXCollections.observableArrayList("Average", "Accumulation"));
        this.calculationSelectionBox.setPromptText("Select a calculation");

        //listening for selection changes
        this.classbookSelectionBox.getSelectionModel().selectedItemProperty()
                .addListener((change, oldValue, newValue) -> {
                    ObservableList<Pupil> pupils = FXCollections
                            .observableArrayList(this.classbookSelectionBox
                                    .getSelectionModel().getSelectedItem()
                                    .getPupils());
                    this.pupilsList.setItems(pupils);
                });

        this.pupilsList.getSelectionModel().selectedItemProperty().addListener((change, oldValue, newValue) -> {
            try {
                this.gradesList.setItems(FXCollections
                        .observableArrayList(this.pupilsList
                                .getSelectionModel().getSelectedItem()
                                .getCertification().getGrades()));
            } catch (NullPointerException e) {
                this.gradesList.setItems(FXCollections.emptyObservableList());
            }
        });

        //text area meta data
        this.presentation.setTextOrigin(VPos.TOP);
//        this.presentation.setWrappingWidth(this.mainStage.getMaxWidth() / 20);

        //button functionality
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
                this.calculate(classbook, this.subjectSelectionBox.getSelectionModel().getSelectedItem());
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
                this.calculate(this.classbookSelectionBox.getSelectionModel().getSelectedItem(), value);
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
     * @param classbook Calculates for the summited classbook.
     * @param subject Calculates for the submitted subject.
     */
    private void calculate(Classbook classbook, Subject subject) {
        switch (this.calculationSelectionBox.getSelectionModel().getSelectedItem()) {
            case "Average":
                this.calculatedGrade = Calculator
                        .calculateAverageGrade(classbook, subject);
                break;
            case "Accumulation":
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
     * @param stage Stage to be closed.
     */
    private void close(Stage stage) {
        if (new ConfirmationStage("Do you really want to close the programm?").display()) {
            stage.close();
        }
    }

}
