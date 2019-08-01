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
import eu.ggnet.gradecalculator.ui.create.CreateClassbookDialog;
import eu.ggnet.gradecalculator.ui.create.CreatePupilDialog;
import eu.ggnet.gradecalculator.ui.update.UpdateCertificationDialog;
import eu.ggnet.gradecalculator.ui.update.UpdateGradeDialog;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    /**
     * The {@link Classbook} selected via {@link #classbookSelectionBox}.
     */
    private Classbook selectedClassbook;

    /**
     * {@link Subject} selected via {@link #subjectSelectionBox}.
     */
    private Subject selectedSubject;

    /**
     * Method to calculate selected via {@link #calculationSelectionBox}.
     */
    private String selectedCalculation;

    /**
     * Result of a calculation.
     */
    private CalculatedGrade calculatedGrade;

    /**
     * {@link ComboBox} to select a {@link Classbook} to do operations on that
     * Classbook.
     */
    @FXML
    private ComboBox<Classbook> classbookSelectionBox;

    /**
     * {@link ComboBox} to select a {@link Subject} to do calculations.
     */
    @FXML
    private ComboBox<Subject> subjectSelectionBox;

    /**
     * {@link ComboBox} to select a method to calculate a
     * {@link CalculatedGrade}.
     */
    @FXML
    private ComboBox<String> calculationSelectionBox;

    /**
     * {@link TextArea} to present the results of calculations.
     */
    @FXML
    private TextArea presentation;

    /**
     * {@link ListView}&lt;{@link Pupil}&gt; to display information about
     * {@link #selectedClassbook} and to select a Pupil to do operations on that
     * Pupil.
     */
    @FXML
    private ListView<Pupil> pupilsListView;

    /**
     * {@link ListView}&lt;{@link Grade}&gt; to display the Grades of the
     * {@link Pupil} selected via {@link #pupilsListView}.
     */
    @FXML
    private ListView<Grade> gradesListView;

    /**
     * {@link Button} to close the main {@link Stage} and the entire
     * application.
     */
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller in the following steps:
     * <ul><li>auto-generation of 5 instances of {@link Classbook} to simulate,
     * with already set {@link Certification}</li>
     * <li>adding {@link ObservableList} to {@link ComboBox} components</li>
     * <li>adding {@link ChangeListener} to lists</li></ul>
     *
     * @param url URL - is not used
     * @param rb ResourceBundle - is not used
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

        this.calculationSelectionBox.setItems(FXCollections.observableArrayList("Durchschnittliche Note", "Häufigste Note"));
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
     * Checks {@link #calculationSelectionBox} for the selected calculation and
     * calls the respective method from {@link Calculator} to generate a
     * {@link CalculatedGrade} for the submitted {@link Classbook} and
     * {@link Subject}.
     *
     * @param classbook Classbook - {@link Classbook} for which the
     * {@link Grade} shall be calculated
     * @param subject Subject - {@link Subject} for which the {@link Grade}
     * shall be calculated
     */
    private void calculate(Classbook classbook, Subject subject) {
        switch (this.selectedCalculation.toLowerCase()) {
            case "durchschnittliche note":
                this.calculatedGrade = Calculator.calculateAverageGrade(classbook, subject);
                break;
            case "häufigste note":
                this.calculatedGrade = Calculator.calculateAccumulatedGrade(classbook, subject);
                break;
        }
    }

    /**
     * Adds a String representation of a {@link CalculatedGrade} to
     * {@link #presentation}.
     */
    private void present() {
        StringBuilder sb = new StringBuilder(this.presentation.getText());
        sb.append(this.calculatedGrade.toSimpleLine());
        this.presentation.setText(new String(sb).concat("\n"));
    }

    /**
     * If all {@link #classbookSelectionBox}, {@link #subjectSelectionBox} and
     * {@link #calculationSelectionBox} are selected:
     * <ul><li>generates a {@link CalculatedGrade} to set
     * {@link #calculatedGrade}</li>
     * <li>the CalculatedGrade is added to {@link #presentation}</li></ul>
     *
     * @see #calculate(eu.ggnet.gradecalculator.model.Classbook,
     * eu.ggnet.gradecalculator.model.Grade.Subject)
     * @see #present()
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
     * If {@link #calculationSelectionBox} and {@link #subjectSelectionBox} are
     * selected:
     * <p>
     * For each {@link Classbook}:
     * <ul><li>generates a {@link CalculatedGrade} to set
     * {@link #calculatedGrade}</li>
     * <li>the CalculatedGrade is added to {@link #presentation}</li></ul>
     *
     * @see #calculate(eu.ggnet.gradecalculator.model.Classbook,
     * eu.ggnet.gradecalculator.model.Grade.Subject)
     * @see #present()
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
     * If {@link #calculationSelectionBox} and {@link #calculationSelectionBox}
     * are selected:
     * <p>
     * For each {@link Subject}:
     * <ul><li>generates a {@link CalculatedGrade} to set
     * {@link #calculatedGrade}</li>
     * <li>the CalculatedGrade is added to {@link #presentation}</li></ul>
     *
     * @see #calculate(eu.ggnet.gradecalculator.model.Classbook,
     * eu.ggnet.gradecalculator.model.Grade.Subject)
     * @see #present()
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
     * Resets {@link #presentation}.
     */
    @FXML
    private void clear() {
        this.presentation.setText("");
    }

    /**
     * Displays a new {@link CreateClassbookDialog} to create a new
     * {@link Classbook}.
     * <p>
     * If a Classbook is returned, that Classbook is added to the available
     * Classbooks.
     */
    @FXML
    private void addClassbook() {
        Optional<Classbook> classbook = new CreateClassbookDialog().showAndWait();
        if (classbook.isPresent()) {
            this.classbooks.add(classbook.get());
            this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
        }
    }

    /**
     * Tries to remove the selected {@link Classbook} from the list of available
     * Classbooks.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new {@link Alert}
     * with a respective message.
     */
    @FXML
    private void removeClassbook() {
        try {
            this.classbooks.remove(this.selectedClassbook);
            this.classbookSelectionBox.setItems(FXCollections.observableArrayList(this.classbooks));
            this.pupilsListView.setItems(FXCollections.emptyObservableList());
        } catch (NullPointerException e) {
            Utilities.alertWarn("Bitte wählen Sie die zu entfernende Klasse aus.\n\n"
                    + "Fehlermeldung:\n" + e.getMessage());
        }
    }

    /**
     * Tries to display a new {@link CreatePupilDialog} to create a new
     * {@link Pupil} for a {@link Classbook}.
     * <p>
     * If a Pupil is returned, that Pupil is added to the selected Classbook.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new {@link Alert}
     * with a respective message.
     */
    @FXML
    private void addPupil() {
        Optional<Pupil> pupil = new CreatePupilDialog().showAndWait();
        try {
            if (pupil.isPresent()) {
                this.selectedClassbook.getPupils().add(pupil.get());
                this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
            }
        } catch (NullPointerException e) {
            Utilities.alertWarn("Bitte wählen Sie eine Klasse aus, um einen Schüler hinzuzufügen.\n\n"
                    + "Fehlermeldung:\n" + e.getMessage());
        }

    }

    /**
     * Tries to remove the selected {@link Pupil} from the selected
     * {@link Classbook}.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new {@link Alert}
     * with a respective message.
     */
    @FXML
    private void removePupil() {
        try {
            this.selectedClassbook.getPupils().remove(this.pupilsListView.getSelectionModel().getSelectedItem());
            this.pupilsListView.setItems(FXCollections.observableArrayList(this.selectedClassbook.getPupils()));
        } catch (NullPointerException e) {
            Utilities.alertWarn("Bitte wählen Sie den zu entfernenden Schüler aus."
                    + "\n\nFehlermeldung:\n" + e.getMessage());
        }

    }

    /**
     * Tries to display a new {@link UpdateCertificationDialog} to create a new
     * {@link Certification} at a {@link Pupil}.
     * <p>
     * If a Certification is returned, the Certification is set at the selected
     * Pupil.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new {@link Alert}
     * with a respective message.
     */
    @FXML
    private void setCertificationAtSelectedPupil() {
        try {
            Optional<Certification> certification = new UpdateCertificationDialog().showAndWait();
            if (certification.isPresent()) {
                this.pupilsListView.getSelectionModel().getSelectedItem().setCertification(certification.get());
                this.gradesListView.setItems(FXCollections
                        .observableArrayList(this.pupilsListView.getSelectionModel()
                                .getSelectedItem().getCertification().getGrades()));
            }
        } catch (NullPointerException e) {
            Utilities.alertWarn("Bitte wählen Sie den Schüler aus, der ein Zeugnis erhalten soll."
                    + "\n\nFehlermeldung:\n" + e.getMessage());
        }

    }

    /**
     * Tries to display a new {@link UpdateGradeDialog} to create a new
     * {@link Grade} for a {@link Certification} at a {@link Pupil}.
     * <p>
     * If a Grade is returned, a new Certification with the old values and the
     * newly created Grade is set at the selected Pupil.
     * <p>
     * Catches a {@link NullPointerException} by displaying a new {@link Alert}
     * with a respective message.
     */
    @FXML
    private void setGradeAtSelectedPupil() {
        try {
            Pupil pupil = this.pupilsListView.getSelectionModel().getSelectedItem();
            List<Grade> grades = pupil.getCertification().getGrades();
            Optional<Grade> grade = new UpdateGradeDialog().showAndWait();
            if (grade.isPresent()) {
                grades.set(grade.get().getSubject().ordinal(), grade.get());
                pupil.setCertification(new Certification(pupil, grades));
                this.gradesListView.setItems(FXCollections
                        .observableArrayList(this.pupilsListView.getSelectionModel()
                                .getSelectedItem().getCertification().getGrades()));
            }
        } catch (NullPointerException e) {
            Utilities.alertWarn("Bitte wählen Sie den Schüler aus, dessen Note Sie bearbeiten möchten."
                    + "\n\nFehlermeldung:\n" + e.getMessage());
        }

    }

    /**
     * Displays a new {@link Alert} to confirm the close request. Closes the
     * main {@link Stage} and the entire application if the event is confirmed.
     */
    @FXML
    private void close() {
        if (new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie die Anwendung wirklich beenden?")
                .showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) this.closeButton.getScene().getWindow();
            stage.close();
        }
    }

}
