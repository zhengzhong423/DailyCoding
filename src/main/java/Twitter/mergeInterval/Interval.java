package Twitter.mergeInterval;

public class Interval {
    public int a, b;
    public Interval(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + "," + b;
    }
}
