package fp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Reduce {

    @Test
    public void reducesListToSingleResult() {

        var numbers = List.of(1, 2, 3, 4);

        assertThat(reduce(numbers, (a, b) -> a + b), is(10));

        assertThat(reduce(numbers, (a, b) -> a * b), is(24));

        assertThat(reduce(List.of("1", "2", "3"),
                (a, b) -> a + b), is("123"));

    }

    private <T> T reduce(List<T> list,
                        BiFunction<T, T, T> func) {

        if(list.isEmpty()){
            throw new IllegalArgumentException("not implemented yet");
        }
        ArrayList<T> list1 = new ArrayList<>(list);
        T result = list1.remove(0);
        for (T each : list1){
            result = func.apply(result,each);
        }
        return result;
    }
}
