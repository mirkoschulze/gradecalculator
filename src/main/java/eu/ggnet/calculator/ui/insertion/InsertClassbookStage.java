package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
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
public class InsertClassbookStage {

    private static final String LOCATION = "/fxml/InsertClassbookScene.fxml";

    private final Stage primaryStage;

    public InsertClassbookStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Add a classbook");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);
    }

    public Classbook display() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(LOCATION));
            Parent root = loader.load();
            InsertClassbookController controller = loader.getController();

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.showAndWait();

            return controller.getClassbook();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + LOCATION + "\nException:\n" + e.getMessage()).display();
            return null;
        }
    }

}
