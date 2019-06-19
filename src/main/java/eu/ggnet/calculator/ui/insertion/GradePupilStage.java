package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Certification;
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
public class GradePupilStage {

    private static final String FXML_URL = "/fxml/GradePupilScene.fxml";

    private final Stage primaryStage;

    public GradePupilStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Create a certification for the selected pupil");
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
    public Certification display() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            GradePupilController controller = loader.getController();

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.showAndWait();

            return controller.getCertification();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nException:\n" + e.getMessage()).display();
            return null;
        }

    }

}
