package oo.hide;

public class Timer {

    private long start = System.currentTimeMillis();
    public String getPassedTime() {
        double difference = System.currentTimeMillis() - start;
        return String.format("%s sec", difference / 1000);
    }
}
