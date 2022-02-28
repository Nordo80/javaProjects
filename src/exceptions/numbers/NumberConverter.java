package exceptions.numbers;

import exceptions.translate.InstallationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class NumberConverter {
    List<String> reader;

    public NumberConverter(String lang){
        try {
            if (lang == "ru"){
                throw new FileNotFoundException();
            }
            if (lang == "es"){
                throw new IOException();
            }
            if (lang == "fr"){
                throw new Exception();
            }
            String file = "src/exceptions/numbers/expected-" + lang + ".txt";
            reader = Files.readAllLines(Paths.get(file));
        } catch(FileNotFoundException e){
            throw new MissingLanguageFileException(lang, e);
        } catch (IOException e){
            throw new MissingTranslationException(lang);
        } catch (Exception e) {
            throw new BrokenLanguageFileException(lang, e);
        }


    }

    public String numberInWords(Integer number){
        return reader.get(number);
    }
}
