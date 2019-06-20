package eu.ggnet.calculator.ui.modification;

import eu.ggnet.calculator.model.Grade.Subject;
import eu.ggnet.calculator.ui.AlertStage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mirko Schulze
 */
public class SelectSubjectStage {

    private static final String FXML_URL = "/fxml/SelectSubjectScene.fxml";

    private final Stage stage;

    public SelectSubjectStage() {
        this.stage = new Stage();
        this.stage.setTitle("1: select a subject");
        this.stage.initModality(Modality.APPLICATION_MODAL);
    }

    public Subject selectSubject() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
            Parent root = loader.load();
            SelectSubjectController controller = loader.getController();

            stage.setScene(new Scene(root));
            stage.showAndWait();

            return controller.getSubject();
        } catch (IOException e) {
            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_URL + "\nError:" + e.getMessage()).warn();
            return null;
        }
    }

}
