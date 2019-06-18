package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Certification;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class GradePupilStage {

    private static final String LOCATION = "/fxml/GradePupilScene.fxml";

    private final Stage primaryStage;

    public GradePupilStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Create a certification");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);
    }

    public Certification display() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOCATION));
            Parent root = loader.load();
            GradePupilController controller = loader.getController();

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.showAndWait();

            return controller.getCertification();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + LOCATION + "\nException:\n" + e.getMessage()).display();
            return null;
        }

    }

}
