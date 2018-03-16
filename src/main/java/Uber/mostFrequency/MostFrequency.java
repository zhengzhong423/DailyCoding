package Uber.mostFrequency;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class FrequencyBucket {
    int count;
    Set<Integer> keySet;
    FrequencyBucket next;
    FrequencyBucket prev;

    public FrequencyBucket(int count) {
        this.count = count;
        this.keySet = new HashSet<>();
    }
}
public class MostFrequency {
    HashMap<Integer, FrequencyBucket> map = new HashMap<>();
    FrequencyBucket head;
    FrequencyBucket tail;

    public static void main(String[] args) {
        MostFrequency mf = new MostFrequency();
        mf.add(1);
        mf.add(1);
        System.out.println(mf.getMostFrequency());
        mf.add(2);
        mf.remove(1);
        mf.add(1);
        System.out.println(mf.getMostFrequency());
    }

    public MostFrequency() {
        head = new FrequencyBucket(-1);
        tail = new FrequencyBucket(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void add(int key) {
        if (!map.containsKey(key)) {
            if (head.next.count != 1) {
                FrequencyBucket fb = new FrequencyBucket(1);
                addAfter(fb, head);
            }
            head.next.keySet.add(key);
            map.put(key, head.next);
        } else {
            FrequencyBucket fb = map.get(key);
            if (fb.next.count != fb.count + 1) {
                addAfter(new FrequencyBucket(fb.count + 1), fb);
            }
            fb.keySet.remove(key);
            fb.next.keySet.add(key);
            map.put(key, fb.next);
            checkIfDelete(fb);
        }
    }

    private void checkIfDelete(FrequencyBucket fb) {
        if (fb.keySet == null || fb.keySet.size() == 0) {
            fb.prev.next = fb.next;
            fb.next.prev = fb.prev;
        }
    }

    private void addAfter(FrequencyBucket fb, FrequencyBucket head) {
        FrequencyBucket tmp = head.next;
        head.next = fb;
        fb.prev = head;
        fb.next = tmp;
        tmp.prev = fb;
    }

    public void remove(int key) {
        if (!map.containsKey(key)) {
            return;
        }
        FrequencyBucket fb = map.get(key);
        if (fb.prev.count != fb.count - 1) {
            addAfter(new FrequencyBucket(fb.count - 1), fb.prev);
        }
        fb.keySet.remove(key);
        fb.prev.keySet.add(key);
        map.put(key, fb.prev);
        checkIfDelete(fb);
    }

    public int getMostFrequency() {
        return tail.prev.keySet.iterator().next();
    }
}


