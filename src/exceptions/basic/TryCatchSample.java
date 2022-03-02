package exceptions.basic;

import java.io.BufferedReader;
public class TryCatchSample {
    public static void main(String[] args){
        Resource resource = new Resource().setData("stuff");

        String data = new TryCatchSample().readDataFrom(resource);
    }

    BufferedReader reader = null;
    public String readDataFrom(Resource resource) {
        String text = null;
        try{
            resource.open();
            text = resource.read() ;
        }catch(Exception e){
            return "someDefaultValue";

        }finally {
            resource.close();
        }
        return text;

    }
}
