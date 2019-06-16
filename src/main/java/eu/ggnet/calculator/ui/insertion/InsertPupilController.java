package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.model.Pupil.Sex;
import eu.ggnet.calculator.ui.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class InsertPupilController implements Initializable {

    @Inject
    private MainController controller;

    private String forename;
    private String surname;
    private int age;
    private Sex sex;
    public Pupil pupil;

    @FXML
    private TextField forenameInput;
    @FXML
    private TextField surnameInput;
    @FXML
    private TextField ageInput;
    @FXML
    private ComboBox<Sex> selectSexBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selectSexBox.setItems(FXCollections.observableArrayList(Sex.values()));
        this.selectSexBox.setPromptText("Select a sex...");
    }

    @FXML
    private void add() {
        if (this.validateInput()) {
            this.takeInput();
            this.pupil = new Pupil(this.sex, this.age, this.forename, this.forename);
            this.controller.getClassbookSelectionBox().getSelectionModel().getSelectedItem().getPupils().add(this.pupil);
        }
        this.forenameInput.clear();
        this.surnameInput.clear();
        this.ageInput.clear();
        this.selectSexBox.setPromptText("Select a sex...");
    }

    @FXML
    private void cancel() {
        this.forenameInput.clear();
        this.surnameInput.clear();
        this.ageInput.clear();
        this.selectSexBox.setPromptText("Select a sex...");
    }

    private void takeInput() {
        this.forename = this.forenameInput.getText();
        this.surname = this.surnameInput.getText();
        this.age = Integer.parseInt(this.ageInput.getText());
        this.sex = this.selectSexBox.getSelectionModel().getSelectedItem();
    }

    private boolean validateInput() {
        return (this.forenameInput.getText() != null
                && !this.forenameInput.getText().isEmpty()
                && this.surnameInput.getText() != null
                && !this.surnameInput.getText().isEmpty()
                && this.ageInput.getText() != null
                && !(this.ageInput.getText().isEmpty()));

    }

}
