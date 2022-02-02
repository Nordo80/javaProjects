package types;

import java.util.*;


public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9, 5};

        System.out.println(sum(numbers)); // 11
        System.out.println(average(numbers));
        System.out.println(minimumElement(numbers));
        System.out.println(asString(numbers));
        System.out.println(mode("abcacbbbacadddddd" ));
        System.out.println(squareDigits("a9b2" ));
    }
    public static double countNum = 0;
    public static int sum(int[] numbers) {
        int result = 0;
        int i;
        for (i = 0; i < numbers.length; i++){
            result+=numbers[i];
            countNum += 1;
        }
        return result;
    }

    public static double average(int[] numbers) {
        double b = countNum;
        double a = sum(numbers);
        double varuable = a/b;
        return varuable;
    }

    public static Integer minimumElement(int[] integers) {
        int minNumber = 0;
        int i;
        for (i = 0; i < integers.length; i++){
            if (minNumber > integers[i]){
                minNumber = integers[i];
            }
        }
        return minNumber;
    }

    public static String asString(int[] elements) {
        String numbersText = "";
        int i;
        String blankk = ", ";
        for (i = 0; i < elements.length; i++){
            if (i == elements.length - 1){
                numbersText += Integer.toString(elements[i]);
            }else{
                numbersText += elements[i] + blankk;
            }
        }

        return numbersText;
    }

    public static Character mode(String input) {
        Map<Character, Integer> map = new Hashtable<>();
        int i;
        char charNum = 0;
        for (i = 0; i < input.length(); i++) {
            if (map.containsKey(input.charAt(i))) {
                map.put(input.charAt(i), map.get(input.charAt(i)) + 1);
            } else {
                map.put(input.charAt(i), 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == Collections.max(map.values())){
                charNum = entry.getKey();

            }
        }
        return charNum;
    }

    public static String squareDigits(String s) {
        String square = "";
        int i;
        for (i = 0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))){
                square += (s.charAt(i) - '0') * (s.charAt(i) - '0');
            }else{
                square += s.charAt(i);
            }
        }
        return square;
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);

        int isolatedCount = 0;

        // count isolates squares here

        return isolatedCount;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
