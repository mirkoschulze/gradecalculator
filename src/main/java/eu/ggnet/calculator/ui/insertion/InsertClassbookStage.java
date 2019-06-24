package eu.ggnet.calculator.ui.insertion;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * {@link Stage} to add a new {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class InsertClassbookStage {

    private static final String FXML_URL = "/fxml/InsertClassbookScene.fxml";

    private final Stage stage;

    public InsertClassbookStage() {
        this.stage = new Stage();
        this.stage.setTitle("Add a classbook");
    }

    /**
     * Tries to display a new {@link Stage} to create a new {@link Classbook}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Classbook
     */
    public Classbook createClassbook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            InsertClassbookController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return controller.getClassbook();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nException:\n" + e.getMessage()).warn();
            return null;
        }
    }

}
