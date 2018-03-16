package Twitter.threadPool;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Event implements Delayed, Runnable {
    public long exeTime;
    public int interval;
    public int id;

    public Event(int id, int interval) {
        this.id = id;
        this.interval = interval;
        exeTime = (System.currentTimeMillis() / 1000) + interval;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return exeTime;
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(exeTime - o.getDelay(TimeUnit.SECONDS));
    }

    @Override
    public void run() {
        System.out.println("Running Event: " + id);
    }

    public Event update() {
        exeTime = (System.currentTimeMillis() / 1000) + interval;
        return this;
    }
}
