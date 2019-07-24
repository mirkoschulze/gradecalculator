package eu.ggnet.gradecalculator.ui;

import eu.ggnet.gradecalculator.Utilities;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class with start() as starting point and main() as fallback.
 * <p>
 * Displays a {@link Stage} to interact with an user.
 *
 * @author Mirko Schulze
 */
public class MainStage extends Application {

    //TODO - komplette javadoc: param & return  < = &lt;, > = &gt;, https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#
    //TODO - tooltips für hinweise
    //TODO - stages in dialoge umwandeln (TextDialog usw.)
    //TODO - reconsider Alert
    //TODO - Regex für die validierung hinterlegen
    //TODO - vorgefertigte ButtonTypes verwenden
    //TODO - threads, Platform. ... 
    //TODO - css
    
    private static final String FXML_PATH = "/fxml/MainScene.fxml";

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
     * Tries to show the primary application {@link Stage}.
     * <p>
     * Catches an {@link IOException} by displaying a new {@link AlertStage}
     * with a respective error message.
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setScene(new Scene(new FXMLLoader(getClass().getResource(FXML_PATH)).load()));
            stage.show();
        } catch (IOException e) {
            Utilities.alertWarn("FXMLoader could not properly load opject graph.\nCheck value: "
                    + FXML_PATH + "\nException message:\n" + e.getMessage());
        }

    }

}