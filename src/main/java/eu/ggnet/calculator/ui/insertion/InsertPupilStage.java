package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * {@link Stage} to add a new {link Pupil} to a selected {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class InsertPupilStage {

    private static final String FXML_URL = "/fxml/InsertPupilScene.fxml";

    private final Stage stage;

    public InsertPupilStage() {
        this.stage = new Stage();
        this.stage.setTitle("Add a pupil");
    }

    /**
     * Tries to display a new {@link Stage} with
     * {@link TextField}, {@link ComboBox} and {@link Button} components to
     * create a new {@link Pupil}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Pupil
     */
    public Pupil createPupil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            InsertPupilController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return controller.getPupil();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nException:\n" + e.getMessage()).warn();
            return null;
        }

    }

}
