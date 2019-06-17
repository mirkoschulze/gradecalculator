/*
*
* by Mirko Schulze
 */
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

    private static final Label WARNING = new Label("Something went wrong.\nBut don't worry.");
    private final Label message;

    public AlertStage(String message) {
        this.message = new Label(message);
    }

    /**
     * Displays a new {@link Stage} with two {@link Label} to show details about
     * a failure and a {@link Button} to close the Stage again.
     */
    public void display() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Alert!");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(WARNING, this.message, closeButton);

        Scene scene = new Scene(vbox, 500, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
