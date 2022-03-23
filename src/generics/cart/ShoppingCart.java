package generics.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShoppingCart<T extends CartItem> {
    private List<T> elements = new ArrayList<>();
    public Integer quantity = 0;
    List<String> quantityList = new ArrayList<>();
    List<Double> discountPercentageList = new ArrayList<>();

    public void add(T item) {
        elements.add(item);
    }

    public void removeById(String id) {
        elements.removeIf(element -> Objects.equals(id, element.getId()));
    }
    public void checkQuantityById(String id) {
        for (T element : elements) {
            if (Objects.equals(element.getId(), id)) {
                quantity += 1;
            }
        }
        for (String i : quantityList) {
            if (Objects.equals(i, id)) {
                quantity += 1;
            }
        }
    }
    public String toString(){
        String newString = "";
        String mainString = "";
        String previousElementId = "";
        for(T element : elements){
            checkQuantityById(element.getId());
            if(!Objects.equals(element.getId(), previousElementId))
                newString = mainString + "(" + element.getId() + ", " + element.getPrice() + ", " + quantity + ")";
            previousElementId = element.getId();
            quantity = 0;
            mainString += newString + ", ";
        }
        return newString;
    }
    public Double getTotal() {
        Double getTotalCounter = 0.0;
        String previousElement = "";
        for(T element : elements){
            if (!Objects.equals(previousElement, element.getId())){
                checkQuantityById(element.getId());
                getTotalCounter += element.getPrice() * quantity;
                quantity = 0;
                previousElement = element.getId();
            }
        }
        if(!discountPercentageList.isEmpty()){
            for(Double i: discountPercentageList){
                getTotalCounter = i * getTotalCounter;
            }
        }
        return getTotalCounter;
    }


    public void increaseQuantity(String id) {
        quantityList.add(id);
    }

    public void applyDiscountPercentage(Double discount) {
        discountPercentageList.add((100 - discount) / 100);
    }

    public void removeLastDiscount() {
        int index = discountPercentageList.size() - 1;
        discountPercentageList.remove(index);
    }

    public void addAll(List<? extends T> elements) {
        for(T element: elements){
            add(element);
        }
    }
}
