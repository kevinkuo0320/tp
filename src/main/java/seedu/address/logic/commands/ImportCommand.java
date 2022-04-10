package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.List;

import com.google.common.io.Files;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.excel.ImportFileParser;


/**
 * Import Excel file to the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports an excel file to the address book. "
            + "Parameters: "
            + "file path "
            + "Example: " + COMMAND_WORD + " "
            + ":C//Users//Documents/students.xlsx ";

    public static final String MESSAGE_SUCCESS = "New file added!";
    public static final String MESSAGE_FILE_TYPE = "Imported file is not in excel format";

    private static String toFile;
    /**
     * Creates an ImportCommand to add the specified
     */
    public ImportCommand(String filePath) throws IllegalArgumentException {
        if (filePath == null) {
            throw new IllegalArgumentException("filepath is not specified");
        }
        requireNonNull(filePath);
        toFile = filePath;
        System.out.println(toFile);
    }

    /**
     *
     * @param model {@code Model} which the command should operate on.
     * @return command result from execute the command
     * @throws CommandException when the command is invalid
     * @throws ParseException when import command is wrong format
     */
    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        int count = 0;
        if(toFile.equals("")) {
            throw new CommandException("The file path cannot be empty! Please specify the file directory");
        }
        requireNonNull(model);
        if (!Files.getFileExtension(toFile).equals("xlsx") && !Files.getFileExtension(toFile).equals("xls")) {
            throw new CommandException(MESSAGE_FILE_TYPE);
        }
        File f = new File(toFile);
        if (!f.exists()) {
            throw new CommandException("the file cannot be found!");
        }
        ImportFileParser converter = new ImportFileParser();
        List<String> res = converter.jsonToPerson(f);
        if (res.isEmpty()) {
            throw new CommandException("Wrong format! Please refers to our User Guide");
        }
        try {
            for (int i = 0; i < res.size(); i++) {
                Command c = new AddressBookParser().parseCommand(res.get(i));
                c.execute(model);
                count = i;
            }
        } catch (CommandException e) {
            throw new CommandException(String.format("Row %d in excel: %s", count + 3, e.getMessage().substring(14)));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

}
