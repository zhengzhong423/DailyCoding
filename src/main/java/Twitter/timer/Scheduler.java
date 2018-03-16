package Twitter.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Scheduler<T> {
    ScheduledExecutorService sec = Executors.newScheduledThreadPool(1);
    List<Future<T>> futureList = new ArrayList<>();
    static Integer num = 0;

    public static void main(String[] args) {
        Scheduler<Integer> scheduler = new Scheduler<>();
        scheduler.scheduleJob(() -> {
            num++;
            return num;
        }, 1*1000);
        System.out.println("Main finished");
//        scheduler.sec.shutdown();
    }

    public void scheduleJob(Callable<T> callable, int timeInMillisecond) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ScheduledFuture<?> handler = sec.scheduleAtFixedRate(() -> {
            try {
                Future<T> future = executor.submit(callable);
                futureList.add(future);
                futureList.stream().forEach(e -> {
                    try {
                        System.out.println(e.get());
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ExecutionException e1) {
                        e1.printStackTrace();
                    }
                });
                if ((Integer)(future.get()) == 10) {
                    sec.shutdown();
                    executor.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 3, timeInMillisecond / 1000, TimeUnit.SECONDS);
        handler.cancel(true);
        executor.shutdown();
    }
}
