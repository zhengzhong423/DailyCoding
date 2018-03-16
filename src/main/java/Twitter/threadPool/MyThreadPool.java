package Twitter.threadPool;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool<E extends Event> implements Runnable {
    PriorityQueue<E> pq = new PriorityQueue<>();
    ReentrantLock lock = new ReentrantLock();
    Condition cond = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool<Event> pool = new MyThreadPool();
        new Thread(pool).start();
        pool.put(new Event(1, 10));
        pool.put(new Event(2, 15));
        Event event = new Event(3, 1);
        pool.put(event);
        Thread.currentThread().sleep(1000 * 3);
        pool.remove(event);
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        pq.add(e);
        if (pq.peek() != e) {
            cond.signal();
        }
        lock.unlock();
    }

    public void remove(E e) {
        lock.lock();
        pq.remove(e);
        cond.signal();
        lock.unlock();
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for (; ; ) {
                if (pq.isEmpty()) {
                    cond.await();
                }
                Event e = pq.peek();
                long diff = e.getDelay(TimeUnit.SECONDS) - System.currentTimeMillis() / 1000;
                if (diff <= 0) {
                    new Thread(e).start();
                    pq.poll();
                    pq.add((E)e.update());
                } else {
                    cond.await(diff, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
