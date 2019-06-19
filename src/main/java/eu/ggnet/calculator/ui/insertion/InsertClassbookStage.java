package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * {@link Stage} to add a new {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class InsertClassbookStage {

    private static final String FXML_URL = "/fxml/InsertClassbookScene.fxml";

    private final Stage primaryStage;

    public InsertClassbookStage() {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Add a classbook");
        this.primaryStage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Tries to display a new {@link Stage} with {@link TextField} and
     * {@link Button} components to create a new {@link Classbook}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Classbook
     */
    public Classbook display() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            InsertClassbookController controller = loader.getController();

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.showAndWait();

            return controller.getClassbook();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nException:\n" + e.getMessage()).display();
            return null;
        }
    }

}
