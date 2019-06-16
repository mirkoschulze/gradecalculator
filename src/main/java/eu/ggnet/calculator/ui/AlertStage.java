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
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class AlertStage {

    private final Label warning;
    private final Label message;

    public AlertStage(String message) {
        this.warning = new Label("Something went wrong.\nBut don't worry.");
        this.message = new Label(message);
    }

    public void display() {
        Stage primaryStage = new Stage(StageStyle.DECORATED);
        primaryStage.setTitle("Alert!");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(this.warning, this.message, closeButton);

        Scene scene = new Scene(vbox, 500, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
