package junit;

import java.util.Arrays;

public class Code {

    private int currentIndex;
    public void append(){

    }
    public static void main(String[] args) {
        int[] numbers = {1,7};
        System.out.println(mode("adda"));
        System.out.println(longestStreak("aaaaaadfglllllaa"));
        for (int i = 0; i < 5; i++){
            System.out.println(removeDuplicates(numbers)[i]);
        }
        System.out.println(sumIgnoringDuplicates(numbers));

    }

    public static boolean isSpecial(int candidate) {
        if (candidate % 11 <= 3) {
            return true;
        } else {
            return false;
        }
    }

    public static int longestStreak(String inputString) {
        int counter = 1;
        int maxCounter = 0;
        char new1 = 0;
        if (inputString == null){
            return maxCounter;
        }else{
            for (char i : inputString.toCharArray()) {
                if (new1 == i) {
                    counter++;
                } else {
                    if (maxCounter < counter) {
                        maxCounter = counter;
                    }
                    counter = 1;
                    new1 = i;
                }
            }
        }
        return maxCounter;
    }

    public static Character mode(String inputString) {
        Character char1 = null;
        if (inputString == null || inputString == "") {
            return char1;
        } else {
            int staticCounter = 0;
            for (char i1 : inputString.toCharArray()) {
                int counter = getCharacterCount(inputString,i1);
                if (counter > staticCounter) {
                    staticCounter = counter;
                    char1 = i1;
                }

            }
            return char1;
        }
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        int counter = 0;
        for (char i: allCharacters.toCharArray()){
            if(i == targetCharacter){
                counter++;
            }
        }
        return counter;
    }

    public static int[] removeDuplicates(int[] integers) {
        int new1[] = {};
        for (int i : integers){
            if(!valueInList(new1,i)){
                new1 = appentValue(new1,i);
            }
        }
        return new1;
    }
    public static boolean valueInList(int[] integers, int value){
        for(int i : integers) {
            if (value == i) {
                return true;
            }
        }
        return false;
    }
    public static int [] appentValue(int [] integers, int value){
        int [] new2 = new int[integers.length + 1];
        for (int i = 0; i < integers.length; i++){
            new2[i] = integers[i];
        }
        new2[new2.length - 1] = value;
        return new2;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int counter = 0;
        for (int i = 0; i < removeDuplicates(integers).length; i++)
            counter += removeDuplicates(integers)[i];
        return counter;
    }

}
