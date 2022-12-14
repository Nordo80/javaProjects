package reflection.tester;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {
    public String result = "";
    public void runTests(List<String> testClassNames) {
        try {
            for (String i : testClassNames) {
                Class<?> aClass = Class.forName(i);
                Method[] fields = aClass.getMethods();
                for (Method field : fields) {
                    MyTest annotation = field.getAnnotation(MyTest.class);
                    if(annotation == null){
                        continue;
                    }
                    Boolean assignableFrom = annotation.expected().isAssignableFrom(actualError(field,aClass));
                    if(assignableFrom){
                        result += field.getName() + "() - OK ";
                    }else{
                        result += field.getName() + "() - FAILED ";
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);

        }
    }
    public Class<? extends Throwable> actualError(Method field, Class aClass) {
        Class classWithError = MyTest.None.class;
        try {
            field.invoke(aClass.getDeclaredConstructor().newInstance());

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e){
            System.out.println(e);

        } catch (InvocationTargetException e){
            classWithError = e.getCause().getClass();
        }
        return classWithError;
    }
    public String getResult() {
        return result;
    }
}
