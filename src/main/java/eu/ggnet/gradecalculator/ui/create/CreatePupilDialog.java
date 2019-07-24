package eu.ggnet.gradecalculator.ui.create;

import eu.ggnet.gradecalculator.model.Classbook;
import eu.ggnet.gradecalculator.model.Pupil;
import eu.ggnet.gradecalculator.model.Pupil.Sex;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Displays a {@link Stage} to add a new {link Pupil} to a {@link Classbook}.
 *
 * @author Mirko Schulze
 */
public class CreatePupilDialog extends Dialog<Pupil> {

    public CreatePupilDialog() {
        TextField forenameInput = new TextField("Max");
        TextField surnameInput = new TextField("Mustermann");
        TextField ageInput = new TextField("23");
        ComboBox<Sex> selectSexBox = new ComboBox<>();
        selectSexBox.setItems(FXCollections.observableArrayList(Arrays.asList(Sex.values())));
        selectSexBox.setValue(Sex.MALE);

        GridPane grid = new GridPane();
        grid.addColumn(0, new Label("Vorname: "));
        grid.addColumn(0, new Label("Nachname: "));
        grid.addColumn(0, new Label("Alter: "));
        grid.addColumn(0, new Label("Geschlecht: "));

        grid.addColumn(1, forenameInput);
        grid.addColumn(1, surnameInput);
        grid.addColumn(1, ageInput);
        grid.addColumn(1, selectSexBox);

        grid.setHgap(5);
        grid.setVgap(5);

        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        this.setResultConverter(type -> {
            if (type == ButtonType.FINISH) {
                return new Pupil(selectSexBox.getValue(), Integer.parseInt(ageInput.getText()), forenameInput.getText(), surnameInput.getText());
            } else {
                return null;
            }
        });
        this.setHeaderText("Tragen Sie für alle Felder Werte ein, um einen neuen Schüler zu einer Klasse hinzuzufügen.");
        this.getDialogPane().setContent(grid);
    }

}
