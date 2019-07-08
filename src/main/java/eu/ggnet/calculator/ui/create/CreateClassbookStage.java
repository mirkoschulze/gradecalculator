package eu.ggnet.calculator.ui.create;

import eu.ggnet.calculator.model.Classbook;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays a {@link Stage} to add a new {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class CreateClassbookStage {

    private static final String FXML_PATH = "/fxml/CreateClassbookScene.fxml";

    private final Stage stage;

    public CreateClassbookStage() {
        this.stage = new Stage();
        this.stage.setTitle("Add a classbook");
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Tries to display a new {@link Stage} to create a new {@link Classbook}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @return Classbook
     */
    public Optional<Classbook> createClassbook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            CreateClassbookController controller = loader.getController();

            this.stage.setScene(new Scene(root));
            this.stage.showAndWait();

            return Optional.ofNullable(controller.getClassbook());
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value at: "
                    + FXML_PATH + "\n\nException message:\n" + e.getMessage()).warn();
            return Optional.empty();
        }
    }

}
