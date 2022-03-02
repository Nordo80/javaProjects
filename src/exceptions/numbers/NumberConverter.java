package exceptions.numbers;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class NumberConverter {
    Properties properties = new Properties();

    public NumberConverter(String lang) {
        String filePath = "src/exceptions/numbers/numbers_" + lang + ".properties";

        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);

            InputStreamReader reader = new InputStreamReader(
                    is, StandardCharsets.UTF_8);

            properties.load(reader);
            if (!properties.containsKey(String.valueOf(1))) {
                throw new IllegalStateException();
            }

        } catch (IllegalStateException e) {
            throw new MissingTranslationException(lang);
        } catch (FileNotFoundException e) {
            throw new MissingLanguageFileException(lang, e);
        } catch (Exception e) {
            throw new BrokenLanguageFileException(lang, e);

        }finally {
            close(is);
        }
    }

    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {
        }
    }


    public String numberInWords(Integer number) {
        if (properties.containsKey(String.valueOf(number))) {
            return properties.getProperty(String.valueOf(number));
        }
        int a = number / 10;
        int b = number % 10;
        int c = a % 10;

        String cd;
        String new1;
        cd = Integer.toString(c) + Integer.toString(b);
        if (c == 0) {
            cd = Integer.toString(b);
        }
        if (b == 0 && c!= 1) {
            cd = Integer.toString(c);
        }
        if (String.valueOf(a).length() == 2) {
            String hundred = properties.getProperty(String.valueOf(a / 10)) + properties.getProperty(String.valueOf("hundreds-before-delimiter")) + properties.getProperty(String.valueOf("hundred"));
            if (cd.equals("0")) {
                return hundred;
            }
            if (c <= 1) {
                if (properties.containsKey(String.valueOf(cd))) {
                    new1 = hundred + properties.getProperty(String.valueOf("hundreds-after-delimiter")) + properties.getProperty(String.valueOf(cd));
                } else {
                    new1 = hundred + properties.getProperty(String.valueOf("hundreds-after-delimiter")) + properties.getProperty(String.valueOf(b)) + properties.getProperty(String.valueOf("teen"));
                }
            } else {
                if (properties.containsKey(String.valueOf(c * 10))) {
                    if(properties.getProperty(String.valueOf(b)).equals(properties.getProperty(String.valueOf(0)))){
                        new1 = hundred + properties.getProperty(String.valueOf("hundreds-after-delimiter")) + properties.getProperty(String.valueOf(c * 10));
                    }else{
                        new1 = hundred + properties.getProperty(String.valueOf("hundreds-after-delimiter")) + properties.getProperty(String.valueOf(c * 10)) + properties.getProperty("tens-after-delimiter") + properties.getProperty(String.valueOf(b));
                    }
                } else {
                    if(b == 0){
                        new1 = hundred + properties.getProperty(String.valueOf("hundreds-after-delimiter")) + properties.getProperty(String.valueOf(c)) + properties.getProperty(String.valueOf("tens-suffix"));
                    }else{
                        new1 = hundred + properties.getProperty(String.valueOf("hundreds-after-delimiter")) + properties.getProperty(String.valueOf(c)) + properties.getProperty(String.valueOf("tens-suffix")) + properties.getProperty("tens-after-delimiter") + properties.getProperty(String.valueOf(b));
                    }
                }

                }
        }else{
            if (a * b == 0) {
                return properties.getProperty(String.valueOf(a)) + properties.getProperty(String.valueOf("tens-suffix"));
            }
            if (a==1){
                new1 = properties.getProperty(String.valueOf(b)) + properties.getProperty(String.valueOf("teen"));
            }else{
                if(properties.containsKey(String.valueOf(a*10))){
                    new1 = properties.getProperty(String.valueOf(a*10)) + properties.getProperty("tens-after-delimiter") + properties.getProperty(String.valueOf(b));
                }else{
                    new1 = properties.getProperty(String.valueOf(a)) + properties.getProperty(String.valueOf("tens-suffix")) + properties.getProperty("tens-after-delimiter") + properties.getProperty(String.valueOf(b));
                }
            }

        }return new1;
    }
}
