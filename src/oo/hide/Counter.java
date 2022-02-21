package oo.hide;

public class Counter {
    public int start;
    public int step;

    public Counter(int start, int step) {
        this.start = start;
        this.step = step;
    }

    public int nextValue() {
      int result = start;
      start += step;
      return result;
    }
}
