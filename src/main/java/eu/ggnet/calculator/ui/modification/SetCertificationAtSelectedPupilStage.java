package eu.ggnet.calculator.ui.modification;

import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.model.Pupil;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * {@link Stage} to set the {@link Certification} of a {@link Pupil}.
 *
 * @author Mirko Schulze
 */
public class SetCertificationAtSelectedPupilStage {

    private static final String FXML_URL = "/fxml/SetCertificationAtSelectedPupilScene.fxml";

    private final Stage stage;

    public SetCertificationAtSelectedPupilStage() {
        this.stage = new Stage();
    }

    /**
     * Tries to display a new {@link Stage} with {@link Label},
     * {@link TextField} and {@link Button} components to create a new
     * {@link Certification}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Certification
     */
    public Certification createCertification() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            SetCertificationAtSelectedPupilController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return controller.getCertification();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nException:\n" + e.getMessage()).warn();
            return null;
        }

    }

}
