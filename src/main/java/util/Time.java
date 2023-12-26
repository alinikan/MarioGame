package util;

public class Time {
    public static float timesStarted = System.nanoTime();

    public static float getTime() {return (float) ((System.nanoTime() - timesStarted) * 1E-9); }
}
