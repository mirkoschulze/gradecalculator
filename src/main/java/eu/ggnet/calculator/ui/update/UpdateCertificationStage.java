package eu.ggnet.calculator.ui.update;

import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Displays a {@link Stage} to set the {@link Certification} at a {@link Pupil}.
 *
 * @author Mirko Schulze
 */
public class UpdateCertificationStage {

    private static final String FXML_PATH = "/fxml/UpdateCertificationScene.fxml";

    private final Stage stage;

    public UpdateCertificationStage() {
        this.stage = new Stage();
    }

    /**
     * Tries to display a new {@link Stage} to create a new
     * {@link Certification}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Certification
     */
    public Optional<Certification> createCertification() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            UpdateCertificationController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return Optional.ofNullable(controller.getCertification());
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value at: "
                    + FXML_PATH + "\n\nException message:\n" + e.getMessage()).warn();
            return Optional.empty();
        }
    }

}
