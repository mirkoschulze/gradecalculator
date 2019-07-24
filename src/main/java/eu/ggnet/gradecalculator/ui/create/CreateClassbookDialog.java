package eu.ggnet.gradecalculator.ui.create;

import eu.ggnet.gradecalculator.model.Classbook;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Displays a {@link Stage} to add a new {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class CreateClassbookDialog extends Dialog<Classbook> {

    public CreateClassbookDialog() {
        TextField titleInput = new TextField();
        titleInput.setTooltip(new Tooltip("Tragen Sie einen Namen wie z.B. \"IT 8i\" ein."));

        HBox hbox = new HBox(5, new Label("Klassenname: "), titleInput);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(5));
        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        this.setResultConverter(type -> {
            if (type == ButtonType.FINISH) {
                return new Classbook(titleInput.getText(), new ArrayList<>());
            } else {
                return null;
            }
        });
        this.setHeaderText("Tragen Sie einen Namen für die Schulklasse ein.");
        this.getDialogPane().setContent(hbox);
    }

}
