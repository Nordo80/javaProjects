package fp.sales;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Analyser {

    private Repository repository;

    public Analyser(Repository repository) {

        this.repository = repository;
    }

    public Double getTotalSales() {
        double result = repository.getEntries().stream()
                .mapToDouble(x -> x.getAmount())
                .sum();
        return result;
    }

    public Double getSalesByCategory(String category) {
        Double result = repository.getEntries().stream()
                .filter(x -> Objects.equals(x.getCategory(), category))
                .mapToDouble(x -> x.getAmount())
                .sum();
        return result;
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        Double result = repository.getEntries().stream()
                .filter(x -> x.getDate().isAfter(start) && x.getDate().isBefore(end))
                .mapToDouble(x -> x.getAmount())
                .sum();
        return result;

    }

    public String mostExpensiveItems() {
        Comparator<Entry> comparator = Comparator.comparing(Entry::getAmount);
        List<Entry> top3 = repository.getEntries().stream()
                .sorted(comparator.reversed())
                .limit(3).toList();
        String result = top3.stream()
                .map(x -> x.getProductId())
                .sorted()
                .collect(Collectors.joining(", "));
        return result;
    }

    public String statesWithBiggestSales() {
        Map<String, Double> map = repository.getEntries().stream()
                .collect(Collectors.toMap(
                        Entry::getState,
                        Entry::getAmount,
                        Double::sum));
        String mapAsString = map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.joining(", "));

        return mapAsString;
    }
}
