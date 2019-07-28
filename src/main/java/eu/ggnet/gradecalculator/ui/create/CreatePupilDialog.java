package eu.ggnet.gradecalculator.ui.create;

import eu.ggnet.gradecalculator.Utilities;
import eu.ggnet.gradecalculator.model.Pupil;
import eu.ggnet.gradecalculator.model.Pupil.Sex;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * Invokes a specified Dialog pane with {@link Label} and {@link TextField}
 * components to create a new Pupil.
 * <p>
 * The created Pupil is the return value of the constructor, if valid values are
 * entered and the finish button is clicked, else the return value is null.
 * <p>
 * This can be used by calling the {@link Dialog#showAndWait() } method.
 *
 * @author Mirko Schulze
 */
public class CreatePupilDialog extends Dialog<Pupil> {

    String forename, surname;
    int age;
    TextField forenameInput, surnameInput, ageInput;
    ComboBox<Sex> selectSexBox;

    public CreatePupilDialog() {
        this.setTitle("Schüler hinzufügen");
        this.setHeaderText("Tragen Sie für alle Felder Werte ein,\num einen neuen Schüler zu einer Klasse hinzuzufügen.");

        forenameInput = new TextField();
        forenameInput.setPromptText("Max");
        forenameInput.setTooltip(new Tooltip("Trage einen Vornamen ein, wie z.b. \"Maria\""));

        surnameInput = new TextField();
        surnameInput.setPromptText("Mustermann");
        surnameInput.setTooltip(new Tooltip("Trage einen Nachnamen ein, wie z.B. \"Musterfrau\""));

        ageInput = new TextField();
        ageInput.setPromptText("23");
        ageInput.setTooltip(new Tooltip("Trage ein gültiges Alter in Jahren ein, wie z.B. \"100\""));

        selectSexBox = new ComboBox<>();
        selectSexBox.setItems(FXCollections.observableArrayList(Arrays.asList(Sex.values())));
        selectSexBox.setValue(Sex.MALE);
        selectSexBox.setTooltip(new Tooltip("Wähle ein Geschlecht aus"));

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5));
        grid.addColumn(0, new Label("Vorname: "));
        grid.addColumn(0, new Label("Nachname: "));
        grid.addColumn(0, new Label("Alter: "));
        grid.addColumn(0, new Label("Geschlecht: "));
        grid.addColumn(1, forenameInput);
        grid.addColumn(1, surnameInput);
        grid.addColumn(1, ageInput);
        grid.addColumn(1, selectSexBox);

        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        Button finishButton = (Button) this.getDialogPane().lookupButton(ButtonType.FINISH);
        finishButton.addEventFilter(ActionEvent.ACTION, eh -> {
            if (this.forenameInput.getText().isEmpty()
                    | this.surnameInput.getText().isEmpty()
                    | this.ageInput.getText().isEmpty()) {
                eh.consume();
                Utilities.alertWarn("Bitte füllen Sie alle Felder korrekt aus.");
            } else {
                String name = this.forenameInput.getText().trim().toLowerCase().replaceAll("\\W|\\d", "");
                this.forename = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                name = this.surnameInput.getText().trim().toLowerCase().replaceAll("\\W|\\d", "");
                this.surname = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                try {
                    this.age = Integer.parseInt(this.ageInput.getText().trim());
                } catch (NumberFormatException e) {
                    Utilities.alertWarn("Bitte geben Sie ein Alter in Ganzzahlen an.");
                    eh.consume();
                }
            }
        });

        this.setResultConverter(type -> {
            if (type == ButtonType.FINISH) {
                return new Pupil(this.selectSexBox.getSelectionModel().getSelectedItem(),
                        this.age, this.forename, this.surname);
            } else {
                return null;
            }
        });

        this.getDialogPane().setContent(grid);
    }

}
