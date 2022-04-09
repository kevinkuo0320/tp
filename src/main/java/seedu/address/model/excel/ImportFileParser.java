package seedu.address.model.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse import file into command format
 */
public class ImportFileParser {

    private static boolean flag = true;
    private List<String> resultList;

    /**
     * Convert result file into list of command string
     * @param file
     * @return list of command for adding student records
     */
    public List<String> jsonToPerson(File file) throws NullPointerException, ParseException {
        resultList = new ArrayList<>();
        ExcelToJsonConverter converter = new ExcelToJsonConverter();
        JsonNode data = converter.excelToJson(file);

        int len = data.get("Sheet1").size();
        for (int i = 0; i < len; i++) {
            String res = "";
            JsonNode person = data.get("Sheet1").get(i);
            String name = "n/" + person.get("NAME").textValue().trim() + " ";
            String block = "b/" + person.get("BLOCK").textValue().trim() + " ";
            String faculty = "f/" + person.get("FACULTY").textValue().toUpperCase().trim() + " ";
            String phone = "p/" + person.get("PHONE").asInt() + " ";
            String email = "e/" + person.get("EMAIL").textValue().trim() + " ";
            String address = "a/" + person.get("ADDRESS").textValue().trim() + " ";
            String mc = "mc/" + person.get("MATRICULATION NUMBER").textValue().trim() + " ";
            String cs = "cs/" + person.get("COVID STATUS").textValue().trim() + " ";
            String tag = "t/" + person.get("TAG").textValue().trim() + " ";
            res = "add " + name + block + faculty + phone + email + address + mc + cs + tag;
            try {
                Command c = new AddressBookParser().parseCommand(res);
            } catch (ParseException e) {
                flag = false;
            }
            resultList.add(res);
        }
        if (!flag) {
            resultList.clear();
        }
        return resultList;
    }
}

