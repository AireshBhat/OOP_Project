import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class NewUserSignup {

    protected static final String FILE = "./users.csv";

    private boolean validate(String userId) {
        try {
            FileReader fileReader = new FileReader(FILE);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(userId)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
