package Twitter.activeUsers;

import java.util.HashSet;
import java.util.Set;

public class ActiveUsers {
    Set<Integer>[] arr = new HashSet[10];

    public void aggregate(long ts, int id) {
        int slot = (int) (ts / (1000 * 60));
        arr[slot].add(id);
    }
}
