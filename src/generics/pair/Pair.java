package generics.pair;

public class Pair<A,U> {

    private A first;
    private U second;

    public Pair(A first, U second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

}
