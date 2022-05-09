package ex2;

import java.util.ArrayList;
import java.util.List;

public class NumberRepository {

    private String[] simpleNumberData = "2, 4, 6, 2, 9, 10".split(", ");
    private String[] scientificNumberData = "2e3, 2e4, 6e3".split(", ");

    public List<SimpleNumber> getSimpleNumbers() {
        List <SimpleNumber> newList = new ArrayList<>();
        for(String i : simpleNumberData){
            SimpleNumber a = new SimpleNumber(Integer.parseInt(i));
            newList.add(a);
        }
        return newList;
    }

    public List<ScientificNumber> getScientificNumbers() {
        List <ScientificNumber> newList = new ArrayList<>();
        char mantisa;
        char exponet;
        for(String i : scientificNumberData){
            mantisa = i.charAt(0);
            exponet = i.charAt(2);
            int mantisa1 = mantisa - '0';
            int exponet1 = exponet - '0';

            ScientificNumber de = new ScientificNumber(mantisa1,exponet1);
            newList.add(de);
            }
        return newList;
        }

    public List<Number> getAllNumbers() {
        List <Number> numberList = new ArrayList<>(getSimpleNumbers());
        for(ScientificNumber i : getScientificNumbers()){
            numberList.add(i);
        }
        //getScientificNumbers().forEach(number -> numberList.add(number));
        return numberList;
    }

}
