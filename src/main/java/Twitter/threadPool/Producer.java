package Twitter.threadPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable {

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public static void main(String[] args) {
        Producer producer = new Producer();
        for (int i = 0; i < 10; i++) {
            new Thread(producer).start();
        }
    }

    @Override
    public void run() {
        produce();
    }

    public void produce() {
        System.out.println("producer wait in line");
        lock.lock();
        try {
            System.out.println("In Lock Block");
            Thread.sleep(1000);
            condition.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
