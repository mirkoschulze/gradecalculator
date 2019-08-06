package eu.ggnet.gradecalculator.ui;

import eu.ggnet.gradecalculator.Utilities;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Main class with start() as starting point and main() as fallback.
 * <p>
 * Displays a {@link Stage} to interact with an user.
 *
 * @author Mirko Schulze
 */
public class MainStage extends Application {

    private static final String FXML_PATH = "/fxml/MainScene.fxml";
    private static final String CSS_PATH = "/css/mainScene.css";

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Tries to show the primary application Stage.
     * <p>
     * Catches an {@link IOException} or {@link IllegalStateException} by
     * displaying a new {@link Alert} with a respective error message.
     *
     * @param stage the main application Stage
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setScene(new Scene(new FXMLLoader(getClass().getResource(FXML_PATH)).load()));
//            stage.getScene().getStylesheets().add(CSS_PATH);
            stage.show();
        } catch (IOException | IllegalStateException e) {
            Utilities.alertError("Der FXMLLoader konnte den Objektgraphen nicht korrekt laden."
                    + "\n\nÜberprüfe folgende Ressource:\n"
                    + FXML_PATH + "\n\nException:\n" + e.getMessage());
        }

    }

}
