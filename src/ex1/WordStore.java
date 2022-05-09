package ex1;

import java.util.ArrayList;
import java.util.List;

public class WordStore {

    public List<String> newList = new ArrayList<>();
    private static final String[] ORDER = {
            "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten"
    };

    public void add(String numberAsWord) {

        newList.add(numberAsWord);
    }

    public List<String> getStoredWords() {
        List<String> newList2 = new ArrayList<>();
        for(String i: ORDER){
            for(String a: newList){
                if(i.equals(a)){
                    newList2.add(i);
                }
            }
        }
        //newList.sort((a, b) -> compare(a, b));
        //return newList;

        return newList2;
    }

    public int compare(String a, String b) {
        if(a.equals(b)){
            return 0;
        }
        int counterMain = 0;
        int counterA = 0;
        int counterB = 0;
        for (String i : ORDER){
            if(i.equals(a)){
                counterA = counterMain;
            }
            if(i.equals(b)){
                counterB = counterMain;
            }
            counterMain++;
        }
        return counterA - counterB;
    }
}
