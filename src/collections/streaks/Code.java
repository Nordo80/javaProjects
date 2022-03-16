package collections.streaks;

import java.util.*;

public class Code {

    public static List<List<String>> getStreakList(String input) {
        LinkedList<List<String>> mainList = new LinkedList<>();
        for (char currentString : input.toCharArray()) {
            String character = String.valueOf(currentString);
            if (mainList.size() == 0) {
                mainList.add(new LinkedList<>(Arrays.asList(character)));
            } else if (mainList.getLast().contains(character)) {
                mainList.getLast().add(character);
            } else {
                mainList.add(new LinkedList<>(Arrays.asList(character)));
            }
        }
        return mainList;
    }
}
