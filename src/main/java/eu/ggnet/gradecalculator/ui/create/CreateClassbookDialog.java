package eu.ggnet.gradecalculator.ui.create;

import eu.ggnet.gradecalculator.model.Classbook;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

/**
 * Invokes a specified {@link Dialog} pane with a {@link Label} and a
 * {@link TextField} to create a new {@link Classbook}.
 * <p>
 * The created Classbook is the return value of the constructor, if a title is
 * entered and the finish button is clicked, else the return value is null.
 * <p>
 * This can be used by calling the {@link Dialog#showAndWait() } method.
 *
 * @author Mirko Schulze
 */
public class CreateClassbookDialog extends Dialog<Classbook> {

    public CreateClassbookDialog() {
        this.setTitle("Klasse hinzfügen");
        this.setHeaderText("Tragen Sie einen Namen für die Schulklasse ein.");
        TextField titleInput = new TextField();
        titleInput.setPromptText("IT 8i");
        titleInput.setTooltip(new Tooltip("Tragen Sie einen Namen wie z.B. \"IT 8i\" ein."));

        HBox hbox = new HBox(5, new Label("Klassenname: "), titleInput);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));

        this.getDialogPane().setContent(hbox);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        Button finishButton = (Button) this.getDialogPane().lookupButton(ButtonType.FINISH);
        finishButton.addEventFilter(ActionEvent.ACTION, eh -> {
            if (titleInput.getText().isEmpty()) {
                eh.consume();
            }
        });
        this.setResultConverter(type -> {
            return (type == ButtonType.FINISH) ? new Classbook(titleInput.getText(), new ArrayList<>()) : null;
        });

    }

}
