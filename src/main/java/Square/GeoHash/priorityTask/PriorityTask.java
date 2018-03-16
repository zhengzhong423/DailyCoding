package Square.GeoHash.priorityTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PriorityTask {

    public static void main(String[] args) {
        PriorityTask pt = new PriorityTask();

        List<Task> taskList = new ArrayList<>();

        Task t0 = new Task(0, 0, new HashSet<>());
        Task t1 = new Task(1, 1, new HashSet<>());
        Task t2 = new Task(2, 3, new HashSet<>());
        Task t3 = new Task(3, 3, new HashSet<>());


    }

    public List<Task> getOrder (List<Task> taskList) {
        PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2) -> t1.priority - t2.priority);

        for (Task task : taskList) {
            if (task.dependencies.size() == 0) {
                pq.add(task);
            }
        }

        List<Task> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            Task task = pq.poll();
            for (Task t : taskList) {
                if (t.dependencies.contains(task)) {
                    t.dependencies.remove(task);
                    if (t.dependencies.size() == 0) {
                        pq.add(t);
                    }
                }
            }
            result.add(task);
        }

        return result;
    }

}

class Task {
    Integer id;
    Integer priority;
    Set<Task> dependencies = new HashSet<>();

    public Task(Integer id, Integer priority, Set<Task> dependencies) {
        this.id = id;
        this.priority = priority;
        this.dependencies = dependencies;
    }
}
