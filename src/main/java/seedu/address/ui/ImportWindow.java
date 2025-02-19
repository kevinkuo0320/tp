package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * class for displaying import window
 */
public class ImportWindow extends UiPart<Stage> {

    private static final String FXML = "ImportWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ImportWindow.class);

    @FXML
    private Label importFile;

    /**
     * Creates a new ImportWindow
     * @param root Stage to use as the root of the ImportWindow
     */
    public ImportWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new ImportWindow
     */
    public ImportWindow() {
        this(new Stage());
    }

    /**
     * Shows the import window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Importing excel file");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the import window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the import window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the import window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
