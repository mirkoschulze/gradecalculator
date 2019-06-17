package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Pupil;
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

/**
 *
 * @author Administrator
 */
@Getter
public class InsertClassbookController implements Initializable {

    private List<Pupil> pupils;
    private Classbook classbook;

    @FXML
    private TextField nameInput;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.pupils = new ArrayList<>();
    }

    @FXML
    private void add() {
        this.classbook = new Classbook(this.nameInput.getText(), this.pupils);
        Stage stage = (Stage) this.addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel() {
        this.nameInput.clear();
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }
}
