package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Pupil;
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
public class InsertPupilStage {

    private static final String LOCATION = "/fxml/InsertPupilScene.fxml";

    private final Stage primaryStage;

    public InsertPupilStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Add a pupil");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);
    }

    public Pupil display() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOCATION));
            Parent root = loader.load();
            InsertPupilController controller = loader.getController();

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.showAndWait();

            return controller.getPupil();
        } catch (IOException e) {
             new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: " 
                    + LOCATION + "\nException:\n" + e.getMessage()).display();
            return null;
        }

    }

}
