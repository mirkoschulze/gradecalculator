package eu.ggnet.calculator.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Invokes a new {@link Stage} to indicate a failure.
 * <p>
 * The AlertStage is kept as simple as possible to minimize possible failures
 * within this class.
 *
 * @author Mirko Schulze
 */
public class AlertStage {

    private static final Label WARNING = new Label("Something went wrong.\nBut don't worry. It's your fault, probably.");
    private final Label message;

    public AlertStage(String message) {
        this.message = new Label(message);
    }

    /**
     * Displays a new {@link Stage} with {@link Label} and {@link Button}
     * components to indicate failures.
     */
    public void display() {
        Stage stage = new Stage();
        stage.setTitle("Alert!");
        stage.initModality(Modality.APPLICATION_MODAL);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(WARNING, this.message, closeButton);

        Scene scene = new Scene(vbox, 500, 300);

        stage.setScene(scene);
        stage.show();
    }

}
