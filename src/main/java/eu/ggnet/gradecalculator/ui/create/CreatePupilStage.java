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
public class CreatePupilStage extends Dialog<Pupil> {
//
//    private static final String FXML_URL = "/fxml/CreatePupilScene.fxml";
//      https://code.makery.ch/blog/javafx-dialogs-official/  Dialog
//    private final Stage stage;

    private TextField forenameInput = new TextField();
    private TextField surnameInput = new TextField();
    private TextField ageInput = new TextField();
    private ComboBox<Sex> selectSexBox = new ComboBox<Sex>();

    public CreatePupilStage() {
//        this.stage = new Stage();
//        this.stage.setTitle("Add a pupil");
//        this.stage.initModality(Modality.APPLICATION_MODAL);

        selectSexBox.setItems(FXCollections.observableArrayList(Arrays.asList(Sex.values())));

        GridPane grid = new GridPane();
        grid.addColumn(0, new Label("Vorname"));
        grid.addColumn(0, new Label("Vorname"));
        grid.addColumn(0, new Label("Vorname"));
        grid.addColumn(0, new Label("Vorname"));

        grid.addColumn(1, forenameInput);
        grid.addColumn(1, surnameInput);
        grid.addColumn(1, ageInput);
        grid.addColumn(1, selectSexBox);

        this.getDialogPane().getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
        this.setResultConverter((type) -> {
            if (type == ButtonType.FINISH) {
                return new Pupil(selectSexBox.getValue(), Integer.parseInt(ageInput.getText()), forenameInput.getText(), surnameInput.getText());
            } else {
                return null;
            }
        });
        this.setHeaderText("Create Pupil");
        this.getDialogPane().setContent(grid);
    }

//
//    /**
//     * Tries to display a new {@link Stage} to create a new {@link Pupil}.
//     * <p>
//     * Catches an {@link IOException} by displaying a new {@link AlertStage}
//     * with a respective error message.
//     *
//     * @return Pupil
//     */
//    public Optional<Pupil> createPupil() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_URL));
//            Parent root = loader.load();
//            CreatePupilController controller = loader.getController();
//
//            this.stage.setScene(new Scene(root));
//            this.stage.showAndWait();
//
//            return Optional.ofNullable(controller.getPupil());
//        } catch (IOException e) {
//            new AlertStage("FXMLoader could not properly load opject graph.\nCheck value at: "
//                    + FXML_URL + "\n\nException message:\n" + e.getMessage()).warn();
//            return Optional.empty();
//        }
//
//    }
}
