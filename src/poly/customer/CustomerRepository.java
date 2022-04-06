package poly.customer;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<AbstractCustomer> customers = new ArrayList<>();

    public Optional<AbstractCustomer> getCustomerById(String id) {
        customers.clear();
        getCustomers();
        for(AbstractCustomer customer: customers){
            if (customer.getId().equals(id)){
                return Optional.of(customer);
            }
        }return Optional.empty();
    }

    public void getCustomers() {
        try {
            List<String[]> list = Files.lines(Paths.get(FILE_PATH))
                    .map(l -> l.split(";")).toList();
            list.stream()
                    .map(x -> customers.add(createCustomer(x))).toList();
        } catch (FileNotFoundException e) {
            System.out.println("File not fouded" + e);
        } catch (IOException e) {
            System.out.println("IO exception!" + e);
        }

    }
    public void reWrite(String id) {
        try {
            String result = "";
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for(String line: lines){
                if(!line.contains(id)){
                    result += line + "\n";
                }
            }
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(result);
            file.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void remove(String id) {
        if(getCustomerCount() != 0){
            customers.removeIf(customer -> customer.getId().equals(id));
            reWrite(id);
        }
    }


    public void save(AbstractCustomer customer) {
        remove(customer.getId());
        customers.add(customer);
        appendCustomerToFile(customer);


    }
    public void appendCustomerToFile(AbstractCustomer customer){
        try{
            FileWriter file = new FileWriter(FILE_PATH, true);
            String type = customer instanceof RegularCustomer ? "REGULAR" : "GOLD";
            String date = customer.getLastOrderDate() == null ? "" : formatter.format(customer.getLastOrderDate());
            String result = type + ";" + customer.getId() + ";" + customer.getName() + ";" + customer.getBonusPoints() + ";" + date + "\n";
            file.write(result);
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public AbstractCustomer createCustomer(String[] data) {
        String status = data[0];
        String productId = data[1];
        String name= data[2];
        int bonus = Integer.parseInt(data[3]);

        if(Objects.equals(status, "GOLD")){
            return new GoldCustomer(productId, name, bonus);
        }else{
            LocalDate date = LocalDate.parse(data[4], formatter);
            return new RegularCustomer(productId, name, bonus, date);
        }
    }

    public int getCustomerCount() {
        return customers.size();
    }
}
