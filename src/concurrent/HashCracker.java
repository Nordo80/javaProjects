package concurrent;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCracker {

    private static final String ALPHABET = getAlphabet();
    private static final String HASH_TO_CRACK_1 =
            "d4faeb0660ce6d23883eced24805d2ff0da3600685e2ac9ad1dedd8cc66104ef";
    private static final String HASH_TO_CRACK_2 =
            "5443bf770be3146692aaeea227235917bc36cf1cbcf366f239f160a3ef149fee";

    public static void main(String[] args) {

        String plainText = "hello";

        System.out.println(sha256(plainText));

    }

    private static String sha256(String plainText) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(bytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte byteValue : bytes) {
            String hex = Integer.toHexString(0xff & byteValue);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private static String getAlphabet() {
        StringBuilder sb = new StringBuilder();
        for (char i = 48; i < 123; i++) {
            sb.append(i);
        }

        return sb.toString();
    }
}

