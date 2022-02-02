package intro;

public class Program {

    public static void main(String[] args) {

        int decimal = asDecimal("11001100");

        System.out.println(decimal); // 205
        System.out.println(asString(204));
    }

    public static String asString(int input) {
        String text = "";
        while (input != 0){
            if(input % 2 == 0){
                text += "0";
            }else{
                text += "1";
            }
            input = input / 2;
        }
        return reverseString(text);
    }

    public static int asDecimal(String input) {
        int ala = input.length();
        int result = 0;
        String text = reverseString(input);
        for ( int i = 0; i < ala; ++i){
            if (text.charAt(i) == '1'){
                result += pow(2, i);
            }
        }
        return result;
    }

    private static int pow(int arg, int power) {
        int result = 1;
        while (power != 0) {
            result = result * arg;
            power--;
        }
        return result;
    }
    public static String reverseString(String str){
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}
