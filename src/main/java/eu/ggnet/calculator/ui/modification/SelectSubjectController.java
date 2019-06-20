package eu.ggnet.calculator.ui.modification;

import eu.ggnet.calculator.model.Grade.Subject;
import eu.ggnet.calculator.ui.AlertStage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lombok.Getter;

/**
 *
 * @author Mirko Schulze
 */
public class SelectSubjectController implements Initializable{
    
    @Getter
    private Subject subject;
    @FXML
    private ComboBox<Subject> subjectSelectionBox;
    
    @FXML
    private Button selectButton;
    @FXML
    private Button cancelButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.subjectSelectionBox.setItems(FXCollections.observableArrayList(Subject.values()));
    }
    
    @FXML
    private void selectSubject(){
        try {
            this.subject = this.subjectSelectionBox.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) this.selectButton.getScene().getWindow();
            stage.close();
        } catch (NullPointerException e) {
            new AlertStage("No subject selected.\nError:\n" + e.getMessage()).warn();
        }
    }
    
    /**
     * Closes the root {@link Stage}.
     */
    @FXML
    private void cancel(){
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
            stage.close();
    }
    
}

    
