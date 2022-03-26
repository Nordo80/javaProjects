package fp.sales;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Repository{

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries(){
        List<Entry> records = new ArrayList<>();
        try {
            List<String[]> list = Files.lines(Paths.get(FILE_PATH))
                    .map(l -> l.split("\t"))
                    .collect(Collectors.toList());
            list.remove(0);
            list.stream()
                    .map(x -> records.add(createEntry(x))).toList();

        } catch (FileNotFoundException e) {
            System.out.println("File not fouded" + e);
        } catch (IOException e) {
            System.out.println("IO exception!" + e);
        }

        return records;
    }
    public Entry createEntry(String[] data) {
        String productId = data[2];
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.forLanguageTag(data[0]));
        LocalDate date = LocalDate.parse(data[0], formatter);
        String state = data[1];
        String category = data[3];
        Double amount = Double.parseDouble(data[5].replace(",","."));
        return new Entry(productId, date, state, category, amount);
    }

}
