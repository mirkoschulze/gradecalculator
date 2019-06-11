/*
*
* by Mirko Schulze
 */
package eu.ggnet.calculatorui.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class ConfirmationStage {

    private final Label stageMessage;
    private boolean answer;

    public ConfirmationStage(String stageMessage) {
        this.stageMessage = new Label(stageMessage);
    }

    public boolean display() {
        Stage primaryStage = new Stage(StageStyle.DECORATED);
        primaryStage.setTitle("Confirmation");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            this.answer = true;
            primaryStage.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            this.answer = false;
            primaryStage.close();
        });

        HBox hbox = new HBox(25);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(yesButton, noButton);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setTop(this.stageMessage);
        borderPane.setCenter(hbox);

        Scene scene = new Scene(borderPane, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.showAndWait();

        return this.answer;
    }

}
