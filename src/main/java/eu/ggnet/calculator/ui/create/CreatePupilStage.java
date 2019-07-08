package eu.ggnet.calculator.ui.create;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays a {@link Stage} to add a new {link Pupil} to a {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class CreatePupilStage {

    private static final String FXML_URL = "/fxml/CreatePupilScene.fxml";

    private final Stage stage;

    public CreatePupilStage() {
        this.stage = new Stage();
        this.stage.setTitle("Add a pupil");
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Tries to display a new {@link Stage} to create a new {@link Pupil}.
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
            CreatePupilController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return controller.getPupil();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value at: "
                    + FXML_URL + "\n\nException message:\n" + e.getMessage()).warn();
            return null;
        }

    }

}
