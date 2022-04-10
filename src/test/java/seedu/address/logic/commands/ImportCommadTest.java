package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ImportCommadTest {

    private static final String TEST_DATA_FOLDER = "src/test/data/ExcelFileTest/successful_tp.xlsx";

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_import_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new ImportCommand(TEST_DATA_FOLDER), model,
                MESSAGE_SUCCESS, expectedModel);
    }

}
