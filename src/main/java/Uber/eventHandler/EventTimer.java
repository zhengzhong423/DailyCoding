package Uber.eventHandler;

// Compute Exclusive Time

// Given a Log File as Below:
// Function         Action      Timestamp
//    F1            Enter       10:00:00
//    F2            Enter       10:00:06
//    F2            Exit        10:00:07
//    F3            Enter       10:00:08
//    F3            Exit        10:00:12
//    F1            Exit        10:00:15
//
// You need to compute the exclusive time spent in a given function.
// For example:
// Exclusive time F1 = 10 second
// Exclusive time F2 = 1 second
// Exclusive time F3 = 4 second

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

class Event {
    int id;
    int action; // 0 enter, 1 exit
    int time;

    public Event(int id, int action, int time) {
        this.id = id;
        this.action = action;
        this.time = time;
    }
}

public class EventTimer {

    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();
        events.add(new Event(0, 0, 0));
        events.add(new Event(1, 0, 6));
        events.add(new Event(1, 1,7));
        events.add(new Event(2,0,8));
        events.add(new Event(0,0,9));
        events.add(new Event(0,1,11));
        events.add(new Event(2,1,12));
        events.add(new Event(0,1, 15));
        //12, 1, 2

        System.out.println(new EventTimer().getEvent(events));
    }

    public List<Integer> getEvent(List<Event> eventsLog) {
        Stack<Event> stack = new Stack<>();
        Map<Integer, Integer> runTimeMap = new HashMap<>();
        int endTime = 0;
        for (Event event: eventsLog) {
            if (event.action == 0) {
                if (!stack.isEmpty()) {
                    int time = event.time - endTime;
                    runTimeMap.put(stack.peek().id,
                            time + runTimeMap.getOrDefault(stack.peek().id, 0));
                }
                stack.push(event);
            } else {
                Event previousEvent = stack.pop();
                if (previousEvent.id != event.id) {
                    throw new RuntimeException("invalid function call");
                }
                int time = event.time - endTime;
                runTimeMap.put(event.id,
                        time + runTimeMap.getOrDefault(event.id, 0));
            }
            endTime = event.time;
        }
        return runTimeMap.entrySet().stream().
                sorted((e1, e2) -> e1.getKey() - e2.getKey()).
                map(e -> e.getValue()).collect(Collectors.toList());
    }
}
