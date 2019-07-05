package eu.ggnet.calculator.ui.update;

import eu.ggnet.calculator.model.Grade;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * {@link Stage} to set a new {@link Grade} at a {@link Pupil}.
 *
 * @author Mirko Schulze
 */
public class UpdateGradeStage {

    private static final String FXML_PATH = "/fxml/UpdateGradeScene.fxml";

    private final Stage stage;

    public UpdateGradeStage() {
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Tries to display a new {@link Stage} to create a new {@link Grade}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Grade
     */
    public Grade updateGrade() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            UpdateGradeController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return controller.getGrade();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_PATH + "\n\nException message:\n" + e.getMessage()).warn();
            return null;
        }
    }

}
